package com.moclase.Memorify.Controller;

import com.moclase.Memorify.repository.WordMeaningRepository;
import com.moclase.Memorify.Dto.WordMeaningDto;
import com.moclase.Memorify.model.WordMeaning;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class WordMeaningController {

    private final ChatClient chatClient;

    public WordMeaningController(OllamaChatModel ollamaChatModel) {
        this.chatClient = ChatClient.builder(ollamaChatModel).build();
    }

    @GetMapping("/ai")
    @ResponseBody
    public String getAnswer(@RequestParam("word") String word) {
        String res = chatClient
                .prompt("dont add no space in front of word,Only reply with a one-word synonym for :" + word + ". Reply with a single word only. No sentences, no punctuation,no explanation only synonym.no paragraph,  " )
                .call().content();
        assert res != null;
        System.out.println(res);
        WordMeaning wordMeaning = wordMeaningRepository.findByWord(word);
        if(!wordMeaning.getMeanings().contains(res)){
            wordMeaning.getMeanings().add(res);
            wordMeaningRepository.save(wordMeaning);
        }
        return res;
    }

    @Autowired
    private WordMeaningRepository wordMeaningRepository;

    @GetMapping("/word-meaning-home")
    public WordMeaningDto wordMeaning() {
        List<WordMeaning> wordMeanings = wordMeaningRepository.findAll();
        WordMeaning wordMeaning = wordMeanings.get((int) (Math.random() * wordMeanings.size()));
        WordMeaningDto wordMeaningDto = new WordMeaningDto();
        wordMeaningDto.setWord(wordMeaning.getWord());
        wordMeaningDto.setType(wordMeaning.getType());
        return wordMeaningDto;
    }
    @GetMapping("/get")
    public ResponseEntity<?> getWordMeaning() {
        return ResponseEntity.ok(wordMeaningRepository.findAll());
    }
    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<String> saveWordMeaning(@RequestBody WordMeaningDto wordMeaningDto) {
        WordMeaning wordMeaning = wordMeaningRepository.findByWord(wordMeaningDto.getWord());

        boolean exists = wordMeaning.getMeanings().stream()
                .anyMatch(m -> m.equalsIgnoreCase(wordMeaningDto.getMeaning()));
        if (!exists) {
            String res=chatClient.prompt("dont add no space in front of word,Only reply with (true or false). is' "+wordMeaningDto.getMeaning() +" ' a meaning or synonym of '"+wordMeaning.getWord()+"'? Reply with a (true or false) only. No sentences, no punctuation,no explanation only (true or false).no paragraph").call().content();
            System.out.println(res);
            if (res.substring(1).toLowerCase().equals("true")) {
                if(!wordMeaning.getMeanings().contains(wordMeaningDto.getMeaning())) {
                    wordMeaning.getMeanings().add(wordMeaningDto.getMeaning());
                }
                wordMeaningRepository.save(wordMeaning);
                return ResponseEntity.ok("Correct");
            }
        }
        System.out.println(wordMeaning.getMeanings());
         return ResponseEntity.ok("wrong");
    }

}

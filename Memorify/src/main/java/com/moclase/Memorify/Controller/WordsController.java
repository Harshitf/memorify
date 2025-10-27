package com.moclase.Memorify.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public abstract class WordsController {
    @GetMapping("/word-game")
    public String getWordGame(Model model){

    String []word={"h"," ","l"," ","o"};

        model.addAttribute("word",word);
        return "wordGame";
    }

    public boolean CheckWord(@ModelAttribute String[] word){

        return true;
    }

}

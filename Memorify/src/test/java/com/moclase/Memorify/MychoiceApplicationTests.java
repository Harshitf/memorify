package com.moclase.Memorify;

import com.moclase.Memorify.Controller.WordMeaningController;
import com.moclase.Memorify.Service.CrossWordGrid;
import com.moclase.Memorify.Service.CrossWordPuzzle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MychoiceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    CrossWordGrid controller;
    @Autowired
    CrossWordPuzzle puzzle;

@Autowired
    WordMeaningController wordMeaningController;
//@Test
//@Disabled
//    public void ai(){
//     wordMeaningController.getAnswer();
//    }
    @Test
    public void  aV(){
//        WordTrie.WordTrieNode root = new WordTrie.WordTrieNode();
//        WordTrie.Structure structure = new WordTrie.Structure();
//        structure.insertWord(root,"cats");
//        //trie.insertWord(root,"cats");
//        System.out.println(structure.searchWord(root,"cat"));

        puzzle.buildCrossWordPuzzle();


    }
}

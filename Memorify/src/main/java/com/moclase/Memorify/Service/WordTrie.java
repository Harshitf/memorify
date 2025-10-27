package com.moclase.Memorify.Service;

import org.springframework.stereotype.Component;

@Component
public class WordTrie {
    public static class WordTrieNode {
        boolean eow;
        WordTrieNode[] children;


        public WordTrieNode() {
            children = new WordTrieNode[26];
            this.eow = false;
        }

    }

    public static class Structure {

        public void build(WordTrieNode root, String[] arr) {
            for (String word : arr) {
                insertWord(root, word);
            }
        }

        public void insertWord(WordTrieNode root, String word) {
            WordTrieNode curr = root;
            for (char c : word.toCharArray()) {
                if (curr.children[c - 'a'] == null) {
                    curr.children[c - 'a'] = new WordTrieNode();
                }
                curr = curr.children[c - 'a'];
            }
            curr.eow = true;
        }

        public boolean searchWord(WordTrieNode root, String word) {
            WordTrieNode curr = root;
            for (char c : word.toCharArray()) {
                if (curr.children[c - 'a'] == null) {
                    return false;
                }
                curr = curr.children[c - 'a'];
            }
            return curr.eow;
        }

        public boolean isPrefixWord(WordTrieNode root, String word) {
            WordTrieNode curr = root;
            for (char c : word.toCharArray()) {
                if (curr.children[c - 'a'] == null) {
                    return false;
                }
                curr = curr.children[c - 'a'];
            }
            return true;
        }

    }
}
package com.moclase.Memorify.Service;

import org.springframework.stereotype.Component;

@Component
public class CrossWordGrid {

    static WordTrie.WordTrieNode root = new WordTrie.WordTrieNode();
    String[] arr = {"oven", "lima", "dent", "avie", "temn", "snat", "cats", "cold"};

    public void getCrossWord() {
        char[][] crossWord = new char[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                crossWord[i][j] = '=';
            }
        }
        WordTrie.Structure structure = new WordTrie.Structure();
        structure.build(root, arr);


        solve(crossWord);
        System.out.println(solve(crossWord));
        print(crossWord);

    }

    public void fillVertically(char[][] crossWord, int i, int j, String word) {

        for (int k = i; k < 4; k++)
            crossWord[k][j] = word.charAt(k);

        System.out.println("fillVertically: column " + j + " word " + word);
    }

    public void removeVertically(char[][] crossWord, int i, int j, String word) {
        for (int k = i; k < 4; k++) {
            crossWord[k][j] = '=';
        }
        System.out.println("removeVertically: column " + j + " word " + word);
    }


    public void fillHorizontally(char[][] crossWord, int i, int j, String word) {

        for (int k = j; k < 4; k++)
            crossWord[i][k] = word.charAt(k);
        System.out.println("fillHorizontally: row " + i + " word " + word);
    }

    public void removeHorizontally(char[][] crossWord, int i, int j, String word) {
        for (int k = i; k < 4; k++) {
            crossWord[k][j] = '=';
        }
        System.out.println("removeHorizontally: row " + i + " word " + word);
    }

    public boolean isVerticallyFilled(char[][] crossWord, int j) {
        String s = "";
        for (int k = 0; k < 4; k++) {
            if (crossWord[k][j] == '=') break;
            s += crossWord[k][j];
        }
        if (s.length() == 4) return true;
        return false;
    }

    public boolean isHorizontallyFilled(char[][] crossWord, int i) {
        String s = "";
        for (int k = 0; k < 4; k++) {
            if (crossWord[i][k] == '=') break;
            s += crossWord[i][k];
        }
        if (s.length() == 4) return true;
        return false;
    }

    public boolean doesMatchVertically(char[][] crossWord, int i, int j, String c) {

        if (i == 0)
            if (crossWord[i][j] != c.charAt(0)) return false;
        for (int k = 0; k < 4; k++) {
            if (k == i) break;
            else if (crossWord[k][j] != c.charAt(k)) return false;
        }
        return true;
    }
//
//    public boolean doesMatchHorizontally(char[][] crossWord, int i, int j, String c) {
//        if(j==0)
//            if(crossWord[i][j] != c.charAt(0))return false;
//        for (int k = 0; k < 4; k++) {
//            if (k== j) break;
//            else if (crossWord[i][k] != c.charAt(k)) return false;
//        }
//        return true;
//    }

    public boolean isAlreadyFilled(char[][] crossWord, String word) {

        int i = 0;
        while (i < 4) {
            for (int k = 0; k < 4; k++) {
                if (crossWord[i][k] != word.charAt(k)) {
                    i++;
                    break;
                }
                if (k == 3)
                    return true;
            }
        }
        i = 0;
        while (i < 4) {
            for (int k = 0; k < 4; k++) {
                if (crossWord[k][i] != word.charAt(k)) {
                    i++;
                    break;
                }
                if (k == 3)
                    return true;
            }
        }
         System.out.println("isAlreadyFilled: " + word);
        return false;
    }


    public boolean isValid(char[][] crossword) {
        int i = 0;
        WordTrie.Structure structure = new WordTrie.Structure();
        while (i < 4) {
            String word = "";
            for (int k = 0; k < 4; k++) {
                if (crossword[i][k] != '=') {
                    word += crossword[i][k];
                }
            }
            if (!structure.isPrefixWord(root, word)) return false;
            i++;
        }
        i = 0;
        while (i < 4) {
            String word = "";

            for (int k = 0; k < 4; k++) {
                if (crossword[k][i] != '=') {
                    word += crossword[k][i];
                }

            }
            if (!structure.isPrefixWord(root, word)) return false;
            i++;
        }
        return true;
    }

    public void print(char[][] crossWord) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(crossWord[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean solve(char[][] crossword) {
        if (crossword[3][3] != '=' && isValid(crossword)) return true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if ((j >= 0 && i == 0) || (j == 0 && i >= 0)) {
                    if (isVerticallyFilled(crossword, j) && isHorizontallyFilled(crossword, i)) {
                        continue;
                    }
                    for (String str : arr) {
                        System.out.println("row" + i + "col" + j);
                        if (isAlreadyFilled(crossword, str)) {
                            continue;
                        }
//
//                    if (isVerticallyFilled(crossword, j)) {
//                        if (doesMatchHorizontally(crossword, i, j, str)) {
//                            fillHorizontally(crossword, i+1, j, str);
//                            print(crossword);
//                            if (isValid(crossword) && solve(crossword)) return true;
//                            removeHorizontally(crossword, i+1, j, str);
//
//                        }
                        //    }
                        if (isHorizontallyFilled(crossword, i)) {
                            if (doesMatchVertically(crossword, i, j, str)) {
                                fillVertically(crossword, i + 1, j, str);
                                print(crossword);
                                if (isValid(crossword) && solve(crossword)) return true;
                                removeVertically(crossword, i + 1, j, str);
                            }
                        } else {
                            fillHorizontally(crossword, i, j, str);
                            print(crossword);
                            if (isValid(crossword) && solve(crossword)) return true;
                            removeHorizontally(crossword, i, j, str);
                        }
                    }
                }
            }

        }


        return false;
    }


}

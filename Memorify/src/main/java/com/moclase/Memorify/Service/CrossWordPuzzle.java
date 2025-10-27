package com.moclase.Memorify.Service;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CrossWordPuzzle {
//    String[] arr = { };
//    String[] arr = {};
//


    public void buildCrossWordPuzzle() {
        char[][] grid = {
                {'*', '*', '*', '*', '*', '*', '='},
                {'=', '=', '*', '=', '*', '=', '='},
                {'=', '=', '*', '=', '*', '=', '='},
                {'=', '=', '*', '*', '*', '*', '*'},
                {'=', '=', '*', '=', '*', '*', '='},
                {'=', '=', '=', '=', '*', '*', '='}
        };
        char[][] crossword = {
                {'=', '=', '=', '=', '=', '=', '*', '=', '=', '='},
                {'=', '=', '*', '*', '*', '*', '*', '*', '=', '='},
                {'=', '=', '=', '=', '=', '=', '*', '=', '=', '='},
                {'=', '=', '=', '=', '=', '=', '*', '=', '=', '='},
                {'=', '=', '=', '*', '*', '*', '*', '*', '*', '='},
                {'=', '=', '=', '=', '=', '=', '*', '=', '*', '='},
                {'=', '=', '=', '=', '=', '=', '*', '=', '*', '='},
                {'=', '=', '=', '=', '=', '=', '=', '=', '*', '='},
                {'=', '=', '=', '=', '=', '=', '=', '=', '*', '='},
                {'=', '=', '=', '=', '=', '=', '=', '=', '*', '='}
        };

//        char[][] crossword = {
//                {'=', '*', '=', '=', '=', '=', '=', '=', '=', '='},
//                {'=', '*', '=', '=', '=', '=', '=', '=', '=', '='},
//                {'=', '*', '=', '=', '=', '=', '=', '=', '=', '='},
//                {'=', '*', '*', '*', '*', '*', '=', '=', '=', '='},
//                {'=', '*', '=', '=', '=', '*', '=', '=', '=', '='},
//                {'=', '*', '=', '=', '=', '*', '=', '=', '=', '='},
//                {'=', '=', '=', '=', '=', '*', '=', '=', '=', '='},
//                {'=', '=', '*', '*', '*', '*', '*', '*', '=', '='},
//                {'=', '=', '=', '=', '=', '*', '=', '=', '=', '='},
//                {'=', '=', '=', '=', '=', '*', '=', '=', '=', '='}
//        };
      String[] arr = {"ICELAND","LONDON", "DELHI", "ANKARA", "INDIA", "LHASA","POLAND", "SPAIN","MEXICO", "PANAMA", "ALMATY"};
        boolean []isFilled = new boolean[arr.length];
      solveWord(crossword,arr,isFilled);
        printCrossWordPuzzle(crossword);

    }

    public boolean isStrartHorizontal(char[][] board, int row, int col) {
        boolean leftBlocked = (col == 0 || board[row][col - 1] == '=');
        boolean canGoRight = (col + 1 < board[0].length && board[row][col + 1] != '=');
        if (leftBlocked && canGoRight)
            return true;
        return false;
    }

    public boolean isStrartVertical(char[][] board, int row, int col) {
        boolean topBlocked = (row == 0 || board[row - 1][col] == '=');
        boolean canGoDown = (row + 1 < board.length && board[row + 1][col] != '=');
        if (topBlocked && canGoDown)
            return true;
        return false;
    }

    public boolean isVerticallyFilled(char[][] crossWord, int i,int j) {

        int k=i;
        while(k!= crossWord.length&&crossWord[k][j] != '='){
            if (crossWord[k][j]=='*') return false;
            k++;

        }

        return true;

    }

    public boolean isHorizontallyFilled(char[][] crossWord, int i,int j) {
        int k=j;
        while(k!= crossWord.length&&crossWord[i][k] != '='){
            if (crossWord[i][k]=='*') return false;
        k++;
        }
         return true;
    }
    private boolean allWordsUsed(boolean[] isFilled) {
        for (boolean i : isFilled)
            if (!i)
                return false;
        return true;
    }

    public boolean solveWord(char[][] word,String[]arr,boolean[] isFilled) {
//        if (allWordsUsed(isFilled)) {
//            return true;
//        }
        for (int i = 0; i < word.length; i++) {
            for (int j = 0; j < word[i].length; j++) {
                if (word[i][j]!='='&&(word[i][j] == '*'||isStrartHorizontal(word,i,j)||isStrartVertical(word,i,j))) {
                    if(isHorizontallyFilled(word,i,j)&&isVerticallyFilled(word,i,j)){continue;}
                    for (int k=0;k< arr.length;k++) {
                        String str=arr[k];

                        if (isFilled[k]) continue;
                        if (isVerticallyFilled(word,i, j)) {
                            if (str.charAt(0) == word[i][j]&&canBePlacedRight(str.substring(1), i, j+1, word)) {
                                fillRight(word, i, j + 1, str.substring(1), 0);
                               isFilled[k]=true;
                               printCrossWordPuzzle(word);
                                if (solveWord(word,arr,isFilled)) return true;
                                isFilled[k]=false;
                                removeRight(word,i,j,str.substring(1),0);
                            }
                            else if(Arrays.asList(arr).indexOf(str)==arr.length-1){return false;}

                        } else if (isHorizontallyFilled(word, i,j)) {
                            if (str.charAt(0) == word[i][j]&&canBePlacedBottom(str.substring(1), i+1, j, word)) {
                                fillBottom(word, i + 1, j, str.substring(1), 0);
                                isFilled[k]=true;
                                printCrossWordPuzzle(word);
                                if (solveWord(word,arr,isFilled)) return true;
                                isFilled[k]=false;
                                removeBottom(word,i,j,str.substring(1),0);
                            } else if(Arrays.asList(arr).indexOf(str)==arr.length-1){return false;}


                        } else {
                            if (canBePlacedRight(str, i, j, word)) {
                                fillRight(word, i, j, str, 0);

                                isFilled[k]=true;
                                printCrossWordPuzzle(word);
                                if (solveWord(word,arr,isFilled)) return true;

                                isFilled[k]=false;
                                removeRight(word,i,j,str,0);
                            } else if (canBePlacedBottom(str, i, j, word)) {
                                fillBottom(word, i, j, str, 0);
printCrossWordPuzzle(word);
                                isFilled[k]=true;
                                if (solveWord(word,arr,isFilled)) return true;
                                removeBottom(word,i,j,str,0);
                                isFilled[k]=false;
                            }
   }
                    }
                }
            }
        }

        return true;
    }

    public void fillRight(char[][] grid, int i, int j, String str, int l) {
        grid[i][j] = str.charAt(l);
        if (l < str.length() - 1)
            fillRight(grid, i, j + 1, str, l + 1);

    }
    public void removeRight(char[][] grid, int i, int j, String str,int l) {
        grid[i][j] = '*';
        if (l < str.length() - 1)
            removeRight(grid, i, j + 1, str, l + 1);
    }
    public void fillBottom(char[][] grid, int i, int j, String str, int l) {
        grid[i][j] = str.charAt(l);
        if (l < str.length() - 1)
            fillBottom(grid, i + 1, j, str, l + 1);
    }
    public void removeBottom(char[][] grid, int i, int j, String str,int l) {
        grid[i][j] = '*';
        if (l < str.length() - 1)
            removeBottom(grid, i + 1, j, str, l + 1);
    }
    private boolean canBePlacedRight(String str, int i, int j, char[][] word) {

        int l = j;
        int len = 0;
        while (l!= word[0].length&&word[i][l] != '=') {
            if(word[i][l]!='*'&&str.charAt(len)!=word[i][l]) return false;
            len++;
            l++;
            if (len == str.length() && !inRange(word, i, l))
                return true;
            if (!inRange(word, i, l))
                return false;

        }
        return false;
    }

    public boolean canBePlacedBottom(String str, int i, int j, char[][] word) {
        int k = i;
        int len = 0;
        while (k!= word.length&&word[k][j] != '=') {
            if(word[k][j]!='*'&&str.charAt(len)!=word[k][j])return false;
            len++;k++;
            if (len == str.length() && !inRange(word, k, j)) return true;
            if (!inRange(word, k, j)) return false;
        }
        return false;
    }
    public boolean inRange(char[][] word, int i, int j) {
        if (i >= word.length || j >= word[i].length || i < 0 || j < 0 || word[i][j] == '=') {
            return false;
        }
        return true;
    }

    public void printCrossWordPuzzle(char[][] grid) {
        for (char[] row : grid) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println();
    }


}

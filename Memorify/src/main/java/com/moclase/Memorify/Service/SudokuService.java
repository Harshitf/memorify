package com.moclase.Memorify.Service;

import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class SudokuService {

public class Pair{
    int i, j;

    public Pair(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
    public Pair getValues(int sr,int sc,HashSet<Pair> set){
    int row=sr+(int) (Math.random()*3);
    int col=sc+(int) (Math.random()*3);
    if(set.contains(new Pair(row,0))|| set.contains(new  Pair(col,1))){return getValues(sr,sc,set);}
    set.add(new Pair(row,0));
    set.add(new Pair(col,1));
    return new Pair(row,col);
    }
    // sudoku generator
    public int[][] generateSudoku(){

        int[][] sudoku=new int[9][9];
        HashSet<Pair> set=new HashSet<>();
        int sr=0,sc=0;
        for (int i=0;i<7;i++){
            Pair p=getValues(i,i,set);
            int row = p.i;
            int col = p.j;
             sudoku[row][col] = (int)(Math.random()*9)+1;
            System.out.println(row+","+col);
            if (sc==6){sr+=3;sc=0;}
            else sc+=3;
        }
//       if(!solve(sudoku)) {
//        return generateSudoku();
//       }
        solve(sudoku);
           removeCells(sudoku);

           return sudoku;





    }
    // making a sudoku by emptying cells
    public void removeCells(int[][] sudoku){
    HashSet<Pair> set=new HashSet<>();
    for (int i=0;i<20;i++){
        Pair p=getValues(i%7,i%7,set);
            int row = p.i;
            int col = p.j;
            sudoku[row][col]=0;
        }
    }


    // valid number placement checker
    public boolean isValid(int[][] board, int row, int col,int value) {
        int rs=(row/3)*3;
        int cs=(col/3)*3;
        for (int i = rs; i < rs+3; i++) {
            for (int j = cs; j < cs+3; j++) {
                if (board[i][j] == value)
                    return false;
            }
        }
        for(int i=0;i<9;i++)
            if(board[i][col] == value||board[row][i]== value)return false;
        return true;
    }


//sudoku solver
    public boolean solve(int[][] board) {

        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]==0){
                    for(int k=1;k<=9;k++){
                        if(isValid(board,i,j,k)){
                            board[i][j]=k;
                        if(solve(board))return true;
                        board[i][j]=0;

                        }
                    }
                    return false;
                }
            }
        }
        return  true;
    }
}

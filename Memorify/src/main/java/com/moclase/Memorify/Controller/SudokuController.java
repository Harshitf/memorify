package com.moclase.Memorify.Controller;

import com.moclase.Memorify.Service.SudokuService;
import com.moclase.Memorify.Dto.SudokuBoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static java.util.Arrays.stream;


@Controller
@CrossOrigin("http://localhost:5173")
public class SudokuController {

    @Autowired
    private SudokuService sudokuService;


   private int[][] solution;
    

    @GetMapping("/sudoku")
    public ResponseEntity<int[][]> generateAndFillSudoku() {
        int[][] sudoku = sudokuService.generateSudoku();
        SudokuBoardDTO sudokuDto=new SudokuBoardDTO();
        sudokuDto.setBoard(sudoku);
        solution = stream(sudoku)
            .map(int[]::clone)
            .toArray(int[][]::new);

        sudokuService.solve(solution); // fill only the solution

        for(int row = 0; row < solution.length; row++) {
            for(int col = 0; col < solution[row].length; col++) {
                System.out.print(solution[row][col]+"  ");
            }
            System.out.println();
        }
        return ResponseEntity.ok(sudoku);
    }

    @PostMapping("/submit")
    @ResponseBody
    public boolean submitSudoku(@RequestBody int[][]sudoku) {
        if (solution == null) return false;
        return java.util.stream.IntStream.range(0, 9).allMatch(i ->
            java.util.stream.IntStream.range(0, 9).allMatch(j ->
                sudoku[i][j] == solution[i][j]
            )
        );
    }


}

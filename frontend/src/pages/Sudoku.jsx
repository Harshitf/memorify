import { useState, useEffect } from "react";
import axios from "axios";
import "../styles/sudoku.css";

export default function Sudoku() {
    const [sudoku, setSudoku] = useState(
        Array(9)
            .fill("")
            .map(() => Array(9).fill(""))
    );
    const [originalBoard, setOriginalBoard] = useState(
        Array(9)
            .fill("")
            .map(() => Array(9).fill(""))
    );

    // Fetch Sudoku from server
    useEffect(() => {
        const getSudoku = async () => {
            try {
                const res = await axios.get("http://localhost:8083/sudoku");
                // Ensure data is a 2D array
                if (Array.isArray(res.data) && res.data.length === 9) {
                    setSudoku(res.data);
                    setOriginalBoard(res.data);
                } else {
                    console.error("Invalid Sudoku data:", res.data);
                }
            } catch (err) {
                console.error("Error fetching Sudoku:", err);
            }
        };

        getSudoku();
    }, []);

    // Handle cell input
    const handleChange = (e, row, col) => {
        const value = e.target.value;
        // Allow only numbers 1-9
        if (!/^[1-9]?$/.test(value)) return;

        // Only allow change if original cell is empty
        if (originalBoard[row][col] !== "") return;

        setSudoku((prev) => {
            const newSudoku = prev.map((r) => [...r]);
            newSudoku[row][col] = value;
            return newSudoku;
        });
    };

    // Submit Sudoku
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const res = await axios.post(
                "http://localhost:8083/submit",
                sudoku,
                { headers: { "Content-Type": "application/json" } }
            );
            console.log("Sudoku submitted:", res.data);
            document.getElementById("result").innerText =
                res.data.message || "Submitted!";
        } catch (err) {
            console.error("Error submitting Sudoku:", err);
        }
    };

    return (
        <div className="sudoku-container">
            <h1>Sudoku Game</h1>
            <div className="sudoku-grid" style={{width:'100%'}}>
                {sudoku.map((row, rowIdx) => (
                    <div key={rowIdx} className="row">
                        {row.map((cell, colIdx) => (
                            <input
                                style={{ width: "2%",
                                    background:String(originalBoard[rowIdx][colIdx] || "")
                                        .trim() !== "" ? "pink": "white"}}
                                key={`${rowIdx}-${colIdx}`}
                                type="text"
                                value={cell || ""}
                                maxLength="1"
                                className="cell"
                                onChange={(e) => handleChange(e, rowIdx, colIdx)}
                                readOnly={String(originalBoard[rowIdx][colIdx]||"").trim() !== ""}

                            />
                        ))}
                    </div>
                ))}
            </div>
            <button className="submit-btn" onClick={handleSubmit}>
                Submit
            </button>
            <div id="result"></div>
        </div>
    );
}

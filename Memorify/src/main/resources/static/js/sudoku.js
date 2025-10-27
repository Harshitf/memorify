
function submitBoard() {
    const board = [];
    for (let i = 0; i < 9; i++) {
        const row = [];
        for (let j = 0; j < 9; j++) {
            const cell = document.getElementById(`cell-${i}-${j}`);
            const val = parseInt(cell.value) || 0;
            row.push(val);
        }
        board.push(row);
    }

    fetch("/submit", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ board: board })
    })
        .then(res => res.json())
        .then(valid => {
            document.getElementById("result").innerText = valid ? "✅ Valid Sudoku!" : "❌ Invalid Sudoku!";
        })
        .catch(err => console.error("Error:", err));
}

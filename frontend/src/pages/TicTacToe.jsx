import { useEffect, useState } from "react";
import axios from "axios";
import { checkWinner } from "../components/CheckWinner.js";
import { Aimove } from "../components/Aimove.js";
import { GiButtonFinger } from "react-icons/gi";
import { VscDebugStart } from "react-icons/vsc";
import styles from "../styles/TicTacToe.module.css";

export default function TicTacToe() {
    const [board, setBoard] = useState([
        ["", "", ""],
        ["", "", ""],
        ["", "", ""],
    ]);
    const [player, setPlayer] = useState(true); // true = player1 (O), false = player2 (X)
    const [winner, setWinner] = useState(null);
    const [loading, setLoading] = useState(false);

    const [view, setView] = useState("menu"); // "menu", "playerDetails", "board"
    const [message, setMessage] = useState("");
    const [gamesPlayed, setGamesPlayed] = useState(null);

    const [playerInfo, setPlayerInfo] = useState({
        player1: { id: 0, username: "", wins: 0, losses: 0, ties: 0 },
        player2: { id: 0, username: "", wins: 0, losses: 0, ties: 0 },
    });

    // Handle bot move
    useEffect(() => {
        if (playerInfo.player2.username === "bot" && !player && !winner) {
            const botMove = async () => {
                await sleep(1000); // delay for realism
                const [r, c] = Aimove(board);
                playMove(r, c);
            };
            botMove();
        }
    }, [player, board, winner]);

    // Save result after game ends
    useEffect(() => {
        if (winner) {
            updatePlayerInfo();
        }
    }, [winner]);

    function sleep(ms) {
        return new Promise((resolve) => setTimeout(resolve, ms));
    }

    // API calls
    async function fetchGamesPlayed() {
        try {
            const res = await axios.post("http://localhost:8083/no-of-game", {
                player1: playerInfo.player1.username,
                player2: playerInfo.player2.username,
            });
            setGamesPlayed(res.data);
        } catch (err) {
            console.error("Error fetching games played:", err);
        }
    }

    async function fetchPlayerStats() {
        try {
            const res = await axios.post("http://localhost:8083/get-player-stats", {
                player1: playerInfo.player1.username,
                player2: playerInfo.player2.username,
            });
            setPlayerInfo(res.data);
        } catch (err) {
            console.error("Error fetching stats:", err);
        }
    }

    async function saveGameResult(updatedInfo) {
        try {
            await axios.post("http://localhost:8083/save-game", updatedInfo);
        } catch (err) {
            console.error("Error saving game:", err);
        }
    }

    // Event Handlers
    function handleChange(e) {
        const { name, value } = e.target;
        setPlayerInfo((prev) => ({
            ...prev,
            [name]: { ...prev[name], username: value },
        }));
    }

    function handleMultiplayer() {
        setPlayerInfo((prev) => ({
            ...prev,
            player2: { ...prev.player2, username: "" },
        }));
        setView("playerDetails");
    }

    function handleAiPlayer() {
        setPlayerInfo((prev) => ({
            ...prev,
            player2: { ...prev.player2, username: "bot" },
        }));
        setView("playerDetails");
    }

    async function handleGameStart() {
        if (playerInfo.player1.username && playerInfo.player2.username) {
            await fetchGamesPlayed();
            await fetchPlayerStats();
            setMessage(`${playerInfo.player1.username}'s turn`);
            setView("board");
            setLoading(true);
        }
    }

    function handleReset() {
        setBoard([
            ["", "", ""],
            ["", "", ""],
            ["", "", ""],
        ]);
        setPlayer(true);
        setWinner(null);
        setMessage(`${playerInfo.player1.username}'s turn`);
        setLoading(false);
        setView("menu");
        playerInfo.player2.username = "";
        playerInfo.player1.username = "";

    }

    function updatePlayerInfo() {
        const updated = { ...playerInfo };

        if (winner === "draw") {
            updated.player1.ties += 1;
            updated.player2.ties += 1;
        } else if (winner === updated.player1.username) {
            updated.player1.wins += 1;
            updated.player2.losses += 1;
        } else if (winner === updated.player2.username) {
            updated.player1.losses += 1;
            updated.player2.wins += 1;
        }

        setPlayerInfo(updated);
        saveGameResult(updated);
    }

    // Game logic
    function playMove(row, col) {
        if (board[row][col] !== "" || winner) return;

        const newBoard = board.map((r) => [...r]);
        newBoard[row][col] = player ? "O" : "X";
        setBoard(newBoard);

        const result = checkWinner(newBoard);

        if (result === "O") {
            setWinner(playerInfo.player1.username);
            setMessage(`${playerInfo.player1.username} wins!`);
        } else if (result === "X") {
            setWinner(playerInfo.player2.username);
            setMessage(`${playerInfo.player2.username} wins!`);
        } else if (newBoard.flat().every((cell) => cell !== "")) {
            setWinner("draw");
            setMessage("It's a draw!");
        } else {
            setPlayer(!player);
            setMessage(`${!player ? playerInfo.player1.username : playerInfo.player2.username}'s turn`);
        }
    }

    // Render Sections
    const renderMenu = () => (
        <>
            <div className={styles.colorStyle} onClick={handleMultiplayer}>
                Multiplayer Game
            </div>
            <div className={styles.colorStyle} onClick={handleAiPlayer}>
                AI Opponent
            </div>
        </>
    );

    const renderPlayerDetails = () => (
        <div className={styles.colorStyle}>
            <label>Enter player name</label>
            <input
                className={styles.inputStyle}
                value={playerInfo.player1.username}
                type="text"
                name="player1"
                onChange={handleChange}
                placeholder="Enter player name"
                autoComplete="off"
            />
            {playerInfo.player2.username !== "bot" && (
                <div>
                    <label>Enter opponent name</label>
                    <input
                        className={styles.inputStyle}
                        value={playerInfo.player2.username}
                        type="text"
                        name="player2"
                        onChange={handleChange}
                        placeholder="Enter opponent name"
                        autoComplete="off"
                    />
                </div>
            )}
            <div className={styles.start} onClick={handleGameStart}>
                <VscDebugStart size={50} />
                <button className={styles.colorStyle} type="button">
                    Start
                </button>
            </div>
        </div>
    );

    const renderBoard = () => (
        <>
            {gamesPlayed !== null && (
                <div className={styles.colorStyle}>Games Played: {gamesPlayed}</div>
            )}
            <p className={styles.colorStyle}>{loading && message}</p>

            <div className={styles.colorStyle}>
                {board.map((row, rIdx) => (
                    <div key={rIdx} style={{ display: "flex" }}>
                        {row.map((cell, cIdx) => (
                            <div
                                key={`${rIdx}-${cIdx}`}
                                className="w-16 h-16 border flex items-center justify-center cursor-pointer"
                                onClick={() => playMove(rIdx, cIdx)}
                            >
                                {cell === "O" && <img src="/images/o-image.png" alt="O" height="50" />}
                                {cell === "X" && <img src="/images/x-image.png" alt="X" height="50" />}
                                {cell === "" && <img src="/images/blank-image.png" alt="Blank" height="50" />}
                            </div>
                        ))}
                    </div>
                ))}
            </div>

            <div className={styles.resetButton} onClick={handleReset}>
                <GiButtonFinger size={50} />
                <button  type="button">
                    Reset Game
                </button>
            </div>
        </>
    );

    return (
        <div className={styles.ticTacToeContainer}>
            <h1>Tic Tac Toe Game</h1>
            {view === "menu" && renderMenu()}
            {view === "playerDetails" && renderPlayerDetails()}
            {view === "board" && renderBoard()}
        </div>
    );
}

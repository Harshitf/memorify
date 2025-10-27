import {useState,useEffect} from 'react';
import axios from 'axios';
import {TbMessageChatbotFilled} from "react-icons/tb";
import { CgArrowUpO } from "react-icons/cg";
import { TbLoaderQuarter } from "react-icons/tb";
import styles from "../styles/chatBotStyle.module.css";


export default function MistralChatBot() {
    const [chatMessage, setChatMessage] = useState("");
    const [history, setHistory] = useState([]);
    const [open, setOpen] = useState(false);
    const [count, setCount] = useState(1);
    const [loading, setLoading] = useState(false);

    function handleChatMessage(e) {
        setChatMessage(e.target.value);
    }

    function handleSubmit(e) {
        e.preventDefault();
        setCount((prev)=>prev+1);
        setLoading(true);
        setHistory((prev) => ([
            ...prev, "Question " + count + " : " + chatMessage
        ]));
        setHistory((prev) => ([
            ...prev,"\n"
        ]));
        setChatMessage("");
        axios.post("http://localhost:8083/get-answer-from-bot", chatMessage, {
            headers: {"Content-Type": "application/json"},
        }).then(res => {
            setHistory((prev) => [...prev,
                "Answer " + count + " : " + res.data
            ]);
            setLoading(false);
            setHistory((prev) => ([
                ...prev,"\n"
            ]));
            setHistory(prev => [
                ...prev,
                <hr key={prev.length} style={{color:"#4797B1"}}/>
            ]);
        })
            .catch(err => {
                console.log(err);
            });
    }

    return (
        <>
            <TbMessageChatbotFilled
                className={styles.botToogle}
                size={85}
                color="4797B1"
                onClick={() => setOpen(!open)}
               />
            {open && (
                <div className={styles.chatBox}>
                    <div className={styles.container}
                         style={{
                             display: "block",
                             height: '400px',
                             width: '250px',
                             
                         }}
                    >
                        {history.map((item, index) => (
                            <div key={index} style={{whiteSpace: "pre-line"}}>{item}</div>
                        ))}
                    </div>
                    <div className={styles.messageSendDiv} style={{resize: ''}}>
                        <input
                            className={styles.chatInput}
                            name="chat"
                            value={chatMessage}
                            onChange={handleChatMessage}
                            placeholder="Ask Anything"
                            autoComplete="off"
                            autoFocus={true}
                        />
                        {loading?(<TbLoaderQuarter className={styles.loader}/>):(<CgArrowUpO className={styles.chatSend} onClick={handleSubmit}/>)}
                    </div>
                </div>)}
        </>
    );
}
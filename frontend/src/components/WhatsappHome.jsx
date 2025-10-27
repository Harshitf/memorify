import styles from '../styles/whatsapp.module.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {useEffect, useState} from 'react';
import {connect, sendMessage} from './websocket.js';

export default function WhatsappHome() {
    const [messages, setMessages] = useState([]);
    const [input, setInput] = useState('');

    useEffect(() => {
        connect((msg) => {
            setMessages(prev => [...prev, msg]);
        });
    }, []);

    const handleSend = () => {
        if (input.trim() !== '') {
            sendMessage(JSON.stringify({ content: input }));
            setInput('');
        }
    };

    return (
        <>dgs
        <div style={{ padding: 20 }}>
            <h2>WebSocket Chat</h2>
            <div>
                {messages.map((msg, idx) => (
                    <div key={idx}>{msg}</div>
                ))}
            </div>

            <input
                value={input}
                onChange={(e) => setInput(e.target.value)}
                placeholder="Type a message..."
            />
            <button onClick={handleSend}>Send</button>
        </div>
        </>
    );
}
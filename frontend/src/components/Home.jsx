import React from "react";
import NavBar from "./NavBar/NavBar.jsx";
import MisteralChatBot from "./MisteralChatBot.jsx";
import "../styles/Home.css";

export default function Home() {
        return (
            <>
                <NavBar/>
                <MisteralChatBot/>
            <div style={{ padding: '20px' }}>
             
                <h1>🎮 Welcome to Games App</h1>
                <p>Navigate to Any of above provided links to continue your journey with us</p>
                <p>This is your homepage. Use the navigation above to explore.</p>
                <p><b>you can also ask anything from mistral ai your chatbot<br/>
                by clicking on icon in bottom right of your screen</b></p>
            </div>
            </>
        );
    }


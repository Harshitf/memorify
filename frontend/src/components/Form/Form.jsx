import React, { useEffect, useState } from "react";
import { GiLever } from "react-icons/gi";
import styles from "./Form.module.css";

import {
    fetchWordMeaning,
    saveWordMeaning,
    fetchAIWord,
} from "../../services/word-meaning-api.js";

function Form({ onCall }) {
    const [formData, setFormData] = useState({
        word: "",
        type: "",
        meaning: "",
    });

    const [isDataLoaded, setIsDataLoaded] = useState(false);

    useEffect(() => {
        if (!isDataLoaded) {
            setIsDataLoaded(true);
            fetchWordMeaning()
                .then((res) => {
                    setFormData({
                        word: res.data.word || "",
                        type: res.data.type || "",
                        meaning: "",
                    });
                })
                .catch((err) => {
                    console.error("❌ Error fetching data:", err);
                    setIsDataLoaded(false);
                });
        }
    }, [isDataLoaded]);

    function handleChange(e) {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
    }

    function handleSubmit(e) {
        e.preventDefault();
        saveWordMeaning(formData)
            .then((res) => {
                console.log("✅ Data saved successfully:", res.data);
            })
            .catch((err) => {
                console.error("❌ Error saving data:", err);
            });
    }

    function handleLeverClick() {
        if (!formData.word) {
            console.warn("⚠️ No word available to send.");
            return;
        }
        fetchAIWord(formData.word)
            .then((res) => {
                onCall(res.data);
            })
            .catch((err) => {
                console.error("❌ Error fetching AI response:", err);
            });
    }

    return (
        <div className={styles.formContainer}>
            <form className={styles.form} onSubmit={handleSubmit}>
                <p className="form-label">
                    Word: <span id="word">{formData.word}</span>
                </p>
                <input type="hidden" name="word" value={formData.word} />

                <p className="form-label">
                    Type: <span>{formData.type}</span>
                </p>
                <input type="hidden" name="type" value={formData.type} />

                <input
                    className={styles.meaning}
                    name="meaning"
                    type="text"
                    value={formData.meaning}
                    onChange={handleChange}
                    placeholder="Enter meaning..."
                    autoComplete="off"
                    autoFocus
                />

                <button className={styles.submitBtn} type="submit">
                    Submit
                </button>
            </form>

            <div className="lever-section">
                <p>Don’t know the answer? No worries, click the lever 👇</p>
                <GiLever size={100} className={styles.lever} onClick={handleLeverClick} />
            </div>
        </div>
    );
}

export default Form;

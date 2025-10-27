console.log("wm.js loaded ✅");

async function addWord(word) {
    console.log("addWord");

        console.log("📦 addWord called with:", word);

    try { const response = await fetch("http://localhost:8083/ai?wors=" + encodeURIComponent(word), {
            method: "GET",
            headers: { "Accept": "application/json" }

        });
console.log("response:", response);
        if (!response.ok) {
            console.error("❌ Network error", response.status);
            return "Error from backend";
        }
console.log("response:", response.text);
        console.log("✅ Got data:");
            const data = await response.text();


        return data;
    }

    catch (err) {
        console.error("❌ Exception caught:", err);
        return "Something went wrong";
    }
}
document.addEventListener("DOMContentLoaded", async function () {
    console.log("🔥 DOM Loaded");

    const wordInput = document.getElementById("aa");
    if (!wordInput) {
        console.log("❌ #aa not found");
        return;
    }

    const word = wordInput.innerText.trim();
    console.log(word);
    const meaning = await addWord(word); // Call the function
console.log(meaning);
    document.getElementById("t").innerText = meaning;

});



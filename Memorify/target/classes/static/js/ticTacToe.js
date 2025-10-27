

let player = true;
let player1 = "";
let player2 = "";

// bot selected
$("#aiOpponent").on("click", function () {
    $("#aiOpponent").hide();
    $("#multiplayerGame").hide();
    $("#get-player-details").show();
    $("#opponentName input").val("bot");
    $("#opponentName").hide();
});

// 1 vs 1 selected
$("#multiplayerGame").on("click", function () {
    $("#aiOpponent").hide();
    $("#multiplayerGame").hide();
    $("#get-player-details").show();
});


$(".start").on("click", (function (e) {
    e.preventDefault();
    player1 = $("#playerName").val();
    player2 = $("#opponentName input").val();
    if (!player1 || !player2) {
        alert("Please fill out required fields");
        return;
    }
    getNoOfGamesPlayed();
    updatePlayerOpponent("p1");
    updatePlayerOpponent("p2");

    $("p").text(player1 + " turn");
    $(".board").show();
}));


function getNoOfGamesPlayed() {

    fetch(`/no-of-game?player1=${encodeURIComponent(player1)}&player2=${encodeURIComponent(player2)}`, {
        method: 'GET'
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(data => {
            console.log("Success:", data);
            $("#noOfGames p").text("Game no : " + data);
            $("#noOfGames").show();
        })
        .catch(error => {
            console.error("Error:", error);
        });

}


const entries = performance.getEntriesByType("navigation");
entries.forEach((entry) => {
    if (entry.type === "reload") {
        console.log(`${entry.name} was reloaded!`);
        console.log(entry);
    }
});

function updatePlayerOpponent(name) {
    if (name == "p1")
        $("." + name + "-opp").val(player2);
    else
        $("." + name + "-opp").val(player1);
}

function updatePlayerWin(id) {
    $("." + id + "-win").val(1);
}

function updatePayerLose(id) {
    $("." + id + "-losses").val(1);
}

function updatePlayerTie(id1, id2) {
    $("." + id1 + "-ties").val(1);
    $("." + id2 + "-ties").val(1);
}

$("img").on("click", function () {
    if ($(this).attr("src") === "/images/blank-image.png") {
        if (player) {
            playMove($(this), "/images/o-image.png");

            if (player2 === "bot" && !$("img").hasClass("disabled")) {
                setTimeout(() => {
                    let move = randomBotMove();
                    console.log(move);
                    let aiCell = $("img[data-row='" + move[0] + "'][data-col='" + move[1] + "']");
                    playMove(aiCell, "/images/x-image.png");
                }, 300);
            }

        } else {
            playMove($(this), "/images/x-image.png");
        }
    }
});

function playMove($img, imagePath) {
    $img.attr("src", imagePath);

    if (checkWinner() === 1) {
        $("img")
            .addClass("disabled")
            .off("click");
        $("p").html(player2 + " wins!");
        updatePlayerWin("p2");
        updatePayerLose("p1");
    } else if (checkWinner() === 2) {

        $("img")
            .addClass("disabled")
            .off("click");
        $("p").html(player1 + " wins!");
        updatePlayerWin("p1");
        updatePayerLose("p2");
    } else if ($("img[src='/images/blank-image.png']").length === 0) {
        $("img").addClass("disabled").off("click");
        $("p").html("It's a draw!");
        updatePlayerTie("p1", "p2");
    } else {
        $("p").html((player ? player2 : player1) + "'s turn");
    }

    player = !player;

}

function checkWinner() {
    for (i = 1; i <= 3; i++) {
        if (
            ($("img[data-row='1'][data-col='" + i + "']").attr("src") === "/images/x-image.png" &&
                $("img[data-row='2'][data-col='" + i + "']").attr("src") === "/images/x-image.png" &&
                $("img[data-row='3'][data-col='" + i + "']").attr("src") === "/images/x-image.png")
            ||
            ($("img[data-row='" + i + "'][data-col='1']").attr("src") === "/images/x-image.png" &&
                $("img[data-row='" + i + "'][data-col='2']").attr("src") === "/images/x-image.png" &&
                $("img[data-row='" + i + "'][data-col='3']").attr("src") === "/images/x-image.png")
            ||
            ($("img[data-row='1'][data-col='1']").attr("src") === "/images/x-image.png" &&
                $("img[data-row='2'][data-col='2']").attr("src") === "/images/x-image.png" &&
                $("img[data-row='3'][data-col='3']").attr("src") === "/images/x-image.png")
            ||
            ($("img[data-row='1'][data-col='3']").attr("src") === "/images/x-image.png" &&
                $("img[data-row='2'][data-col='2']").attr("src") === "/images/x-image.png" &&
                $("img[data-row='3'][data-col='1']").attr("src") === "/images/x-image.png")
        )
            return 1;
        else if (
            ($("img[data-row='1'][data-col='" + i + "']").attr("src") === "/images/o-image.png" &&
                $("img[data-row='2'][data-col='" + i + "']").attr("src") === "/images/o-image.png" &&
                $("img[data-row='3'][data-col='" + i + "']").attr("src") === "/images/o-image.png")
            ||
            ($("img[data-row='" + i + "'][data-col='1']").attr("src") === "/images/o-image.png" &&
                $("img[data-row='" + i + "'][data-col='2']").attr("src") === "/images/o-image.png" &&
                $("img[data-row='" + i + "'][data-col='3']").attr("src") === "/images/o-image.png")
            ||
            ($("img[data-row='1'][data-col='1']").attr("src") === "/images/o-image.png" &&
                $("img[data-row='2'][data-col='2']").attr("src") === "/images/o-image.png" &&
                $("img[data-row='3'][data-col='3']").attr("src") === "/images/o-image.png")
            ||
            ($("img[data-row='1'][data-col='3']").attr("src") === "/images/o-image.png" &&
                $("img[data-row='2'][data-col='2']").attr("src") === "/images/o-image.png" &&
                $("img[data-row='3'][data-col='1']").attr("src") === "/images/o-image.png")
        )
            return 2;

    }

}

function randomBotMove() {
    let available = [];

    $("img").each(function () {
        if ($(this).attr("src") === "/images/blank-image.png") {
            let r = $(this).data("row");
            let c = $(this).data("col");
            available.push([r, c]);
        }
    });
    // if all cells are filled
    if (available.length === 0) return null;
    return available[getRandomRowCol(available.length)];
}

function getRandomRowCol(length) {
    return Math.floor(Math.random() * (length));
}


$("#resetButton").on("click", (function (e) {
    e.preventDefault();
    $("#p1-stats input").val(0);
    $("#p2-stats input").val(0);
    $("#opponentName input").val("");
    $("#playerName").val("");
    location.reload();
}));
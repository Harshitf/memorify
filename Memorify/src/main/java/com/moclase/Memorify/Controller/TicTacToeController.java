package com.moclase.Memorify.Controller;

import com.moclase.Memorify.repository.PlayerRepository;
import com.moclase.Memorify.Exceptions.PlayerNotFoundException;
import com.moclase.Memorify.Service.PlayerService;
import com.moclase.Memorify.model.Multiplayer;
import com.moclase.Memorify.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@CrossOrigin("http://localhost:5173")
public class TicTacToeController {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    PlayerService playerService;

    @GetMapping("/tic-tac-toe")
    public ResponseEntity<Multiplayer> ticTacToe() {
        Multiplayer m = new Multiplayer();
        m.setPlayer1(new Player());
        m.setPlayer2(new Player());
        return ResponseEntity.ok(m);
    }
    @PostMapping("/get-player-stats")
    public ResponseEntity<Map<String,Player>> getPlayerDetail(@RequestBody Map<String, String> body) {

        Player player1=playerRepository.findByUsernameAndOpponentName(body.get("player1"), body.get("player2")).orElse(new Player(body.get("player1"), body.get("player2")));
        Player player2=playerRepository.findByUsernameAndOpponentName(body.get("player2"), body.get("player1") ).orElse(new Player(body.get("player2"), body.get("player1")));

        return ResponseEntity.ok(Map.of("player1", player1, "player2", player2));

    }

    @PostMapping("/save-game")
    public ResponseEntity<String> UpdateWinner(@RequestBody Map<String,Player> body) {
        System.out.println(body.get("player1") + " " + body.get("player2"));
        playerService.recordData(body.get("player1"));
        playerService.recordData(body.get("player2"));
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/no-of-game")
    @ResponseBody
    public ResponseEntity<?> noOfGame(@RequestBody Map<String,String> body) throws PlayerNotFoundException {
        try {
            String player1 = body.get("player1");
            String player2 = body.get("player2");
            System.out.println("Player 1: " + player1);
            System.out.println("Player 2: " + player2);
            Player p = playerRepository.findByUsernameAndOpponentName(player1, player2).orElse(new Player(player1,player2));
            System.out.println("Player 1: " + p);
            return ResponseEntity.ok(p.getWins() + p.getLosses() + p.getTies());
        } catch (Exception e) {
            System.out.println("new player in game");
        }
        return ResponseEntity.badRequest().build();
    }
}

package com.moclase.Memorify.Service;

import com.moclase.Memorify.model.Player;
import com.moclase.Memorify.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PlayerService {


    private final PlayerRepository playerRepository;

    public Player findOrCreate(String username, String opponent) {
        return playerRepository.findByUsernameAndOpponentName(username, opponent).orElse(new Player(username, opponent));
    }

    public void recordData(Player player) {
        Player p = findOrCreate(player.getUsername(), player.getOpponentName());
        if (player.getWins() == 1)
            p.setWins(p.getWins() + 1);
        if (player.getLosses() == 1)
            p.setLosses(p.getLosses() + 1);
        if (player.getTies() == 1)
            p.setTies(p.getTies() + 1);
        playerRepository.save(p);
        System.out.println(p);

    }

    public void Delete() {
        playerRepository.deleteAll();
    }

    public void addPlayerOnlineStatus(String sender, boolean b) {
    }

    public boolean playerExists(String sender) {
        return false;
    }
}

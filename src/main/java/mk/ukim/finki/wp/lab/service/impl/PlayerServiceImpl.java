package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Player;
import mk.ukim.finki.wp.lab.repository.PlayerRepository;
import mk.ukim.finki.wp.lab.service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Player> listPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player findByPlayerId(String playerId) {
        Player player = playerRepository.findByPlayerId(playerId).orElse(null);
        if (player == null) {
            throw new IllegalArgumentException("Player doesn't exist");
        }
        return player;
    }
}

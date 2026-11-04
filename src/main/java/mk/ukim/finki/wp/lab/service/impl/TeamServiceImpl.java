package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Player;
import mk.ukim.finki.wp.lab.model.Team;
import mk.ukim.finki.wp.lab.repository.PlayerRepository;
import mk.ukim.finki.wp.lab.repository.TeamRepository;
import mk.ukim.finki.wp.lab.service.TeamService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public TeamServiceImpl(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Team> listTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team findById(Long id) {
        Team team = teamRepository.findById(id).orElse(null);
        if (team == null) {
            throw new IllegalArgumentException();
        }
        return team;
    }

    boolean isPartOfTeam(Player player) {
        List<Team> teams = teamRepository.findAll();
        for (Team team : teams) {
            if (team.getPlayers().contains(player)) {
                return true;
            }
        }
        return false;
    }

    boolean jerseyNumberPresentWithinTeam(Team team, int jerseyNumber) {
        for (Player player : team.getPlayers()) {
            if (player.getJerseyNumber() == jerseyNumber) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Team addPlayerToTeam(Long teamId, String playerId) {
        Team toBeAddedTo = teamRepository.findById(teamId).orElse(null);
        Player playerToBeAdded = playerRepository.findByPlayerId(playerId).orElse(null);
        if (toBeAddedTo == null || playerToBeAdded == null) {
            throw new IllegalArgumentException("The given team and / or player was not found.");
        }
        if (isPartOfTeam(playerToBeAdded)) {
            throw new IllegalArgumentException("The player is already part of a team!");
        }
        if (jerseyNumberPresentWithinTeam(toBeAddedTo, playerToBeAdded.getJerseyNumber())) {
            throw new IllegalArgumentException("The jersey number already exists in that team!");
        }

        List<Player> new_players = toBeAddedTo.getPlayers();
        new_players.add(playerToBeAdded);
        toBeAddedTo.setPlayers(new_players);

        teamRepository.save(toBeAddedTo);
        return toBeAddedTo;
    }
}

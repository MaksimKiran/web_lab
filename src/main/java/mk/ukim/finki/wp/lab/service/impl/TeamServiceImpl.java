package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.League;
import mk.ukim.finki.wp.lab.model.Player;
import mk.ukim.finki.wp.lab.model.Team;
import mk.ukim.finki.wp.lab.repository.LeagueRepository;
import mk.ukim.finki.wp.lab.repository.PlayerRepository;
import mk.ukim.finki.wp.lab.repository.TeamRepository;
import mk.ukim.finki.wp.lab.service.TeamService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final LeagueRepository leagueRepository;

    public TeamServiceImpl(TeamRepository teamRepository, PlayerRepository playerRepository, LeagueRepository leagueRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.leagueRepository = leagueRepository;
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
                return true;
            }
        }
        return false;
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

    @Override
    public Team create(String name, String city, Long leagueId) {
        League league = leagueRepository.findById(leagueId).orElse(null);
        if (name == null || name.isBlank() || city == null || city.isBlank() || league == null) {
            throw new IllegalArgumentException("Invalid Arguments!");
        }
        Team newTeam = new Team(name, city, new ArrayList<>(),  league);
        return teamRepository.save(newTeam);
    }

    @Override
    public void update(Long teamId, String name, String city, Long leagueId) {
        Team team = teamRepository.findById(teamId).orElse(null);
        League league = leagueRepository.findById(leagueId).orElse(null);
        if (team == null || name == null || name.isBlank() || city == null || city.isBlank() || league == null) {
            throw new IllegalArgumentException("Invalid Arguments!");
        }

        team.setName(name);
        team.setCity(city);
        team.setLeague(league);
    }

    @Override
    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }
}

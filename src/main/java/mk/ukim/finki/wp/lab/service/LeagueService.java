package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.League;

import java.util.List;
import java.util.Optional;

public interface LeagueService {
    List<League> listLeagues();
    Optional<League> findById(Long id);
}

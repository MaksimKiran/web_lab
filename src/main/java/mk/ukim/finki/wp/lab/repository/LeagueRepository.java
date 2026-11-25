package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.League;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface LeagueRepository {
    List<League> findAll();
    Optional<League> findById(Long id);
}
package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.League;
import mk.ukim.finki.wp.lab.repository.LeagueRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class InMemoryLeagueRepository implements LeagueRepository {

    @Override
    public List<League> findAll() {
        return DataHolder.leagues;
    }

    @Override
    public Optional<League> findById(Long id) {
        return DataHolder.leagues.stream().filter(l -> l.getId().equals(id)).findFirst();
    }
}

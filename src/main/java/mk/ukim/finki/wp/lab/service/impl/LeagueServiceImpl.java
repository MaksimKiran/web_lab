package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.League;
import mk.ukim.finki.wp.lab.repository.LeagueRepository;
import mk.ukim.finki.wp.lab.service.LeagueService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LeagueServiceImpl implements LeagueService {
    private final LeagueRepository leagueRepository;

    public LeagueServiceImpl(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    @Override
    public List<League> listLeagues() {
        return leagueRepository.findAll();
    }

    @Override
    public Optional<League> findById(Long id) {
        return leagueRepository.findById(id);
    }
}

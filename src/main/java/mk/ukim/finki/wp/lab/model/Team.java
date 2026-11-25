package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Data
@AllArgsConstructor
public class Team {
    static Long nextId = 1L;

    private Long id;
    private String name;
    private String city;
    private List<Player> players;
    private League league;

    public Team(String name, String city, List<Player> players, League league){
        this.name = name;
        this.city = city;
        this.players = players;
        this.league = league;
        this.id = nextId++;
    }
}

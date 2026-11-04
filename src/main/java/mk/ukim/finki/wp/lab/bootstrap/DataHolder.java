package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Player;
import mk.ukim.finki.wp.lab.model.Team;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Team> teams = null;
    public static List<Player> players = null;

    @PostConstruct
    public void init() {
        teams = new ArrayList<>();
        players = new ArrayList<>();

        teams.add(new Team(1L, "MaksoTeam", "Skopje", new ArrayList<>()));
        teams.add(new Team(2L, "KanevTeam", "Struga", new ArrayList<>()));
        teams.add(new Team(3L, "KalinTeam", "Skopje", new ArrayList<>()));
        teams.add(new Team(4L, "AleksTeam", "Queens", new ArrayList<>()));
        teams.add(new Team(5L, "MaeTeam", "Portland", new ArrayList<>()));

        players.add(new Player("makso23", "Maksim", "Kirandjiski", "left", 23));
        players.add(new Player("takenforransom", "Mae", "Josifoska", "right", 67));
        players.add(new Player("ginko69", "Stefan", "Kanevche", "right", 69));
        players.add(new Player("sakkoker", "Aleks", "Galevski", "left", 8));
        players.add(new Player("PrekuRedBasist", "Kalin", "Malovski", "left", 2));

        players.add(new Player("evilmakso23", "Kaksim", "Mirandjiski", "right", -23));
        players.add(new Player("eviltakenforransom", "Jae", "Mosifoska", "left", -67));
        players.add(new Player("evilginko69", "Ktefan", "Sanevche", "left", -69));
        players.add(new Player("evilsakkoker", "Gleks", "Aalevski", "right", -8));
        players.add(new Player("evilPrekuRedBasist", "Malin", "Kalovski", "right", -2));
    }
}

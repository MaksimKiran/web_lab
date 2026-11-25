package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Player;
import mk.ukim.finki.wp.lab.model.Team;
import mk.ukim.finki.wp.lab.model.League;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Team> teams = null;
    public static List<Player> players = null;
    public static List<League> leagues = null;

    @PostConstruct
    public void init() {
        teams = new ArrayList<>();
        players = new ArrayList<>();
        leagues = new ArrayList<>();

        leagues.add(new League(1L, "maksoLeague", "Macedonia","best league of them all"));
        leagues.add(new League(2L, "kaneveague", "Macedonia","second best league of them all"));
        leagues.add(new League(3L, "aleksLeague", "Macedonia","third best league of them all"));

        teams.add(new Team("MaksoTeam", "Skopje", new ArrayList<>(), leagues.getFirst()));
        teams.add(new Team("KanevTeam", "Struga", new ArrayList<>(), leagues.get(1)));
        teams.add(new Team("KalinTeam", "Skopje", new ArrayList<>(), leagues.get(2)));
        teams.add(new Team("AleksTeam", "Queens", new ArrayList<>(), leagues.get(1)));
        teams.add(new Team("MaeTeam", "Portland", new ArrayList<>(), leagues.get(2)));

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

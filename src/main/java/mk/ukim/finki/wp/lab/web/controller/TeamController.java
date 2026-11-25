package mk.ukim.finki.wp.lab.web.controller;


import mk.ukim.finki.wp.lab.model.League;
import mk.ukim.finki.wp.lab.service.LeagueService;
import mk.ukim.finki.wp.lab.service.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class TeamController {
    private final TeamService teamService;
    private final LeagueService leagueService;

    public TeamController(TeamService teamService, LeagueService leagueService) {
        this.teamService = teamService;
        this.leagueService = leagueService;
    }

    @GetMapping("/teams")
    public String getTeamsPage(@RequestParam(required = false) String errorMessage, Model model) {
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("teams", teamService.listTeams());
        return "listTeams";
    }

    @GetMapping("/")
    public String homePage() {
        return "redirect:/teams";
    }

    @PostMapping("/teams/add")
    public String saveTeam(@RequestParam String name,
                           @RequestParam String city,
                           @RequestParam Long leagueId) {
        try {
            League league = leagueService.findById(leagueId).orElseThrow(IllegalArgumentException::new);
            teamService.create(name, city, leagueId);

        } catch (IllegalArgumentException e) {
            return "redirect:/teams?errorMessage=" + URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
        }

        return "redirect:/listTeams";
    }

    @PostMapping("/teams/edit/{teamId}")
    public String editTeam(@PathVariable Long teamId,
                           @RequestParam String name,
                           @RequestParam String city,
                           @RequestParam Long leagueId) {

        try {
            League league = leagueService.findById(leagueId).orElse(null);
            teamService.update(teamId, name, city, leagueId);

        } catch (IllegalArgumentException e) {
            return "redirect:/teams?errorMessage=" + URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
        }

        return "redirect:/listTeams";
    }

    @PostMapping("/teams/delete/{id}")
    public String deleteTeam(@PathVariable Long id) {

    }

}

package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Team;
import mk.ukim.finki.wp.lab.service.TeamService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "TeamDetailsServlet", urlPatterns = {"/teamDetails"})
public class TeamDetailsServlet extends HttpServlet {
    private final TeamService teamService;
    private final SpringTemplateEngine springTemplateEngine;

    public TeamDetailsServlet(TeamService teamService, SpringTemplateEngine springTemplateEngine) {
        this.teamService = teamService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teamId = req.getParameter("teamId");
        String playerId = req.getParameter("playerId");
        if (teamId == null || teamId.isBlank() || playerId == null || playerId.isBlank()) {
            resp.sendRedirect("/teamDetails");
            return;
        }
        try {
            teamService.addPlayerToTeam(Long.parseLong(teamId), playerId);
        } catch (IllegalArgumentException e) {
            String message = URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
            resp.sendRedirect("/players?teamId=" + teamId + "&errorMessage=" + message);
            return;
        }

        resp.sendRedirect("/teamDetails?teamId=" + teamId);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teamId = req.getParameter("teamId");
        if (teamId == null || teamId.isBlank()) {
            resp.sendRedirect("/teams");
            return;
        }

        Team team;
        try {
            team = teamService.findById(Long.parseLong(teamId));
        } catch (IllegalArgumentException e) {
            resp.sendRedirect("/teams");
            return;
        }

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);
        context.setVariable("team", team);

        springTemplateEngine.process("teamDetails", context, resp.getWriter());
    }
}

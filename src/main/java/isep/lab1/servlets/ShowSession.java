package isep.lab1.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static isep.lab1.util.Html.escape;
import isep.lab1.util.Html;

/**
 * 1.3.1 : "Welcome on my site" pour un nouveau visiteur, "Welcome back" sinon, plus le nombre de visites.
 * 1.4.2 - 2 : affiche aussi le nombre de sessions actives (attribut d'application "activeSessions"
 *             mis à jour par ActiveSessionListener).
 *
 * EXTRA (à toi) : demander nom complet et date de naissance à la première visite, puis afficher
 * "Hi, [full name]. There are [n] days to your birthday." (java.time.LocalDate, ChronoUnit.DAYS).
 */
@WebServlet("/ShowSession")
public class ShowSession extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(); // crée la session si elle n'existe pas

        Integer visits = (Integer) session.getAttribute("visitCount");
        String message;
        if (visits == null) {
            visits = 1;
            message = "Welcome on my site";
        } else {
            visits = visits + 1;
            message = "Welcome back";
        }
        session.setAttribute("visitCount", visits);

        Object activeSessions = getServletContext().getAttribute("activeSessions");
        Object appName = getServletContext().getAttribute("appName");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(Html.header(message));
        out.println("<p>Nombre de visites dans cette session : " + visits + "</p>");
        out.println("<p>ID de session : " + escape(session.getId()) + "</p>");
        out.println("<p>Sessions actives sur le serveur : " + activeSessions + "</p>");
        out.println("<p>Application : " + escape(String.valueOf(appName)) + "</p>");
        out.println("<p><a href=\"Logout\">Invalider la session (session.invalidate())</a></p>");
        out.println(Html.footer());
    }
}

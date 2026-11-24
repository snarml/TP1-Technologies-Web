package isep.lab1.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import isep.lab1.util.Html;

/**
 * Servlet utilitaire pour les tests : invalide la session courante.
 * Sert à tester SecurityFilter (accès après invalidation) et ActiveSessionListener (sessionDestroyed).
 */
@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); // false : ne PAS en créer une nouvelle
        boolean hadSession = session != null;
        if (hadSession) {
            session.invalidate();
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(Html.header("Déconnexion"));
        out.println(hadSession ? "<p>La session a été invalidée.</p>" : "<p>Aucune session active.</p>");
        out.println("<p><a href=\"ListItems\">Essayer d'accéder à ListItems</a> | <a href=\"login.html\">Se connecter</a></p>");
        out.println(Html.footer());
    }
}

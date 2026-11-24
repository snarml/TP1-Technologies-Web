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
 * 1.4.1 - 4 : récupère le nom d'utilisateur, le stocke dans l'attribut de session "user",
 * affiche une confirmation et un lien vers items.html.
 *
 * Si l'utilisateur se reconnecte avec un autre nom, l'attribut "user" est REMPLACÉ :
 * SessionAttributeListener affichera alors attributeReplaced (1.4.2 - 4).
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");

        if (username == null || username.isBlank()) {
            response.sendRedirect("login.html");
            return;
        }

        HttpSession session = request.getSession(); // crée ou récupère la session
        session.setAttribute("user", username.trim());

        // EXTRA 1.4.3 - 1 (à toi) : stocker aussi un objet SessionUser dans la session, par exemple :
        // session.setAttribute("sessionUser", new isep.lab1.listeners.SessionUser(username.trim()));

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(Html.header("Connexion réussie"));
        out.println("<p>Bienvenue <strong>" + escape(username) + "</strong> !</p>");
        out.println("<p><a href=\"items.html\">Aller à la page des items</a></p>");
        out.println("<p><a href=\"AttributeTest?session=replace\">Modifier l'attribut user</a> | "
                + "<a href=\"AttributeTest?session=remove\">Supprimer l'attribut user</a> | "
                + "<a href=\"Logout\">Se déconnecter</a></p>");
        out.println(Html.footer());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("login.html");
    }
}

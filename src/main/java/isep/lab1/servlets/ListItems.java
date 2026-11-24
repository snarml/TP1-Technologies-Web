package isep.lab1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static isep.lab1.util.Html.escape;
import isep.lab1.util.Html;

/**
 * 1.3.2 : stocke les items envoyés par items.html dans la session (ArrayList<String>)
 * et affiche la liste à chaque appel.
 * Protégée par SecurityFilter (1.4.1 - 5) : il faut passer par login.html avant.
 *
 * EXTRA (à toi) : afficher aussi combien de fois chaque item a été saisi
 * (indice : Collections.frequency(items, item) ou une Map<String, Integer>).
 */
@WebServlet("/ListItems")
public class ListItems extends HttpServlet {

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        ArrayList<String> items = (ArrayList<String>) session.getAttribute("items");
        if (items == null) {
            items = new ArrayList<>();
            session.setAttribute("items", items);
        }

        String newItem = request.getParameter("newItem");
        if (newItem != null && !newItem.isBlank()) {
            items.add(newItem.trim());
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(Html.header("Mes items"));
        out.println("<p>Utilisateur connecté : " + escape(String.valueOf(session.getAttribute("user"))) + "</p>");
        if (items.isEmpty()) {
            out.println("<p>Aucun item pour l'instant.</p>");
        } else {
            out.println("<ul>");
            for (String item : items) {
                out.println("<li>" + escape(item) + "</li>");
            }
            out.println("</ul>");
        }
        out.println("<p><a href=\"items.html\">Ajouter un autre item</a> | <a href=\"Logout\">Se déconnecter</a></p>");
        out.println(Html.footer());
    }

    /** Accès direct par l'URL : on affiche simplement la liste. */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

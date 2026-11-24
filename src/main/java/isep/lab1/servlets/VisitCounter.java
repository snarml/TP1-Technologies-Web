package isep.lab1.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import isep.lab1.util.Html;

/**
 * 1.2.4 (EXTRA) : compter le nombre de visites avec un cookie.
 *
 * À TOI DE LE COMPLÉTER :
 *  1. lire le cookie "visits" dans request.getCookies() (0 s'il n'existe pas),
 *  2. ajouter 1,
 *  3. renvoyer un nouveau Cookie("visits", ...) avec setMaxAge(...) via response.addCookie(),
 *  4. afficher le nombre de visites.
 */
@WebServlet("/VisitCounter")
public class VisitCounter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int visits = 0;
        // TODO : lire le cookie "visits" et l'incrémenter
        // TODO : renvoyer le cookie mis à jour (AVANT d'écrire dans "out")

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(Html.header("Compteur de visites (cookie)"));
        out.println("<p>Nombre de visites : " + visits + "</p>");
        out.println(Html.footer());
    }
}

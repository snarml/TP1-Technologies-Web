package isep.lab1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static isep.lab1.util.Html.escape;
import isep.lab1.util.Html;

/**
 * 1.1 : reçoit le formulaire (POST) et affiche les données dans un tableau HTML.
 * 1.1.3 : accessible via deux URLs : /FormServlet et /Form_Servlet.
 * 1.2.1 : ajoute 4 cookies (firstName, lastName, birthDay, sex).
 */
@WebServlet({"/FormServlet", "/Form_Servlet"})
public class FormServlet extends HttpServlet {

    private static final int ONE_DAY = 60 * 60 * 24;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String birthDay = request.getParameter("birthDay");
        String sex = request.getParameter("sex");

        // 1.2 : création des cookies (avant d'écrire le corps de la réponse !)
        addCookie(response, "firstName", firstName);
        addCookie(response, "lastName", lastName);
        addCookie(response, "birthDay", birthDay);
        addCookie(response, "sex", sex);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(Html.header("Données reçues par FormServlet"));
        out.println("<table border=\"1\" cellpadding=\"6\">");
        out.println("<tr><th>Prénom</th><th>Nom</th><th>Date de naissance</th><th>Sexe</th></tr>");
        out.println("<tr><td>" + escape(firstName) + "</td><td>" + escape(lastName) + "</td><td>"
                + escape(birthDay) + "</td><td>" + escape(sex) + "</td></tr>");
        out.println("</table>");
        out.println("<p>URL utilisée : " + escape(request.getServletPath()) + "</p>");
        out.println("<p><a href=\"ReadCookie\">Lire les cookies</a></p>");
        out.println(Html.footer());
    }

    /** Si quelqu'un tape l'URL directement (GET), on le renvoie vers le formulaire. */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("form.html");
    }

    /**
     * Les valeurs sont encodées (URLEncoder) car un cookie ne peut pas contenir
     * d'espaces, de virgules, de points-virgules... (ex. un prénom composé).
     */
    private static void addCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, URLEncoder.encode(value == null ? "" : value, StandardCharsets.UTF_8));
        cookie.setMaxAge(ONE_DAY); // sans setMaxAge : cookie de session (supprimé à la fermeture du navigateur)
        response.addCookie(cookie);
    }
}

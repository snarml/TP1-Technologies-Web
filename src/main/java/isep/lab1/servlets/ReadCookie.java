package isep.lab1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
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
 * 1.2.3 : lit les 4 cookies et affiche leurs valeurs.
 * Appelée par un lien <a href="ReadCookie"> : un lien envoie une requête GET, d'où doGet().
 */
@WebServlet("/ReadCookie")
public class ReadCookie extends HttpServlet {

    private static final String[] NAMES = {"firstName", "lastName", "birthDay", "sex"};

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(Html.header("Lecture des cookies"));

        Cookie[] cookies = request.getCookies(); // null s'il n'y a aucun cookie
        out.println("<table border=\"1\" cellpadding=\"6\"><tr><th>Cookie</th><th>Valeur</th></tr>");
        for (String name : NAMES) {
            out.println("<tr><td>" + name + "</td><td>" + escape(find(cookies, name)) + "</td></tr>");
        }
        out.println("</table>");
        out.println(Html.footer());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private static String find(Cookie[] cookies, String name) {
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(name)) {
                    return URLDecoder.decode(c.getValue(), StandardCharsets.UTF_8);
                }
            }
        }
        return "(absent)";
    }
}

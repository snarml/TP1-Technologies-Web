package isep.lab1.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import isep.lab1.util.Html;

/**
 * Servlet de test pour les listeners d'attributs (1.4.2 - 4 et 5).
 *
 *  /AttributeTest                 : ajoute, remplace et supprime un attribut d'APPLICATION et de REQUÊTE
 *  /AttributeTest?session=replace : remplace l'attribut de session "user"
 *  /AttributeTest?session=remove  : supprime l'attribut de session "user"
 *
 * Regarde ensuite la console Tomcat : chaque opération déclenche un message du listener correspondant.
 */
@WebServlet("/AttributeTest")
public class AttributeTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        StringBuilder report = new StringBuilder();
        String sessionAction = request.getParameter("session");

        if (sessionAction == null) {
            // Portée APPLICATION (ServletContext) -> ApplicationAttributeListener
            ServletContext ctx = getServletContext();
            ctx.setAttribute("testAttr", "valeur 1");     // attributeAdded
            ctx.setAttribute("testAttr", "valeur 2");     // attributeReplaced
            ctx.removeAttribute("testAttr");              // attributeRemoved
            report.append("<li>Application : add / replace / remove de testAttr</li>");

            // Portée REQUÊTE (ServletRequest) -> RequestAttributeListener
            request.setAttribute("testAttr", "valeur 1");
            request.setAttribute("testAttr", "valeur 2");
            request.removeAttribute("testAttr");
            report.append("<li>Requête : add / replace / remove de testAttr</li>");
        } else {
            // Portée SESSION -> SessionAttributeListener
            HttpSession session = request.getSession(false);
            if (session == null) {
                report.append("<li>Pas de session : connecte-toi d'abord via login.html</li>");
            } else if ("replace".equals(sessionAction)) {
                session.setAttribute("user", "utilisateur_modifie");
                report.append("<li>Session : attribut user remplacé</li>");
            } else if ("remove".equals(sessionAction)) {
                session.removeAttribute("user");
                report.append("<li>Session : attribut user supprimé</li>");
            }
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(Html.header("Test des attributs"));
        out.println("<ul>" + report + "</ul>");
        out.println("<p>Regarde les messages dans la console Tomcat.</p>");
        out.println(Html.footer());
    }
}

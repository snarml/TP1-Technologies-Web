import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet du déploiement MANUEL (sans IDE).
 * Pas d'annotation : elle est déclarée à la main dans WEB-INF/web.xml.
 *
 * Compilation (CMD, depuis le dossier deploiement-manuel\src) :
 *   javac -d "%CATALINA_HOME%\webapps\TP1Manuel\WEB-INF\classes" HelloServlet.java
 * (ça marche sans -cp grâce à CLASSPATH=.;%CATALINA_HOME%\lib\servlet-api.jar)
 */
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>Hello</title></head><body>");
        out.println("<h1>Bonjour depuis une servlet déployée manuellement !</h1>");
        out.println("<p>Heure du serveur : " + new java.util.Date() + "</p>");
        out.println("</body></html>");
    }
}

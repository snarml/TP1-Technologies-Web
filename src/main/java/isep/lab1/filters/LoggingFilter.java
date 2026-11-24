package isep.lab1.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 1.4.1 - 1 : filtre qui journalise chaque requête dans la console Tomcat.
 *
 * ÉTAPE 1 (annotation) : décommenter la ligne @WebFilter ci-dessous et RETIRER la déclaration
 *                        de LoggingFilter dans web.xml.
 * ÉTAPE 3 et suivantes (web.xml, état actuel) : annotation commentée, filtre déclaré dans web.xml
 *                        avec le paramètre d'initialisation "logPrefix".
 * Ne jamais utiliser les deux en même temps (le filtre serait enregistré deux fois).
 */
// @javax.servlet.annotation.WebFilter("/*")
public class LoggingFilter implements Filter {

    private String prefix = "[LOG]";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String p = filterConfig.getInitParameter("logPrefix"); // null avec l'annotation seule
        if (p != null) {
            prefix = p;
        }
        System.out.println(prefix + " LoggingFilter initialisé (init)");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String url = req.getRequestURL()
                + (req.getQueryString() != null ? "?" + req.getQueryString() : "");

        System.out.println(prefix + " AVANT chain.doFilter -> " + req.getMethod() + " " + url);

        // EXTRA (à toi) : mesurer le temps d'exécution avec System.nanoTime()
        // long start = System.nanoTime();

        chain.doFilter(request, response); // passe au filtre suivant ou à la servlet

        // long durationMs = (System.nanoTime() - start) / 1_000_000;
        int status = ((HttpServletResponse) response).getStatus();
        System.out.println(prefix + " APRÈS chain.doFilter <- " + url + " (statut HTTP " + status + ")");
    }

    @Override
    public void destroy() {
        System.out.println(prefix + " LoggingFilter détruit (destroy)");
    }
}

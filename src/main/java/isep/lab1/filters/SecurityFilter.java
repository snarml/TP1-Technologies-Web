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
import javax.servlet.http.HttpSession;

/**
 * 1.4.1 - 5 : protège /ListItems (mapping dans web.xml).
 * Si la session n'existe pas ou ne contient pas "user", redirige vers login.html
 * SANS appeler chain.doFilter() : la servlet ListItems n'est alors jamais exécutée.
 */
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("[SECURITY] SecurityFilter initialisé (init)");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        System.out.println("[SECURITY] AVANT : vérification de l'accès à " + req.getRequestURI());

        HttpSession session = req.getSession(false); // false : ne crée pas de session
        boolean connected = session != null && session.getAttribute("user") != null;

        if (connected) {
            System.out.println("[SECURITY] Accès AUTORISÉ pour " + session.getAttribute("user"));
            chain.doFilter(request, response);
            System.out.println("[SECURITY] APRÈS chain.doFilter");
        } else {
            System.out.println("[SECURITY] Accès REFUSÉ -> redirection vers login.html (pas de chain.doFilter)");
            res.sendRedirect(req.getContextPath() + "/login.html");
        }
    }

    @Override
    public void destroy() {
        System.out.println("[SECURITY] SecurityFilter détruit (destroy)");
    }
}

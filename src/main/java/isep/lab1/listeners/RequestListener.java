package isep.lab1.listeners;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * 1.4.2 - 3 : observe le début et la fin de chaque requête HTTP.
 * À comparer avec LoggingFilter : le listener OBSERVE seulement, il ne peut ni bloquer
 * ni modifier la requête (pas de chain.doFilter).
 */
@WebListener
public class RequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        System.out.println("[REQUEST] Requête initialisée : " + req.getMethod() + " " + req.getRequestURI());
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        System.out.println("[REQUEST] Requête terminée : " + req.getRequestURI());
    }
}

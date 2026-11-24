package isep.lab1.listeners;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 1.4.2 - 2 : compte les sessions actives et stocke le nombre dans l'attribut
 * d'application "activeSessions" (affiché par ShowSession).
 * AtomicInteger : plusieurs sessions peuvent être créées en même temps par des threads différents.
 */
@WebListener
public class ActiveSessionListener implements HttpSessionListener {

    private final AtomicInteger activeSessions = new AtomicInteger();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        int n = activeSessions.incrementAndGet();
        se.getSession().getServletContext().setAttribute("activeSessions", n);
        System.out.println("[SESSION] Session créée : " + se.getSession().getId() + " | actives = " + n);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // Garde-fou : le compteur ne descend pas sous 0 (ex. sessions restaurées après un redémarrage)
        int n = activeSessions.updateAndGet(v -> Math.max(0, v - 1));
        se.getSession().getServletContext().setAttribute("activeSessions", n);
        System.out.println("[SESSION] Session détruite : " + se.getSession().getId() + " | actives = " + n);
    }
}

package isep.lab1.listeners;

import java.io.Serializable;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * 1.4.3 - 1 (EXTRA) : objet qui est notifié LUI-MÊME quand il est ajouté à une session
 * ou retiré d'une session. Pas besoin d'annotation ni de web.xml.
 *
 * À TOI : décommenter la ligne correspondante dans LoginServlet, puis retirer l'objet
 * (session.removeAttribute("sessionUser") ou session.invalidate()) et observer la console.
 */
public class SessionUser implements HttpSessionBindingListener, Serializable {

    private static final long serialVersionUID = 1L;

    private final String username;

    public SessionUser(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("[BINDING] " + username + " ajouté à la session " + event.getSession().getId());
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("[BINDING] " + username + " retiré de la session " + event.getSession().getId());
    }

    @Override
    public String toString() {
        return "SessionUser(" + username + ")";
    }
}

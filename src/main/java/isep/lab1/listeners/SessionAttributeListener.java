package isep.lab1.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * 1.4.2 - 4 : observe les attributs de SESSION (ajout, remplacement, suppression).
 * Test : login.html (ajout de "user"), puis les liens "Modifier" / "Supprimer" affichés par LoginServlet.
 */
@WebListener
public class SessionAttributeListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("[SESSION-ATTR] Ajouté : " + event.getName() + " = " + event.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        // Attention : event.getValue() renvoie l'ANCIENNE valeur
        Object newValue = event.getSession().getAttribute(event.getName());
        System.out.println("[SESSION-ATTR] Remplacé : " + event.getName()
                + " (ancienne = " + event.getValue() + ", nouvelle = " + newValue + ")");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println("[SESSION-ATTR] Supprimé : " + event.getName() + " (valeur = " + event.getValue() + ")");
    }
}

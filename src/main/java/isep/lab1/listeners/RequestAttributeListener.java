package isep.lab1.listeners;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * 1.4.2 - 5 : observe les attributs de REQUÊTE (ServletRequest).
 * Test : /AttributeTest. Le Controller MVC (request.setAttribute("person", ...)) en déclenche aussi.
 */
@WebListener
public class RequestAttributeListener implements ServletRequestAttributeListener {

    @Override
    public void attributeAdded(ServletRequestAttributeEvent event) {
        System.out.println("[REQ-ATTR] Ajouté : " + event.getName() + " = " + event.getValue());
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent event) {
        System.out.println("[REQ-ATTR] Remplacé : " + event.getName() + " (ancienne valeur = " + event.getValue() + ")");
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent event) {
        System.out.println("[REQ-ATTR] Supprimé : " + event.getName());
    }
}

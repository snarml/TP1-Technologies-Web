package isep.lab1.listeners;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * 1.4.2 - 5 : observe les attributs d'APPLICATION (ServletContext).
 * Test : /AttributeTest. Tu verras aussi "appName" et "activeSessions", et des attributs internes de Tomcat.
 */
@WebListener
public class ApplicationAttributeListener implements ServletContextAttributeListener {

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        System.out.println("[APP-ATTR] Ajouté : " + event.getName() + " = " + event.getValue());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        System.out.println("[APP-ATTR] Remplacé : " + event.getName() + " (ancienne valeur = " + event.getValue() + ")");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        System.out.println("[APP-ATTR] Supprimé : " + event.getName());
    }
}

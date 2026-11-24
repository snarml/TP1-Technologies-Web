package isep.lab1.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 1.4.2 - 1 : réagit au démarrage et à l'arrêt de l'application.
 *
 * 1.4.2 - 6 : ce listener est déclaré dans web.xml avec <listener> (état actuel).
 * Pour la version annotation (étape 1), décommenter @WebListener et retirer le <listener> de web.xml.
 */
// @javax.servlet.annotation.WebListener
public class ApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("[APP] Application démarrée : " + sce.getServletContext().getContextPath());
        sce.getServletContext().setAttribute("appName", "MP2 Web Application");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[APP] Application arrêtée");
    }
}

package isep.lab1.util;

/** Petit utilitaire : échappe le texte saisi par l'utilisateur avant de l'écrire dans une page HTML. */
public final class Html {

    private Html() {
    }

    public static String escape(String s) {
        if (s == null) {
            return "";
        }
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }

    /** Début de page HTML commun aux servlets. */
    public static String header(String title) {
        return "<!DOCTYPE html><html lang=\"fr\"><head><meta charset=\"UTF-8\"><title>"
                + escape(title) + "</title></head><body><h1>" + escape(title) + "</h1>";
    }

    public static String footer() {
        return "<p><a href=\"index.html\">Retour à l'accueil</a></p></body></html>";
    }
}

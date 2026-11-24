<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head><meta charset="UTF-8"><title>JSP declaration</title></head>
<body>
<%-- Une déclaration <%! ... %> crée des champs / méthodes de la servlet générée à partir de la JSP --%>
<%! private int numEntries = 10;
    private int randomInt(int range) {
        return(1 + ((int)(Math.random() * range)));
    } %>

<h1>A random list from 1 to 100:</h1>
<ul>
<% for(int i=0; i<numEntries; i++) {
    out.println("<li>" + randomInt(100));
} %>
</ul>
<p><a href="index.html">Retour à l'accueil</a></p>
</body>
</html>

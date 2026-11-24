<%@ page contentType="text/html;charset=UTF-8" import="java.util.Date" %>
<!DOCTYPE html>
<html lang="fr">
<head><meta charset="UTF-8"><title>JSP scriptlet</title></head>
<body>
    <h1>2.1.2 Scriptlet JSP</h1>
    <%-- Un scriptlet <% ... %> contient du code Java exécuté à chaque requête --%>
    <%
        Date now = new Date();
        String host = request.getRemoteHost();
        out.println("<p>Heure actuelle : " + now + "</p>");
        out.println("<p>Adresse (request.getRemoteHost()) : " + host + "</p>");
    %>
    <%-- Observation pour le rapport : getRemoteHost() donne l'adresse du CLIENT ;
         l'adresse du serveur s'obtient avec request.getLocalAddr(). --%>
    <p><a href="index.html">Retour à l'accueil</a></p>
</body>
</html>

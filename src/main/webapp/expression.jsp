<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head><meta charset="UTF-8"><title>JSP expression</title></head>
<body>
    <h1>2.1.1 Expression JSP</h1>
    <%-- Une expression <%= ... %> est évaluée puis écrite directement dans la page --%>
    <p>Nombre aléatoire : <%= Math.random() %></p>
    <p><a href="index.html">Retour à l'accueil</a></p>
</body>
</html>

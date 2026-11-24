<%@ page contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%-- 2.2 : crée le bean, le remplit avec les paramètres du formulaire, puis le relit --%>
<jsp:useBean id="person" class="isep.lab1.bean.Person" scope="request" />
<%-- property="*" : chaque paramètre est copié dans la propriété du même nom --%>
<jsp:setProperty name="person" property="*" />
<!DOCTYPE html>
<html lang="fr">
<head><meta charset="UTF-8"><title>bean.jsp</title></head>
<body>
    <h1>2.2 Données lues depuis le bean Person</h1>
    <table border="1" cellpadding="6">
        <tr><th>Prénom</th><td><jsp:getProperty name="person" property="firstName" /></td></tr>
        <tr><th>Nom</th><td><jsp:getProperty name="person" property="lastName" /></td></tr>
        <tr><th>Date de naissance</th><td><jsp:getProperty name="person" property="birthDay" /></td></tr>
        <tr><th>Sexe</th><td><jsp:getProperty name="person" property="sex" /></td></tr>
    </table>
    <p><a href="index.html">Retour à l'accueil</a></p>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<%-- 3.4 VUE : n'instancie rien (type= et non class=) et ne modifie rien (seulement getProperty) --%>
<jsp:useBean id="person" type="lab.mvc.model.Person" scope="request" />
<!DOCTYPE html>
<html lang="fr">
<head><meta charset="UTF-8"><title>Né en 1990 ou après</title></head>
<body>
    <h1>Page « young » : personne née en 1990 ou après</h1>
    <ul>
        <li>Prénom : <jsp:getProperty name="person" property="firstName" /></li>
        <li>Nom : <jsp:getProperty name="person" property="lastName" /></li>
        <li>Date de naissance : <jsp:getProperty name="person" property="birthDay" /></li>
        <li>Sexe : <jsp:getProperty name="person" property="sex" /></li>
    </ul>
    <p><a href="index.html">Retour</a></p>
</body>
</html>

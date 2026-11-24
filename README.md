# TP1 : Web Technologies (Servlets, JSP, MVC, Filters & Listeners)

MP2-GL, ISIMM, 2026-2027

## Environnement

| Outil | Version |
|---|---|
| JDK | 17.0.13 |
| Apache Tomcat | 9.0.96 (API `javax.servlet`) |
| IDE | *à compléter (Eclipse / IntelliJ + version)* |
| Navigateur | *à compléter* |

## Structure

```
TP1/
├── deploiement-manuel/            # Déploiement SANS IDE (configuration manuelle)
│   ├── src/HelloServlet.java
│   └── TP1Manuel/                 # à copier dans %CATALINA_HOME%\webapps\
│       ├── index.html
│       └── WEB-INF/web.xml
├── src/main/java/
│   ├── isep/lab1/servlets/        # FormServlet, ReadCookie, VisitCounter, ShowSession,
│   │                              # ListItems, LoginServlet, LogoutServlet, AttributeTestServlet
│   ├── isep/lab1/bean/Person      # 2.2 JavaBean (bean.jsp)
│   ├── isep/lab1/filters/         # LoggingFilter, SecurityFilter
│   ├── isep/lab1/listeners/       # Application, ActiveSession, Request, SessionAttribute,
│   │                              # ApplicationAttribute, RequestAttribute, SessionUser
│   ├── isep/lab1/util/Html        # échappement HTML
│   ├── lab/mvc/model/Person       # 3. MVC : modèle
│   └── lab/mvc/controller/Controller  # 3. MVC : contrôleur
├── src/main/webapp/
│   ├── index.html                 # formulaire MVC + menu de navigation
│   ├── form.html, items.html, login.html, form-jsp.html
│   ├── expression.jsp, scriptlet.jsp, declaration.jsp, bean.jsp
│   ├── old.jsp, young.jsp         # vues MVC
│   └── WEB-INF/web.xml            # filtres + ApplicationListener
└── pom.xml                        # optionnel (import Maven)
```

## Déploiement manuel (sans IDE)

1. Copier `deploiement-manuel\TP1Manuel` dans `%CATALINA_HOME%\webapps\`.
2. Créer le dossier `%CATALINA_HOME%\webapps\TP1Manuel\WEB-INF\classes`.
3. Compiler depuis `deploiement-manuel\src` :
   `javac -d "%CATALINA_HOME%\webapps\TP1Manuel\WEB-INF\classes" HelloServlet.java`
4. Démarrer Tomcat : `"%CATALINA_HOME%\bin\startup.bat"`
5. Tester : http://localhost:8080/TP1Manuel/ puis http://localhost:8080/TP1Manuel/hello

> Si Tomcat est installé dans `C:\Program Files`, il faut ouvrir CMD **en administrateur** pour écrire dans `webapps`.

## Dans l'IDE

- **Eclipse** : créer un *Dynamic Web Project* `TP1` (Target runtime : Apache Tomcat v9.0), puis copier
  `src/main/java` et `src/main/webapp` dans le projet. Autre possibilité : *File > Import > Existing Maven Projects*.
- **IntelliJ IDEA** : ouvrir le dossier (Maven), puis *Run > Edit Configurations > Tomcat Server > Local*,
  et dans l'onglet Deployment ajouter `TP1:war exploded` avec le contexte `/TP1`.

## URLs de test (contexte `/TP1`)

| Exercice | URL |
|---|---|
| 1.1 / 1.2 | http://localhost:8080/TP1/form.html, puis `/FormServlet` et `/Form_Servlet` |
| 1.2.3 | http://localhost:8080/TP1/ReadCookie |
| 1.3.1 | http://localhost:8080/TP1/ShowSession |
| 1.3.2 | http://localhost:8080/TP1/items.html (connexion demandée) |
| 2.1 | `/expression.jsp`, `/scriptlet.jsp`, `/declaration.jsp` |
| 2.2 | http://localhost:8080/TP1/form-jsp.html |
| 3 MVC | http://localhost:8080/TP1/ (formulaire en haut) |
| 1.4.1 | `/login.html`, `/ListItems`, `/Logout` |
| 1.4.2 | `/AttributeTest`, `/AttributeTest?session=replace`, `/AttributeTest?session=remove` |

## Enregistrement des composants (une seule méthode par composant)

| Composant | Méthode |
|---|---|
| Servlets | `@WebServlet` |
| LoggingFilter, SecurityFilter | `web.xml` (l'ordre des `<filter-mapping>` donne l'ordre d'exécution) |
| ApplicationListener | `web.xml` (`<listener>`) |
| Autres listeners | `@WebListener` |

## À compléter (extras)

- [ ] `VisitCounter` : compteur de visites par cookie (1.2.4)
- [ ] `ShowSession` : nombre de jours jusqu'à l'anniversaire (1.3.1 extra)
- [ ] `ListItems` : nombre de saisies de chaque item (1.3.2 extra)
- [ ] `LoggingFilter` : temps d'exécution avec `System.nanoTime()`
- [ ] `LoginServlet` : stocker un `SessionUser` (1.4.3)

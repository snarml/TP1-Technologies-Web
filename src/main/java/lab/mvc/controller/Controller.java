package lab.mvc.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.mvc.model.Person;

/**
 * 3.3 : CONTRÔLEUR de l'application MVC.
 *  1. lit les données du formulaire (index.html),
 *  2. crée le bean Person (modèle),
 *  3. le place dans la requête (request.setAttribute),
 *  4. transmet la requête à old.jsp (né avant 1990) ou young.jsp (vue).
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {

    private static final Pattern YEAR = Pattern.compile("(\\d{4})");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Person person = new Person(
                request.getParameter("firstName"),
                request.getParameter("lastName"),
                request.getParameter("birthDay"),
                request.getParameter("sex"));

        request.setAttribute("person", person);

        String page = bornBefore1990(person.getBirthDay()) ? "/old.jsp" : "/young.jsp";

        // Avec include(), la JSP incluse ne peut pas modifier les en-têtes :
        // le type de contenu doit donc être fixé ici, par le contrôleur.
        response.setContentType("text/html;charset=UTF-8");

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.include(request, response);
        // À comparer (observation pour le rapport) : dispatcher.forward(request, response);
    }

    /** Accepte "mm/dd/yyyy" comme demandé dans le TP (et aussi "yyyy-mm-dd"). */
    private static boolean bornBefore1990(String birthDay) {
        if (birthDay == null) {
            return false;
        }
        Matcher m = YEAR.matcher(birthDay);
        return m.find() && Integer.parseInt(m.group(1)) < 1990;
    }
}

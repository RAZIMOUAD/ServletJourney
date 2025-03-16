package org.example.formservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class FormServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();


        out.println("<!DOCTYPE html>");
        out.println("<html lang='fr'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Confirmation - MonProjetJEE</title>");
        out.println("<link rel='stylesheet' href='css/style.css'>");
        out.println("<link rel='preconnect' href='https://fonts.googleapis.com'>");
        out.println("<link rel='preconnect' href='https://fonts.gstatic.com' crossorigin>");
        out.println("<link href='https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap' rel='stylesheet'>");
        out.println("</head>");
        out.println("<body>");

        // En-tête avec navigation et section hero
        out.println("<header>");
        out.println("  <nav class='navbar'>");
        out.println("    <div class='container'>");
        out.println("      <a href='index.html' class='logo'>MonProjetJEE</a>");
        out.println("      <ul class='nav-links'>");
        out.println("        <li><a href='index.html'>Accueil</a></li>");
        out.println("        <li><a href='Form.html'>Inscription</a></li>");
        out.println("      </ul>");
        out.println("    </div>");
        out.println("  </nav>");
        out.println("  <div class='hero'>");
        out.println("    <div class='hero-content'>");
        out.println("      <h1>Confirmation</h1>");
        out.println("      <p>Your registration has been successfully processed.</p>");
        out.println("    </div>");
        out.println("  </div>");
        out.println("</header>");

        // Section principale de confirmation
        out.println("<main>");
        out.println("  <div class='container form-container'>");
        out.println("    <h2>Thank you " + request.getParameter("civility") + " " +  request.getParameter("firstname") + " " +  request.getParameter("lastname") + "!</h2>");
        out.println("    <p>We have received your registration details.</p>");
        out.println("    <p><strong>Email:</strong> " +  request.getParameter("email") + "</p>");
        out.println("    <a href='index.html' class='btn'>Return to Home</a>");
        out.println("  </div>");
        out.println("</main>");

        // Pied de page
        out.println("<footer>");
        out.println("  <div class='container'>");
        out.println("    <p>&copy; 2025 MonProjetJEE. Tous droits réservés.</p>");
        out.println("  </div>");
        out.println("</footer>");

        out.println("</body>");
        out.println("</html>");
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

package register;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ticket")
public class TicketServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final static String query = "SELECT * FROM reserve";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html>");
        response.getWriter().println("<head>");
        response.getWriter().println("<meta charset='UTF-8'>");
        response.getWriter().println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        response.getWriter().println("<title>User Booking</title>");
        response.getWriter().println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
        response.getWriter().println("<link rel='stylesheet' type='text/css' href='css/edit.css'>");
        response.getWriter().println("</head>");
        response.getWriter().println("<body>");

        // Navbar
        response.getWriter().println("<nav class='navbar navbar-expand-lg navbar-light bg-light'>");
        response.getWriter().println("    <img src='images/logo.png' alt='Logo' height='75' width='75'>");
        response.getWriter().println("    <button class='navbar-toggler' type='button' data-toggle='collapse' data-target='#navbarNav' aria-controls='navbarNav' aria-expanded='false' aria-label='Toggle navigation'>");
        response.getWriter().println("        <span class='navbar-toggler-icon'></span>");
        response.getWriter().println("    </button>");
        response.getWriter().println("    <div class='collapse navbar-collapse justify-content-end' id='navbarNav'>");
        response.getWriter().println("        <ul class='navbar-nav'>");
        response.getWriter().println("            <li class='nav-item active'>");
        response.getWriter().println("                <a class='nav-link' href='UserHome.html'>Home</a>");
        response.getWriter().println("            </li>");
        response.getWriter().println("            <li class='nav-item active'>");
        response.getWriter().println("                <a class='nav-link' href='About.html'>About</a>");
        response.getWriter().println("            </li>");
        response.getWriter().println("            <li class='nav-item active'>");
        response.getWriter().println("                <a class='nav-link' href='Contact.html'>Contact</a>");
        response.getWriter().println("            </li>");
        response.getWriter().println("        </ul>");
        response.getWriter().println("    </div>");
        response.getWriter().println("</nav>");

        // Table
        response.getWriter().println("<div class='table-container'>");
        response.getWriter().println("<h2>Bookings:</h2>");
        response.getWriter().println("<div class='table-responsive'>");
        response.getWriter().println("<table class='table table-striped table-bordered'>");
        response.getWriter().println("<thead>");
        response.getWriter().println("<tr>");
        response.getWriter().println("<th>PNR No.</th>");
        response.getWriter().println("<th>Source</th>");
        response.getWriter().println("<th>Destination</th>");
        response.getWriter().println("<th>Class</th>");
        response.getWriter().println("<th>Journey Date</th>");
        response.getWriter().println("<th>Number of Passengers</th>");
        response.getWriter().println("</tr>");
        response.getWriter().println("</thead>");
        response.getWriter().println("<tbody>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Reservation", "root", "2580");
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                response.getWriter().println("<tr>");
                response.getWriter().println("<td>" + rs.getInt(1) + "</td>");
                response.getWriter().println("<td>" + rs.getString(2) + "</td>");
                response.getWriter().println("<td>" + rs.getString(3) + "</td>");
                response.getWriter().println("<td>" + rs.getString(4) + "</td>");
                response.getWriter().println("<td>" + rs.getString(5) + "</td>");
                response.getWriter().println("<td>" + rs.getString(6) + "</td>");
            }
        } catch (SQLException se) {
            response.getWriter().println("<h2 class='bg-danger text-light text-center'>" + se.getMessage() + "</h2>");
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.getWriter().println("</tbody>");
        response.getWriter().println("</table>");
        response.getWriter().println("</div>");
        response.getWriter().println("<button class='button' style='background-color: black; color: white; display: block; margin: 0 auto; width: 150px; height: 40px;' onclick=\"window.location.href='UserHome.html'\">Back</button>");
        response.getWriter().println("</div>");
        response.getWriter().println("</div>");

        // Footer
        response.getWriter().println("<footer style='background-color: black; color: white; text-align: center;'>");
        response.getWriter().println("&copy; 2023 Your Company Name");
        response.getWriter().println("</footer>");

        // Close the HTML document
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

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

@WebServlet("/updatetrain")
public class UpdateTrainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final static String query = "SELECT * FROM train";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html>");
        response.getWriter().println("<head>");
        response.getWriter().println("<meta charset='UTF-8'>");
        response.getWriter().println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        response.getWriter().println("<title>Admin Train Management</title>");
        response.getWriter().println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
        response.getWriter().println("<link rel='stylesheet' type='text/css' href='css/edit.css'>");
        response.getWriter().println("</head>");
        response.getWriter().println("<body>");
        
        //NavBar
        response.getWriter().println("<nav class='navbar navbar-expand-lg navbar-light bg-light'>");
        response.getWriter().println("    <img src='images/logo.png' alt='Logo' height='75' width='75'>");
        response.getWriter().println("    <button class='navbar-toggler' type='button' data-toggle='collapse' data-target='#navbarNav' aria-controls='navbarNav' aria-expanded='false' aria-label='Toggle navigation'>");
        response.getWriter().println("        <span class='navbar-toggler-icon'></span>");
        response.getWriter().println("    </button>");
        response.getWriter().println("    <div class='collapse navbar-collapse justify-content-end' id='navbarNav'>");
        response.getWriter().println("        <ul class='navbar-nav'>");
        response.getWriter().println("            <li class='nav-item active'>");
        response.getWriter().println("                <a class='nav-link' href='AdminHome.html'>Home</a>");
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
        response.getWriter().println("<h2>Train Information</h2>");
        response.getWriter().println("<div class='table-responsive'>");
        response.getWriter().println("<table class='table table-striped table-bordered'>");
        response.getWriter().println("<thead>");
        response.getWriter().println("<tr>");
        response.getWriter().println("<th>Train ID</th>");
        response.getWriter().println("<th>Train Name</th>");
        response.getWriter().println("<th>Departure Time</th>");
        response.getWriter().println("<th>Arrival Time</th>");
        response.getWriter().println("<th>Source</th>");
        response.getWriter().println("<th>Destination</th>");
        response.getWriter().println("<th>1A</th>");
        response.getWriter().println("<th>2A</th>");
        response.getWriter().println("<th>3A</th>");
        response.getWriter().println("<th>3E</th>");
        response.getWriter().println("<th>SL</th>");
        response.getWriter().println("<th>Action</th>");
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
                response.getWriter().println("<td>" + rs.getString(7) + "</td>");
                response.getWriter().println("<td>" + rs.getString(8) + "</td>");
                response.getWriter().println("<td>" + rs.getString(9) + "</td>");
                response.getWriter().println("<td>" + rs.getString(10) + "</td>");
                response.getWriter().println("<td>" + rs.getString(11) + "</td>");
                response.getWriter().println("<td class='actions'>");
                response.getWriter().println("<button class='button'><a href='editurl?trn_no=" + rs.getInt(1) + "'>Edit</a></button>");
                response.getWriter().println("<button class='button'><a href='delete?trn_no=" + rs.getInt(1) + "'>Delete</a></button>");
                response.getWriter().println("</td>");
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
        response.getWriter().println("<br>");
        response.getWriter().println("<div class='centered-button'>");
        response.getWriter().println("<button class='button' onclick=\"window.location.href='AdminHome.html'\">Back</button>");
        response.getWriter().println("</div>");
        response.getWriter().println("</div>");

        //Footer
        response.getWriter().println("<footer>");
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

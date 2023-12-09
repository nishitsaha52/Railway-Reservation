package register;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Define the query to fetch train data
        String query = "SELECT * FROM train WHERE origin = ? OR dest = ?";

        String origin = request.getParameter("origin");
        String dest = request.getParameter("dest");
        try {
            // Load the database driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Reservation", "root", "2580");

            // Create a PreparedStatement for the query
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, origin);
            preparedStatement.setString(2, dest);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Create an HTML response
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            // Adding a basic navigation bar
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>Search Results</title>");
            out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
            out.println("<link rel='stylesheet' type='text/css' href='css/edit.css'>");
            out.println("</head>");
            out.println("<body>");

            // NavBar (similar to UpdateTrainServlet)
            out.println("<nav class='navbar navbar-expand-lg navbar-light bg-light'>");
            out.println("    <img src='images/logo.png' alt='Logo' height='75' width='75'>");
            out.println("    <button class='navbar-toggler' type='button' data-toggle='collapse' data-target='#navbarNav' aria-controls='navbarNav' aria-expanded='false' aria-label='Toggle navigation'>");
            out.println("        <span class='navbar-toggler-icon'></span>");
            out.println("    </button>");
            out.println("    <div class='collapse navbar-collapse justify-content-end' id='navbarNav'>");
            out.println("        <ul class='navbar-nav'>");
            out.println("            <li class='nav-item active'>");
            out.println("                <a class='nav-link' href='AdminHome.html'>Home</a>");
            out.println("            </li>");
            out.println("            <li class='nav-item active'>");
            out.println("                <a class='nav-link' href='About.html'>About</a>");
            out.println("            </li>");
            out.println("            <li class='nav-item active'>");
            out.println("                <a class='nav-link' href='Contact.html'>Contact</a>");
            out.println("            </li>");
            out.println("        </ul>");
            out.println("    </div>");
            out.println("</nav>");

            // Content container (similar to UpdateTrainServlet)
            out.println("<div class='table-container'>");
            out.println("<h2>Search Results</h2>");
            out.println("<div class='table-responsive'>");
            out.println("<table class='table table-striped table-bordered'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Train No.</th>");
            out.println("<th>Train Name</th>");
            out.println("<th>Source</th>");
            out.println("<th>Destination</th>");
            out.println("<th>Departure Time</th>");
            out.println("<th>Arrival Time</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            while (resultSet.next()) {
                String trn_no = resultSet.getString("tName");
                String tName = resultSet.getString("tName");
                String source = resultSet.getString("origin");
                String destin = resultSet.getString("dest");
                String dTime = resultSet.getString("dTime");
                String aTime = resultSet.getString("aTime");

                out.println("<tr><td>" + trn_no + "</td><td>" + tName + "</td><td>" + source + "</td><td>" + destin + "</td><td>" + dTime + "</td><td>" + aTime + "</td></tr>");
            }
            out.println("</tbody>");
            out.println("</table>");
            out.println("<br>");

            // Styled Back button in the middle
            out.println("<div class='centered-button'>");
            out.println("<button class='button' onclick=\"window.location.href='SearchTrain.html'\">Back</button>");
            out.println("</div>");

            out.println("</div>");

            // Footer (similar to UpdateTrainServlet)
            out.println("<footer>");
            out.println("&copy; 2023 Your Company Name");
            out.println("</footer>");

            // Close the HTML document
            out.println("</body>");
            out.println("</html>");

            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

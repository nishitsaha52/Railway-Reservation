package register;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cancel")
public class CancelServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        // Retrieve the PNR number from the request
		String pnr = request.getParameter("pnr-number");

        // Database connection variables
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Create a database connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Reservation", "root", "2580");

            // Prepare the SQL statement to delete the record
            String deleteQuery = "DELETE FROM reserve WHERE pnr=?";
            stmt = conn.prepareStatement(deleteQuery);
            stmt.setString(1, pnr);

            // Execute the delete statement
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                // Deletion successful
                response.getWriter().println("Booking with PNR number " + pnr + " has been canceled.");
            } else {
                // No matching record found
                response.getWriter().println("No booking found with PNR number " + pnr);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred while canceling the booking.");
        } finally {
            // Close database resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

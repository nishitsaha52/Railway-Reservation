package register;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/addtrain")
public class AddTrainsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tName = request.getParameter("tName");
        String origin = request.getParameter("origin");
        String dest = request.getParameter("dest");
        String aTime = request.getParameter("aTime");
        String dTime = request.getParameter("dTime");
        String class1A = request.getParameter("1A");
        String class2A = request.getParameter("2A");
        String class3A = request.getParameter("3A");
        String class3E = request.getParameter("3E");
        String classSL = request.getParameter("SL");
        RequestDispatcher dispatcher = null;
        Connection con = null;

        if (!isValidTimeFormat(aTime)) {
            request.setAttribute("timeError", "Invalid time format for Arrival Time (HH:mm:ss)");
            dispatcher = request.getRequestDispatcher("AdminHome.html");
            dispatcher.forward(request, response);
            return;
        }
        if (!isValidTimeFormat(dTime)) {
            request.setAttribute("timeError", "Invalid time format for Departure Time (HH:mm:ss)");
            dispatcher = request.getRequestDispatcher("AdminHome.html");
            dispatcher.forward(request, response);
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Reservation", "root", "2580");
            PreparedStatement pst = con.prepareStatement("INSERT INTO train (tName, origin, dest, aTime, dTime, 1A, 2A, 3A, 3E, SL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, tName);
            pst.setString(2, origin);
            pst.setString(3, dest);
            pst.setString(4, aTime);
            pst.setString(5, dTime);
            pst.setString(6, class1A);
            pst.setString(7, class2A);
            pst.setString(8, class3A);
            pst.setString(9, class3E);
            pst.setString(10, classSL);

            int rowCount = pst.executeUpdate();
            dispatcher = request.getRequestDispatcher("AdminHome.html");
            if (rowCount > 0) {
                request.setAttribute("status", "success");
            } else {
                request.setAttribute("status", "failed");
            }
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isValidTimeFormat(String time) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            sdf.setLenient(false);
            sdf.parse(time);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}

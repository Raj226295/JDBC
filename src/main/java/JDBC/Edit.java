package JDBC;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/Edit")
public class Edit extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/employee", "root", "");

            PreparedStatement ps =
                con.prepareStatement("SELECT * FROM empData WHERE empid=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pw.println("<h2>Edit Employee</h2>");
                pw.println("<form action='Update' method='post'>");

                pw.println("<input type='hidden' name='id' value='" + rs.getInt("empid") + "'>");

                pw.println("Name:<br>");
                pw.println("<input type='text' name='username' value='" + rs.getString("empname") + "'><br><br>");

                pw.println("Mobile:<br>");
                pw.println("<input type='text' name='mobileno' value='" + rs.getString("mobileno") + "'><br><br>");

                pw.println("<input type='submit' value='Update'>");
                pw.println("</form>");
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

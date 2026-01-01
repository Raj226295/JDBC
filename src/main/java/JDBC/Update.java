package JDBC;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

@WebServlet("/Update")
public class Update extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("ename");
        String mobile = request.getParameter("mobile");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/employee", "root", "");

            PreparedStatement ps = con.prepareStatement(
                "UPDATE empData SET empName=?, mobileno=? WHERE empid=?");

            ps.setString(1, name);
            ps.setString(2, mobile);
            ps.setInt(3, id);

            ps.executeUpdate();
            con.close();

            response.sendRedirect("Select");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

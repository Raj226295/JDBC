package JDBC;

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

/**
 * Servlet implementation class EditSelect
 */
@WebServlet("/EditSelect")
public class EditSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditSelect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		response.setContentType("Text/html");
		PrintWriter pw=response.getWriter();
		int id=Integer.parseInt(request.getParameter("id"));
		try
		{
			
			//load driver
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","");
			
			PreparedStatement ps=con.prepareStatement("select * from employee where empid=?");
			ResultSet rs=ps.executeQuery();
			rs.next();
			pw.println("<form action ='EditSelect?id="+ id+ "'method='post'>");
			
			pw.println("<table>");
			pw.println("<tr>");
			
			
			
			pw.println("<td> <input type ='text' name='username' value='"+rs.getString(1)+"'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td> <input type ='text' name='mobileno' value='"+rs.getString(2)+"'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td><button type='submit 'name='edit'>Edit</button></td>");
			pw.println("</table>");
			pw.println("<button type='submit' name=' Showuser' ><a href ='Select'>Showuser</a>");
			
		}
		catch(SQLException e) {
			e.getStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
	}
		pw.println("<button type='submit' name=' ShowuUser' ><a href ='index.jsp'>ShowUser</a>");
	}
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

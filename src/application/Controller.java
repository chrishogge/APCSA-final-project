package application;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hospital.Doctor;
import hospital.DoctorDAO;

public class Controller extends HttpServlet {

	  private static final long serialVersionUID = 1L;
	  private DoctorDAO dao;
	  
	  public void init()
	  {
	    final String url = "jdbc:mysql://localhost:3306/library?serverTimezone=EST";
	    final String username = "root";
	    final String password = "rootpwd";
	    
	    dao = new DoctorDAO(url, username, password);
	  }
	  
	  @Override
	  protected void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException
	  {
	    doGet(request, response);
	  }
	  
	  @Override
	  protected void doGet(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException
	  {
	    final String action = request.getServletPath();
	    
	    try {
	      switch (action) {
	        default:
	          viewBooks(request, response);
	          break;
	      }
	    } catch (SQLException e) {
	      throw new ServletException(e);
	    }
	  }
	  
	  private void viewBooks(HttpServletRequest request, HttpServletResponse response)
	      throws SQLException, ServletException, IOException
	  {
	    List<Doctor> doctors = dao.getDoctors();
	    request.setAttribute("doctors", doctors);
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("doctors.jsp");
	    dispatcher.forward(request, response);
	  }
	
}

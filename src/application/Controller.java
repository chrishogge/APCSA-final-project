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
		final String url = getServletContext().getInitParameter("JDBC-URL");
		final String username = getServletContext().getInitParameter("JDBC-USERNAME");
		final String password = getServletContext().getInitParameter("JDBC-PASSWORD");
	    
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
		      case "/update":
		          updateDoctor(request, response);
		          break;
	        default:
	          viewDoctors(request, response);
	          break;
	      }
	    } catch (SQLException e) {
	      throw new ServletException(e);
	    }
	  }
	  
	  private void viewDoctors(HttpServletRequest request, HttpServletResponse response)
	      throws SQLException, ServletException, IOException
	  {
	    List<Doctor> doctors = dao.getDoctors();
	    request.setAttribute("doctors", doctors);
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("doctors.jsp");
	    dispatcher.forward(request, response);
	  }
	  
	  private void updateDoctor(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, ServletException, IOException
			{	
			  final String action = request.getParameter("action");
			  final int id = Integer.parseInt(request.getParameter("doc_id"));
			  
			  Doctor doctor = dao.getDoctor(id);
			  switch (action) {
			    case "active":
			      doctor.setActive(true);
			      break;
			    case "inactive":
			      doctor.setActive(false);
			      break;
			  }
			  dao.updateDoctor(doctor);
			  
			  response.sendRedirect(request.getContextPath() + "/");
			}
	
}

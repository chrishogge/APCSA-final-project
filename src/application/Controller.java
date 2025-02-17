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
	      	  case "/add":
		      case "/edit":
			      showEditForm(request, response);
			    	break;
		      case "/insert":
		    	  insertDoctor(request,response);
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
			  final String action = request.getParameter("action") != null
			    ? request.getParameter("action")
			    : request.getParameter("submit").toLowerCase();
			  final int id = Integer.parseInt(request.getParameter("doc_id"));
				
			  Doctor doctor = dao.getDoctor(id);
			  switch (action) {
			    case "active":
			      doctor.setActive(true);
			      break;
			    case "inactive":
			      doctor.setActive(false);
			      break;
			    case "save":
			      String first_name = request.getParameter("first_name");
			      String last_name = request.getParameter("last_name");
			      int department_id = Integer.parseInt(request.getParameter("department_id"));
					
			      doctor.setfirst_name(first_name);
			      doctor.setlast_name(last_name);
			      doctor.setdepartment_id(department_id);
			      break;
			    case "delete":
			      deleteDoctor(id, request, response);
			      return;
			    }

			    dao.updateDoctor(doctor);
			    response.sendRedirect(request.getContextPath() + "/");
			  }
	  
	  private void deleteDoctor(final int id, HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, ServletException, IOException
			{	
			  dao.deleteDoctor(dao.getDoctor(id));	
			  response.sendRedirect(request.getContextPath() + "/");
			}
	  
	  private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, ServletException, IOException
			{
			  try {
			    final int id = Integer.parseInt(request.getParameter("doc_id"));
			    
			    Doctor doctor = dao.getDoctor(id);
			    request.setAttribute("doctor", doctor);
			  } finally {
			    RequestDispatcher dispatcher = request.getRequestDispatcher("doctorform.jsp");
			    dispatcher.forward(request, response);
			  }
			}
	  
	  private void insertDoctor(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, ServletException, IOException
			{
			  String first_name = request.getParameter("first_name");
			  String last_name = request.getParameter("last_name");
			  int department_id = Integer.parseInt(request.getParameter("department_id"));
			  int ssn = Integer.parseInt(request.getParameter("ssn"));
			  
			  dao.insertDoctor(ssn, first_name, last_name, department_id);
			  response.sendRedirect(request.getContextPath() + "/");
			}
	
}

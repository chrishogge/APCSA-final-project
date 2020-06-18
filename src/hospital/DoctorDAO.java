package hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

	  private final String url;
	  private final String username;
	  private final String password;
	  
	  public DoctorDAO(String url, String username, String password)
	  {
	    super();
	    
	    this.url = url;
	    this.username = username;
	    this.password = password;
	  }
	  
	  public Doctor getDoctor(int doc_id) throws SQLException
	  {
	    final String sql = "SELECT * FROM doctors WHERE doc_id = ?";
	    
	    Doctor doctor = null;
	    Connection conn = getConnection();
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    
	    pstmt.setInt(1, doc_id);
	    ResultSet rs = pstmt.executeQuery();
	    
	    if (rs.next()) {
	      String first_name = rs.getString("first_name");
	      String last_name = rs.getString("last_name");
	      int ssn = rs.getInt("ssn");
	      int department_id = rs.getInt("department_id");
	      boolean active = rs.getBoolean("active");
	      
	      doctor = new Doctor(doc_id, ssn, first_name, last_name, department_id, active);
	    }
	    
	    rs.close();
	    pstmt.close();
	    conn.close();
	    
	    return doctor;
	  }
	  
	  public List<Doctor> getDoctors() throws SQLException
	  {
	    final String sql = "SELECT * FROM doctors ORDER BY doc_id ASC";
	    
	    List<Doctor> doctors = new ArrayList<>();
	    Connection conn = getConnection();
	    Statement stmt = conn.createStatement();
	    ResultSet rs = stmt.executeQuery(sql);
	    
	    while (rs.next()) {
	      int id = rs.getInt("doc_id");
	      String first_name = rs.getString("first_name");
	      String last_name = rs.getString("last_name");
	      int ssn = rs.getInt("ssn");
	      int department_id = rs.getInt("department_id");
	      boolean active = rs.getBoolean("active");
	      
	      doctors.add(new Doctor(id, ssn, first_name, last_name, department_id, active));
	    }
	    
	    rs.close();
	    stmt.close();
	    conn.close();
	    
	    return doctors;
	  }
	  
	  public boolean insertDoctor(int ssn, String first_name, String last_name, int department_id) throws SQLException
	  {
	    final String sql = "INSERT INTO doctors (ssn, first_name, last_name, department_id) " +
	        "VALUES (?, ?, ?, ?)";
	    
	    Connection conn = getConnection();
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    
	    pstmt.setString(2, first_name);
	    pstmt.setString(3, last_name);
	    pstmt.setInt(1, ssn);
	    pstmt.setInt(4, department_id);
	    int affected = pstmt.executeUpdate();
	    
	    pstmt.close();
	    conn.close();
	    
	    return affected == 1;
	  }
	  
	  public boolean updateDoctor(Doctor doctor) throws SQLException
	  {
	    final String sql = "UPDATE doctors SET first_name = ?, last_name = ?, ssn = ?, department_id = ? " +
	        "WHERE doc_id = ?";
	    
	    Connection conn = getConnection();
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    
	    pstmt.setString(1, doctor.getfirst_name());
	    pstmt.setString(2, doctor.getlast_name());
	    pstmt.setInt(3, doctor.getssn());
	    pstmt.setInt(4, doctor.getdepartment_id());
	    pstmt.setInt(5, doctor.getDoc_id());
	    int affected = pstmt.executeUpdate();
	    
	    pstmt.close();
	    conn.close();
	    
	    return affected == 1;
	  }
	  
	  public boolean deleteDoctor(Doctor doctor) throws SQLException
	  {
	    final String sql = "DELETE FROM doctors WHERE doc_id = ?";
	    
	    Connection conn = getConnection();
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    
	    pstmt.setInt(1, doctor.getDoc_id());
	    int affected = pstmt.executeUpdate();
	    
	    pstmt.close();
	    conn.close();
	    
	    return affected == 1;
	  }
	  
	  private Connection getConnection() throws SQLException
	  {
	    final String driver = "com.mysql.cj.jdbc.Driver";
	    
	    try {
	      Class.forName(driver);
	    } catch (ClassNotFoundException e) {
	      e.printStackTrace();
	    }
	    
	    return DriverManager.getConnection(url, username, password);
	  }
	
}

package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {
	public static void main(String[] args) throws SQLException
	  {
	    final String url = "jdbc:mysql://localhost:3306/Hospital?serverTimezone=EST";
	    final String username = "root";
	    final String password = "rootpwd";

	    Connection conn = DriverManager.getConnection(url, username, password);
	    Statement stmt = conn.createStatement();
	    ResultSet rs = stmt.executeQuery("SELECT * FROM doctors");

	    System.out.println("Doctors in the Hospital:\n");
	    while (rs.next()) {
	      String title = rs.getString("first_name");
	      String author = rs.getString("last_name");
	      int copies = rs.getInt("department_id");
	      int available = rs.getInt("doc_id");

	      System.out.println(" --> " + title + " by " + author + " (" + available + " of " + copies + ")");
	    }
	    
	    rs.close();
	    stmt.close();
	    conn.close();
	  }
}

package hospital;

public class Doctor {
	  private int doc_id;
	  private int ssn;
	  private String first_name;
	  private String last_name;
	  private int department_id;

	  public Doctor(int doc_id, int ssn, String first_name, String last_name, int department_id)
	  {
	    super();

	    this.doc_id = doc_id;
	    this.first_name = first_name;
	    this.last_name = last_name;
	    this.ssn = ssn;
	    this.department_id = department_id;
	  }
	  
	  public int getDoc_id()
	  {
	    return doc_id;
	  }

	  public String getfirst_name()
	  {
	    return first_name;
	  }
	    
	  public String getlast_name()
	  {
	    return last_name;
	  }

	  public int getssn()
	  {
	    return ssn;
	  }
	    
	  public void setssn(int ssn)
	  {
	    this.ssn = ssn;
	  }
	    
	  public int getdepartment_id()
	  {
	    return department_id;
	  }
	    
	  public void setdepartment_id(int department_id)
	  {
	    this.department_id = department_id;
	  }
}

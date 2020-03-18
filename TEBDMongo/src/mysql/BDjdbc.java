package mysql;

import java.sql.Connection;
import java.sql.DriverManager;

public class BDjdbc extends BD implements java.io.Serializable {
	  static BDjdbc bdjdbc= new BDjdbc();

	  private String dataSource;


	  public BDjdbc() {
	    
	  }
	  
	  public void setDataSource(String dataSource){
	  	this.dataSource = dataSource;
	  }

	  public void setConexao() throws
	    Exception {
	      try {
    	  	String driver = "com.mysql.jdbc.Driver";

	 		 
	 		 Class.forName(driver);

	 		 String url = "jdbc:mysql://127.0.0.1:3306/tebd";

	 		
	 		 this.conn = DriverManager.getConnection(url, "root", "password" );
	 		 
	          conn.setAutoCommit(false);
	       }catch (Exception e){
	           System.out.println(e);
	          throw  e;
	      }
	  }
  

	  

	public String toString (){
		return "Login = " + this.login + " Senha = " + this.senha;
	}

	public static void main(String args[])throws Exception {
		
		// BDjdbc conexao = new BDjdbc();
	     
	     
	        String driver = "com.mysql.jdbc.Driver";

	    		 
	       	Class.forName(driver);

	    		String url = "jdbc:mysql://127.0.0.1:3306/tebd";

	    		
	    		 Connection c= DriverManager.getConnection(url, "root", "password" );
	    		 System.out.println(c.getCatalog());

	     
	     //conexao.setDataSource(url);
	     //conexao.setConexao();
	     //conexao.getConexao().setCatalog("sae");
	    // System.out.println(conexao.getConexao().getCatalog());
	  //   conexao.setDataSource("jdbc:interbase://localhost/"+
	    //           Persistencia.CAMINHO+"Noticia.gdb");  //d:\\IPT-Web\\escola\\
		
	}
}
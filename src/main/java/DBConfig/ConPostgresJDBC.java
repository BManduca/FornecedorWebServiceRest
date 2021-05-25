package DBConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConPostgresJDBC implements ConnectionJDBC{
	
	private Connection con = null;
	
	public ConPostgresJDBC() throws ClassNotFoundException, SQLException {
		
		Class.forName("org.postgresql.Driver");
		
		Properties prop = new Properties();
		prop.put("user", "postgres");
		prop.put("password", "manduca");
		
		this.con = DriverManager.getConnection("jdbc:postgresql://localhost/DB_Fornecedores", prop);
		this.con.setAutoCommit(false);
		
	}
	
	@Override
	public Connection getCon() {
		return this.con;
	}
	
	@Override
	public void close() {
		
		if(this.con != null) {
			try {
				this.con.close();
			} catch (SQLException e) {
				Logger.getLogger(ConPostgresJDBC.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		
	}
	
	@Override
	public void commit() throws SQLException {
		
		this.con.commit();
		this.close();
		
	}
	
	@Override
	public void rollback() {
		
		if(this.con != null) {
			try {
				this.con.rollback();
			} catch (SQLException e) {
				Logger.getLogger(ConPostgresJDBC.class.getName()).log(Level.SEVERE, null, e);
			} finally {
				this.close();
			}
		}
		
	}
	
}

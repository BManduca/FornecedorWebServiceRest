package DBConfig;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionJDBC {

	public Connection getCon();
	
	public void close();
	
	public void commit() throws SQLException;
	
	public void rollback();
	
}

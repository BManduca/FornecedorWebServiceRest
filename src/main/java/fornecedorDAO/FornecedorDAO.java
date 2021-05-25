package fornecedorDAO;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.brunno.provider.webservice.Fornecedor;

import DBConfig.ConPostgresJDBC;
import DBConfig.ConnectionJDBC;

public class FornecedorDAO {
	
	private final ConnectionJDBC con;
	
	public FornecedorDAO() throws SQLException, ClassNotFoundException {
		this.con = new ConPostgresJDBC();
	}
	
	
	public int add(Fornecedor provider) throws SQLException, ClassNotFoundException {
		int idProvider = 0;
		
		String sql = "INSERT INTO providers (name, email, comment, cnpj) VALUES (?, ?, ?, ?) RETURNING idProvider";
		
		try {
			PreparedStatement prepstate = this.con.getCon().prepareStatement(sql);
			prepstate.setString(1, provider.getNameFornec());
			prepstate.setString(2, provider.getEmailFornec());
			prepstate.setString(3, provider.getComment());
			prepstate.setString(4, provider.getCnpjFornec());
			
			ResultSet resset = prepstate.executeQuery();
			
			if (resset.next()) {
				idProvider = resset.getInt("idProvider");
			}
			
			this.con.commit();
			
		} catch (SQLException e) {
			this.con.rollback();
			throw e;
		}
		
		return idProvider;
		
	}
	
	public int alter(Fornecedor provider) throws SQLException, ClassNotFoundException {
		
		String sql = "UPDATE providers SET name = ?, email = ?, comment = ?, cnpj = ? WHERE ID = ?";
		
		int changeLines = 0;
		
		try {
			PreparedStatement prepstate = this.con.getCon().prepareStatement(sql);
			prepstate.setString(1, provider.getNameFornec());
			prepstate.setString(2, provider.getEmailFornec());
			prepstate.setString(3, provider.getComment());
			prepstate.setString(4, provider.getCnpjFornec());
			prepstate.setInt(5, provider.getIdFornec());
			
			changeLines = prepstate.executeUpdate();
			
			
		} catch (SQLException e) {
			this.con.rollback();
			throw e;
		}
		
		return changeLines;
		
	}
	
	public List<Fornecedor> list() throws SQLException, ClassNotFoundException {
		
		String sql = "SELECT ID, name, email, comment, cnpj FROM providers";
		
		try {
			PreparedStatement prepstate = this.con.getCon().prepareStatement(sql);
			ResultSet resset = prepstate.executeQuery();
			
			List<Fornecedor> providers = new ArrayList<Fornecedor>();
			
			while (resset.next()) {
				providers.add(parser(resset));
				
			}
			
			return providers;
			
		} catch (SQLException e) {
			throw e;
		}
		
	}
	
	public int delete(int ID) throws SQLException, ClassNotFoundException {
		int changeLines = 0;
		
		String sql = "DELETE FROM providers WHERE ID = ?";
		
		try {
			PreparedStatement prepstate = this.con.getCon().prepareStatement(sql);
			prepstate.setInt(1, ID);
			changeLines = prepstate.executeUpdate();
			this.con.commit();
		} catch (SQLException e) {
			this.con.rollback();
			throw e;
		}
		
		return changeLines;
		
	}
	
	public Fornecedor select(int ID) throws SQLException, ClassNotFoundException {
		String sql = "SELECT ID, name, email, comment, cnpj FROM providers WHERE ID = ?";
		
		try {
			PreparedStatement prepstate = this.con.getCon().prepareStatement(sql);
			prepstate.setInt(1, ID);
			ResultSet resset = prepstate.executeQuery();
			
			if (resset.next()) {
				return parser(resset);
			}
			
		} catch (SQLException e) {
			throw e;
		}
		
		return null;
		
	}
	
	private Fornecedor parser(ResultSet resset) throws SQLException {
		Fornecedor provider = new Fornecedor();
		
		provider.setIdFornec(resset.getInt("ID"));
		provider.setNameFornec(resset.getString("name"));
		provider.setEmailFornec(resset.getString("email"));
		provider.setComment(resset.getString("comment"));
		provider.setCnpjFornec(resset.getString("cnpj"));
		
		return provider;
	}
	
}

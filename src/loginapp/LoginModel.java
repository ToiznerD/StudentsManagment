package loginapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;

public class LoginModel {
	Connection connection;
	
	public LoginModel() {
		try {
			this.connection = dbConnection.getConnection();
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		
		if(this.connection == null) {
			System.exit(1);
		}
	}
	
	public boolean isDatabaseConnected() {
		return this.connection != null;
	}
	
	public int isLogin(String user, String pass, String opt) throws Exception{
		PreparedStatement pr = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM login WHERE username = ? AND password = ? AND divission = ?";
		
		try {
			pr = connection.prepareStatement(sql);
			pr.setString(1, user);
			pr.setString(2, pass);
			pr.setString(3, opt);
			rs = pr.executeQuery();
			
			if(rs.next()) 
				return rs.getInt(1);
			return 0;
		}
		catch(SQLException ex) {
			return 0;
		}
		finally {
			pr.close();
			rs.close();
		}
	}
}

package com.rent.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.rent.view.UserDetails;

import util.PasswordUtil;





public class ManageUserDAO {

	public static int insert(UserDetails user) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;

		HashMap<String, String> saltHash = new HashMap<String, String>();
		saltHash = PasswordUtil.hashandSaltPassword(user.getPassword());
		
		String query
		= "INSERT INTO USER_DETAILS (USER_ID, USER_NAME, PASSWORD, SALT) "
				+ "VALUES (?, ?, ?, ?)";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, user.getUserId());
			ps.setString(2, user.getFirstName()+" "+user.getLastName());
			ps.setString(3, saltHash.get("password"));
			ps.setString(4, saltHash.get("salt"));
			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			return 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}

	public static UserDetails validateUser(String email, String password)  {

		ConnectionPool pool = null;
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {

			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			
			
			//Encrypt pwd here
			String query = "SELECT * FROM USER_DETAILS "
					+ "WHERE USER_ID = ?";

			ps = connection.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			UserDetails user = null;
			if (rs.next()) {
				String salt =rs.getString("SALT");
				String hashedpassword = rs.getString("PASSWORD");
				if(validatePassword(salt,hashedpassword,password)){
					user = new UserDetails();
					user.setFirstName(rs.getString("USER_NAME"));
					user.setUserId(rs.getString("USER_ID"));
				}
				
			}
			return user;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}

	private static boolean validatePassword(String salt, String hashedpassword, String password) throws NoSuchAlgorithmException {
		String confirmPassword = PasswordUtil.hashPassword(password + salt);
		System.out.println("Password:"+confirmPassword+"::"+hashedpassword);
		if(hashedpassword.equals(confirmPassword)){
			return true;
		}
		// TODO Auto-generated method stub
		return false;
		
	}
	
	private static HashMap<String, String> getSaltandPAssword(String password) throws NoSuchAlgorithmException {
		HashMap<String, String> saltHash = new HashMap<String, String>();
		saltHash = PasswordUtil.hashandSaltPassword(password);
		// TODO Auto-generated method stub
		return saltHash;
		
	}


}
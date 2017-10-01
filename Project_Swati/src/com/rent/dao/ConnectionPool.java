package com.rent.dao;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConnectionPool {

    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;
    
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException {         
    	Class.forName("com.mysql.jdbc.Driver");        
    	String dbName = "mydb";         
    	String userName = "root";         
    	String password = "root1234";         
    	String hostname = "mydb.cx2xixbse7xd.us-west-2.rds.amazonaws.com";         
    	String port = "3306"; 
        String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;        
        return DriverManager.getConnection(jdbcUrl); 
    }
    
    private ConnectionPool() {
        
    }

    public static synchronized ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

   

    public void freeConnection(Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
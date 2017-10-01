package com.rent.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.rent.view.RentItemDetails;
import com.rent.view.RentItemUser;







public class ManageBookDAOImpl {
	
	 public static int addBook(RentItemDetails rentItemDetails) throws ClassNotFoundException, SQLException {
		 	ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;

	        String query
	                = "INSERT INTO RENT_ITEM_DETAILS (ITEM_TITLE, ITEM_AUTHOR, ITEM_ISBN, EDITION, ITEM_IMAGE,"
	                		+ "ITEM_PRICE, AVAILABLE_FROM, OWNER_ID) "
	                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, rentItemDetails.getTitle());
	            ps.setString(2, rentItemDetails.getAuthor());
	            ps.setString(3, rentItemDetails.getIsbn());
	            ps.setString(4, rentItemDetails.getEdition());
	            ps.setBlob(5, rentItemDetails.getImageUrl());
	            ps.setString(6, rentItemDetails.getPrice());
	            ps.setString(7, rentItemDetails.getAvailableFrom());
	            ps.setString(8, rentItemDetails.getOwnerId());
	            return ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	            return 0;
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
	 
	 
	 public static ArrayList<RentItemDetails> getBooks(String searchStr)  throws ClassNotFoundException, SQLException{
			ArrayList<RentItemDetails> booklist = new ArrayList<RentItemDetails>();
			ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        String query = "SELECT * FROM RENT_ITEM_DETAILS ";
	        
	        if(null != searchStr && !searchStr.trim().isEmpty()) {
	        	query = query + " WHERE ITEM_PK like '%" + searchStr + "%' OR ITEM_ISBN  like '%" + searchStr
	        			+ "%' OR ITEM_AUTHOR  like '%" + searchStr
	        			+ "%' OR ITEM_TITLE like '%" + searchStr + "%'";
	        }
	        
	        try {
	            ps = connection.prepareStatement(query);
	            rs = ps.executeQuery();
	            RentItemDetails detail = null;
	            System.out.println(query);      
	            while (rs.next()) {
	            	detail = new RentItemDetails();
	            	detail.setRentItemPk(rs.getLong("ITEM_PK"));
	            	detail.setAuthor(rs.getString("ITEM_AUTHOR"));
	            	detail.setAvailableFrom(rs.getString("AVAILABLE_FROM"));
	            	detail.setEdition(rs.getString("EDITION"));
	            	detail.setImageUrl(rs.getBinaryStream("ITEM_IMAGE"));
	            	detail.setIsbn(rs.getString("ITEM_ISBN"));
	            	detail.setOwnerId(rs.getString("OWNER_ID"));
	            	detail.setPrice(rs.getString("ITEM_PRICE"));
	            	detail.setTitle(rs.getString("ITEM_TITLE"));
	            	detail.setStatusValue(rs.getString("AVAILABLE_FROM"));
	            	byte[] imgData = rs.getBytes("ITEM_IMAGE"); // blob field 
	                String encode = Base64.getEncoder().encodeToString(imgData);
	                detail.setImageBase(encode);
	            	booklist.add(detail);
	            }
	            return booklist;
	        } catch (SQLException e) {
	            System.out.println(e);
	            return null;
	        } finally {
	            DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
		}
	 
	 public static RentItemDetails getBook(String bookid)  throws ClassNotFoundException, SQLException{
			ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        if(null != bookid && !bookid.isEmpty()) {
	        	String query = "SELECT * FROM RENT_ITEM_DETAILS WHERE ITEM_PK = ?";
	        	try {
		            ps = connection.prepareStatement(query);
		            ps.setString(1, bookid);
		            rs = ps.executeQuery();
		            RentItemDetails detail = null;
		            System.out.println(rs);      
		            while (rs.next()) {
		            	detail = new RentItemDetails();
		            	detail.setRentItemPk(rs.getLong("ITEM_PK"));
		            	detail.setAuthor(rs.getString("ITEM_AUTHOR"));
		            	detail.setAvailableFrom(rs.getString("AVAILABLE_FROM"));
		            	detail.setEdition(rs.getString("EDITION"));
		            	detail.setImageUrl(rs.getBinaryStream("ITEM_IMAGE"));
		            	detail.setIsbn(rs.getString("ITEM_ISBN"));
		            	detail.setOwnerId(rs.getString("OWNER_ID"));
		            	detail.setPrice(rs.getString("ITEM_PRICE"));
		            	detail.setTitle(rs.getString("ITEM_TITLE"));
		            	byte[] imgData = rs.getBytes("ITEM_IMAGE"); // blob field 
		                String encode = Base64.getEncoder().encodeToString(imgData);
		                detail.setImageBase(encode);
		            }
		            return detail;
		        } catch (SQLException e) {
		            System.out.println(e);
		            return null;
		        } finally {
		            DBUtil.closeResultSet(rs);
		            DBUtil.closePreparedStatement(ps);
		            pool.freeConnection(connection);
		        }
	        }
	        else{
	        	return null;
	        }
	        
	        
		}
	 
	 public static int addRental(RentItemUser rentItemUser) throws ClassNotFoundException, SQLException {
		 	ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;

	        String query
	                = "INSERT INTO RENT_ITEM_USER (RENTER_ID, ITEM_PK, DUE_DATE, ITEM_STATUS, AMOUNT_DUE)"
	                + "VALUES (?, ?, ?, ?, ?)";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, rentItemUser.getRenterId());
	            ps.setLong(2, rentItemUser.getItemPk());
	            ps.setString(3, rentItemUser.getDueDate().toString());
	            ps.setString(4, rentItemUser.getItemStatus());
	            ps.setBigDecimal(5, rentItemUser.getAmountDue());
	            return ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	            return 0;
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
	 
	 public static ArrayList<RentItemDetails> getRental(String userId) throws ClassNotFoundException, SQLException {
		 	ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        String query
	                = "SELECT RID.ITEM_PK, RID.ITEM_TITLE, RID.ITEM_AUTHOR, RID.AVAILABLE_FROM, RID.ITEM_IMAGE, RID.ITEM_ISBN, RID.ITEM_PRICE, RIU.ITEM_STATUS "+
	                	",RID.EDITION ,RIU.DUE_DATE FROM RENT_ITEM_DETAILS RID INNER JOIN RENT_ITEM_USER RIU ON RID.ITEM_PK = RIU.ITEM_PK "+
	                		"WHERE RIU.RENTER_ID = ?";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, userId);
	            rs = ps.executeQuery();
	            ArrayList<RentItemDetails> list = new ArrayList<RentItemDetails>();
	            RentItemDetails detail = null;
	            while (rs.next()) {
	            	detail = new RentItemDetails();
	            	detail.setRentItemPk(rs.getLong("ITEM_PK"));
	            	detail.setAuthor(rs.getString("ITEM_AUTHOR"));
	            	detail.setAvailableFrom(rs.getString("DUE_DATE"));
	            	detail.setEdition(rs.getString("EDITION"));
	            	detail.setImageUrl(rs.getBinaryStream("ITEM_IMAGE"));
	            	detail.setIsbn(rs.getString("ITEM_ISBN"));
	            	detail.setPrice(rs.getString("ITEM_PRICE"));
	            	detail.setTitle(rs.getString("ITEM_TITLE"));
	            	detail.setStatus(rs.getString("ITEM_STATUS"));
	            	byte[] imgData = rs.getBytes("ITEM_IMAGE"); // blob field 
	                String encode = Base64.getEncoder().encodeToString(imgData);
	                detail.setImageBase(encode);
	                list.add(detail);
	            }
	            return list;
	        } catch (SQLException e) {
	            System.out.println(e);
	            return null;
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
	 
	 public static ArrayList<RentItemDetails> getMyBooks(String userId) throws ClassNotFoundException, SQLException {
		 ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        if(null != userId && !userId.isEmpty()) {
	        	ArrayList<RentItemDetails> list = new ArrayList<RentItemDetails>();
	        	String query = "SELECT RID.ITEM_PK, RID.ITEM_TITLE, RID.ITEM_AUTHOR, RID.AVAILABLE_FROM , RID.ITEM_IMAGE, RID.ITEM_ISBN, RID.ITEM_PRICE, "
	        			+ "RIU.ITEM_STATUS, RID.OWNER_ID ,RID.EDITION FROM RENT_ITEM_DETAILS RID LEFT OUTER JOIN "
	        			+ "RENT_ITEM_USER RIU ON  RID.ITEM_PK = RIU.ITEM_PK WHERE RID.OWNER_ID = ?";
	        	try {
		            ps = connection.prepareStatement(query);
		            ps.setString(1, userId);
		            rs = ps.executeQuery();
		            RentItemDetails detail = null;
		            System.out.println(rs);      
		            while (rs.next()) {
		            	detail = new RentItemDetails();
		            	detail.setRentItemPk(rs.getLong("ITEM_PK"));
		            	detail.setAuthor(rs.getString("ITEM_AUTHOR"));
		            	detail.setAvailableFrom(rs.getString("AVAILABLE_FROM"));
		            	detail.setEdition(rs.getString("EDITION"));
		            	detail.setImageUrl(rs.getBinaryStream("ITEM_IMAGE"));
		            	detail.setIsbn(rs.getString("ITEM_ISBN"));
		            	detail.setPrice(rs.getString("ITEM_PRICE"));
		            	detail.setTitle(rs.getString("ITEM_TITLE"));
		            	if(rs.getString("ITEM_STATUS")!=null && !rs.getString("ITEM_STATUS").trim().isEmpty()){
		            		detail.setStatus(rs.getString("ITEM_STATUS"));
		            	}
		            	else{
		            		detail.setStatus("Available");
		            	}
		            	byte[] imgData = rs.getBytes("ITEM_IMAGE"); // blob field 
		                String encode = Base64.getEncoder().encodeToString(imgData);
		                detail.setImageBase(encode);
		                list.add(detail);
		            }
		            return list;
		        } catch (SQLException e) {
		            System.out.println(e);
		            return null;
		        } finally {
		            DBUtil.closeResultSet(rs);
		            DBUtil.closePreparedStatement(ps);
		            pool.freeConnection(connection);
		        }
	        }
	        else{
	        	return null;
	        }
	    }
	 
	 public static ArrayList<RentItemDetails> getMyRequests(String userId) throws ClassNotFoundException, SQLException {
		 ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        if(null != userId && !userId.isEmpty()) {
	        	ArrayList<RentItemDetails> list = new ArrayList<RentItemDetails>();
	        	String query = "SELECT RID.ITEM_PK, RID.ITEM_TITLE, RID.ITEM_AUTHOR, RID.AVAILABLE_FROM , RID.ITEM_IMAGE, "
	        			+ "RID.ITEM_ISBN, RID.ITEM_PRICE, RIU.ITEM_STATUS, RIU.RENTER_ID, RID.OWNER_ID ,RID.EDITION FROM RENT_ITEM_DETAILS RID "
	        			+ "INNER JOIN RENT_ITEM_USER RIU ON  RID.ITEM_PK = RIU.ITEM_PK WHERE RID.OWNER_ID = ? AND (RIU.ITEM_STATUS= ? OR RIU.ITEM_STATUS= ?)";
	        	try {
		            ps = connection.prepareStatement(query);
		            ps.setString(1, userId);
		            ps.setString(2, "requested");
		            ps.setString(3, "return_requested");
		            rs = ps.executeQuery();
		            RentItemDetails detail = null;
		            System.out.println(rs);      
		            while (rs.next()) {
		            	detail = new RentItemDetails();
		            	detail.setRentItemPk(rs.getLong("ITEM_PK"));
		            	detail.setAuthor(rs.getString("ITEM_AUTHOR"));
		            	detail.setAvailableFrom(rs.getString("AVAILABLE_FROM"));
		            	detail.setEdition(rs.getString("EDITION"));
		            	detail.setImageUrl(rs.getBinaryStream("ITEM_IMAGE"));
		            	detail.setIsbn(rs.getString("ITEM_ISBN"));
		            	detail.setPrice(rs.getString("ITEM_PRICE"));
		            	detail.setTitle(rs.getString("ITEM_TITLE"));
		            	detail.setStatus(rs.getString("ITEM_STATUS"));
		            	detail.setRenter_id(rs.getString("RENTER_ID"));
		            	byte[] imgData = rs.getBytes("ITEM_IMAGE"); // blob field 
		                String encode = Base64.getEncoder().encodeToString(imgData);
		                detail.setImageBase(encode);
		                list.add(detail);
		            }
		            return list;
		        } catch (SQLException e) {
		            System.out.println(e);
		            return null;
		        } finally {
		            DBUtil.closeResultSet(rs);
		            DBUtil.closePreparedStatement(ps);
		            pool.freeConnection(connection);
		        }
	        }
	        else{
	        	return null;
	        }
	    }
	 
	 public static ArrayList<RentItemDetails> getMyRentalRequests(String userId) throws ClassNotFoundException, SQLException {
		 ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        if(null != userId && !userId.isEmpty()) {
	        	ArrayList<RentItemDetails> list = new ArrayList<RentItemDetails>();
	        	String query = "SELECT RID.ITEM_PK, RID.ITEM_TITLE, RID.ITEM_AUTHOR, RID.AVAILABLE_FROM , RID.ITEM_IMAGE, "
	        			+ "RID.ITEM_ISBN, RID.ITEM_PRICE, RIU.ITEM_STATUS, RID.OWNER_ID ,RID.EDITION FROM RENT_ITEM_DETAILS RID "
	        			+ "INNER JOIN RENT_ITEM_USER RIU ON  RID.ITEM_PK = RIU.ITEM_PK WHERE RIU.RENTER_ID = ? AND (RIU.ITEM_STATUS= ? OR RIU.ITEM_STATUS= ?)";
	        	try {
		            ps = connection.prepareStatement(query);
		            ps.setString(1, userId);
		            ps.setString(2, "requested");
		            ps.setString(3, "rent_confirmed");
		            rs = ps.executeQuery();
		            RentItemDetails detail = null;
		            System.out.println(rs);      
		            while (rs.next()) {
		            	detail = new RentItemDetails();
		            	detail.setRentItemPk(rs.getLong("ITEM_PK"));
		            	detail.setAuthor(rs.getString("ITEM_AUTHOR"));
		            	detail.setAvailableFrom(rs.getString("AVAILABLE_FROM"));
		            	detail.setEdition(rs.getString("EDITION"));
		            	detail.setImageUrl(rs.getBinaryStream("ITEM_IMAGE"));
		            	detail.setIsbn(rs.getString("ITEM_ISBN"));
		            	detail.setPrice(rs.getString("ITEM_PRICE"));
		            	detail.setTitle(rs.getString("ITEM_TITLE"));
		            	detail.setStatus(rs.getString("ITEM_STATUS"));
		            	byte[] imgData = rs.getBytes("ITEM_IMAGE"); // blob field 
		                String encode = Base64.getEncoder().encodeToString(imgData);
		                detail.setImageBase(encode);
		                list.add(detail);
		            }
		            return list;
		        } catch (SQLException e) {
		            System.out.println(e);
		            return null;
		        } finally {
		            DBUtil.closeResultSet(rs);
		            DBUtil.closePreparedStatement(ps);
		            pool.freeConnection(connection);
		        }
	        }
	        else{
	        	return null;
	        }
	    }
	 
	 //Return requested - change the status of the book in RENT_ITEM_USER to return_requested
	 public static int updateReturnStatus(String bookId) throws ClassNotFoundException, SQLException {
		 	ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;

	        String query
	                = "UPDATE RENT_ITEM_USER SET ITEM_STATUS = 'return_requested' where ITEM_PK = ?" ;
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, bookId);
	            return ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	            return 0;
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
	 
	 public static int updateRentStatus(String bookId, String status, String renter_id) throws ClassNotFoundException, SQLException {
		 System.out.println("Status"+status);
		 	ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        PreparedStatement ps2 = null;
	        

	        String query1
         = "UPDATE RENT_ITEM_USER SET ITEM_STATUS = ? where ITEM_PK = ? " ;
	        String query
	                = "UPDATE RENT_ITEM_USER SET ITEM_STATUS = ?, DUE_DATE = ? where ITEM_PK = ? AND RENTER_ID = ? " ;
	        String query2
         = "UPDATE RENT_ITEM_DETAILS SET AVAILABLE_FROM = ? where ITEM_PK = ?" ;
	        try {
	        	Calendar cal = Calendar.getInstance();
	            cal.setTime(new Date());
	            cal.add(Calendar.DATE, 180); //minus number would decrement the days
	            
	        	ps = connection.prepareStatement(query1);
	        	ps.setString(1, "rejected");
	            ps.setString(2, bookId);
	            ps.executeUpdate();
	            DBUtil.closePreparedStatement(ps);
	            
	            ps2 = connection.prepareStatement(query);
	            ps2.setString(1, status);
	            ps2.setString(2, cal.getTime().toString());
	            ps2.setString(3, bookId);
	            ps2.setString(4, renter_id);
	            
	            ps2.executeUpdate();
	            DBUtil.closePreparedStatement(ps2);
	            
	            ps = connection.prepareStatement(query2);
	            ps.setString(1, null);
	            ps.setString(2, bookId);
	            return ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	            return 0;
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
	 
	 public static int markReceived(String bookId, String renter_id) throws ClassNotFoundException, SQLException {
		 	ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;

	        String query
      = "UPDATE RENT_ITEM_USER SET ITEM_STATUS = 'Recieved' where ITEM_PK = ? AND RENTER_ID = ?" ;
	        try {
	        	ps = connection.prepareStatement(query);
	        	ps.setString(1, bookId);
	            ps.setString(2, renter_id);
	            return ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	            return 0;
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
	 
	 public static String checkRentStatus(long bookid, String userid)  throws ClassNotFoundException, SQLException{
			ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        if(0 != bookid && null != userid && !userid.isEmpty()) {
	        	String query = "SELECT ITEM_STATUS FROM RENT_ITEM_USER WHERE ITEM_PK = ? AND RENTER_ID = ?";
	        	try {
		            ps = connection.prepareStatement(query);
		            ps.setLong(1, bookid);
		            ps.setString(2, userid);
		            rs = ps.executeQuery();
		            System.out.println(rs);      
		            while (rs.next()) {
		            	if(rs.getString("ITEM_STATUS")!=null){
		            		return rs.getString("ITEM_STATUS");
		            	}
		            }
		        } catch (SQLException e) {
		            System.out.println(e);
		            return null;
		        } finally {
		            DBUtil.closeResultSet(rs);
		            DBUtil.closePreparedStatement(ps);
		            pool.freeConnection(connection);
		        }
	        }
	        return null;
		}
	 
	 public static int confirmReturn(String bookId, String renter_id) throws ClassNotFoundException, SQLException, ParseException {
		 	ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;

	        String query
	        = "DELETE FROM RENT_ITEM_USER where ITEM_PK = ? AND RENTER_ID = ?" ;
	        String query2
	         = "UPDATE RENT_ITEM_DETAILS SET AVAILABLE_FROM = ? where ITEM_PK = ?" ;
	        try {
	        	ps = connection.prepareStatement(query);
	        	ps.setString(1, bookId);
	            ps.setString(2, renter_id);
	            ps.executeUpdate();
	            DBUtil.closePreparedStatement(ps);
	            ps = connection.prepareStatement(query2);
	            String date1= new SimpleDateFormat("dd/MM/yyyy").format(new Date()); 
	            ps.setString(1, date1);
	            ps.setString(2, bookId);
	            return ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	            return 0;
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
	 
}
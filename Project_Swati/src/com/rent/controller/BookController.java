package com.rent.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;

import com.rent.dao.ManageBookDAOImpl;
import com.rent.dao.ManageUserDAO;
import com.rent.view.RentItemDetails;
import com.rent.view.RentItemUser;
import com.rent.view.UserDetails;


/**
 * Servlet implementation class UserController
 */
@WebServlet(urlPatterns={"/addBook","/main","/search","/rent","/rentRequest", "/myrentals", "/mybooks", 
		"/notifications","/allBook","/return","/returnRequest","/confirmRequest","/confirmReturn","/markReceived"})
@MultipartConfig(maxFileSize = 16177215)
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		String url = "/home.jsp";
		UserDetails user = (UserDetails)request.getSession().getAttribute("user");
		
        // get current action
        String action = request.getParameter("action");
        System.out.println("Inside servlet:"+action);
        
        if(action == null){
        	url = "/home.jsp";
        }
        
        //On click of sell link, add book action is called
        else if (action.equals("addBook")) {
        	if(null!=user && null!=user.getUserId()){
        		String name = request.getParameter("bookName");
            	String author = request.getParameter("author");
            	String isbn = request.getParameter("isbn");
            	String edition = request.getParameter("version");
            	String price = request.getParameter("price");
            	String fromDate = request.getParameter("fromDate");
            	InputStream inputStream = null; // input stream of the upload file
                
                // obtains the upload file part in this multipart request
                Part filePart = request.getPart("image");
                
                RentItemDetails details = new RentItemDetails();
                details.setAuthor(author);
                details.setTitle(name);
                details.setIsbn(isbn);
                details.setEdition(edition);
                details.setPrice(price);
                details.setAvailableFrom(fromDate);
                details.setOwnerId(user.getUserId());
                if (filePart != null) {
                    // prints out some information for debugging
                    System.out.println(filePart.getName());
                    System.out.println(filePart.getSize());
                    System.out.println(filePart.getContentType());
                     
                    // obtains input stream of the upload file
                    inputStream = filePart.getInputStream();
                    details.setImageUrl(inputStream);
                }
                try {
    				int count = ManageBookDAOImpl.addBook(details);
    				if(count==1){
    					request.setAttribute("msg", "Book addded successfully!!");
    				} else{
    					request.setAttribute("errormsg", "Error adding book.");
    	        	}
    				url="/addBook.jsp";
    			} catch (ClassNotFoundException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        	} else{
        		request.setAttribute("errormsg", "Please login to sell books.");
        		url="/login.jsp";
        	}
        	
        	
        } 
        // When the user searches for any book
        else if (action.equals("search")) {
    		ArrayList<RentItemDetails> booklist = new ArrayList<RentItemDetails>();
    		String searchText = request.getParameter("searchText");
    		System.out.println("searchText:"+searchText);
        	try {
        		booklist = ManageBookDAOImpl.getBooks(searchText);
        		if(null != booklist && booklist.size()>0){
        			request.setAttribute("books", booklist);
                	System.out.println("Books:"+booklist.size());
        		} else{
        			request.setAttribute("errormsg", "No books found.");
        		}
        		
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		url="/allBook.jsp";
        } 
        //fetch all the books available on the site
        else if (action.equals("allBook")) {
    		ArrayList<RentItemDetails> booklist = new ArrayList<RentItemDetails>();
    		String searchText = "";
        	try {
        		booklist = ManageBookDAOImpl.getBooks(searchText);
        		if(null != booklist && booklist.size()>0){
        			request.setAttribute("books", booklist);
                	System.out.println("Books:"+booklist.size());
        		} else{
        			request.setAttribute("errormsg", "No books found.");
        		}
    			
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	url="/allBook.jsp";
        } 
        // Rent books 
        else if (action.equals("rent")) {
        	if(null!=user && null!=user.getUserId()){
        		RentItemDetails book = new RentItemDetails();
        		String bookid = request.getParameter("bookid");
            	try {
            		if(bookid!=null && !bookid.isEmpty()){
            			book = ManageBookDAOImpl.getBook(bookid);
        				request.getSession().setAttribute("book", book);
        				url="/rent.jsp";
            		} else{
                		request.setAttribute("errormsg", "Error renting book.");
                		url="/login.jsp";
                	}
            		
    			} catch (ClassNotFoundException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        	} else{
        		request.setAttribute("errormsg", "Please login to rent books.");
        		url="/login.jsp";
        	}
    		
        	
        } 
        //Rent request for the book
        else if (action.equals("rentRequest")) {
        	if(null!=user && null!=user.getUserId()){
        		RentItemUser rentItemUser = new RentItemUser();
            	RentItemDetails book = (RentItemDetails) request.getSession().getAttribute("book");
            	if(book!=null){
            		try {
            			String status = ManageBookDAOImpl.checkRentStatus(book.getRentItemPk(), user.getUserId());
            			if(null!=status){
            				request.setAttribute("errormsg", "Book already requested. Status:"+status);
            			} else{
            				rentItemUser.setAmountDue(new BigDecimal(book.getPrice()));
                    		Date date1= new SimpleDateFormat("dd/MM/yyyy").parse(book.getAvailableFrom()); 
                    		rentItemUser.setDueDate(date1);
                    		rentItemUser.setItemPk(book.getRentItemPk());
                    		rentItemUser.setItemStatus("requested");
                    		rentItemUser.setRenterId(user.getUserId());
                    		int count = ManageBookDAOImpl.addRental(rentItemUser);
            				request.setAttribute("status", "requested");
            			}
                		
        				url="/rent.jsp";
        			} catch (ClassNotFoundException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			} catch (SQLException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			} catch (ParseException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
            	}
            	
        	} else{
        		request.setAttribute("errormsg", "Please login to rent books.");
        		url="/login.jsp";
        	}
        } 
        //get My Rentals
        else if (action.equals("myrentals")) {
        	if(null!=user && null!=user.getUserId()){
        		ArrayList<RentItemDetails> rentItemDetails = new ArrayList<RentItemDetails>();
            	try {
            		rentItemDetails = ManageBookDAOImpl.getRental(user.getUserId());
            		if(rentItemDetails!=null && rentItemDetails.size()>0){
            			request.setAttribute("myrentals", rentItemDetails);
            		} else{
            			request.setAttribute("errormsg", "No rented books found.");
            		}
    				
    				url="/myrentals.jsp";
    			} catch (ClassNotFoundException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			} 
        	} else{
        		request.setAttribute("errormsg", "Please login to see rented books.");
        		url="/login.jsp";
        	}
        	
        	
        } 
        // Return book
        else if (action.equals("return")) {
        	if(null!=user && null!=user.getUserId()){
        		url = getReturnList(request, url, user); 
        	} else{
        		request.setAttribute("errormsg", "Please login to return books.");
        		url="/login.jsp";
        	}
        	
        	
        } 
        //Return requested 
        else if (action.equals("returnRequest")) {
        	if(null!=user && null!=user.getUserId()){
        		String bookid = request.getParameter("bookid");
        		if(null!=bookid && !bookid.isEmpty()){
        			try {
                		int count = ManageBookDAOImpl.updateReturnStatus(bookid);
                		if(count==1){
                			request.setAttribute("msg", "Book return request sent.");
                		} else{
                    		request.setAttribute("errormsg", "Error returning book");
                    	}
                		url = getReturnList(request, url, user); 
        			} catch (ClassNotFoundException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			} catch (SQLException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        		}
            	
            	
        	} else{
        		request.setAttribute("errormsg", "Please login to return books.");
        		url="/login.jsp";
        	}
        	
        	
        } 
        //get My books
        else if (action.equals("mybooks")) {
        	if(null!=user && null!=user.getUserId()){
        		ArrayList<RentItemDetails> rentItemDetails = new ArrayList<RentItemDetails>();
            	try {
            		rentItemDetails = ManageBookDAOImpl.getMyBooks(user.getUserId());
            		if(null != rentItemDetails && rentItemDetails.size()>0){
            			request.setAttribute("mybooks", rentItemDetails);
            		} else{
            			request.setAttribute("errormsg", "No books found.");
            		}
    				
    				url="/mybooks.jsp";
    			} catch (ClassNotFoundException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			} 
        	} else{
        		request.setAttribute("errormsg", "Please login to see My Books.");
        		url="/login.jsp";
        	}
        	
        	
        	
        } else if (action.equals("notifications")) {
        	if(null!=user && null!=user.getUserId()){
        		url = getNotifications(request, url, user); 
        	} else{
        		request.setAttribute("errormsg", "Please login to see notifications.");
        		url="/login.jsp";
        	}
        	
        	
        } 
        // Confirm rent request
        else if (action.equals("confirmRequest")) {
        	if(null!=user && null!=user.getUserId()){
        		String bookid = request.getParameter("bookid");
        		String renter_id = request.getParameter("renter_id");
        		
        		if(null!=bookid && !bookid.isEmpty()){
        			try {
        				
        				int count = ManageBookDAOImpl.updateRentStatus(bookid,"rent_confirmed", renter_id);
                		if(count==1){
                			request.setAttribute("msg", "Book rent request confirmed.");
                		} else{
                    		request.setAttribute("errormsg", "Error confirming request");
                    	}
                		url = getNotifications(request, url, user); 
        				
        			} catch (ClassNotFoundException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			} catch (SQLException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        		}
            	
            	
        	} else{
        		request.setAttribute("errormsg", "Please login to confirm requests.");
        		url="/login.jsp";
        	}
        	
        	
        } 
        //Mark Received
        else if (action.equals("markReceived")) {
        	if(null!=user && null!=user.getUserId()){
        		String bookid = request.getParameter("bookid");
        		if(null!=bookid && !bookid.isEmpty()){
        			try {
        				int count = ManageBookDAOImpl.markReceived(bookid, user.getUserId());
                		if(count==1){
                			request.setAttribute("msg", "Book marked as received.");
                		} else{
                    		request.setAttribute("errormsg", "Error confirming received status");
                    	}
                		url = getNotifications(request, url, user); 
        			} catch (ClassNotFoundException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			} catch (SQLException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        		}
            	
            	
        	} else{
        		request.setAttribute("errormsg", "Please login to confirm requests.");
        		url="/login.jsp";
        	}
        }
        	//Confirm Return
        	else if (action.equals("confirmReturn")) {
            	if(null!=user && null!=user.getUserId()){
            		String bookid = request.getParameter("bookid");
            		String renter_id = request.getParameter("renter_id");
            		if(null!=bookid && !bookid.isEmpty()){
            			try {
            				int count = ManageBookDAOImpl.confirmReturn(bookid, renter_id);
                    		if(count==1){
                    			request.setAttribute("msg", "Book returned.");
                    			url = getNotifications(request, url, user); 
                    		} else{
                        		request.setAttribute("errormsg", "Error confirming received status");
                        		url = getNotifications(request, url, user); 
                        	}
            				
            			} catch (ClassNotFoundException e) {
            				// TODO Auto-generated catch block
            				e.printStackTrace();
            			} catch (SQLException e) {
            				// TODO Auto-generated catch block
            				e.printStackTrace();
            			} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
            		}
                	
                	
            	} else{
            		request.setAttribute("errormsg", "Please login to confirm requests.");
            		url="/login.jsp";
            	}
        	
        	
        } 
        getServletContext()
        .getRequestDispatcher(url)
        .forward(request, response);
	}

	private String getReturnList(HttpServletRequest request, String url, UserDetails user) {
		ArrayList<RentItemDetails> rentItemDetails = new ArrayList<RentItemDetails>();
		ArrayList<RentItemDetails> returnItemDetails = new ArrayList<RentItemDetails>();
		try {
			rentItemDetails = ManageBookDAOImpl.getRental(user.getUserId());
			if(null!=rentItemDetails && rentItemDetails.size()>0){
				for(RentItemDetails item : rentItemDetails){
					if(item.getStatus().equals("Recieved")){
						returnItemDetails.add(item);
					}
				}
				if(null!=returnItemDetails && returnItemDetails.size()>0){
					request.setAttribute("myreturns", returnItemDetails);
				} else{
					request.setAttribute("msg", "No books to return");
				}
				
				
			} else{
				request.setAttribute("msg", "No books to return");
			}
			url="/return.jsp";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}

	private String getNotifications(HttpServletRequest request, String url, UserDetails user) {
		ArrayList<RentItemDetails> rentItemDetails = new ArrayList<RentItemDetails>();
		ArrayList<RentItemDetails>  rentItemDetails1 = new ArrayList<RentItemDetails>();
		try {
			rentItemDetails = ManageBookDAOImpl.getMyRequests(user.getUserId());
			rentItemDetails1 = ManageBookDAOImpl.getMyRentalRequests(user.getUserId());
			if(null!=rentItemDetails && rentItemDetails.size()>0){
				request.setAttribute("myrequests", rentItemDetails);
			} else{
				request.setAttribute("errormsg", "No pendind requests.");
			}
			if(null!=rentItemDetails1 && rentItemDetails1.size()>0){
				request.setAttribute("myrentalrequests", rentItemDetails1);
			} else{
				request.setAttribute("errormsg", "No pendind rental requests.");
			}
			
			url="/notifications.jsp";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

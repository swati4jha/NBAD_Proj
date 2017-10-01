package com.rent.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rent.dao.ManageBookDAOImpl;
import com.rent.dao.ManageUserDAO;
import com.rent.view.RentItemDetails;
import com.rent.view.UserDetails;


/**
 * Servlet implementation class UserController
 */
@WebServlet(urlPatterns={"/login","/signup","/logout"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		String url = "/home.jsp";
		 
        // get current action
        String action = request.getParameter("action");
        System.out.println("Inside servlet:"+action);
        if (action == null) {
        	url = "/home.jsp";  // default action
        } else if (action.equals("login")) {
        	String email = request.getParameter("email");
        	String password = request.getParameter("password");
        	System.out.println(email+"::"+password);
        	if(email!=null){
        		String[] parts = email.split("@");
            	String domain = parts[1]; 
            	if(domain!=null && domain.equals("uncc.edu")){
            		ManageUserDAO userdb = new ManageUserDAO();
        			UserDetails userDetails = userdb.validateUser(email, password);
                	
                	if(null != userDetails && null != userDetails.getUserId()){
                		request.getSession().setAttribute("user", userDetails);
                		ArrayList<RentItemDetails> booklist = new ArrayList<RentItemDetails>();
                    	try {
                    		booklist = ManageBookDAOImpl.getBooks(null);
            				System.out.println("Add success:"+booklist.size());
            			} catch (ClassNotFoundException e) {
            				// TODO Auto-generated catch block
            				e.printStackTrace();
            			} catch (SQLException e) {
            				// TODO Auto-generated catch block
            				e.printStackTrace();
            			}
                    	request.setAttribute("books", booklist);
                    	System.out.println("Books:"+booklist.size());
                		url="/allBook?action=allBook";
                		
                	}else{
                		System.out.println("Invalid password");
                		request.setAttribute("msg", "Invalid credentials!");
                		url="/login.jsp";
                	}
            	}else{
            		request.setAttribute("errormsg", "Please login using uncc id");
            		url="/login.jsp";
            	}
            	
        	}
        	
        	
        } else if (action.equals("signup")) {
        	String firstname = request.getParameter("firstname");
        	String lastname = request.getParameter("lastname");
        	String password = request.getParameter("password");
        	String email = request.getParameter("email");
        	String confirm_password = request.getParameter("confirm_password");
        	if(email!=null){
        		String[] parts = email.split("@");
            	String domain = parts[1]; 
            	if(domain!=null && domain.equals("uncc.edu")){
            		if(firstname!=null && !firstname.trim().isEmpty() && lastname!=null && !lastname.trim().isEmpty() && email!=null && !email.trim().isEmpty()
                			&& password!=null && !password.trim().isEmpty()  && confirm_password!=null && !confirm_password.trim().isEmpty()){
                		if(password.equals(confirm_password)){
                			if(password.length()>7){
                				UserDetails user = new UserDetails();
                    			user.setFirstName(firstname);
                    			user.setLastName(lastname);
                    			user.setUserId(email);
                    			user.setPassword(password);
                    			ManageUserDAO userdb = new ManageUserDAO();
                            	
                    			try {
                    				int count = ManageUserDAO.insert(user);
                    				if(count==1){
                    					ArrayList<RentItemDetails> booklist = new ArrayList<RentItemDetails>();
                    					request.getSession().setAttribute("user", user);
                        				booklist = ManageBookDAOImpl.getBooks(null);
                                    	request.setAttribute("books", booklist);
                                    	url="/allBook?action=allBook";
                    				}
                    				
                    			} catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException e) {
                    				// TODO Auto-generated catch block
                    				e.printStackTrace();
                    			}
                			}else{
                        		request.setAttribute("errormsg", "Passwords should be minimum 8 characters!");
                        		url="/login.jsp";
                    		}
                			
                			
                		}else{
                    		request.setAttribute("errormsg", "Passwords do not match!");
                    		url="/login.jsp";
                		}
                	} else {
                		request.setAttribute("errormsg", "Invalid password!");
                		url="/login.jsp";
                	}
            	} else{
            		request.setAttribute("errormsg", "Please signup using uncc id");
            		url="/login.jsp";
            	}
        	}
        	
        		
        } else if (action.equals("logout")) {
        	if(request.getSession().getAttribute("user")!=null){
        		request.getSession().invalidate();
        	} 
        	url="/home.jsp";
        }
        
        getServletContext()
        .getRequestDispatcher(url)
        .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

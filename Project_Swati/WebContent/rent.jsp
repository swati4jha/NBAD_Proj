<%-- 
    Document   : rent
    Created on : 20/4/2017
    Author     : Swati
--%>
<%-- Include tag is used to import header page --%>
<%@page import="java.net.URLEncoder"%>
<%@ include file="headerMain.jsp" %>
<div class="col-lg-12">
	<section id="main" >
	<p class="text-success message-show"><i>${msg}</i></p>
		<p class="text-danger message-show"><i>${errormsg}</i></p>
	      <div class="table-responsive">          
		  <table class="table borderless">
		    <tbody>
		    	<tr>
		    		<td>
		    			<div><img src="data:image/jpeg;base64,${book.imageBase}" class="img-responsive" alt="Responsive image"></div>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>
		    			<div>${book.title}</div>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>
		    			<div>By: ${book.author}</div>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>
		    			<div>Edition: ${book.edition}</div>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>
		    			<div>Price: $ ${book.price}</div>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>
		    			<span>Available</span>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>
		    		<c:choose>
					    <c:when test="${status=='requested'}">
							<button class="btn btn-sm btn-info" disabled>Rent requested</button>
					    </c:when>    
					    <c:otherwise>
					       <form action="rent" method="POST">
                            <input type="hidden" name="action" value="rentRequest" /> 
                            <input type="hidden" name="bookid" value="${book.rentItemPk}" />  
							<input class="btn btn-sm btn-success" type="submit" value="Rent"></input>
						</form>
					    </c:otherwise>
					</c:choose>
		    		<c:if test="${status=='requested'}">
		    		</c:if>
		    		
		    		</td>
		    	</tr>
   		    </tbody>
		  </table>
		  </div>
	</section>
</div>

<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>
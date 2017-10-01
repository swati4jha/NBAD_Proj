<%-- 
    Document   : notification
    Created on : 20/4/2017
    Author     : Swati
--%>
<%-- Include tag is used to import header page --%>
<%@page import="java.net.URLEncoder"%>
<%@ include file="headerMain.jsp" %>
<div class="col-lg-12 page_header">
  <div>My Requests </div>
</div>
<div class="col-lg-12">
	<section id="main" class="whiteBg" >
	<p class="text-success message-show"><i>${msg}</i></p>
		<p class="text-danger message-show"><i>${errormsg}</i></p>
	<c:forEach var="book" items="${myrequests}" varStatus="rowCounter">
		<div class="row">
	      <div class="col-lg-2"> 
	      <img src="data:image/jpeg;base64,${book.imageBase}" class="img-responsive center-align" alt="Responsive image">
	      </div>
	      <div class="col-lg-10">
		      <div>${book.title}</div>
			  <div>By: ${book.author}</div>
			  <div>Edition: ${book.edition}</div>
			  <div>Price: $ ${book.price}</div>
			  <div>Status: ${book.status}</div>
			  <c:if test="${book.status!= null && book.status == 'return_requested'}">
						<form action="rent" method="POST">
                            <input type="hidden" name="action" value="confirmReturn" />
                            <input type="hidden" name="renter_id" value="${book.renter_id}" />   
                            <input type="hidden" name="bookid" value="${book.rentItemPk}" />  
							<input class="btn btn-sm btn-success" type="submit" value="Confirm Return"></input>
					</form>
			  </c:if>
			  <c:if test="${book.status!= null && book.status == 'requested'}">
				  <form action="rent" method="POST">
	                   <input type="hidden" name="action" value="confirmRequest" /> 
	                   <input type="hidden" name="bookid" value="${book.rentItemPk}" /> 
	                   <input type="hidden" name="renter_id" value="${book.renter_id}" />  
					   <input class="btn btn-sm btn-success" type="submit" value="Confirm Request"></input>
				  </form>
			  </c:if>
	      </div> 
	     </div> 
	     <hr>     
		  </c:forEach> 
	</section>
</div>

<div class="col-lg-12 page_header no-margin">
  <div>My Rental Requests </div>
</div>
<div class="col-lg-12">
	<section id="main" class="whiteBg" >
	<c:forEach var="book" items="${myrentalrequests}" varStatus="rowCounter">
		<div class="row">
	      <div class="col-lg-2"> 
	      <img src="data:image/jpeg;base64,${book.imageBase}" class="img-responsive center-align" alt="Responsive image">
	      </div>
	      <div class="col-lg-10">
		      <div>${book.title}</div>
			  <div>By: ${book.author}</div>
			  <div>Edition: ${book.edition}</div>
			  <div>Price: $ ${book.price}</div>
			  <div>Status: ${book.status}</div>
			  <c:if test="${book.status!= null && book.status == 'rent_confirmed'}">
						<form action="rent" method="POST">
                            <input type="hidden" name="action" value="markReceived" /> 
                            <input type="hidden" name="bookid" value="${book.rentItemPk}" />  
							<input class="btn btn-sm btn-success" type="submit" value="Mark Received"></input>
					</form>
			  </c:if>
	      </div> 
	     </div> 
	     <hr>     
		  </c:forEach> 
	</section>
</div>

<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>
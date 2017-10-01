<%-- 
    Document   : myrental
    Created on : 20/4/2017
    Author     : Swati
--%>
<%-- Include tag is used to import header page --%>
<%@page import="java.net.URLEncoder"%>
<%@ include file="headerMain.jsp" %>
<div class="col-lg-12 page_header">
  <div>My Rentals </div>
</div>
<div class="col-lg-12">
	<section id="main" class="whiteBg" >
	<p class="text-success message-show"><i>${msg}</i></p>
	<p class="text-danger message-show"><i>${errormsg}</i></p>
	<c:forEach var="book" items="${myrentals}" varStatus="rowCounter">
		<div class="row">
	      <div class="col-lg-2"> 
	      <img src="data:image/jpeg;base64,${book.imageBase}" class="img-responsive center-align" alt="Responsive image">
	      </div>
	      <div class="col-lg-10">
		      <div>${book.title}</div>
			  <div>By: ${book.author}</div>
			  <div>Edition: ${book.edition}</div>
			  <div>Price: $ ${book.price}</div>
			  <div>${book.status}</div>
			  <c:if test="${book.status!= null && book.status == 'Recieved'}">
			  <div>Due Return Date: ${book.availableFrom}</div>
			  </c:if>
	      </div> 
	     </div> 
	     <hr>     
		  </c:forEach> 
	</section>
</div>

<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>
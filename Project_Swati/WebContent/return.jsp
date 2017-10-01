<%-- 
    Document   : myrental
    Created on : 20/4/2017
    Author     : Swati
--%>
<%-- Include tag is used to import header page --%>
<%@page import="java.net.URLEncoder"%>
<%@ include file="headerMain.jsp" %>
<div class="col-lg-12 page_header">
  <div>Return Books</div>
</div>
<div class="col-lg-12">
	<section id="main" class="whiteBg" >
	<p class="text-success message-show"><i>${msg}</i></p>
	<p class="text-danger message-show"><i>${errormsg}</i></p>
	<c:forEach var="book" items="${myreturns}" varStatus="rowCounter">
		<div class="row">
	      <div class="col-lg-2"> 
	      <img src="data:image/jpeg;base64,${book.imageBase}" class="img-responsive center-align" alt="Responsive image">
	      </div>
	      <div class="col-lg-10">
		      <div>${book.title}</div>
			  <div>By: ${book.author}</div>
			  <div>Edition: ${book.edition}</div>
			  <div>Price: $ ${book.price}</div>
			  <form action="rent" method="POST">
                   <input type="hidden" name="action" value="returnRequest" /> 
                   <input type="hidden" name="bookid" value="${book.rentItemPk}" />  
				   <input class="btn btn-sm btn-success" type="submit" value="Return Book"></input>
			  </form>
	      </div> 
	     </div> 
	     <hr>     
		  </c:forEach> 
	</section>
</div>

<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>
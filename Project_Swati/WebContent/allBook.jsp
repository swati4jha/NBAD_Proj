<%-- 
    Document   : allBooks
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
		  <table class="table table-bordered">
		    <tbody>
		    <c:forEach var="book" items="${books}" varStatus="rowCounter">
		        <c:if test="${rowCounter.count % 5 == 1}">
			      <tr>
			    </c:if>
			    	<td>
				<div>
					<img src="data:image/jpeg;base64,${book.imageBase}" class="img-responsive center-align" alt="Responsive image">
					<div>${book.title}</div>
					<div>By: ${book.author}</div>
					<div>Edition: ${book.edition}</div>
					<div>Price: $ ${book.price}</div>
					<span>${book.status}</span>
					<c:if test="${book.status!= null && book.status == 'Available'}">
						<form action="rent" method="POST">
                            <input type="hidden" name="action" value="rent" /> 
                            <input type="hidden" name="bookid" value="${book.rentItemPk}" />  
							<input class="btn btn-sm btn-success pull-right" type="submit" value="Rent"></input>
					</form>
					</c:if>
					
					
				</div>
				</td>
			    <c:if test="${rowCounter.count % 5 == 0||rowCounter.count == fn:length(values)}">
			      </tr>
			    </c:if>
		    </c:forEach>
   		    </tbody>
		  </table>
		  </div>
	</section>
</div>

<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>
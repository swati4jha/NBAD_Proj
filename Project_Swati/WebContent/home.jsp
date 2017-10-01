<%-- 
    Document   : home
    Created on : 20/4/2017
    Author     : Swati
--%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<div class="col-lg-12">
	<section id="home_page" >
		<p class="text-success message-show"><i>${msg}</i></p>
		<p class="text-danger message-show"><i>${errormsg}</i></p>
	    <form class="navbar-form searchBar" action="allBook">
	    <input type="hidden" name="action" value="allBook" /> 
        <div class="form-group">
          <input type="text" class="form-control search" name="searchText" placeholder="Search by title or author or ISBN">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
	</section>
</div>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="home.jsp">Rent On Campus</a>
      
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right">
      	<li>
      	<form class="navbar-form" action="search" method="POST">
      	<input type="hidden" name="action" value="search" />  
        <div class="form-group">
          <input type="text" class="form-control" name="searchText" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      	</li>
        <li><a href="allBook?action=allBook">Rent</a></li>
        <li><a href="return?action=return">Return</a></li>
        <li><a href="addBook.jsp">Sell</a></li>
        <c:if test="${user != null}">
	        
	        <li><a href="myrentals?action=myrentals">My Rentals</a></li>
	        <li><a href="mybooks?action=mybooks">My Books</a></li>
	        <li><a href="notifications?action=notifications">Notifications</a></li>
    	   <li><a href="logout?action=logout">Logout</a></li>
        </c:if>
        <c:if test="${user == null}">
        	<li><a href="login.jsp">Login</a></li>
        </c:if>
        <li><a href="help.jsp">FAQ</a></li>
        
        
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
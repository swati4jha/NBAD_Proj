<%-- 
    Document   : addBook
    Created on : 20/4/2017
    Author     : Swati
--%>
<%-- Include tag is used to import header page --%>
<%@ include file="headerMain.jsp" %>
<div class="col-lg-8">
	<section id="main" class="addBook">
		<p class="text-success message-show"><i>${msg}</i></p>
		<p class="text-danger message-show"><i>${errormsg}</i></p>
		<form class="form-horizontal" action="addBook" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="action" value="addBook" />
		  <div class="form-group">
		    <label class="control-label col-sm-2" for="bookName">Book Name:</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" required name="bookName" id="bookName" placeholder="Enter book name">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-2" for="author">Author:</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" required name="author" id="author" placeholder="Author">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-2" for="isbn">ISBN:</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" required name="isbn" id="isbn" placeholder="ISBN">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-2" for="version">Version:</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" required name="version" id="version" placeholder="Version">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-2" for="price">Price:</label>
		    <div class="col-sm-10">
		      <input type="number" class="form-control" required name="price" id="price" placeholder="Price">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-2" for="price">Available from:</label>
		   	<div class="col-sm-10">
		    <div class='input-group date' id='datetimepicker1'>
                    <input type='text' name="fromDate" required class="form-control" />
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
		  </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-2" for="image">Upload image:</label>
		    <div class="col-sm-10">
		      <input type="file" class="form-control" name="image" id="image" placeholder="Image">
		    </div>
		  </div>
		  <div class="form-group"> 
		    <div class="col-sm-offset-2 col-sm-10">
		      <input type="submit" value="Submit" class="btn btn-default btn-info">
		    </div>
		  </div>
		</form>
	</section>
	</div>
	<script type="text/javascript">
            $(function () {
                $('#datetimepicker1').datetimepicker();
            });
        </script>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>


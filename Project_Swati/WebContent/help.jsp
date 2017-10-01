<%-- 
    Document   : home
    Created on : 20/4/2017
    Author     : Swati
--%>
<%-- Include tag is used to import header page --%>
<%@ include file="headerMain.jsp" %>
<div class="col-lg-12">
	<div class="container whiteBg">
    <br />

    <div class="panel-group" id="accordion">
        <div class="faqHeader">General questions</div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">Is account registration required with uncc id?</a>
                </h4>
            </div>
            <div id="collapseOne" class="panel-collapse collapse in">
                <div class="panel-body">
                    Account registration at <strong>Rent On Campus</strong> with uncc id is only required if you will be renting or taking books on rent. 
                    This ensures a valid communication channel for all parties involved in any transactions. 
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTen">Can I put my books on rent?</a>
                </h4>
            </div>
            <div id="collapseTen" class="panel-collapse collapse">
                <div class="panel-body">
                    Yes after registration you can put your books on the rent. 
                    Please mention all the details related to book correctly like version, author, title. 
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseEleven">What is the currency used for all transactions?</a>
                </h4>
            </div>
            <div id="collapseEleven" class="panel-collapse collapse">
                <div class="panel-body">
                    All prices selling and renting are in <strong>USD</strong>
                </div>
            </div>
        </div>

        <div class="faqHeader">Sellers/Renters</div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">Who cen sell items?</a>
                </h4>
            </div>
            <div id="collapseTwo" class="panel-collapse collapse">
                <div class="panel-body">
                    Any registered user, who is a current student of <strong>uncc</strong> and has valid <strong>uncc id</strong>, can post it on <strong>Rent On Campus</strong>.
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">I want to put my book on rent - what are the steps?</a>
                </h4>
            </div>
            <div id="collapseThree" class="panel-collapse collapse">
                <div class="panel-body">
                    The steps involved in this process are really simple. All you need to do is:
                    <ul>
                        <li>Register an account</li>
                        <li>Click <strong>Add Books</strong> link and enter all the details of the book</li>
                        <li>If anyone requests for your book, an email will be sent to your registered uncc id ,you need to confirm the request.</li>
                        <li>Once you confirm the request, an email will be sent to the interested party and he/she will come and collect the book, pay the amount.</li>
                        <li>After the requested party receives the book, they need to update the status as received.</li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseFive">How do I request for the book?</a>
                </h4>
            </div>
            <div id="collapseFive" class="panel-collapse collapse">
                <div class="panel-body">
                The steps involved in this process are really simple. All you need to do is:
                    <ul>
                        <li>Register an account</li>
                        <li>Click <strong>Rent</strong> link and search for your book.</li>
                        <li>If you find your book, check for the availability. If the book is available, click on rent button.</li>
                        <li>Once the renter confirms the request, an email will be sent to you and you can collect the book after paying the amount.</li>
                        <li>After you receive the book, you need to update the status as received.</li>
                    </ul>
                    <br />
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseSix">Why rent/sell items here?</a>
                </h4>
            </div>
            <div id="collapseSix" class="panel-collapse collapse">
                <div class="panel-body">
                    There are a number of reasons why you should join us:
                    <ul>
                        <li>A great way to rent textbooks from university students.</li>
                        <li>Renting from university students is less time consuming and cheap.</li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseEight">What are the payment options?</a>
                </h4>
            </div>
            <div id="collapseEight" class="panel-collapse collapse">
                <div class="panel-body">
                    Payment has to be done in person to the person from whom you are renting the book.
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>
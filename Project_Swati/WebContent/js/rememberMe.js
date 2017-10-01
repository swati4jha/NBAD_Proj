$(function() {
	 
    if (localStorage.chkbx && localStorage.chkbx != '') {
        $('#login-remember').attr('checked', 'checked');
        $('#login-username').val(localStorage.usrname);
        $('#login-password').val(localStorage.pass);
    } else {
        $('#login-remember').removeAttr('checked');
        $('#login-username').val('');
        $('#login-password').val('');
    }

    $('#login-remember').click(function() {

        if ($('#login-remember').is(':checked')) {
            // save username and password
            localStorage.usrname = $('#login-username').val();
            localStorage.pass = $('#login-password').val();
            localStorage.chkbx = $('#login-remember').val();
        } else {
            localStorage.usrname = '';
            localStorage.pass = '';
            localStorage.chkbx = '';
        }
    });
});
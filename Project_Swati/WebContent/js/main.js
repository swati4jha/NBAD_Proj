function login(user){
	var id = $("[name='email']").val(); 
	var name = (id.split("@"))[0];
	var pwd = $("[name='password']").val(); 
	console.log(name+pwd);
	if(user=="admin"){
		document.getElementById("myForm").action = "admin.jsp?user=Hello,Kim";
	}else {
		document.getElementById("myForm").action = "main.jsp?user=Hello,"+name;
		
	}
	
}

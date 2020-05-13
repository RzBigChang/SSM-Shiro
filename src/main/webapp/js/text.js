function Add(){
	var username=$("#username").val();
	var password=$("#password").val();
	$.ajax({
		url:"Add",
		type:"post",
		data:{
			username:username,
			password:password
		},
		success:function(josn){
			if(josn>0){
				alert("添加成功");
				lcoation.href="findUser"
			}else{
				alert("添加失败")
				lcoation.href="findUser"
			}
		}
	})
}
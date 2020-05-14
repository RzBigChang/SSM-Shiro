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
function Update(){
	var id=$("#id").val();
	var username=$("#username").val();
	$.ajax({
		url:"Update",
		type:"post",
		data:{
			id:id,
			username:username
		},
		success:function(json){
			if(json>0){
				alert("修改成功");
				location.href="findUser"
			}else{
				alert("修改失败");
				location.href="findUser"
			}
		}
	})
}
function shan(id){
	alert("1111111111111");
	$.ajax({
		url:"/SSM_Redis/Delete",
		type:"post",
		data:{
			id:id
		},
		success:function(json){
			if(json>0){
				alert("删除成功");
				location.href="findUser"
			}else{
				alert("删除成功");
				location.href="findUser"
			}
		}
	})
}
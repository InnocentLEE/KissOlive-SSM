function checkform(){
	
				var userphone=$("#usertel").val();
				var userpassword=$("#password").val();
				var userstaus=1;
				if(!userphone){
					$("#userError").html("账号不能为空");
					userstaus=0;
				}
				else if(userphone.length < 11 || userphone.length > 11){
					$("#userError").html("账号长度错误");
					userstaus=0;
				}
				else if(!/^1[3|4|5|7|8][0-9]{9}$/.test(userphone)){
					$("#userError").html("账号只能为数字");
					userstaus=0;
				}
				if(userstaus==1){
					
					if(!userpassword){
						$("#userError").html("密码不能为空");
						userstaus=0;
					}
					else if(!/^[a-zA-Z]\w{5,17}$/.test(userpassword)){
						$("#userError").html("密码格式错误");
						userstaus=0;
					}
				}
				if(userstaus==1)
					return true;
				else
					return false;
}

//登录
function login() {
    var userphone=$("#usertel").val();
    var userpassword=$("#password").val();
    if(userphone == "" || userpassword == ""){
    	alert("66");
        $("#userError").html("账号不能为空");
/*        var text="账号密码不能为空";
        document.getElementById('show_msg').innerHTML=text;
        $('#tip').addClass('bbox');
        $('#tipbtn').one('click',function(){
        	return false;
        })*/
		return false;
    }
    $.ajax({
        type:"post",
        url:"http://localhost:8080/user/login.do",
        data:{
            phone_number:userphone,
            password:userpassword
        },
        dataType:'json',
        success: function(data) {
            if(data.status==0) {
					window.location.href="http://localhost:8080/admin";
            }else{
               var text="登录失败!"+data.msg;
                document.getElementById('show_msg').innerHTML=text;
                $('#tip').addClass('bbox');
                $('#tipbtn').one('click',function(){
                    window.location.reload();
                })
            }
        }
    })
}
function checkform2()
{
				var userphone=$("#tel").val();
				var userstaus=1;
					if(!userphone){
					$("#telError").html("账号不能为空");
					userstaus=0;
				}
				else if(userphone.length < 11 || userphone.length > 11){
					$("#telError").html("账号长度错误");
					userstaus=0;
				}
				else if(!/^1[3|4|5|7|8][0-9]{9}$/.test(userphone)){
					$("#telError").html("账号只能为数字");
					userstaus=0;
					}
}
//弹出框取消按钮事件
$('.popup_de .btn_cancel').click(function(){
    $('.popup_de').removeClass('bbox');
});
//弹出框关闭按钮事件
$('.popup_de .popup_close').click(function(){
    $('.popup_de').removeClass('bbox');
});

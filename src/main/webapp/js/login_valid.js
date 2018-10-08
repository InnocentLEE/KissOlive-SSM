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

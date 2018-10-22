var telflag = false;
var passwordflag = false;
var repasswordflag = false;
var detailaddressflag = false;
var nameflag = false;
var telphoneflag = false;
var verifyCodeflag = false;
var verifyCodeNumber;
$(function() {
    /*
     * 得到所有的错误信息，循环遍历之。调用一个方法来确定是否显示错误信息！
     */
    $(".errorClass").each(function() {
        showError($(this));//遍历每个元素，使用每个元素来调用showError方法
    });

    $("#VerifyCodeButton").attr("disabled",true);

    /*
     * 输入框得到焦点隐藏错误信息
     */
    $(".inputClass").focus(function() {
        var labelId = $(this).attr("id") + "Error";//通过输入框找到对应的label的id
        $("#" + labelId).text(" ");//把label的内容清空！
        showError($("#" + labelId));//隐藏没有信息的label
    });
    /*
     * 得到所有的正确信息，循环遍历之。调用一个方法来确定是否显示正确信息！
     */
    $(".correctClass").each(function() {
        showCorrect($(this));//遍历每个元素，使用每个元素来调用showCorrect方法
    });

    /*
     * 输入框得到焦点隐藏正确信息
     */
    $(".inputClass").focus(function() {
        var labelId = $(this).attr("id") + "Correct";//通过输入框找到对应的label的id
        $("#" + labelId).text(" ");//把label的内容清空！
        showCorrect($("#" + labelId));//隐藏没有信息的label
    });

    /*
     * 输入框失去焦点进行校验
     */
    $(".inputClass").blur(function() {
        var id = $(this).attr("id");//获取当前输入框的id
        var funName = "validate" + id.substring(0,1).toUpperCase() + id.substring(1) + "()";//得到对应的校验函数名
        eval(funName);//执行函数调用
        inputflag = true;
    });

});

function checkform(){

    var obj1 = document.getElementById('submit');
    //alert($(usertelError).text+"\n"+$(passwordError).text+"\n"+$(password2Error).text+"\n"+$(detailError).text+"\n"+$(nameError).text+"\n"+$(telError).text+"\n"+$(mobilebtnError).text);
    if(telflag&&passwordflag&&repasswordflag&&detailaddressflag&&nameflag&&telphoneflag&&verifyCodeflag)
    {
        var usertel = $("#usertel").val();
        var password = $("#password").val();
        var province = $("#province option:selected").val();
        var city = $("#city option:selected").val();
        var district = $("#district option:selected").val();
        var detail = $("#detail").val();
        var name = $("#name").val();
        var tel = $("#tel").val();
        var verifyCode = $("#verifyCode").val();
        $.ajax({
            url:"http://localhost:8080/user/register.do",
            data:{
                phone_number:usertel,
                password:password,
                address_province:province,
                address_city:city,
                address_district:district,
                address_detail:detail,
                address_consignee:name,
                address_telphone:tel,
                verify_code:verifyCode
            },
            type:"POST",
            dataType:"json",
            async:false,//是否异步请求，如果是异步，那么不会等服务器返回，这个函数就向下运行了。
            cache:false,
            success:function(result) {
                  if(result.status==0){
                      alert(result.msg+"  跳转到首页");
                      window.location.href = "http://localhost:8080/home";
                  }
            }
        });
    }
    else{
        alert('警告，存在信息验证有误，请重新输入！！！');
    }

}
function validateUsertel() {
    var id = "usertel";
    var value = $("#" + id).val();
    //获取输入框内容
    var staus = 0; //判断验证情况
    /*
     * 非空校验
     */
    if(!value) {
        /*
         * 获取对应的label
         * 添加错误信息
         * 显示label
         */
        staus = 1;
        $("#" + id + "Error").text("● 手机号码不能为空！");
        showError($("#" + id + "Error"));
        $("#VerifyCodeButton").attr("disabled",true);
        telflag = false;
        return false;
    }
    /*
    *  长度校验
    */
    if(value.length < 11 || value.length > 11) {
        /*
         * 获取对应的label
         * 添加错误信息
         * 显示label
         */
        staus = 1;
        $("#" + id + "Error").text("● 请输入11位正确的手机号码！");
        showError($("#" + id + "Error"));
        $("#VerifyCodeButton").attr("disabled",true);
        telflag = false;
        return false;
    }
    /*
     * 规范校验
     */
    if(!(/^1[3|4|5|7|8][0-9]{9}$/.test(value))) {
        /*
         * 获取对应的label
         * 添加错误信息
         * 显示label
         */
        staus = 1;
        $("#" + id + "Error").text("● 请输入11位正确的手机号码！");
        showError($("#" + id + "Error"));
        $("#VerifyCodeButton").attr("disabled",true);
        telflag = false;
        return false;
    }
    /*
     * 是否已存在校验
     */
    $.ajax({
        url:"http://localhost:8080/user/check_phone_number.do",
        data:{
            phone_number:value
        },
        type:"POST",
        dataType:"json",
        async:false,//是否异步请求，如果是异步，那么不会等服务器返回，这个函数就向下运行了。
        cache:false,
        success:function(result) {
            if(result.status) {
                //如果校验失败
                staus = 1;
                $("#" + id + "Error").text("● 手机号已被注册！");
                showError($("#" + id + "Error"));
                $("#VerifyCodeButton").attr("disabled",true);
                telflag = false;
                //return false;
            }
        }
    });
    if(!staus)
    {
        $("#" + id + "Error").text("");
        $("#" + id + "Correct").text("● 通过信息验证");
        showCorrect($("#" + id + "Correct"));
        $("#VerifyCodeButton").removeAttr("disabled");
    }
    telflag = true;
    return true;
}
function validatePassword() {
    var id = "password";
    var value = $("#" + id).val();//获取输入框内容
    var staus = 0; //判断验证情况
    if(!value) {
        staus = 1;
        $("#" + id + "Error").text("● 密码不能为空！");
        showError($("#" + id + "Error"));
        passwordflag  = false;
        return false;
    }
    /*
     * 规范校验
     */
    if(!/^[a-zA-Z]\w{5,17}$/.test(value)) {
        staus = 1;
        $("#" + id + "Error").text("● 密码以字母开头，长度6-18位，只包含数字、字母或下划线！");
        showError($("#" + id + "Error"));
        passwordflag  = false;
        return false;
    }
    if(!staus)
    {
        $("#" + id + "Error").text("");
        $("#" + id + "Correct").text("● 通过信息验证");
        showCorrect($("#" + id + "Correct"));
    }
    passwordflag = true;
    return true;
}
function validatePassword2() {
    var id = "password2";
    var value = $("#" + id).val();//获取输入框内容
    var staus = 0; //判断验证情况
    if(!value) {
        staus = 1;
        $("#" + id + "Error").text("● 密码不能为空！");
        showError($("#" + id + "Error"));
        repasswordflag  = false;
        return false;
    }
    /*
     * 重复校验
     */
    if(value != $("#password").val()) {
        staus = 1;
        $("#" + id + "Error").text("● 两次输入的密码不一致");
        showError($("#" + id + "Error"));
        repasswordflag  = false;
        return false;
    }
    if(!staus)
    {
        $("#" + id + "Error").text("");
        $("#" + id + "Correct").text("● 通过信息验证");
        showCorrect($("#" + id + "Correct"));
    }
    repasswordflag = true;
    return true;
}
function validateDetail() {
    var id = "detail";
    var value = $("#" + id).val();//获取输入框内容
    var staus = 0; //判断验证情况
    if(!value) {
        staus = 1;
        $("#" + id + "Error").text("● 详细地址不能为空！");
        showError($("#" + id + "Error"));
        detailaddressflag  = false;
        return false;
    }
    if(!staus)
    {
        $("#" + id + "Error").text("");
        $("#" + id + "Correct").text("● 通过信息验证");
        showCorrect($("#" + id + "Correct"));
    }
    detailaddressflag = true;
    return true;

}
function validateName() {
    var id = "name";
    var value = $("#" + id).val();//获取输入框内容
    var staus = 0; //判断验证情况
    if(!value) {
        staus = 1;
        $("#" + id + "Error").text("● 收件人不能为空！");
        showError($("#" + id + "Error"));
        nameflag  = false;
        return false;
    }
    if(!staus)
    {
        $("#" + id + "Error").text("");
        $("#" + id + "Correct").text("● 通过信息验证");
        showCorrect($("#" + id + "Correct"));
    }
    nameflag = true;
    return true;

}
function validateTel() {
    var id = "tel";
    var value = $("#" + id).val();//获取输入框内容
    var staus = 0; //判断验证情况
    if(!value) {
        staus = 1;
        $("#" + id + "Error").text("● 联系方式不能为空！");
        showError($("#" + id + "Error"));
        telphoneflag = false;
        return false;
    }
    /*
     * 规范校验
     */
    if(!(/^1[3|4|5|7|8][0-9]\d{4,8}$/.test(value)))
    {
        staus = 1;
        $("#" + id + "Error").text("● 请输入11位正确的手机号码！");
        showError($("#" + id + "Error"));
        telphoneflag  = false;
        return false;
    }
    if(!staus)
    {
        $("#" + id + "Error").text("");
        $("#" + id + "Correct").text("● 通过信息验证");
        showCorrect($("#" + id + "Correct"));
    }
    telphoneflag = true;
    return true;
}
function validateCode() {
    var id = "verifyCode";
    var value = $("#" + id).val();//获取输入框内容
    var staus = 0; //判断验证情况
    if(!value) {
        staus = 1;
        $("#" + id + "Error").text("● 验证码不能为空！");
        showError($("#" + id + "Error"));
        verifyCodeflag  = false;
        return false;
    }
    if(verifyCodeNumber != value){
        staus = 1;
        $("#" + id + "Error").text("错误的验证码！");
        showError($("#" + id + "Error"));
        verifyCodeflag  = false;
        return false;
    }
    if(!staus)
    {
        $("#" + id + "Error").text("");
        $("#" + id + "Correct").text("● 通过信息验证");
        showCorrect($("#" + id + "Correct"));
    }
    verifyCodeflag = true;
    return true;

}
function sentUserpassword(){
    //发送用户密码到手机调用后台函数
    var id = "tel";
    var value = $("#" + id).val();
    /*
     * ajax获取手机号码到后台，以便后台对忘记密码问题进行处理
     */
    $.ajax({
        url:"/KissOlive/servlet/UserServlet",
        data:{method:"findpassword", tel:value},
        type:"POST",
        dataType:"json",
        async:false,//是否异步请求，如果是异步，那么不会等服务器返回，这个函数就向下运行了。
        cache:false,
        success:function(result) {
            if(!result) {
                //如果校验失败
                staus = 1;
                $("#" + id + "Error").text("● 用户不存在");
                showError($("#" + id + "Error"));
                submitflag = false;
                return false;
            }
            else{
                alert('密码已发送至手机！请自行查看！');
            }
        }
    });
}
function sentVerifyCode(){
    //发送验证码调用后台函数
    var id = "usertel";
    var value = $("#" + id).val();//获取输入框内容

    $.ajax({
        url:"http://localhost:8080/user/sent_verify_code.do",//要请求的servlet
        data:{
            phone_number:value
        },//给服务器的参数
        type:"POST",
        dataType:"json",
        async:false,//是否异步请求，如果是异步，那么不会等服务器返回，我们这个函数就向下运行了。
        cache:false,
        success:function(result) {
            if(result.status) {//如果校验失败
                $("#" + id + "Error").text("● 发送验证码失败");
                showError($("#" + id + "Error"));
                submitflag = false;
                return false;
            }else{
                verifyCodeNumber = result.data.verifyCode;
                //alert(verifyCodeNumber);
            }
        }
    });
}
function showError(ele) {
    var text = ele.text();//获取元素的内容
    if(!text) {//如果没有内容
        ele.css("display", "none");//隐藏元素
    } else {//如果有内容
        ele.css("display", "");//显示元素
    }
}
function showCorrect(ele) {
    var text = ele.text();//获取元素的内容
    if(!text) {//如果没有内容
        ele.css("display", "none");//隐藏元素
    } else {//如果有内容
        ele.css("display", "");//显示元素
    }
}

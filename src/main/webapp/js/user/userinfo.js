$(function () {

    $(".addr_choice").citySelect({
        prov: "广东",
        city: "肇庆",
        dist: "端州区",
        nodata: "none"
    });

});

function add_addr() {
    $(".div_add_addr").slideToggle();
}

function edit_name(){
    $(".editname").slideToggle();
    $("#update_username").slideToggle();
}

function edit_phone(){
    $(".editphone").slideToggle();
    $("#update_userphone").slideToggle();
}

$("button[class='edit']").click(function () {
    $('#addrModal').modal('show');

});
$("button[class='sure']").click(function () {
    $('#addrModal').modal('hide');

});

function cancel_add_addr() {
    $(".div_add_addr").slideToggle();
}
var addresslist= new Vue({
    el:"#addresslist",
    data:{
        addresslist:[]
    },
    created:function () {
        this.getAddresslist();
    },
    methods:{
        getAddresslist:function () {
            $.ajax({
                type:"post",
                url:"http://localhost:8080/user/get_address_list.do",
                cache: false,
                dataType:'json',
                success :function (data) {
                    if(data.status==0){
                        addresslist.addresslist = data.data;
                        addresslist.updateAddr();
                    }else{
                        alert(data.msg);
                    }
                },
                error:function(){
                    alert("获取异常");
                }
            })
        },
        editaddr:function (index) {
            //editindex = index;
            $("#editaddr" + index).slideToggle();
            $("#addr" + index).slideToggle();
        },
        canceladdr:function(index){
            $("#editaddr" + index).slideToggle();
            $("#addr" + index).slideToggle();
        },
        doEditaddr:function (index){
            var editname = $("#editname"+index).val();
            var edittel = $("#edittel"+index).val();
            var eitdprovince = $("#eitdprovince"+index).find("option:selected").text();
            var eitdcity = $("#eitdcity"+index).find("option:selected").text();
            var eitddistrict = $("#eitddistrict"+index).find("option:selected").text();
            var eitddetail = $("#eitddetail"+index).val();
            //alert(editname+"    "+edittel+"    "+eitdprovince+"    "+eitdcity+"    "+eitddistrict+"    "+eitddetail);
            $.ajax({
                type:"post",
                url:"http://localhost:8080/user/update_address.do",
                data:{
                    address_province:eitdprovince,
                    address_city:eitdcity,
                    address_detail:eitddetail,
                    address_telphone:edittel,
                    address_id:index,
                    address_district:eitddistrict,
                    address_consignee:editname
                },
                dataType:'json',
                success: function(data) {
                    if(data.status==0) {
                        alert(data.msg);
                       window.location.reload();
                    }
                },
                error:function(){
                    alert("系统异常");
                }
            });
        },
        updateAddr:function () {
            this.$nextTick(function () {
                $(".edit_addr").citySelect({
                    prov: "广东",
                    city: "肇庆",
                    dist: "端州区",
                    nodata: "none"
                });
            })
        }
        
    }
})
var user = new Vue({
    el:"#user",
    data:{
        user:[]
    },
    created:function () {
        this.getUser();
    },
    methods:{
        getUser:function () {
            $.ajax({
                type:"post",
                url:"http://localhost:8080/user/get_info.do",
                dataType:'json',
                success: function(data) {
                    if(data.status==0) {
                        user.user = data.data.user;
                    }
                },
                error:function(){
                    alert("登录异常");
                }
            });
        }
    }
})
function saveUsername() {
    var username = $("#username").val();
    $.ajax({
        type:"post",
        url:"http://localhost:8080/user/update_username.do",
        data:{
            username:username
        },
        cache: false,
        dataType:'json',
        success: function(data) {
            if(data.status==0) {
                alert(data.msg);
                window.location.reload();
            }else
                alert(data.msg);
        },
        error:function(){
            alert("系统异常");
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
var telflag = false;
var verifyCodeflag = false;
var verifyCodeNumber;
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
                return false;
            }else{
                verifyCodeNumber = result.data.verifyCode;
                alert(verifyCodeNumber);
            }
        }
    });
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
        $("#" + id + "Correct").text("");
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
        $("#" + id + "Correct").text("");
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
                $("#" + id + "Correct").text("");
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
function validateCode() {
    var id = "verifyCode";
    var value = $("#" + id).val();//获取输入框内容
    var staus = 0; //判断验证情况
    if(!value) {
        staus = 1;
        $("#" + id + "Error").text("● 验证码不能为空！");
        $("#" + id + "Correct").text("");
        showError($("#" + id + "Error"));
        verifyCodeflag  = false;
        return false;
    }
    if(verifyCodeNumber != value){
        staus = 1;
        $("#" + id + "Error").text("错误的验证码！");
        $("#" + id + "Correct").text("");
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
function changeTel() {
    if(telflag&&verifyCodeflag){
        alert("修改手机号");
    }
}
function saveAddr() {
    var editname = $("#addname").val();
    var edittel = $("#addtel").val();
    var eitdprovince = $("#addprovince").find("option:selected").text();
    var eitdcity = $("#addcity").find("option:selected").text();
    var eitddistrict = $("#adddistrict").find("option:selected").text();
    var eitddetail = $("#detail").val();
    //alert(editname+"    "+edittel+"    "+eitdprovince+"    "+eitdcity+"    "+eitddistrict+"    "+eitddetail);
    $.ajax({
        type:"post",
        url:"http://localhost:8080/user/add_address.do",
        data:{
            address_province:eitdprovince,
            address_city:eitdcity,
            address_detail:eitddetail,
            address_telphone:edittel,
            address_district:eitddistrict,
            address_consignee:editname
        },
        dataType:'json',
        success: function(data) {
            if(data.status==0) {
                alert(data.msg);
                window.location.reload();
            }
        },
        error:function(){
            alert("系统异常");
        }
    });
}

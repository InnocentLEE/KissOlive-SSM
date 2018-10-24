var search1 = new Vue({
    el:"#search",
    data:{
        search:[]
    }
})
var headImg;
var head = new Vue({
    el:"#head",
    data:{
        loginflag:0,
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
                        head.loginflag = 1;
                        head.user = data.data.user;
                        headImg = head.user.imgUrl;
                    }else{
                        head.loginflag = 0;
                    }
                },
                error:function(){
                    alert("登录异常");
                }
            });
        }
    }
})

function regis() {
    window.location.href = "http://localhost:8080/register";
}

function  login() {
    var phone_number = $("#phone_number").val();
    var password = $("#password").val();
    if(phone_number == "" || password == ""){
        alert(phone_number+"   "+password +"sdfsdf");
        $("#passwordError").text("账号或者密码不能为空！！！");
        return;
    }
    $.ajax({
        type:"post",
        url:"http://localhost:8080/user/login.do",
        data:{
            phone_number:phone_number,
            password:password
        },
        dataType:'json',
        success: function(data) {
            if(data.status==0) {
                //alert(data.msg);
                $('#loginModal').modal('hide');
                window.location.reload();
            }else{
                alert(data.msg);
                $("#passwordError").text(data.msg);
            }
        },
        error:function(){
            alert("登录异常");
        }
    })

}
function logout() {
    $.ajax({
        type:"post",
        url:"http://localhost:8080/user/logout.do",
        dataType:'json',
        success: function(data) {
            window.location.reload();
        }
    })
}
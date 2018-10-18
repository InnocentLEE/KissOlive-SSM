var search1 = new Vue({
    el:"#search",
    data:{
        search:[]
    }
})
var brand = new Vue({
    el:"#dropdownList",
    data:{
        brand:[]
    },
    created:function () {
        this.getBrand();
    },
    methods:{
        getBrand:function () {
            $.ajax({
                type:'post',
                url:'http://localhost:8080/production/get_brand_put_on.do',
                cache: false,
                dataType:'json',
                success: function(data) {
                    if(data.status==0) {
                        brand.brand = data.data;
                        brand.updateBrand();
                    }
                },
                error:function(){
                    alert("获取异常");
                }
            });
        },
        updateBrand:function () {
            this.$nextTick(function () {
                $('.box').hover(
                    function () {
                        //alert("123");
                        var overlay = $(this).find('.box-overlay');
                        overlay.removeClass(overlay.data('return')).addClass(overlay.data('hover'));
                    },
                    function () {
                        //alert("1235");
                        var overlay = $(this).find('.box-overlay');
                        overlay.removeClass(overlay.data('hover')).addClass(overlay.data('return'));
                    }
                );
            })
        }
    }
})
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
                alert(data.msg);
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
        },
        error:function(){
            alert("退出异常");
        }
    })
}
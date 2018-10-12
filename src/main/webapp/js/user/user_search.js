function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null;
}
var search=getUrlParam('search');
var search1 = new Vue({
    el:"#search",
    data:{
        search:[]
    }
})
var rcontent = new Vue({
    el:"#rcontent",
    data:{
        rcontent:[],
        ContentLength:[]
    },
    created:function () {
        this.init();
    },
    methods:{
        init:function () {
            if(search!=null){
                this.getSearchContent();
            }else{
                this.getBrandContent();
            }
        },
        getSearchContent:function () {
            doGetSearchContent();
        },
        getBrandContent:function () {
            doGetBrandContent();
        }
    }
})
function doGetSearchContent() {
    $.ajax({
        type:'post',
        url:'http://localhost:8080/production/search_productions.do',
        data:{
            search:search
        },
        cache: false,
        dataType:'json',
        success: function(data) {
            if(data.status==0) {
                rcontent.rcontent = data.data;
                rcontent.ContentLength = data.data.length;
            }
        },
        error:function(){
            alert("获取异常");
        }
    });
}
function doGetBrandContent() {
    $.ajax({
        type:'post',
        url:'http://localhost:8080/production/get_brand_put_on.do',
        cache: false,
        dataType:'json',
        success: function(data) {
            if(data.status==0) {
                rcontent.rcontent = data.data;
            }
        },
        error:function(){
            alert("获取异常");
        }
    });
}
var brand = new Vue({
    el:"#brandlist",
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
                    }
                },
                error:function(){
                    alert("获取异常");
                }
            });
        }
    }
})


















function regis() {
    window.location.href = "http://localhost:8080/KissOlive/page/user/user_register.jsp";
}
/*
 * 登录滑动验证码验证结果判断
 */
$(function() {
    var slider = new SliderUnlock("#slider", {
        successLabelTip: "验证通过"
    }, function() {
        //alert("验证成功,即将跳转至商城首页");
        //window.location.href="http://localhost:8080/KissOlive/page/user/user_home.jsp"
        //document.getElementByIdx("btn").disabled=true;
        $("#submit").attr("disabled", false);
    });
    slider.init();
    $("#li_login").click(function() {
        $('#loginModal').modal('show');
    });
})

$('#mpanel2').codeVerify({
    type: 1,
    width: '200px',
    height: '50px',
    fontSize: '30px',
    codeLength: 6,
    btnId: 'check-btn',
    ready: function() {},
    success: function() {

        $('#myModal').modal('hide');

    },
    error: function() {
        alert('验证码不匹配！');
    }
});
//$(function () { $('#collapseOne').collapse('hide')});

$("#a_brandlist").click(function(){

    if($("#collapseOne").attr("class")=="panel-collapse collapse"){
        $(this).find("span").removeClass("addicon");
        $(this).find("span").addClass("minusicon");

    }else{
        $(this).find("span").removeClass("minusicon");
        $(this).find("span").addClass("addicon");
    }
});
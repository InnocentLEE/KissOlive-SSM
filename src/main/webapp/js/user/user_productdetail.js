function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null;
}
$("#li_login").click(function() {
    $('#loginModal').modal('show');
});
var id = getUrlParam('id');
var content = new Vue({
    el:"#content",
    data:{
        content:[],
        assess:[]
    },
    created:function () {
        this.getProductionDeatail();
        //this.getProductionAssess();
    },
    methods:{
        getProductionDeatail:function () {
            $.ajax({
                type:"post",
                url:"http://localhost:8080/production/get_production_show.do",
                data:{
                    production_id:id
                },
                dataType:'json',
                success:function (data) {
                    content.content = data.data;
                    content.updateDetail();
                },
                error:function () {
                    alert("获取异常");
                }
            })
        },
        getProductionAssess:function(){
            $.ajax({
                type:"post",
                url:"http://localhost:8080/production/get_production_assess.do",
                data:{
                    production_id:id
                },
                dataType:'json',
                success:function (data) {
                    content.assess = data.data;
                },
                error:function () {
                    alert("获取异常");
                }
            })
        },
        updateDetail:function () {
            this.$nextTick(function () {
                document.getElementById("gprice").innerHTML = "选择颜色查看价钱";
                document.getElementById("production_detail").innerHTML = content.content.detail;
                changeAmount();
            })
        }
    }
})
function changeAmount() {
    //获得文本框对象
    var t = $("#gnumber");
    //初始化数量为1,并失效减
    $('#reduce').attr('disabled', true);

    //数量增加操作
    $("#plus").click(function () {
        // 给获取的val加上绝对值，避免出现负数
        t.val(Math.abs(parseInt(t.val())) + 1);
        if (parseInt(t.val()) != 1) {
            $('#reduce').attr('disabled', false);
        }
        ;
    })
    //数量减少操作
    $("#reduce").click(function () {
        t.val(Math.abs(parseInt(t.val())) - 1);
        if (parseInt(t.val()) == 1) {
            $('#reduce').attr('disabled', true);
        }
        ;
    })
}
function selectColor(obj) {
    $(obj).addClass("selected").siblings().removeClass("selected");
    //var value = $('#wrap input[name="gid"]:checked').attr("id");
    document.getElementById("gprice").innerHTML = "￥ " + $(obj).attr("value") + "元";
}
function valid() {
    var price = $('#gprice').text();

    if (head.loginflag == 0) {
        alert("您还没有登录！");
    } else if (price == "选择颜色查看价钱") {
        alert("请选择想购买的口红颜色！");
    } else {
        doSubmit();
    }
}
function doSubmit() {
    var gid = $("li[class='color-item selected']").attr("id"); //获取商品id
    var gnumber = $('#gnumber').val(); //获取商品当前数量gnmber
    $.ajax({
        type:"post",
        url:"http://localhost:8080/shoppingCart/add_card.do",
        data:{
            goodsId:gid,
            num:gnumber
        },
        dataType:'json',
        success:function (data) {
            alert("添加成功");
            window.location.href="http://localhost:8080/myshoppingcart";
        },
        error:function () {
            alert("添加异常");
        }
    })
}
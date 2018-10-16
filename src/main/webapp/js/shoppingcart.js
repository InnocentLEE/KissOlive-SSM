var search1 = new Vue({
    el:"#searchform",
    data:{
        search:[]
    }
})
var selectid = null;
var myTabContent = new Vue({
    el:"#myTabContent",
    data:{
        myTabContent:[],
        ContentLength:[]
    },
    created:function () {
        this.getMyShoppingCart();
    },
    methods:{
        getMyShoppingCart:function () {
            doGetMyShoppingCart();
        },
        updateMyShoppingCart:function () {
            this.$nextTick(function (){
                doUpdate();
                totalMoney();
            })
        },
        toId:function (id) {
            selectid = id;
        }
    }
})
function del() {
    //alert(selectid);
    var cardIds = new Array();
    cardIds.push(selectid);
    $.ajax({
        type:'post',
        url:'http://localhost:8080/shoppingCart/delete_card_ByBatch.do',
        data:{
            cardIds: cardIds
        },
        cache: false,
        traditional: true,
        dataType:'json',
        success: function(data) {
            if(data.status==0) {
                $("#"+selectid).remove();
                alert(data.msg);
                $("#myModal1").modal('hide');
                totalMoney();
            }
        },
        error:function(){
            alert("删除异常");
        }
    });
}
function doGetMyShoppingCart(){
    $.ajax({
        type:'get',
        url:'http://localhost:8080/shoppingCart/get_myCard.do',
        cache: false,
        dataType:'json',
        success: function(data) {
            if(data.status==0) {
                myTabContent.myTabContent = data.data;
                if(typeof(data.data) != "undefined")
                    myTabContent.ContentLength = data.data.length;
                else
                    myTabContent.ContentLength = 0;
                myTabContent.updateMyShoppingCart();
            }
        },
        error:function(){
            alert("获取异常");
        }
    });
}
function doUpdate(){

    //全局的checkbox选中和未选中的样式
    var $allCheckbox = $('input[type="checkbox"]'),     //全局的全部checkbox
        $wholeChexbox = $('.whole_check');
    //$cartBox = $('.cartBox'),                       //每个商铺盒子
    //$shopCheckbox = $('.shopChoice'),               //每个商铺的checkbox
    $sonCheckBox = $('.son_check');                 //每个商铺下的商品的checkbox
    // $("i.far.fa-check-square").hide();

    //===============================================全局全选与单个商品的关系================================
    $wholeChexbox.click(function () {
        /*	var txt1="<i class=\"far fa-square\"></i>";
            var txt2="<i class=\"far fa-check-square\"></i>";*/

        if ($(this).is(':checked')) {
            $sonCheckBox.each(function () {
                $(this).prop("checked", true);
            });
        } else {
            $sonCheckBox.each(function () {
                $(this).prop("checked", false);
            });
        }
        totalMoney();
    });

    $sonCheckBox.each(function () {
        $(this).click(function () {
            if ($(this).is(':checked')) {
                //判断：所有单个商品是否勾选
                var len = $sonCheckBox.length;
                var num = 0;
                $sonCheckBox.each(function () {
                    if ($(this).is(':checked')) {
                        num++;
                    }
                });
                if (num == len) {
                    $wholeChexbox.prop("checked", true);
                }
            } else {
                //单个商品取消勾选，全局全选取消勾选
                $wholeChexbox.prop("checked", false);

            }
            totalMoney();
        })
    });

    //=================================================商品数量==============================================
    var $plus = $('.plus'),
        $reduce = $('.reduce'),
        $all_sum = $('.sum');
    $plus.click(function () {
        var $inputVal = $(this).prev('input'),
            $count = parseFloat($inputVal.val()) + 1,
            $obj = $(this).parents('.amount_box').find('.reduce'),
            $priceTotalObj = $(this).parents('.order_lists').find('.sum_price'),
            $price = $(this).parents('.order_lists').find('.price').html(),  //单价
            $priceTotal = $count * parseFloat($price.substring(1));
        $cid = $(this).attr("id");
        $inputVal.val($count);
        // console.log($count);

        //数量变化ajax
        $.ajax({
            url: "/KissOlive/MainServlet",//要请求的servlet
            data: {method: "ajaxUpdateCart", number: $count, cid: $cid},//给服务器的参数
            type: "POST",
            dataType: "json",
            async: false,//是否异步请求，如果是异步，那么不会等服务器返回，这个函数就向下运行了。
            cache: false,
            success: function (result) {
                if (!result) {//如果校验失败
                    alert("数量增加失败！请重试！");
                    return false;
                }
            }
        });
        $priceTotal = $priceTotal.toFixed(2);
        $priceTotalObj.html('￥' + $priceTotal);
        if ($inputVal.val() > 1 && $obj.hasClass('reSty')) {
            $obj.removeClass('reSty');
        }
        totalMoney();
    });

    $reduce.click(function () {
        var $inputVal = $(this).next('input'),
            $count = parseFloat($inputVal.val()) - 1,
            $priceTotalObj = $(this).parents('.order_lists').find('.sum_price'),
            $price = $(this).parents('.order_lists').find('.price').html(),  //单价
            $priceTotal = $count * parseFloat($price.substring(1));
        $cid = $(this).attr("id");

        if ($inputVal.val() > 1) {
            $inputVal.val($count);
            // console.log($count);

            //数量变化ajax
            $.ajax({
                url: "/KissOlive/MainServlet",//要请求的servlet
                data: {method: "ajaxUpdateCart", number: $count, cid: $cid},//给服务器的参数
                type: "POST",
                dataType: "json",
                async: false,//是否异步请求，如果是异步，那么不会等服务器返回，这个函数就向下运行了。
                cache: false,
                success: function (result) {
                    if (!result) {//如果校验失败
                        alert("数量减少失败！请重试！");
                        return false;
                    }
                }
            });
            $priceTotal = $priceTotal.toFixed(2);
            $priceTotalObj.html('￥' + $priceTotal);
        }
        if ($inputVal.val() == 1 && !$(this).hasClass('reSty')) {
            $(this).addClass('reSty');
        }
        totalMoney();
    });

    $all_sum.keyup(function () {
        var $count = 0.00,
            $priceTotalObj = $(this).parents('.order_lists').find('.sum_price'),
            $price = $(this).parents('.order_lists').find('.price').html(),  //单价
            $priceTotal = 0.00;
        if ($(this).val() == '') {
            $(this).val('1');
        }
        $(this).val($(this).val().replace(/\D|^0/g, ''));
        $count = $(this).val();
        $priceTotal = $count * parseFloat($price.substring(1));
        $(this).attr('value', $count);
        $priceTotal = $priceTotal.toFixed(2);
        $priceTotalObj.html('￥' + $priceTotal);
        totalMoney();

    })
}

//======================================总计==========================================

function totalMoney() {
    var total_money = 0.00;
    var total_count = 0.00;
    var select = $(".son_check");
    for(var i=0;i<select.length;i++){
        if(select[i].checked){
            var temp = $(select[i]).parents("td").siblings();
            total_count += parseFloat($(temp[3]).find(".sum").val());
            total_money += parseFloat($(temp[4]).find('.sum_price').html().substring(1));
        }
    }
    total_money = total_money.toFixed(2);
    //alert(total_money+"    "+total_count);
    $('.total_text').html('￥' + total_money);
    $('.piece_num').html(total_count);

    // console.log(total_money,total_count);

}

function settlement(){
    var judge = 0;
    $(".son_check").each(function () { //遍历每个元素
        if ($('.son_check').is(':checked')) {
            // 如果该商品被选中
            judge = 1;
        }
    });
    if (judge == 0)
        alert("您未选中任何商品，请选择商品再点击购买！！");
    else {
        doSettlement();
    }
}
function doSettlement() {
    var jsonObj = new Array();
    var select = $(".son_check");
    var card = new Array();
    for(var i=0;i<select.length;i++){
        if(select[i].checked){
            var temp = $(select[i]).parents("td").siblings();
            var id = $(select[i]).parents("tr").prop("id");
            var temp = {
                id : id,
                goodsId : $(select[i]).val(),
                number: parseFloat($(temp[3]).find(".sum").val()),
                price:parseFloat($(temp[4]).find('.sum_price').html().substring(1)),
            };
            card.push(temp);
        }
    }
    var price = parseFloat($('.total_text').html().substring(1));
    var temp = {
        price : price,
        items : card
    };
    console.log(JSON.stringify(temp));
    $.ajax({
        type : "post",
        url : "http://localhost:8080/order",
        data : JSON.stringify(temp),//将orderItems转换为JSON字符串
        contentType : 'application/json;charset=utf-8',
        dataType : "json",// 数据类型可以为 text xml json script jsonp
        success : function(result) {// 返回的参数就是 action里面所有的有get和set方法的参数
           if(result.status==0){
               alert("下单成功！");
               window.location.href = "http://localhost:8080/pay?id="+result.data.orderId;
           }else {
               alert(result.msg);
           }
        }
    });
}
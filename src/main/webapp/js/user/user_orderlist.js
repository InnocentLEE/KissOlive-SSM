var page = 1;
var size = 100;
var orderlist = new Vue({
    el:"#orderlist",
    data:{
        orderlist1:[],
        orderlist2:[],
        orderlist3:[],
        orderlist4:[],
        orderlist5:[],
        orderlist0:[],
        page:1,
        size:100
    },
    created:function () {
        this.getOrderlist(0);
        this.getOrderlist(1);
        this.getOrderlist(2);
        this.getOrderlist(3);
        this.getOrderlist(4);
        this.getOrderlist(-1);
    },
    methods:{
        getOrderlist:function (status) {
            doGetOrderlist(status);
        },
        getDetail:function (id) {
            window.location.href="http://localhost:8080/order_detail?id="+id;
        },
        cancelOrder:function (id) {
            var text="确定取消该订单吗?";
            document.getElementById('show_msg').innerHTML=text;
            $('#tip').addClass('bbox');
            $('#tipbtn').one('click',function(){
                doCancel(id);
            })
        },
        payOrder:function (id) {
            window.location.href="http://localhost:8080/pay?id="+id;
        },
        receivedOrder:function (id) {
            var text="确定收货吗?";
            document.getElementById('show_msg').innerHTML=text;
            $('#tip').addClass('bbox');
            $('#tipbtn').one('click',function(){
                doReceived(id);
            })
        }
    }
})
//弹出框取消按钮事件
$('.popup_de .btn_cancel').click(function(){
    $('.popup_de').removeClass('bbox');
});
//弹出框关闭按钮事件
$('.popup_de .popup_close').click(function(){
    $('.popup_de').removeClass('bbox');
});
function doReceived(id) {
    $.ajax({
        type:"put",
        url:"http://localhost:8080/order/receive/"+id,
        cache: false,
        dataType:'json',
        success :function (data) {
            if(data.status==0){
                window.location.reload();
            }else{
                alert(data.msg);
            }
        }
    })
}
function doCancel(id) {
    $.ajax({
        type:"put",
        url:"http://localhost:8080/order/cancel/"+id,
        cache: false,
        dataType:'json',
        success :function (data) {
            if(data.status==0){
               window.location.reload();
            }else{
                alert(data.msg);
            }
        }
    })
}
function doGetOrderlist(status) {
    $.ajax({
        type:"get",
        url:"http://localhost:8080/orders/"+status+"/"+page+"/"+size,
        cache: false,
        dataType:'json',
        success :function (data) {
            if(data.status==0){
                if(status==0)
                    orderlist.orderlist1 = data.data;
                else if(status==1)
                    orderlist.orderlist2 = data.data;
                else if(status==2)
                    orderlist.orderlist3 = data.data;
                else if(status==3)
                    orderlist.orderlist4 = data.data;
                else if(status==4)
                    orderlist.orderlist5 = data.data;
                else if(status==-1)
                    orderlist.orderlist0= data.data;
            }else{
                alert(data.msg);
            }
        }
    })
}
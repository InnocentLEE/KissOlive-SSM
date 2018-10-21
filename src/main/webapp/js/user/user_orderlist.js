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
        page:1,
        size:100
    },
    created:function () {
        this.getOrderlist(0);
        this.getOrderlist(1);
        this.getOrderlist(2);
        this.getOrderlist(3);
        this.getOrderlist(4);
    },
    methods:{
        getOrderlist:function (status) {
            doGetOrderlist(status);
        },
        getDetail:function (id) {
            window.location.href="http://localhost:8080/order_detail?id="+id;
        },
        cancelOrder:function (id) {
            alert("已取消");
        },
        payOrder:function (id) {
            window.location.href="http://localhost:8080/pay?id="+id;
        },
        receivedOrder:function (id) {
            alert("已确认");
        }
    }
})
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
            }else{
                alert(data.msg);
            }
        }
    })
}
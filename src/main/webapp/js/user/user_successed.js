function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null;
}
var id = getUrlParam('id');
var myTabContent= new Vue({
    el:"#container",
    data:{
        orderinfo:[],
        QRcode:[]
    },
    created:function () {
        this.getOrder();
        this.getQR_code();
    },
    methods:{
        getOrder:function () {
            $.ajax({
                type:"get",
                url:"http://localhost:8080/order/info/"+id,
                cache: false,
                dataType:'json',
                success :function (data) {
                    if(data.status==0){
                        myTabContent.orderinfo = data.data;
                    }else{
                        alert(data.msg);
                    }
                }
            })
        },
        getQR_code:function () {
            $.ajax({
                type:"post",
                url:"http://localhost:8080/pay.do",
                data:{
                    order_id:id
                },
                cache: false,
                dataType:'json',
                success :function (data) {
                    if(data.status==0){
                        myTabContent.QRcode = data.data;
                    }else{
                        alert(data.msg);
                    }
                }
            })
        }
    }
})
//var temp =1;
function judgePay() {
    $.ajax({
        type:"post",
        url:"http://localhost:8080/query_order_pay_status.do",
        data:{
            order_id:id
        },
        cache: false,
        dataType:'json',
        success :function (data) {
            if(data.data==true){
                alert("支付成功");
                clearInterval(time);
            }
        }
    })
   /*alert(temp);temp+=1;
   if(temp==3){
       clearInterval(time);
   }*/
}
var time = setInterval("judgePay()","2000");
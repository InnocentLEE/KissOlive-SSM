function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null;
}
var id = getUrlParam('id');
var myTabContent= new Vue({
    el:"#myTabContent",
    data:{
        orderinfo:[],
        productionlist:[],
        productionnum:[]
    },
    created:function () {
        this.getOrderInfo();
        this.getOrder();
    },
    methods:{
       getOrder:function () {
            $.ajax({
                type:"get",
                url:"http://localhost:8080/order/"+id,
                cache: false,
                dataType:'json',
                success :function (data) {
                    if(data.status==0){
                        myTabContent.productionlist = data.data;
                        myTabContent.productionnum = data.data.goods.length;
                        console.log("productionlist ="+data.data.toString());
                    }else{
                        alert(data.msg);
                    }
                }
            })
        },
        getOnePrice:function(price,num){
            return price/num;
        },
        getOrderInfo:function () {
            $.ajax({
                type:"get",
                url:"http://localhost:8080/order/info/"+id,
                cache: false,
                dataType:'json',
                success :function (data) {
                    if(data.status==0){
                        myTabContent.orderinfo = data.data;
                        console.log("orderinfo="+data.data.toString());
                    }else{
                        alert(data.msg);
                    }
                }
            })
        }
    }
})
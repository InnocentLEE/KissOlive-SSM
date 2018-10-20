function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null;
}
var id = getUrlParam('id');
function judge(){
    var addrid = $("input[name='selectaddr']:checked").val();
    $.ajax({
        type:"put",
        url:"http://localhost:8080/order/"+id+"/"+addrid,
        cache: false,
        dataType:'json',
        success :function (data) {
            if(data.status==0){
                window.location.href = "http://localhost:8080/pay_success?id="+id;
            }else{
                alert("提交失败，系统异常！");
            }
        }
    })
}
var myTabContent= new Vue({
    el:"#myTabContent",
    data:{
        addresslist:[],
        productionlist:[],
        productionnum:[]
    },
    created:function () {
        this.getAddresslist();
        this.getOrder();
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
                        myTabContent.addresslist = data.data;
                    }else{
                        alert(data.msg);
                    }
                }
            })
        },
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
                    }else{
                        alert(data.msg);
                    }
                }
            })
        },
        getOnePrice:function(price,num){
            return price/num;
        }
    }
})
$(function () {

    $(".addr_choice").citySelect({
        prov: "广东",
        city: "肇庆",
        dist: "端州区",
        nodata: "none"
    });

});

function add_addr() {
    $(".div_add_addr").slideToggle();
}

$("#update").click(function () {
    $(".editname").slideToggle();
    $("#update_username").slideToggle();
});
$("button[class='edit']").click(function () {
    $('#addrModal').modal('show');

});
$("button[class='sure']").click(function () {
    $('#addrModal').modal('hide');

});


function cancel() {
    $(".editname").slideToggle();
    $("#update_username").slideToggle();
}

function cancel_add_addr() {
    $(".div_add_addr").slideToggle();
}
var addresslist= new Vue({
    el:"#addresslist",
    data:{
        addresslist:[]
    },
    created:function () {
        this.getAddresslist();
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
                        addresslist.addresslist = data.data;
                    }else{
                        alert(data.msg);
                    }
                },
                error:function(){
                    alert("获取异常");
                }
            })
        },
        editaddr:function (index) {
            //editindex = index;
            $("#editaddr" + index).slideToggle();
            $("#addr" + index).slideToggle();
        },
        canceladdr:function(index){
            $("#editaddr" + index).slideToggle();
            $("#addr" + index).slideToggle();
        }
        
    }
})

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

function edit_name(){
    $(".editname").slideToggle();
    $("#update_username").slideToggle();
}

function edit_phone(){
    $(".editphone").slideToggle();
    $("#update_userphone").slideToggle();
}

$("button[class='edit']").click(function () {
    $('#addrModal').modal('show');

});
$("button[class='sure']").click(function () {
    $('#addrModal').modal('hide');

});

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
var user = new Vue({
    el:"#user",
    data:{
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
                        user.user = data.data.user;
                    }
                },
                error:function(){
                    alert("登录异常");
                }
            });
        }
    }
})


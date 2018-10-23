var head = new Vue({
    el:"#admin_index",
    data:{
        loginflag:0,
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
                        head.loginflag = 1;
                        head.user = data.data.user;
                    }else{
                        head.loginflag = 0;
                    }
                },
                error:function(){
                    alert("登录异常");
                }
            });
        }
    }
})
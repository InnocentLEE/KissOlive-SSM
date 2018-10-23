//用户列表
var setlist = new Vue({
    el:"#chatlist",
    data:{
        userlist:[]
    },
    created:function () {
        this.getList();
    },
    methods:{
        getList:function () {
            $.ajax({
                type:'get',
                url:'http://localhost:8080/chat/admin/get_userList.do',
                cache: false,
                dataType:'json',
                success: function(data) {
                    if(data.status==0) {
                        console.log()
                        setlist.userlist = data.data;
                    }
                },
                error:function(){
                    console.log("客服获取用户列表错误！")
                }
            });
        }
    }
});

//聊天框获取
var id;
var username;
function build_chatDIV(obj) {
    id = $(obj).attr('href').substring(2);
    //判断是否已存在该ID标签页
    if( !document.getElementById("U"+id) ) {
        //不存在则删除生成
/*        $(".tab-pane").attr("id","U"+id);
        var dom = document.getElementById("chat-content");
        while(dom.hasChildNodes()){
            dom.removeChild(dom.firstChild);
        }*/
        var dom = document.getElementById("myTabContent");
        while(dom.hasChildNodes()){
            dom.removeChild(dom.firstChild);
        }

        $("#myTabContent").append("" +
            "<div class='tab-pane fade ' id='U" + id + "' >" +
            " <div class=\"div-chat-title\">" +
            " <p class=\"p-chat-object\">"+ $(obj).text().substring(0,$(obj).text().length-1)+"</p>" +
            " </div>"+
            "<div id='chat-content' class='chat-content'>" +
            "</div>" +
            "<div id='msgpanel'>" +
            "<textarea autoHeight='true' class='chatinput emotion' id='chatinput' placeholder='输入回复信息..'></textarea>" +
            "<div class='tools-box'>" +
            "<span class='face-icon tool-icon' id='face'>☺</span>" +
            "<button type=\"button\" class=\"btn-send\" id=\"analytic\" onclick=\"out()\">发送</button>" +
            "</div>" +
            "</div>"
        );
        $('#face').SinaEmotion($('.emotion'));
        $(function() {
            $.fn.autoHeight = function() {
                function autoHeight(elem) {
                    elem.style.height = 'auto';
                    //elem.scrollTop = 0; //防抖动
                    elem.style.height = elem.scrollHeight + 'px';
                }
                this.each(function() {
                    autoHeight(this);
                    $(this).on('keyup', function() {
                        autoHeight(this);
                    });
                });
            }
           /* $('textarea[autoHeight]').autoHeight();*/
        });

        //获取初始化数据
        $.ajax({
            type:'get',
            url:'http://localhost:8080/chat/admin/get_AllMessage/'+id,
            cache: false,
            dataType:'json',
            success: function(data) {
                if(data.status === 0) {
                    console.log("获取用户："+id+"历史消息成功 打印名字："+$(obj).text().substring(0,$(obj).text().length-1));
                    console.log("obj.text=="+$(obj).text().substring(0,$(obj).text().length-1));
                    //将数据渲染聊天框
                    username = $(obj).text().substring(0,$(obj).text().length-1);

                    for ( var i in data.data ){
                        if(data.data[i].source === 2) {
                            $("#chat-content").append("" +
                                "<div class='clearfloat'>" +
                                "   <div class='time-column'>" +
                                "       <small class='chat-date'>" +data.data[i].updatetime+ "</small>" +
                                "   </div>" +
                                "   <div class='right'>" +
                                "       <div class='chat-nickname'> 我 </div>" +
                                "       <div class='chat-message'>" + AnalyticEmotion(data.data[i].message) + "</div>" +
                                "       <div class='chat-avatars'><img src='../../img/user/user_prointro/missolive.png' alt=''></div>" +
                                "   </div>" +
                                "</div>");
                        }else if(data.data[i].source === 1){
                            $("#chat-content").append("" +
                                "<div class='clearfloat'>" +
                                "   <div class='time-column'>" +
                                "       <small class='chat-date'>" +data.data[i].updatetime+ "</small>" +
                                "   </div>" +
                                "   <div class='left'>" +
                                "       <div class='chat-nickname'>"+username+"</div>" +
                                "       <div class='chat-avatars'><img src='../../img/guke.png' alt=''></div>" +
                                "       <div class='chat-message'>" + AnalyticEmotion(data.data[i].message) + "</div>" +
                                "   </div>" +
                                "</div>");
                        }
                    }
                    var chatC = document.getElementById("chat-content");
                    chatC.scrollTop = chatC.scrollHeight;
                }
            },
            error:function(){
                console.log("获取历史消息成功错误！")
            }
        });
        //

    }
}

$(function () {
    connect();
});

var stompClient;
function connect() {
    var socket = new SockJS("/endpointChat");
    stompClient = Stomp.over(socket);
    stompClient.connect(
        {
            name: "0"//userId
        },
        function connectCallback(frame){
            console.log("link success!"),
                //（管理员端订阅）
                stompClient.subscribe("/topic/toAdmin",function(data) {
                    console.log("管理员收到用户发来信息"+data.body);
                    var message = $.parseJSON(data.body);
                    //判断当前聊天框是否为该用户

                    if( message.to !== id ){
                        setlist.getList();
                    }else if( message.to === id && document.getElementById("U"+message.to) ){
                        //显示消息
                        showMessage(message);
                        //滚动条拉到最下
                        var chatC = document.getElementById("chat-content");
                        chatC.scrollTop = chatC.scrollHeight;
                        $.ajax({
                            type:'get',
                            url:'http://localhost:8080/chat/update_MessageStatus/'+id+'/1',
                            cache: false,
                            dataType:'json',
                            success: function(data) {
                                if(data.status==0) {
                                    console.log("设置已读成功");
                                }
                            },
                            error:function(){
                                console.log("客服获取用户列表错误！")
                            }
                        });
                    }
                })
        },
        function errorCallBack(response){console.log("link error!");}
    );
}

//发送消息
function out() {
    var inputText = $('.emotion').val();
    //管理员发送信息
    stompClient.send("/app/message",{},JSON.stringify({
        message : inputText,
        datetime :currentTime(),
        type :2,
        from :"0",//userId
        to : id+""
    }));

    $("#chat-content").append("" +
        "<div class='clearfloat'>" +
        "   <div class='time-column'>" +
        "       <small class='chat-date'>"+currentTime()+"</small>" +
        "   </div>" +
        "   <div class='right'>" +
        "       <div class='chat-nickname'> 我 </div>" +
        "       <div class='chat-message'>" + AnalyticEmotion(inputText) + "</div>" +
        "       <div class='chat-avatars'><img src='../../img/user/user_prointro/missolive.png' alt=''></div>" +
        "   </div>" +
        "</div>");
    $("#chatinput").val("");
    var chatC = document.getElementById("chat-content");
    chatC.scrollTop = chatC.scrollHeight;

}

function disconnect() {
    stompClient.disconnect(
        function(){
            console.log("已断开链接");
        });
}

function currentTime() {
    var d = new Date(),
        str = '';
    str += d.getFullYear() + '-';
    var month = d.getMonth()+1;
    if(month < 10){
        str += '0'+month + '-';
    }else{
        str += month +'-';
    }
    var date = d.getDate();
    if(date < 10){
        str += '0'+date + ' ';
    }else{
        str += date +' ';
    }
    if( d.getHours() < 10){
        str += '0'+d.getHours()+':'
    }else
        str += d.getHours() + ':';
    if( d.getMinutes() < 10){
        str += '0'+d.getMinutes()+':'
    }else
        str += d.getMinutes() + ':';
    if( d.getSeconds() < 10){
        str += '0'+d.getSeconds()+''
    }else
        str += d.getSeconds() + '';
    return str;
}

function showMessage(data) {
    console.log("收到信息已显示");
    if (data.type === 2 ) {
        $("#chat-content").append("" +
            "<div class='clearfloat'>" +
            "   <div class='time-column'>" +
            "       <small class='chat-date'>" + data.datetime + "</small>" +
            "   </div>" +
            "   <div class='right'>" +
            "       <div class='chat-nickname'>" + "我" + "</div>" +
            "       <div class='chat-message'>" + AnalyticEmotion(data.message) + "</div>" +
            "       <div class='chat-avatars'></div>" +
            "   </div>" +
            "</div>");
    } else {
        $("#chat-content").append("" +
            "<div class='clearfloat'>" +
            "   <div class='time-column'>" +
            "       <small class='chat-date'>" + data.datetime + "</small>" +
            "   </div>" +
            "   <div class='left'>" +
            "       <div class='chat-nickname'>" + username + "</div>" +
            "       <div class='chat-avatars'><img src=\"../../img/user/user_prointro/missolive.png\" alt=\"\"></div>" +
            "       <div class='chat-message'>" + AnalyticEmotion(data.message) + "</div>" +
            "   </div>" +
            "</div>");
    }
};
/**
 * Created by lizhenya on 2018/5/19.
 */
function openCart() {
    var t = $(window).width();
    0 == $(".shopping_all").length ? t > 768 ? $("#shop_cart").animate({
        right: 35
    }, 300) : $("#shop_cart").animate({
        right: 0
    }, 300) : t > 980 ? $("#shop_cart").animate({
        right: 35
    }, 300) : $("#shop_cart").animate({
        right: 0
    }, 300),
    $(".compare_btn").hasClass("open") && closeCompare(),
        $(".shop_cart").addClass("bg")
}
function openBar() {
    if(head.loginflag==0){
        alert("用户未登录！");
        return;
    }

    connect();
    toolbar.hasClass("open") || (toolbar.addClass("open"),
        $("#shop_cart .lazyload").removeClass("hidden").find("img").trigger("appear"),
        $(".global_toolbar").removeClass("default"),
        $(".toolbar_btn").css({
            top: "50%",
            marginTop: -$(".toolbar_btn").height() / 2
        })),
        $(".global_toolbar").removeClass("opacity"),
        $(".toolbar_btn").removeClass("default")
}
function closeBar() {
    disconnect();
    $(".toolbar_btn a").removeClass("current"),
        toolbar.removeClass("open"),
        $(".toolbar_btn").removeClass("default")
}
function openCartMeiu() {
    var t = $(window).width();
    $(".bar_consult").hasClass("current") || $(".bar_consult").addClass("current").siblings("a").removeClass("current"),
        $(".global_toolbar").hasClass("open") ? closeBar() : openBar(),
        t > 768 ? $(".bar_consult").hasClass("current") || $(".bar_consult").trigger("click") : openBar()
}


var toolbar = $(".global_toolbar");
$(function() {
    $("#wap_cartbtn_display").addClass("none");
    var t = $(window).height();
    $(window).width();
        $("body").click(function(t) {
            toolbar.hasClass("open") && closeBar()
        }),
        toolbar.click(function(t) {
            t.stopPropagation()
        }),
        $(".go_compare").click(function(t) {
            t.stopPropagation(),
                gpXlItemId = $("#hideItemId").val(),
                $(".m-pic_details.show").length > 3 ? $("#popup_full").fadeIn(300) : (LoadCompareAll(),
                    joinCompareOne(this),
                    $(".m-pic_details.show").length < 2 ? $(".m-compared .m-con .m-list .m-btn .m-db").removeClass("off") : $(".m-compared .m-con .m-list .m-btn .m-db").addClass("off"),
                    $(".m-compared .m-con .m-list .m-btn .m-del").removeClass("delete"))
        }),
        $("#popup_full .solid_btn").click(function() {
            $("#popup_full").fadeOut(300)
        }),
        $("#compare_column").find(".m-db").click(function() {
            if ($(this).hasClass("off")) {
                var t = $("#hideCompareList").val()
                    , e = $("#hideItemId").val()
                    , i = $(this).attr("data-url")
                    , r = "";
                r = (r = (r = (r = "?" == /\?/.exec(i) ? i + "&hideCompareList=" + t + e + "|" : i + "?hideCompareList=" + t + e + "|").replace("en-GB", "uk")).replace("ru-RU", "ru")).replace("test120.huawei.com/zh", "test120.huawei.com/zh/EBG/Home").replace("test120.huawei.com/cn", "test120.huawei.com/zh/EBG/Home"),
                    window.open(r)
            }
        })
    $(window).width();
    $("#shop_cart h4 a").click(function() {
        closeCart(),
            closeCompare()
    }),
        $("#shop_cart").find(".go_btn").click(function() {
            if ($(this).hasClass("disable"))
                return !1;
            AllUpdateOrderShoppingCar()
        }),
        $(window).resize(function() {
            //autoShopcartH();
        }),
        $(".compare_table tr:odd").addClass("gray_bg"),
        $(".compare_table tr td:last-child").addClass("last");
    var e;
    e = /MSIE /i.test(navigator.userAgent) ? 565 : 625,
        $("#choose_products li").click(function() {
            $(this).addClass("current").siblings("li").removeClass("current"),
                $(this).find("dl").each(function() {
                    $(this).find("dd span:even").each(function() {
                        var t = 0
                            , e = $(this).outerHeight(!0)
                            , i = $(this).next("span").outerHeight(!0);
                        t = e >= i ? e : i,
                            $(this).height(t),
                            $(this).next("span").height(t)
                    })
                });
            var t = 0
                , i = 60
                , r = $("#productBox").height();
            $(this).find("dl").each(function() {
                i += $(this).outerHeight(!0)
            }),
                i > e && i > r ? $(".choose_products").height(i) : $(".choose_products").height("auto");
            var n = $(document).scrollTop()
                , o = $("#choose_products").offset().top - $("#nav-cont.fix .nav-list").height()
                , s = $("#choose_products ul").offset().top;
            if (n >= o) {
                var a = (n = $(document).scrollTop()) - s + $("#nav-cont.fix .nav-list").height();
                $(this).find(".item_layer").css({
                    top: a
                });
                var c = (t = a > 0 ? a : 0) + $("#pc_sidebar h3").height();
                if (i + c < e) {
                    var h = $("#pc_sidebar").height() - c;
                    $(this).find(".item_layer").css({
                        "min-height": h
                    })
                } else
                    $(this).find(".item_layer").css({
                        height: i
                    }),
                        $(".choose_products").height(i + t + 50)
            } else
                $(this).find(".item_layer").css({
                    top: -50
                }),
                    $(this).find(".item_layer").css({
                        "min-height": e
                    })
        }),
        $(".item_layer dd span a").click(function(t) {
            t.stopPropagation(),
                $("#choosen_tips").show(),
                $(".product_series .picbox font").hide(),
                $(this).parents(".item_layer").parent("li").removeClass("current"),
                $(".choose_products").height("auto")
        }),
        $(".r_item dd a").click(function() {
            $("#choosen_tips").show(),
                $(".product_series .picbox font").hide(),
                $(this).parents(".fixed_sidebar").hide()
        });
    var i = t - $(".fixed_sidebar h1").outerHeight(!0);
    $(".l_sidebar,.r_item").height(i),
        $(".l_sidebar li").click(function() {
            $(this).addClass("current").siblings("li").removeClass("current");
            var t = $(this).index();
            $(".item_wrap").eq(t).removeClass("none").siblings(".item_wrap").addClass("none")
        }),
        $(".wap_pro_sidebar").click(function() {
            $(".fixed_sidebar").fadeIn()
        }),
        $(".fixed_sidebar h1 a").click(function() {
            $(this).parents(".fixed_sidebar").fadeOut()
        }),
        $("body").click(function() {
            $("#pc_sidebar li").removeClass("current")
        }),
        $("#pc_sidebar,.item_layer").click(function(t) {
            t.stopPropagation()
        }),
        $(".toolbar_btn a:not('.bar_forum')").bind("click", function(t) {
            t.stopPropagation();
            var e = $(this).attr("data-id")
                , i = $("." + e);
            $(this).hasClass("current") ? $(this).removeClass("current") : $(this).addClass("current").siblings("a").removeClass("current"),
                i.hasClass("open") ? closeBar() : openBar()
        }),
        $(".wap_cartbtn").bind("click", function(t) {
            t.stopPropagation(),
                autoShopcartH(),
            $(this).hasClass("disable") || ($(".global_toolbar").hasClass("open") ? closeBar() : openBar())
        }),
        $(".js_column h4 a").click(function() {
            closeBar()
        })
});

//WebSocket
$(function () {
    //判断是否登录
    //if(login) {
    //  connect();
    //}
})
var stompClient;

function connect() {
    var socket = new SockJS("/endpointChat");
    stompClient = Stomp.over(socket);
    stompClient.connect(
        {
            name: head.user.id//userId
        },
        function connectCallback(frame){
            console.log("link success!"),
            //获取历史信息
            $.ajax({
                type:'get',
                url:'http://localhost:8080/chat/user/get_AllMessage/'+head.user.id,//userId
                cache: false,
                dataType:'json',
                success: function(data) {
                    if(data.status === 0) {
                        console.log("获取历史消息成功");
                        //将数据渲染聊天框

                        for ( var i in data.data ){
                            if(data.data[i].source === 1) {
                                $("#chat-content").append("" +
                                    "<div class='clearfloat'>" +
                                    "   <div class='time-column'>" +
                                    "       <small class='chat-date'>" +data.data[i].updatetime+ "</small>" +
                                    "   </div>" +
                                    "   <div class='right'>" +
                                    "       <div class='chat-nickname'> 我 </div>" +
                                    "       <div class='chat-message'>" + AnalyticEmotion(data.data[i].message) + "</div>" +
                                    "       <div class='chat-avatars'><img src='"+headImg +"' alt=''></div>" +
                                    "   </div>" +
                                    "</div>");
                            }else if(data.data[i].source === 2){
                                $("#chat-content").append("" +
                                    "<div class='clearfloat'>" +
                                    "   <div class='time-column'>" +
                                    "       <small class='chat-date'>" +data.data[i].updatetime+ "</small>" +
                                    "   </div>" +
                                    "   <div class='left'>" +
                                    "       <div class='chat-nickname'> KissOlive客服 </div>" +
                                    "       <div class='chat-avatars'><img src='../../img/user/user_prointro/missolive.png' alt=''></div>" +
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
                    console.log("获取历史消息错误！")
                }
            });
            //链接成功后订阅通信
            stompClient.subscribe("/user/topic/message",function(data) {
                console.log("收到信息"+data.body);
                //收到信息判断：
                var message = $.parseJSON(data.body);
                if( message.type === 2) //为管理员发来信息
                {
                    showMessage(message);
                    var chatC = document.getElementById("chat-content");
                    chatC.scrollTop = chatC.scrollHeight;
                    $.ajax({
                        type:'get',
                        url:'http://localhost:8080/chat/update_MessageStatus/'+message.to+'/2',
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

function disconnect() {
    stompClient.disconnect(
        function(){
            console.log("已断开链接");
        });
}


function showMessage(data) {
    console.log("收到信息已显示");
    if (data.type === 1 ) {
        $("#chat-content").append("" +
            "<div class='clearfloat'>" +
            "   <div class='time-column'>" +
            "       <small class='chat-date'>" + data.datetime + "</small>" +
            "   </div>" +
            "   <div class='right'>" +
            "       <div class='chat-nickname'>" + "我" + "</div>" +
            "       <div class='chat-message'>" + AnalyticEmotion(data.message) + "</div>" +
            "       <div class='chat-avatars'><img src='\"+headImg +\"' alt=''></div>" +
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
            "       <div class='chat-nickname'>" + "KissOlive客服" + "</div>" +
            "       <div class='chat-avatars'><img src='../../img/user/user_prointro/missolive.png' alt=''></div>" +
            "       <div class='chat-message'>" + AnalyticEmotion(data.message) + "</div>" +
            "   </div>" +
            "</div>");
    }
};

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


//聊天室
//$(function () {
//  var socket = io();
//
//  var _username = null;
//
//  //设置用户名
//  var setUsername = function () {
//      _username = $("#name").val().trim();
//      if (_username) {
//          socket.emit("login", {username: _username});
//      }
//  };
//
//  //开始聊天
//  var beginChat = function () {
//      $(".login-container").css("display", "none");
//      $(".chat-container").css("display", "block");
//  };
//
//  //发送消息
//  var sendMessage = function () {
//      var _message = $(".chatinput").val();
//
//      if (_message) {
//          socket.emit("sendMessage", {username: _username, message: _message});
//      }
//  };
//
//  // 获取时间
//  function currentTime() {
//      var d = new Date(),
//          str = '';
//      str += d.getFullYear() + '-';
//      str += d.getMonth() + 1 + '-';
//      str += d.getDate() + '  ';
//      str += d.getHours() + ':';
//      str += d.getMinutes() + ':';
//      str += d.getSeconds();
//      return str;
//  }
//
    //显示消息
//  var showMessage = function (data) {
//      if (data.username === _username) {
//          $(".chat-content").append("" +
//              "<div class='clearfloat'>" +
//              "   <div class='time-column'>" +
//              "       <small class='chat-date'>" + currentTime() + "</small>" +
//              "   </div>" +
//              "   <div class='right'>" +
//              "       <div class='chat-nickname'>" + data.username + "</div>" +
//              "       <div class='chat-message'>" + data.message + "</div>" +
//              "       <div class='chat-avatars'></div>" +
//              "   </div>" +
//              "</div>");
//      } else {
//          $(".chat-content").append("" +
//              "<div class='clearfloat'>" +
//              "   <div class='time-column'>" +
//              "       <small class='chat-date'>" + currentTime() + "</small>" +
//              "   </div>" +
//              "   <div class='left'>" +
//              "       <div class='chat-nickname'>" + data.username + "</div>" +
//              "       <div class='chat-avatars'></div>" +
//              "       <div class='chat-message'>" + data.message + "</div>" +
//              "   </div>" +
//              "</div>");
//      }
//  };
//
//  //点击登录按钮触发setUsername();
//  $("#loginbtn").on("click", function () {
//      setUsername();
//  });
//
//  //用户名表单中回车触发setUsername();
//  $("#name").on("keyup", function (event) {
//      if (event.keyCode === 13) {
//          setUsername();
//      }
//  });
//
//  //发送信息表单中回车触发 sendMessage();
//  $("#chatinput").on("keyup", function (event) {
//      if (event.keyCode === 13) {
//          sendMessage();
//          $("#chatinput").val(""); //清空
//      }
//  });
//
//
//  /**socket.io部分逻辑代码*/
//  // 验证用户名是否重复
//  socket.on('judgeUsername', function (data) {
//      $(".login-container .form-group").append("<div class='judge-warm'>用户名重复!</div>");
//      setTimeout(function () {
//          $(".judge-warm").remove();
//      }, 1500)
//  });
//
//  //监听到登录成功后
//  socket.on("loginSuccess", function (data) {
//      if (data.username === _username) {
//          beginChat(data);
//      }
//  });
//
//  //监听到事件发生就 显示消息
//  socket.on("receiveMessage", function (data) {
//      showMessage(data);
//  });
//
//
//});
var brand = new Vue({
    el:"#brand",
    data:{
        brand:[]
    },
    created:function () {
        var self = this;
        $.ajax({
            type:'post',
            url:'http://localhost:8080/production/get_brand_put_on.do',
            cache: false,
            dataType:'json',
            success: function(data) {
                if(data.status==0) {
                    self.brand = data.data;
                    self.updateBrand();
                }
            },
            error:function(){
                alert("获取异常");
            }
        });

    },
    methods:{
        updateBrand:function () {
            this.$nextTick(function () {
                $("#brand a").click(function() {
                    if($("#brand a").eq($(this).index() - 1).hasClass("bgColor")) {
                        $("#brand a").eq($(this).index() - 1).removeClass("bgColor");
                    } else {
                        $("#brand a").eq($(this).index() - 1).addClass("bgColor").siblings().removeClass("bgColor");
                    }
                })
            })
        }
    }
})

var Funct = new Vue({
    el:"#function",
    data:{
        Funct:[]
    },
    created:function () {
        var self = this;
        $.ajax({
            type:'post',
            url:'http://localhost:8080/production/get_function_list.do',
            cache: false,
            dataType:'json',
            success: function(data) {
                if(data.status==0) {
                    self.Funct = data.data;
                    self.updateFunction();
                }
            },
            error:function(){
                alert("获取异常");
            }
        });
    },
    methods:{
        updateFunction:function () {
            this.$nextTick(function () {
                $("#function a").click(function() {
                    if($("#function a").eq($(this).index() - 1).hasClass("bgColor")) {
                        $("#function a").eq($(this).index() - 1).removeClass("bgColor");
                    } else {
                        $("#function a").eq($(this).index() - 1).addClass("bgColor");
                    }
                })
            })
        }
    }
})

var origin = new Vue({
    el:"#origin",
    data:{
        origin:[]
    },
    created:function () {
        var self = this;
        $.ajax({
            type:'post',
            url:'http://localhost:8080/production/get_origin_list.do',
            cache: false,
            dataType:'json',
            success: function(data) {
                if(data.status==0) {
                    self.origin = data.data;
                    self.updateOrigin();
                }
            }
        });
    },
    methods:{
        updateOrigin:function () {
            this.$nextTick(function () {
                $("#origin a").click(function(){
                    if($("#origin a").eq($(this).index()-1).hasClass("bgColor")){
                        $("#origin a").eq($(this).index()-1).removeClass("bgColor");
                    }else{
                        $("#origin a").eq($(this).index()-1).addClass("bgColor").siblings().removeClass("bgColor");
                    }
                })
            })
        }
    }
})

var hotspot = new Vue({
    el:"#hot-spot",
    data:{
        hotspot:[]
    },
    created:function () {
        var self = this;
        $.ajax({
            type:'post',
            url:'http://localhost:8080/production/get_hotspot_list.do',
            cache: false,
            dataType:'json',
            success: function(data) {
                if(data.status==0) {
                    self.hotspot = data.data;
                    self.updateHotspot();
                }
            }
        });
    },
    methods:{
        updateHotspot:function () {
            this.$nextTick(function () {
                $("#hot-spot a").click(function() {
                    if($("#hot-spot a").eq($(this).index() - 1).hasClass("bgColor")) {
                        $("#hot-spot a").eq($(this).index() - 1).removeClass("bgColor");
                    } else {
                        $("#hot-spot a").eq($(this).index() - 1).addClass("bgColor");
                    }
                })
            })
        }
    }
})

var markettime = new Vue({
    el:"#market-time",
    data:{
        markettime:[]
    },
    created:function () {
        var self = this;
        $.ajax({
            type:'post',
            url:'http://localhost:8080/production/get_market_time_list.do',
            cache: false,
            dataType:'json',
            success: function(data) {
                if(data.status==0) {
                    self.markettime = data.data;
                    self.updateMarketTime();
                }
            }
        });
    },
    methods:{
        updateMarketTime:function () {
            this.$nextTick(function () {
                $("#market-time a").click(function(){
                    if($("#market-time a").eq($(this).index()-1).hasClass("bgColor")){
                        $("#market-time a").eq($(this).index()-1).removeClass("bgColor");
                    }else{
                        $("#market-time a").eq($(this).index()-1).addClass("bgColor").siblings().removeClass("bgColor");
                    }
                })
            })
        }
    }
})

var skin = new Vue({
    el:"#skin",
    data:{
        skin:[]
    },
    created:function () {
        var self = this;
        $.ajax({
            type:'post',
            url:'http://localhost:8080/production/get_skin_list.do',
            cache: false,
            dataType:'json',
            success: function(data) {
                if(data.status==0) {
                    self.skin = data.data;
                    self.updateSkin();
                }
            }
        });
    },
    methods:{
        updateSkin:function () {
            this.$nextTick(function () {
                $("#skin a").click(function(){
                    if($("#skin a").eq($(this).index()-1).hasClass("bgColor")){
                        $("#skin a").eq($(this).index()-1).removeClass("bgColor");
                    }else{
                        $("#skin a").eq($(this).index() - 1).addClass("bgColor");
                    }
                })
            })
        }
    }
})

//加载编辑器及自定义配置
$(document).ready(function() {
    $('#summernote').summernote({
        height: 400,//初始化默认高度
        width:630,
        lang:'zh-CN',//注意这里，若要设置语言，则需要引入该语言配置js
        placeholder:"请在这里写下您的内容",
        fontSize:"16",
        toolbar: [
            ['color', ['color']],
            ['fontsize', ['fontsize']],
            ['para', ['paragraph']],
            ['style', ['bold','underline', 'clear']],
            ['insert', ['picture', 'link']],
            ['table', ['table']],
            ['view',['codeview','fullscreen']],
        ],//配置工具栏
        callbacks: {
            onImageUpload: function(file) {  //图片默认以二进制的形式存储到数据库，调用此方法将请求后台将图片存储到服务器，返回图片请求地址到前端
                returnImageUrl(file);
            }
        }
    });
});
function returnImageUrl(file){
    //将图片放入Formdate对象中
    var formData = new FormData();
    formData.append("img", file[0]);
    $.ajax({
        type:'post',
        url:'http://localhost:8080/production/upload_image.do',
        cache: false,
        data:formData,
        processData: false,
        contentType: false,
        dataType:'json',
        success: function(picture) {
            //console.log(picture);
            $('#summernote').summernote('insertImage',picture.url);
        },
        error:function(){
            alert("上传失败");
        }
    });
}
function sendarticle(){
    var formData = new FormData();
    var sendFile = $('#file-upload').get(0).files[0];
    if (typeof (sendFile) != "undefined") {
        formData.append("img", sendFile);
    }else{
        alert("品牌图片不能为空！");
        return;
    }

    var brandlinks =document.getElementById("brand").getElementsByTagName("a");
    var brand_id = null;
    for(var i=0;i<brandlinks.length;i++){
        if(brandlinks[i].className=="btn btn-default bgColor"){
            brand_id = brandlinks[i].id;
            break;
        }
    }
    if(brand_id == "" || brand_id == null){
        alert("品牌不能为空！");
        return;
    }
    formData.append("brand_id", brand_id);


    var functionlinks =document.getElementById("function").getElementsByTagName("a");
    var functions = new Array();
    for(var i=0;i<functionlinks.length;i++){
        if(functionlinks[i].className=="btn btn-default bgColor"){
            functions.push(functionlinks[i].id);
        }
    }
    if(functions == "" || functions == null){
        alert("功能不能为空！");
        return;
    }
    formData.append("functions", functions);

    var originlinks =document.getElementById("origin").getElementsByTagName("a");
    var origin_id = null;
    for(var i=0;i<originlinks.length;i++){
        if(originlinks[i].className=="btn btn-default bgColor"){
            origin_id = originlinks[i].id;
            break;
        }
    }
    if(origin_id == "" || origin_id == null){
        alert("产地不能为空！");
        return;
    }
    formData.append("origin_id", origin_id);

    var hotspotlinks =document.getElementById("hot-spot").getElementsByTagName("a");
    var hotspots = new Array();
    for(var i=0;i<hotspotlinks.length;i++){
        if(hotspotlinks[i].className=="btn btn-default bgColor"){
            hotspots.push(hotspotlinks[i].id);
        }
    }
    if(hotspots == "" || hotspots == null){
        alert("热点不能为空！");
        return;
    }
    formData.append("hotspots", hotspots);

    var markettimelinks =document.getElementById("market-time").getElementsByTagName("a");
    var market_time_id = null;
    for(var i=0;i<markettimelinks.length;i++){
        if(markettimelinks[i].className=="btn btn-default bgColor"){
            market_time_id = markettimelinks[i].id;
            break;
        }
    }
    if(market_time_id == "" || market_time_id == null){
        alert("上市时间不能为空！");
        return;
    }
    formData.append("market_time_id", market_time_id);

    var skinlinks =document.getElementById("skin").getElementsByTagName("a");
    var skins = new Array();
    for(var i=0;i<skinlinks.length;i++){
        if(skinlinks[i].className=="btn btn-default bgColor"){
            skins.push(skinlinks[i].id);
        }
    }
    if(skins == "" || skins == null){
        alert("适应肤质不能为空！");
        return;
    }
    formData.append("skins", skins);

    var production_name = null;
    var detail = null;
    var description = null;
    production_name = $('#product-name').val();
    description = $('#description').val();
    detail = $('#summernote').summernote('code');
    if(production_name == ""){
        alert("产品名不能为空！");
        return;
    }
    if(detail == "<p><br></p>"){
        alert("内容不能为空！");
        return;
    }
    formData.append("production_name", production_name);
    formData.append("description", description);
    formData.append("detail", detail);
    var url = "http://localhost:8080/production/add_production.do";
    submit(formData,url);
}
function submit(data,url){
    $.ajax({
        type : 'post',
        url : url,
        cache : false,
        data : data,
        processData : false,
        contentType : false,
        dataType : 'json', //请求成功后，后台返回图片访问地址字符串，故此以text格式获取，而不是json格式
        success : function(data) {
            alert(data.msg);
        },
        error : function() {
            alert("添加失败");
        }
    });
}

window.onload=function(){
    var imgArea=document.getElementById("imgArea");
    imgArea.ondragenter=function(){
        /*this.innerHTML="可以释放了";*/
    }
    imgArea.ondragover=function(ev){
        ev.preventDefault();
    }
    imgArea.ondragleave=function(){
        /*this.innerHTML="将文件拖拽到此区域"; */
        /*$("#icon-add").css("opacity","1");*/
    }
    imgArea.ondrop=function(ev){
        ev.preventDefault();
        var files=ev.dataTransfer.files;
        var fd=new FileReader();
        if(files[0].type.indexOf('image')!=-1){
            /*上传图片宽高比例：16：9*/
            fd.readAsDataURL(files[0]);


            var Max_Width = 630;
            var Max_Height = 350;
            var isAllow =false;

            fd.onload=function(){
                //获取上传图片宽高
                var data = this.result;
                var img = new Image();
                img.onload = function(){
                    var w = img.width;
                    var h = img.height;
                    //判断是否符合大小
                    isAllow = w >= Max_Width && h >= Max_Height;
                    showTip(isAllow,data);
                    console.log(w+":"+h);
                };
                img.src = data;
            };
        }else{alert("请选择图片上传");}
    }
}
function showTip(isAllow,url){
    var previewImg =document.getElementById("banner-pic");
    var Imgarea = document.getElementById("imgArea");
    var reloadBtn = document.getElementById("reloadbtn");
    var dragContainer = document.getElementById("dragContainer");
    var iconAdd = document.getElementById("icon-add");
    var fileBtn =  document.getElementById("filebtn");
    var fileUpload =  document.getElementById("file-upload");
    if(isAllow){
        //预览图片
        previewImg.src= url;
        previewImg.style.height="355px";
        previewImg.style.opacity = "1";
        reloadBtn.style.display="flex";
        dragContainer.style.display="none";
        iconAdd.style.display="none";
        fileBtn.style.display="block";
        fileUpload.style.display="none";
        Imgarea.style.height="355px";
    }else{
        $("#myModal").modal('show');
    }

}
$("#filebtn").on("change",function(){
    var Max_Width = 630;
    var Max_Height = 350;
    var isAllow =false;
    var reads= new FileReader();
    f=document.getElementById('filebtn').files[0];
    reads.readAsDataURL(f);
    reads.onload=function (e) {
        //获取上传图片宽高
        var data = this.result;
        var img = new Image();
        img.onload = function(){
            var w = img.width;
            var h = img.height;
            //判断是否符合大小
            isAllow = w >= Max_Width && h >= Max_Height;
            showTip(isAllow,data);
            console.log(w+":"+h);
        };
        img.src = data;
    };
});
$("#file-upload").on("change",function(){
    var Max_Width = 630;
    var Max_Height = 350;
    var isAllow =false;
    var reads= new FileReader();
    f=document.getElementById('file-upload').files[0];
    reads.readAsDataURL(f);
    reads.onload=function (e) {
        //获取上传图片宽高
        var data = this.result;
        var img = new Image();
        img.onload = function(){
            var w = img.width;
            var h = img.height;
            //判断是否符合大小
            isAllow = w >= Max_Width && h >= Max_Height;
            showTip(isAllow,data);
            console.log(w+":"+h);
        };
        img.src = data;
    };

    /* document.getElementById('banner-pic').style.height="355px";
     document.getElementById('banner-pic').style.opacity = "1";
     document.getElementById('reloadbtn').style.display="flex";
     document.getElementById('dragContainer').style.display="none";
     document.getElementById('icon-add').style.display="none";
     document.getElementById('filebtn').style.display="block";
     document.getElementById('file-upload').style.display="none";
     document.getElementById('imgArea').style.height="355px";*/

    /* alert("dssad");*/
});

$("#imgArea").hover(function(){
    $("#icon-add").css("opacity","0");
    $("#dragContainer").css("opacity","1");
    $("#reloadbtn").css("opacity","1");

},function(){

    $("#icon-add").css("opacity","1");
    $("#dragContainer").css("opacity","0");
    $("#reloadbtn").css("opacity","0");

});

$(".WriteCover-deleteButton").click(function(){
    var previewImg =document.getElementById("banner-pic");
    var Imgarea = document.getElementById("imgArea");
    var reloadBtn = document.getElementById("reloadbtn");
    var dragContainer = document.getElementById("dragContainer");
    var iconAdd = document.getElementById("icon-add");
    var fileBtn =  document.getElementById("filebtn");
    var fileUpload =  document.getElementById("file-upload");
    previewImg.src= "";
    previewImg.style.height="150px";
    Imgarea.style.height="150px";
    previewImg.style.opacity = "0";
    reloadBtn.style.display="none";
    dragContainer.style.display="block";
    iconAdd.style.display="block";
    fileBtn.style.display="none";
    fileUpload.style.display="block";

});
$(function () {
    $("#update").click(function() {
        $("#function").slideToggle();
        $("#updatefunction").slideToggle();
    });
})
function cancel() {
    $("#function").slideToggle();
    $("#updatefunction").slideToggle();
}
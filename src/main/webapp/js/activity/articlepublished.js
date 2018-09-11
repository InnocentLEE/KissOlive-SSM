 
 //加载编辑器及自定义配置 
$(document).ready(function() {  
    $('#summernote').summernote({  
        height: 400,//初始化默认高度    
        width:630,
        focus: true,//是否定位
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
            	sendfile(file);           	
            }  
        }
    });
});
function sendfile(file){
	//将图片放入Formdate对象中                                         
    var formData = new FormData();  
    formData.append("uploadIMG", file[0]); 
    $.ajax({                            
         type:'post',        
         url:'../../publicityManage/uploadIMG',                        
         cache: false,
         data:formData, 
         processData: false,
         contentType: false,
         dataType:'json',
         success: function(picture) { 
        	 //console.log(picture);
        	 if(picture.status == 0){        		
        		 $('#summernote').summernote('insertImage',picture.data); 
        	 }
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
		formData.append("coverpath", sendFile);
	}else{
		formData.append("coverpath", "");
	}
	var title = null;
	var content = null;
	title = $('#title').val();
	content = $('#summernote').summernote('code');
	if(title == ""){
		alert("标题不能为空！");
		return;
	}
	if(content == "<p><br></p>"){
		alert("内容不能为空！");
		return;
	}
	formData.append("title", title);
	formData.append("content", content);
	formData.append("source", "计算机学院");
	var url = "../../publicityManage/insertNews";
	//submit(formData,url);
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
			alert("发布失败");
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

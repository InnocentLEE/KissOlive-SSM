$(document).ready(function() {
    $('#table_id_example').DataTable({
        "processing": true,
        "aLengthMenu": [5, 10, 20],
        /*"aoColumnDefs": [ { "bSortable": false, "aTargets": [ 0 ] }],*/
        "order": [[ 0, 'asc' ]],
        "language": {
            "decimal": "",
            "emptyTable": "表中没有可用数据",
            "info": "显示 _START_ 到 _END_ 的 _TOTAL_ 条数",
            "infoEmpty": "没有记录",
            "infoFiltered": "(filtered from _MAX_ total entries)",
            "infoPostFix": "",
            "thousands": ",",
            "lengthMenu": " 每页显示 _MENU_ 条",
            "loadingRecords": "加载中...",
            "processing": "处理中...",
            "search": "搜索：",
            "zeroRecords": "没有找到符合条件的数据",
            "paginate": {
                "first": "首页",
                "last": "尾页",
                "next": "下一页",
                "previous": "上一页"
            },
            "aria": {
                "sortAscending": ": activate to sort column ascending",
                "sortDescending": ": activate to sort column descending"
            }
        },
        "sDom": "<'col-sm-12' <'row col-sm-12' <'col-sm-2'<'#btn'>> <'col-sm-8'> <'cos-sm-2'f>> t <'col-sm-6'> <'col-sm-6'>>"
    });
    var btnn = "<button class=\"btn btn-primary btn-sm\" data-toggle='modal' data-target='#myModal'><i class=\"fa fa-plus\"></i>添加</button>\n";
    $("#btn")[0].innerHTML = btnn;
    /*$("#all").click(function(){
        $('[name=all]:checkbox').prop('checked',this.checked);//checked为true时为默认显示的状态
    });*/
    //弹出框取消按钮事件
    $('.popup_de .btn_cancel').click(function(){
        $('.popup_de').removeClass('bbox');
    });
    //弹出框关闭按钮事件
    $('.popup_de .popup_close').click(function(){
        $('.popup_de').removeClass('bbox');
    });
    $('#logo-img').click(function(){
        $('#brand_logo').trigger("click");
        //$('#edit_logo').trigger("click");
    });
    $('#edit_logo-img').click(function(){
        //$('#brand_logo').trigger("click");
        $('#edit_logo').trigger("click");
    });
    
});

var table_id_example = new Vue({
    el : '#table_id_example',
    data :{
        datas:[]
    },
    created:function () {
        var self = this;
        $.ajax({
            type: 'post',
            url: 'http://localhost:8080/production/get_brand_list.do',
            async: false,
            dataType: 'json',
            success: function (result) {
                if (result.status == 0)
                    self.datas = result.data;
                else {
                    showmsg(result.msg);
                }
            }
        })
    },
    methods:{
        change_status:function (id,status) {
            doChangeStatus(id,status);
        }
    }
})

//获取图片路劲的方法，兼容多种浏览器，通过createObjectURL实现
function getObjectURL(file){
    var url = null;
    if(window.createObjectURL != undefined){
        url = window.createObjectURL(file);//basic
    }else if(window.URL != undefined){
        url = window.URL.createObjectURL(file);
    }else if(window.webkitURL != undefined){
        url = window.webkitURL.createObjectURL(file);
    }

    return url;
}

$(function(){
    $('#brand_logo').change(function () {
        var objUrl = getObjectURL(this.files[0]);
        //alert("1:"+objUrl);
       if(objUrl){
           var previewImg =document.getElementById("logo-img");
           previewImg.src= objUrl;
           previewImg.style.display = "block";
       }
    });
    $('#edit_logo').change(function () {
        var objUrl = getObjectURL(this.files[0]);
        //alert("2:"+objUrl);
        if(objUrl){
            $('#edit_logo-img').attr("src",objUrl);
        }
    });
})

var edit_brand_id;
function setvalue(obj) {
    var tds = $(obj).parent().parent().find('td');
    edit_brand_id = tds.eq(0).text();
    var edit_brand_name = tds.eq(1).text();
    var edit_brand_imgUrl = tds.eq(2).find('img').attr("src");
    var edit_brand_status = tds.eq(3).text();
    //alert(edit_brand_id+"       "+edit_brand_name+"       "+edit_brand_status+"       "+edit_brand_imgUrl);
    $('#edit_brand_name').val(edit_brand_name);
    $('#edit_logo-img').attr("src",edit_brand_imgUrl);
    if(edit_brand_status=="已上架"){
        $('#edit_brand_status').val("1");
    }else{
        $('#edit_brand_status').val("0");
    }
}

function brand_submit() {
    var formData = new FormData();
    var brand_logo = $('#brand_logo').get(0).files[0];
    var select = $('#brand_status').val();
    var brand_name = $('#brand_name').val();
    formData.append("logo_img",brand_logo);
    formData.append("brand_status",select);
    formData.append("brand_name",brand_name);
    $.ajax({
        type : 'post',
        url : 'http://localhost:8080/production/add_brand.do',
        cache : false,
        data : formData,
        processData : false,
        contentType : false,
        dataType : 'json', //请求成功后，后台返回图片访问地址字符串，故此以text格式获取，而不是json格式
        success : function(result) {
            showmsg(result.msg);
        }
    })
}

function edit_brand() {
    var formData = new FormData();
    var brand_logo = $('#edit_logo').get(0).files[0];
    var select = $('#edit_brand_status').val();
    var brand_name = $('#edit_brand_name').val();
    formData.append("brand_id",edit_brand_id);
    formData.append("logo_img",brand_logo);
    formData.append("brand_status",select);
    formData.append("brand_name",brand_name);
    $.ajax({
        type : 'post',
        url : 'http://localhost:8080/production/edit_brand.do',
        cache : false,
        data : formData,
        processData : false,
        contentType : false,
        dataType : 'json', //请求成功后，后台返回图片访问地址字符串，故此以text格式获取，而不是json格式
        success : function(result) {
            showmsg(result.msg);
        }
    })
}
function doChangeStatus(id,status) {
    if(status==0){
        var text="确定要下架该品牌吗?";
    }else{
        var text="确定要上架该品牌吗?";
    }
    document.getElementById('show_msg').innerHTML=text;
    $('#tip').addClass('bbox');
    $('#tipbtn').one('click',function(){
        $.ajax({
            type : 'post',
            url : 'http://localhost:8080/production/change_brand_put_on_status.do',
            cache : false,
            data : {
                "brand_id":id,
                "brand_status":status
            },
            dataType : 'json', //请求成功后，后台返回图片访问地址字符串，故此以text格式获取，而不是json格式
            success : function(result) {
                showmsg(result.msg);
            }
        })
    })
}

function showmsg(msg) {
    document.getElementById('showmsg').innerHTML=msg;
    $('#show').addClass('bbox');
    $('#showbtn').one('click',function(){
        window.parent.frames["right"].location.reload();
    })
}
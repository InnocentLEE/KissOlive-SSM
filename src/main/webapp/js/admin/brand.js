$(document).ready(function() {
    $('#table_id_example').DataTable({
        "processing": true,
        "aLengthMenu": [5, 10, 20],
        "aoColumnDefs": [ { "bSortable": false, "aTargets": [ 0 ] }],
        "order": [[ 1, 'asc' ]],
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
    var btnn = "<button class=\"btn btn-primary btn-sm\" data-toggle='modal' data-target='#myModal'><i class=\"fa fa-plus\"></i>添加</button>\n" +
        "\t\t\t\t<button class=\"btn btn-success btn-sm\"><i class=\"fa fa-upload\"></i>上架</button>\n" +
        "\t\t\t\t<button class=\"btn btn-danger btn-sm\"><i class=\"fa fa-download\"></i>下架</button>";
    $("#btn")[0].innerHTML = btnn;
    $("#all").click(function(){
        $('[name=all]:checkbox').prop('checked',this.checked);//checked为true时为默认显示的状态
    });
    //弹出框取消按钮事件
    $('.popup_de .btn_cancel').click(function(){
        $('.popup_de').removeClass('bbox');
    });
    //弹出框关闭按钮事件
    $('.popup_de .popup_close').click(function(){
        $('.popup_de').removeClass('bbox');
    });
    $('.img-display').click(function(){
        $('#brand_logo').trigger("click");
    });
    
});
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
           alert(result.msg);
           window.parent.frames["right"].location.reload();
        }
    })
}
var table_id_example = new Vue({
    el : '#table_id_example',
    data :{
        datas:[]
    },
    created:function () {
        var self = this;
        $.ajax({
            type : 'post',
            url : 'http://localhost:8080/production/get_brand_list.do',
            async : false,
            dataType : 'json',
            success : function(result) {
                if(result.status==0)
                    self.datas = result.data;
                else{
                    alert(result.msg);
                }
            }
        })
    }
})

function imgchange() {
    var read=new FileReader();
    read.readAsDataURL(this.files[0]);
    read.onload=function(){
        var url=read.result;
        var previewImg =document.getElementById("logo-img");
        alert("1234");
        previewImg.src= url;
        previewImg.style.display = "block";
    }
}

function edit_imgchange() {
    alert("123");
    var read=new FileReader();
    read.readAsDataURL(this.files[0]);
    read.onload=function(){
        var url=read.result;
        alert(url);
        $('#edit_logo-img').attr("src",url);
    }
}
/*$('#brand_logo').onchange=function () {
    var read=new FileReader();
    read.readAsDataURL(this.files[0]);
    read.onload=function(){
        var url=read.result;
        var previewImg =document.getElementById("logo-img");
        alert("1234");
        previewImg.src= url;
        previewImg.style.display = "block";
    }
}

$('#edit_brand_logo').onchange=function (){
    alert("123");
    var read=new FileReader();
    read.readAsDataURL(this.files[0]);
    read.onload=function(){
        var url=read.result;
        alert(url);
        $('#edit_logo-img').attr("src",url);
    }
}*/

var edit_brand_id;
function setvalue(obj) {
    var tds = $(obj).parent().parent().find('td');
    edit_brand_id = tds.eq(0).find('input').val();
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
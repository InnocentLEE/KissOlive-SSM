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

brand_logo.onchange=function () {
    var read=new FileReader();
    read.readAsDataURL(this.files[0]);
    read.onload=function(){
        var url=read.result;
        var previewImg =document.getElementById("logo-img");
        previewImg.src= url;
    }

}
$(document).ready(function() {
    $('#table_id_undo').DataTable({
        "processing": true,
        "aLengthMenu": [5, 10, 20],
        "order": [[ 0, 'asc' ]],
        "bPaginate":true,
        "bSort":true,
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
        "sDom": "<'col-sm-12' <'row col-sm-12' <'col-sm-2'l> <'col-sm-8'<'#btn'>> <'col-sm-8'> <'cos-sm-2'f>> t <'col-sm-6' i> <'col-sm-6' p>>"
    });
   /* var btnn = "<a class=\"btn btn-primary btn-sm\" href='admin_addproduction.html' target='right'><i class=\"fa fa-plus\"></i>添加</a>\n";
    $("#btn")[0].innerHTML = btnn;*/
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


    $('#table_id_willdo').DataTable({
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
        "sDom": "<'col-sm-12' <'row col-sm-12' <'col-sm-2'l> <'col-sm-8'<'#btn'>> <'col-sm-8'> <'cos-sm-2'f>> t <'col-sm-6' i> <'col-sm-6' p>>"
    });

    $('#table_id_will-confirm').DataTable({
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
        "sDom": "<'col-sm-12' <'row col-sm-12' <'col-sm-2'l> <'col-sm-8'<'#btn'>> <'col-sm-8'> <'cos-sm-2'f>> t <'col-sm-6' i> <'col-sm-6' p>>"
    });

    $('#table_id_done').DataTable({
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
        "sDom": "<'col-sm-12' <'row col-sm-12' <'col-sm-2'l> <'col-sm-8'<'#btn'>> <'col-sm-8'> <'cos-sm-2'f>> t <'col-sm-6' i> <'col-sm-6' p>>"
    });

    $('#table_id_canceled').DataTable({
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
        "sDom": "<'col-sm-12' <'row col-sm-12' <'col-sm-2'l> <'col-sm-8'<'#btn'>> <'col-sm-8'> <'cos-sm-2'f>> t <'col-sm-6' i> <'col-sm-6' p>>"
    });
});
/*
var table_id_example = new Vue({
    el : '#table_id_example',
    data :{
        datas:[],
        maxlength:15
    },
    created:function () {
        var self = this;
        var page_num = 99999999;
        var page = 1;
        $.ajax({
            type: 'post',
            url: 'http://localhost:8080/production/get_productions.do?page='+page+'&page_num='+page_num,
            async: false,
            dataType: 'json',
            success: function (result) {
                if (result.status == 0)
                    self.datas = result.data.production_details_list;
                else {
                    showmsg(result.msg);
                }
            }
        })
    },
    methods:{
        titleformat:function (str) {
            if(str.length>this.maxlength)
                return str.slice(0,this.maxlength) + "...";
            else
                return str;
        }
    }
})*/

function showmsg(msg) {
    document.getElementById('showmsg').innerHTML=msg;
    $('#show').addClass('bbox');
    $('#showbtn').one('click',function(){
        window.parent.frames["right"].location.reload();
    })
}
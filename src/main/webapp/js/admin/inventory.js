$(document).ready(function() {
    $('#table_id_example').DataTable({

        "processing": true,
        "aLengthMenu": [5, 10, 20],
        /*		 "aoColumnDefs": [ { "bSortable": false, "aTargets": [ 0 ] }],
                 "order": [[ 1, 'asc' ]],*/
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
        "sDom": "<'col-sm-12' <'row col-sm-12' <'col-sm-2'l> <'col-sm-8'<'#btn'>> <'cos-sm-2' f>> t <'col-sm-6' i> <'col-sm-6'p>>"
    });
    var btnn = "<button class='btn btn-default' type='button' data-toggle='modal' data-target='#myModal' >入库</button>";
    $("#btn")[0].innerHTML = btnn;
});

$("#newinventory").click(function() {
    var myDate = new Date();
    var date2 = "" + myDate.getFullYear() + (myDate.getMonth() + 1) + myDate.getDate() + myDate.getHours() + myDate.getMinutes() + myDate.getSeconds();
    $("#newinventory1").val(date2);
});
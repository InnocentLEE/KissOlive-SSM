$(document).ready(function() {
        $('#table_id_example').DataTable({
            "jQueryUI": true,
            "processing": true,
            "aLengthMenu": [ 5, 10, 20],
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

            "columnDefs" : [ {
                "targets" : 2,//操作按钮目标列
                "data" : null,
                "render" : function(data, type,row) {
                    var html = "<a class='btn btn-success btn-xs' data-toggle='modal' data-target='#myModal2' onclick='edit(this)'><i class='fa fa-arrow-up'></i> 编辑</a>"
                    html += "<a class='btn btn-danger btn-xs' onclick='delete1(this)'><i class='fa fa-arrow-down'></i> 删除</a>"
                    return html;
                }
            } ],
            "sDom": "<'col-sm-12' <'row col-sm-12' <'col-sm-2'l> <'col-sm-8'<'#btn'>> <'cos-sm-2' f>> t <'col-sm-6' i> <'col-sm-6'p>>"
        });
        var btnn = "<button class='btn btn-default' type='button' data-toggle='modal' data-target='#myModal' >入库</button>";
        $("#btn")[0].innerHTML = btnn;
    });
<!--编辑按钮功能,获取当前列的ID -->
var supID;
function edit(btn)
    {
        var tr = btn.parentElement.parentElement;
        var td1= tr.cells[1];
        var td2=tr.cells[0];
        supID=td2.innerText;
        alert(supID);
        document.getElementById("supplier2").value=td1.innerText;
    }

<!--删除按钮功能-->
function delete1(btn)
{
    if (confirm("确认要删除？")) {
        var tr = btn.parentElement.parentElement;
        var td = tr.cells[0];
        alert(td.innerText);
    }
}
<!--模态框1提交按钮功能-->
function innersup() {
    //1.获取表单的值
    var supplier_name = $("#supplier").val();
    alert(supplier_name);
    //2.发送ajax请求
    $.ajax({

    })
    //3.关闭模态框
    $('#myModal').modal('hide');

}
<!--模态框2提交按钮功能-->
function updatesup() {
    //1.获取表单的值
    var update_supplier_name = $("#supplier2").val();
    alert(update_supplier_name);
    //2.发送ajax请求
    $.ajax({

    })
    //3.关闭模态框
    $("#myModal2").modal('hide');
}
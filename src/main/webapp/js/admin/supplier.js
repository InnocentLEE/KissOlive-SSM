$(document).ready(function() {
        $('#table_id_example').DataTable({
            "jQueryUI": true,
            "processing": true,
            "aLengthMenu": [ 5, 10, 20],
            "language": {
                "decimal": "",
                "emptyTable": "表中没有可用数据",
                "info": "第 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
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

     /*       "columnDefs" : [ {
                "targets" : 2,//操作按钮目标列
                "data" : null,
                "render" : function(data, type,row) {
                    var html = "<a class='btn btn-success btn-xs' data-toggle='modal' data-target='#myModal2' onclick='edit(this)'><i class='fa fa-arrow-up'></i> 编辑</a>"
                    html += "<a class='btn btn-danger btn-xs' onclick='delete1(this)'><i class='fa fa-arrow-down'></i> 删除</a>"
                    return html;
                }
            } ],*/
            "sDom": "<'col-sm-12' <'row col-sm-12' <'col-sm-2'l> <'col-sm-8'<'#btn'>> <'cos-sm-2' f>> t <'col-sm-6' i> <'col-sm-6'p>>"
        });
        var btnn = "<button class='btn btn-default' type='button' data-toggle='modal' data-target='#myModal' onclick=''>添加供应商</button>";
        $("#btn")[0].innerHTML = btnn;
    });
//Vue设置，渲染表格数据
var table_id_example = new Vue({
        el : '#table_id_example',
        data :{
            datas:[]
        },
        created:function () {
            var self = this;
            $.ajax({
                type: 'get',
                url: 'http://localhost:8080/supplier',
                async: false,
                dataType: 'json',
                success: function (result) {
                    if (result.status == 0)
                        self.datas = result.data;
                }
            })
        }
    })

//编辑按钮功能,获取当前列的ID
var supID;
function edit(btn)
{
        var tr = btn.parentElement.parentElement;
        var td1= tr.cells[1];
        var td2=tr.cells[0];
        supID=td2.innerText;
        document.getElementById("supplier2").value=td1.innerText;
}


<!--删除按钮功能-->
function delete1(btn)
{
    var tr = btn.parentElement.parentElement;
    var td = tr.cells[0];
    var userID=td.innerText;

    var text="确认删除？";
    document.getElementById('show_msg').innerHTML=text;
    $('#tip').addClass('bbox');
    $('#tipbtn').one('click',function(){
        //发送删除ajax请求
        $.ajax({
            type:'delete',
            url:'http://localhost:8080/supplier/'+userID,
            datatype:"json",
            success:function (result) {
                if(result.status == 0)
                    window.parent.frames["right"].location.reload();
                else{

                }
            }
        })
    })
}
function checkedsup() {
    var supplier_name = $("#supplier").val();
    if(!supplier_name)
    {
        alert("请填写供应商名称");
        return false;
    }
    else{
        return true;
    }

}
<!--模态框1添加供应商提交按钮功能-->
function innersup() {
    //1.获取表单的值
    var supplier_name = $("#supplier").val();
    if(checkedsup()){
        //2.发送ajax请求
        $.ajax({
            type:'post',
            url:'http://localhost:8080/supplier',
            datatype:"json",
            data:{
                name:supplier_name
            },
            success:function (result) {
                if(result.status == 0)
                //3.关闭模态框
                    $('#myModal').modal('hide');
                    window.parent.frames["right"].location.reload();

            }
        })
    }
}
<!--模态框2提交按钮功能-->
function updatesup() {
    //1.获取表单的值
    var update_supplier_name = $("#supplier2").val();
    //2.发送ajax请求
    $.ajax({
            type:'put',
            url:'http://localhost:8080/supplier/'+supID,
            data:{
                name:update_supplier_name
            },
            datatype:'json',
            success:function (result) {
                if(result.status==0)
                {
                    var text="修改成功";
                    document.getElementById('show_msg').innerHTML=text;
                    $('#tip').addClass('bbox');
                    $('#tipbtn').one('click',function(){
                        window.parent.frames["right"].location.reload();
                    })
                }
            }
    })
    //3.关闭模态框
    $("#myModal2").modal('hide');
}
//弹出框取消按钮事件
$('.popup_de .btn_cancel').click(function(){
    $('.popup_de').removeClass('bbox');
});
//弹出框关闭按钮事件
$('.popup_de .popup_close').click(function(){
    $('.popup_de').removeClass('bbox');
});
<!--权限管理的datatables的配置-->
    $(document).ready(function() {
        $('#per_table').DataTable({
            "jQueryUI": true,
            "processing": true,
            "aLengthMenu": [ 5, 10, 20],
            "language": {
                "decimal": "",
                "emptyTable": "表中没有可用数据",
                "info": "显示 _START_ 到 _END_ 的 _TOTAL_ 条数",
                "infoEmpty": "没有记录",
                "infoFiltered": "(从所有记录中搜索)",
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
                "targets" : 7,//操作按钮目标列
                "data" : null,
                "render" : function(data, type,row) {
                    var html = "<a class='btn btn-danger btn-xs' data-toggle='modal' data-target='#per_myModal2' onclick='update(this)'><span class='glyphicon glyphicon-pencil'></span> 修改权限</a>"
                    return html;
                }
            } ],
            "sDom": "<'col-md-12' <'row col-md-12' <'col-md-2'l> <'cos-md-2' f>> t <'col-md-6' i> <'col-md-6'p>>"
        });
    });
<!--修改权限的作流程 -->

//修改权限按钮的操作
//获取当前列的ID值
var userID;
function update(a)
{
    var tr = a.parentElement.parentElement;
    var td = tr.cells[0];
    alert(td.innerText);
    userID=td.innerText;
}

//提交按钮的操作
$("#update_account").click(function(){

    //获取表单的值
    var a=$("#home_admin").prop("checked");
    var b=$("#product_admin").prop("checked");
    var c=$("#activity_admin").prop("checked");
    var d=$("#brand_admin").prop("checked");
    var e=$("#inventory_admin").prop("checked");
    var f=$("#order_admin").prop("checked");
    if(a)
    {
        $("#home_admin").attr("value",1);
    }
    else{
        $("#home_admin").attr("value",0);
    }
    if(b)
    {
        $("#product_admin").attr("value",1);
    }
    else{
        $("#product_admin").attr("value",0);
    }
    if(c)
    {
        $("#activity_admin").attr("value",1);
    }
    else{
        $("#activity_admin").attr("value",0);
    }
    if(d)
    {
        $("#brand_admin").attr("value",1);
    }
    else{
        $("#brand_admin").attr("value",0);
    }
    if(e)
    {
        $("#inventory_admin").attr("value",1);
    }
    else{
        $("#inventory_admin").attr("value",0);
    }
    if(f)
    {
        $("#order_admin").attr("value",1);
    }
    else{
        $("#order_admin").attr("value",0);
    }

    var formData = new FormData();
    var home_value=$("#home_admin").attr("value");
    alert(home_value);
    var product_value=$("#product_admin").attr("value");
    alert(product_value);
    var activity_value=$("#activity_admin").attr("value");
    alert(activity_value);
    var brand_value=$("#brand_admin").attr("value");
    alert(brand_value);
    var inventory_value=$("#inventory_admin").attr("value");
    alert(inventory_value);
    var order_value=$("#order_admin").attr("value");
    alert(order_value);

    formData.append("home_admin",home_value);
    formData.append("product_admin",product_value);
    formData.append("activity_admin",activity_value);
    formData.append("brand_admin",brand_value);
    formData.append("inventory_admin",inventory_value);
    formData.append("order_admin",order_value);
/*    for (var value of formData.values()) {
        console.log(value);
    }*/
    //2.ajax发送请求提交
    $.ajax({
        type:'post',
        url:'',
        data:formData,
        success:function(result){
            //3.成功之后的回调函数关闭模态框
            $('#per_myModal2').modal('hide');
            //4.显示返回信息
            showmsg(result.msg);
        }
    });

})

//VUE设置，列表渲染
var per_table=new Vue({
    el:'#per_table',
    data:{
        datas:[]
    },
    created:function () {
        var self = this;
        $.ajax({
            type: 'get',
            url: 'http://localhost:8080/permit',
            dataType: 'JSONP',
            success: function (result) {
                /*self.datas = result.data;*/
                console.log(result);
            }
        })
    }
})

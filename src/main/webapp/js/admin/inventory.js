$(document).ready(function() {
    $('#table_id_example').DataTable({

        "processing": true,
        "aLengthMenu": [5, 10, 20],
        /*		 "aoColumnDefs": [ { "bSortable": false, "aTargets": [ 0 ] }],
                 "order": [[ 1, 'asc' ]],*/
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
        "sDom": "<'col-sm-12' <'row col-sm-12' <'col-sm-2'l> <'col-sm-8'<'#btn'>> <'cos-sm-2' f>> t <'col-sm-6' i> <'col-sm-6'p>>"
    });
    var btnn = "<button class='btn btn-default' type='button' data-toggle='modal' data-target='#myModal' onclick='getmsg()'>入库</button>";
    $("#btn")[0].innerHTML = btnn;
});
//给下拉列表增加标志
function setSelect(){
    var obj=document.getElementById("select1");
    obj.add(new Option("商品","0"));


/*    $("#select1").add(new Option("商品","1"));
    $("#select1").attr("checked",checked);*/
}
//验证入库表单
function checked() {
    var index=1;
    var innerID=$("#newinventory1").val();
    var permitdate=$("#permitdate").val();
    var innernumber=$("#innernumber").val();
    if(!innerID)
    {
        alert("入库号不能为空");
        index=0;
    }
    else if($("#select1").val()==0||$("#select2").val()==0||$("#select3").val()==0||$("#select4").val()==0)
    {
        alert("未完成选择");
        index=0;
    }
    else if(!permitdate)
    {
        alert("未选择报纸期限");
        index=0;
    }
    else if(!innernumber)
    {
        alert("未输入数量");
        index=0;
    }

    if(index==1)
        return true;
    else
        return false;
}
//根据上面选定的品牌id请求产品名字
function getproduct(brandID)
{
    var formData = new FormData();
    formData.append("brand_ids",brandID);
            $.ajax({
                type: 'post',
                url: 'http://localhost:8080/production/get_productions_by_brand.do',
                async: false,
                dataType: 'json',
                processData : false,
                contentType : false,
                data:formData,
                success: function (result) {
                    if (result.status == 0)
                        brand_product_color.products = result.data;
                }
            })
}
//根据上面的选定的产品id请求产品颜色
function getColor(productID)
{
    $.ajax({
        type: 'post',
        url: 'http://localhost:8080/production/get_production_show.do',
        dataType: 'json',
        data: {
            production_id: productID
        },
        success: function (result) {
            if (result.status == 0)
                brand_product_color.colors = result.data.goodses;
        }
    })
}
//大的VUE
var brand_product_color=new Vue({
    el:'#myModal',
    data:{
        brands:[],
        products:[],
        colors:[],
        suppliers:[]
    },
    created:function () {
    var self=this;
        $.ajax({
            type: 'post',
            url: 'http://localhost:8080/production/get_brand_list.do',
            async: false,
            dataType: 'json',
            success: function (result) {
                if (result.status == 0)
                    self.brands = result.data;
            }
        })
    }
})
//品牌下拉列表改变事件
function changebrand()
{
    var brandID=$("#select1").val();
    alert(brandID);
    getproduct(brandID);
}
//产品下拉列表改变事件
function changeproduct() {
    var productID=$("#select2").val();
    alert(productID);
    getColor(productID);
}
//点击入库按钮发送请求获取品牌，产品，色号
function getmsg() {
    $.ajax({
        type: 'get',
        url: 'http://localhost:8080/supplier',
        dataType: 'json',
        success: function (result) {
            if (result.status == 0)
                brand_product_color.suppliers = result.data;
        }
    })
}

//生成入库号
$("#newinventory").click(function() {
    var myDate = new Date();
    var date2 = "" + myDate.getFullYear() + (myDate.getMonth() + 1) + myDate.getDate() + myDate.getHours() + myDate.getMinutes() + myDate.getSeconds();
    $("#newinventory1").val(date2);
});


//VUE设置，列表渲染
var table_id_example=new Vue({
    el:'#table_id_example',
    data:{
        datas:[]
    },
    created:function () {
        var self = this;
        $.ajax({
            type: 'get',
            url: 'http://localhost:8080/stock',
            async: false,
            dataType: 'json',
            success: function (result) {
                if (result.status == 0)
                    self.datas = result.data;
            }
        })
    },
    methods:{
        timeformat:function (time) {
            return new Date(time).toLocaleDateString();
        }
    }
})
//单击模态框提交按钮时间
function addiven() {
    if(checked()){
        //1.获取表单的值
        var formData = new FormData();
        var addnumber=$("#newinventory1").val();
        var goodsid=$("#select3").val();
        var permitdate=$("#permitdate").val();
        var number=$("#innernumber").val();
        var supplierid=$("#select4").val();
        formData.append("stockId",addnumber);
        formData.append("goodsId",goodsid);
        formData.append("shelfdate",permitdate);
        formData.append("number",number);
        formData.append("supplierId",supplierid);
        //2.ajax发送请求提交
        $.ajax({
            type:'post',
            url:'http://localhost:8080/stock',
            async: false,
            data:formData,
            processData : true,
            processData : false,
            contentType : false,
            dataType : 'json',
            success:function(result){
                //3.成功之后的回调函数关闭模态框
                if(result.status==0) {
                    $('#per_myModal2').modal('hide');
                    window.parent.frames["right"].location.reload();
                }
                else
                {
                    $('#per_myModal2').modal('hide');
                    alert(reslut.msg);
                    window.parent.frames["right"].location.reload();
                }
            }
        })
    }


}
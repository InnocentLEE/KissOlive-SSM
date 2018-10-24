var arrshow = new Array();
arrshow.push(4);
arrshow.push(6);
arrshow.push(9);
arrshow.push(11);
var show = new Vue({
    el:"#show",
    data:{
        data1:[],
        data2:[],
        data3:[],
        data4:[],
        data5:[]
    },
    created:function () {
        this.getShow();
    },
    methods:{
        getShow:function () {
            for(var index=0;index<arrshow.length;index++) {
                var formdata = new FormData();
                formdata.append("brand_ids", arrshow[index]);
                doGetBrandContent(formdata, index+1);
            }
        }
    }
})
function doGetBrandContent(formdata,index) {
    $.ajax({
        type:'post',
        url:'http://localhost:8080/production/get_productions_by_brand.do',
        data:formdata,
        cache: false,
        processData:false,
        contentType : false,
        dataType:'json',
        success: function(data) {
            if(data.status==0) {
                if(index==1)
                    show.data1 = data.data;
                else if(index==2)
                    show.data2 = data.data;
                else if(index==3)
                    show.data3 = data.data;
                else if(index==4)
                    show.data4 = data.data;
                else if(index==5)
                    show.data5 = data.data;
            }
        },
        error:function(){
            alert("获取异常");
        }
    });
}
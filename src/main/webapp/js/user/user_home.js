var search = new Vue({
    el:"#search",
    data:{
        search:[]
    }
})
var brand = new Vue({
    el:"#dropdownList",
    data:{
        brand:[]
    },
    created:function () {
        this.getBrand();
    },
    methods:{
        getBrand:function () {
            $.ajax({
                type:'post',
                url:'http://localhost:8080/production/get_brand_put_on.do',
                cache: false,
                dataType:'json',
                success: function(data) {
                    if(data.status==0) {
                        brand.brand = data.data;
                        brand.updateBrand();
                    }
                },
                error:function(){
                    alert("获取异常");
                }
            });
        },
        updateBrand:function () {
            this.$nextTick(function () {
                $('.box').hover(
                    function () {
                        //alert("123");
                        var overlay = $(this).find('.box-overlay');
                        overlay.removeClass(overlay.data('return')).addClass(overlay.data('hover'));
                    },
                    function () {
                        //alert("1235");
                        var overlay = $(this).find('.box-overlay');
                        overlay.removeClass(overlay.data('hover')).addClass(overlay.data('return'));
                    }
                );
            })
        }
    }
})
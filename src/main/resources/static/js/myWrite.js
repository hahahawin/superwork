// 首先需要引入jQuery


$("*[maxlength]").bind('keydown',function () {
    var maxlength = $(this).attr('maxlength');
    var value = $(this).val();
    if(maxlength){
        if(value.length>=maxlength){
           //如果引入了layui 没有就直接用alert
            layer.tips("长度超过了"+maxlength,$(this),{
                tips:3//显示方位 下方
            })
        }
    }
})

function renderKey(){
    $("*[maxlength]").bind('keydown',function () {
        var maxlength = $(this).attr('maxlength');
        var value = $(this).val();
        if(maxlength){
            if(value.length>=maxlength){
                //如果引入了layui 没有就直接用alert
                layer.tips("长度超过了"+maxlength,$(this),{
                    tips:3//显示方位 下方
                })
            }
        }
    })
}

// 全局处理Ajax 请求
$.ajaxSetup({
    crossDomain: true,
    xhrFields: {
        withCredentials: true // 要在这里设置 跨域设置cookie
    },
    success:function(data,xhr,settings){
        var index = this.hasShade;
        if(index != null && index !="undefined" ){
            layer.close(index)
        }
        if(data.status == 402){//权限不足
            layer.msg(data.msg,{icon:2,time:1500})
        }else if(data.status == 401){//没有登录
            top.layer.alert(data.msg, {
                end: function () {
                    top.location.href = '/';
                }
            })
        }else{
            if(typeof this.mysuccess != 'undefined'){
                this.mysuccess(data);
            }else{
                layer.alert("未定义成功的回调方法！")
            }
        }
    }
});
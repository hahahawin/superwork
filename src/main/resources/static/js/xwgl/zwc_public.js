function selXqlist(params, selectid){

    // $.ajax({
    //     url:selXqxxUrl,    //请求的url地址
    //     dataType:"json",   //返回格式为json
    //     contentType: 'application/json',
    //     async:true,//请求是否异步，默认为异步，这也是ajax重要特性
    //     data: JSON.stringify({belong_org_id : beone_org_id}),    //参数值
    //     type:"POST",   //请求方式
    //     success:function(req){
    //         var list = req.rows;
    //         var options = '<option value=""">-- 请选择 --</option>';
    //         if(list){
    //             for(var i=0;i<list.length;i++){
    //                 options += '<option value="'+ list[i].di_key +'">'+ list[i].di_value +'</option>';
    //             }
    //         }
    //         $("#xqjbxx_id").html(options);
    //     }
    // });

    $.post(selXqxxUrl, params, function(json){
        var list = json.rows;
        var options = '<option value="">-- 请选择 --</option>';
        if(list){
            for(var i=0;i<list.length;i++){
                options += '<option value="'+ list[i].di_key +'">'+ list[i].di_value +'</option>';
            }
        }
        $("#"+selectid).html(options);
    },'json');
}

//通过校区查询楼宇
function selLyxxByXqId(value, selectid){
    if(value != ''){
        var params = {
            belong_org_id : beone_org_id,
            xqjbxx_id : value
        };
        $.post(selLyUrl, params, function (json) {
            var list = json.rows;
            var options = '<option value="">-- 请选择 --</option>';
            if(list){
                for(var i=0;i<list.length;i++){
                    options += '<option value="'+ list[i].di_key +'">'+ list[i].di_value +'</option>';
                }
            }
            $("#"+selectid).html(options);
        },'json')
    }else {
        $("#"+selectid).empty();
    }
}

//通过楼宇查询楼层信息
function selLcxxByJzw(value, selectid){
    if(value != ''){
        var params = {
            belong_org_id : beone_org_id,
            jzwjbxx_id : value
        };
        $.post(selLcxxUrl, params, function (json) {
            var list = json.rows;
            var options = '<option value="">-- 请选择 --</option>';
            if(list){
                for(var i=0;i<list.length;i++){
                    options += '<option value="'+ list[i].di_key +'">'+ list[i].di_value +'</option>';
                }
            }
            $("#"+selectid).html(options);
        },'json')
    }else{
        $("#"+selectid).empty();
    }
}
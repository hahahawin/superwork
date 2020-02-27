//分页实现方法
function getList(page,callback){
    $("body").mLoading("show");//显示loading组件
    var params = {
        pageSize : '8',
        pageNo : page,
        belong_org_id :beone_org_id,
        creator_id : user_id,
        yj_mc : $("#yj_mc").val(),
        yjlx_id:$("#yjlx_id").val(),
        only_bond:$("input[name='only_bond']:checked").val()
    };
    $.post(yjListUrl, params, function(data){
        $("body").mLoading("hide");
        if (data.resultCode=='error') {
            showMsg(data.resultMsg);
        }else {
            if (data.total == '0') {
                $('#myyj_table').html("<div style='text-align:center'>暂无数据</div>");
            } else {
                d3url = data.d3url;
                var num = parseInt(data.total / params.pageSize);
                var pnum = data.total % params.pageSize;
                if (pnum != 0)
                    num += 1;
                jsonRows = eval(data.rows);
                onResize();
                typeof callback === 'function' && callback({
                    totalPage: num
                });
            }
        }
    },'json');

}


//拼装数据
function json_table(){
    var html = '' ;
    var size = 0;
    if (jsonRows != null && jsonRows != '') {
        size = jsonRows.length;
    }
    var bond_list;
    var ybdsb = '';
    for (var i = 0; i < size; i++) {
        var obj  = jsonRows[i] ;
        var icon_path = obj.fjtp;     //图片路径
        var mxfj = obj.fjdz;
        var filePathName = "" ;  //文件名称
        var fileName = obj.fjmc;
        var fileName2 = '';
        var yj_id = obj.yj_id;
        var sfbd = obj.sfbd;
        bond_list = obj.bond_list;
        ybdsb = '';
        if(bond_list != undefined && bond_list != 'undefined'){
            for(var m=0;m<bond_list.length;m++){
                ybdsb += bond_list[m].serial_num+',';
            }
        }
        console.log("bond_list:"+bond_list);
        if(bodyWidth<500) {
            if(i%1==0){
                if(i!=0)
                    html += '</div>' ;
                html += '<div class="row">' ;
            }
            html += '<div class="col-xs-12">' ;
        }else if(bodyWidth<800) {
            if(i%2==0){
                if(i!=0)
                    html += '</div>' ;
                html += '<div class="row">' ;
            }
            html += '<div class="col-xs-6">' ;
        }else if(bodyWidth<1100) {
            if(i%3==0){
                if(i!=0)
                    html += '</div>' ;
                html += '<div class="row">' ;
            }
            html += '<div class="col-xs-4">' ;
        }else{

            if(i%4==0){
                if(i!=0)
                    html += '</div>' ;
                html += '<div class="row">' ;
            }
            html += '<div class="col-xs-3">' ;
        }

        html += '<div title="预览" style="width:100% ;height: 250px ;line-height: 250px;background-color: #ECECEC;position:relative;text-align:center;margin-top: 20px;" onmouseover="to_over(\''+obj.yj_id+'\');" onmouseout="to_out(\''+obj.yj_id+'\');" onclick="modelShow(\''+mxfj+'\',\''+obj.yj_id+'\');">' +
            '<img style="max-height: 250px;position:absolute;top:50%;left:50%;transform: translate(-50%,-50%);" src="'+icon_path+'" class="img-responsive"/>' ;
        html += '</div>';
        if(getLength(fileName) > 15){
            fileName2 = cutstr(fileName, 15);
        }else{
            fileName2 = fileName;
        }
        html += '<div title="'+fileName+'" style="float:left;margin:20px 0px 15px 15px;font-size:16px;color: #333333;">'+fileName2+'</div>';
        html += '<div style="float:right;margin:25px 10px;font-size:10px;color: #888888">';
        html += '<a class="btn btn-success btn-xs"  href="javascript:void(0);" onclick="sbbdpage(\''+ yj_id+'\',\''+ ybdsb +'\');">绑定</a>';
        if(sfbd == '1'){
            html += '<a class="btn btn-danger btn-xs" style="margin-left: 10px;" href="javascript:void(0);" onclick="jbpage(\''+ yj_id+'\');">解绑</a>';
        }else{
            html += '<a class="btn btn-danger btn-xs disabled" style="margin-left: 10px;" href="javascript:void(0);" onclick="jbpage(\''+ yj_id+'\');">解绑</a>';
        }

        html += '</div>';
        html += '</div>';
    }

    if(size>0){
        html += '</div>' ;
    }
    // alert(html);
    $('#myyj_table').html(html);
}

//设备查询
var ybdsbs = '';
function sbbdpage(yj_id, ybdsb){
    c_yj_id = yj_id;
    ybdsbs = ybdsb;
    $("#sbgl_table").bootstrapTable('destroy');
    selSblist();
    $("#demoBox").css('display', "block");
}

function cxsblist(){
    $("#sbgl_table").bootstrapTable('destroy');
    selSblist();
}

function resetcxsblist(){
    $('#device_name').val('');
    $('#serial_num').val('');
    $('#account').val('');
    $("#sbgl_table").bootstrapTable('destroy');
    selSblist();
}

function selSblist(){
    $('#sbgl_table').bootstrapTable({
        method : 'post',
        url : sblistUrl,//请求路径
        striped : true, //是否显示行间隔色
        clickToSelect: true, //点击行是否选中
        pageNumber : 1, //初始化加载第一页
        pagination : true,//是否分页
        singleSelect : false,
        sidePagination : 'server',//server:服务器端分页|client：前端分页
        pageSize : 20,//单页记录数
        height: 500, //自定义表格的高度
        pageList : [ 5, 10, 20, 30 ],//可选择单页记录数
        queryParams : function(params) {//上传服务器的参数
            var temp = {//如果是在服务器端实现分页，limit、offset这两个参数是必须的
                pageSize : params.limit, // 每页显示数量
                pageNo : (params.offset / params.limit) + 1, //当前页码
                belong_org_id : beone_org_id,
                device_name : $('#device_name').val(),
                serial_num : $('#serial_num').val(),
                account : $('#account').val()
            };
            return temp;
        },
        columns : [ {
            checkbox: true,
            field : 'checked',
            formatter : function(value, row, index) {
                if (ybdsbs.indexOf(row.device_mac) > -1)
                    return {
                        checked : true//设置选中
                };
                return value;
            }
        }, {
            title : '设备名称',
            field : 'device_name',
            sortable : false
        }, {
            title : '设备序号',
            field : 'serial_num',
            sortable : false
        }, {
            title : '设备MAC',
            field : 'device_mac',
            sortable : false
        }, {
            title : '设备账户',
            field : 'account',
            sortable : false
        }, {
            title : '编辑时间',
            field : 'edited_time',
            sortable : false,
            formatter : function(value) {
                return timeFormat(value,"yyyy-MM-dd hh:mm:ss");
            }
        }]
    })
}

//解绑界面
function jbpage(yj_id){
    c_yj_id = yj_id;
    $('#jieb_table').bootstrapTable('destroy');
    selJblist();
    $("#jiebBox").css('display', "block");
}

function selJblist(){
    $('#jieb_table').bootstrapTable({
        method : 'post',
        url : bondlistUrl,//请求路径
        striped : true, //是否显示行间隔色
        clickToSelect: true, //点击行是否选中
        pageNumber : 1, //初始化加载第一页
        pagination : false,//是否分页
        singleSelect : false,
        sidePagination : 'server',//server:服务器端分页|client：前端分页
        pageSize : 20,//单页记录数
        height: 500, //自定义表格的高度
        pageList : [ 5, 10, 20, 30 ],//可选择单页记录数
        queryParams : function(params) {//上传服务器的参数
            var temp = {//如果是在服务器端实现分页，limit、offset这两个参数是必须的
                pageSize : params.limit, // 每页显示数量
                pageNo : (params.offset / params.limit) + 1, //当前页码
                belong_org_id : beone_org_id,
                yj_id : c_yj_id
            };
            return temp;
        },
        columns : [ {
            checkbox: true
        }, {
            title : '设备名称',
            field : 'device_name',
            sortable : false
        }, {
            title : '设备序号',
            field : 'serial_num',
            sortable : false
        }, {
            title : '设备MAC',
            field : 'device_mac',
            sortable : false
        }, {
            title : '设备账户',
            field : 'account',
            sortable : false
        }, {
            title : '编辑时间',
            field : 'edited_time',
            sortable : false,
            formatter : function(value) {
                return timeFormat(value,"yyyy-MM-dd hh:mm:ss");
            }
        }]
    })
}

//解绑
function jiebing(yj_id){
    var one = $('#jieb_table').bootstrapTable('getSelections');
    var devces = new Array();
    for(var i=0;i<one.length;i++){
        devces.push(one[i].device_mac);
    }
    if(devces != ''){
        var params = {
            yj_id:c_yj_id,
            device_mac:devces.join(",")
        }
        $("body").mLoading("show");//显示loading组件
        $.post(unbondDevUrl, params, function(json){
            $("body").mLoading("hide");
            if(json.resultCode == 'success'){
                showMsg("解绑成功！");
                $("#jiebBox").css('display', "none");
                $('#pager').pageInit(getList);
            }
        },'json');
    }else{
        showMsg("请选择要解绑的设备！");
    }
}

//绑定
function bindSbxx(){
    var one = $('#sbgl_table').bootstrapTable('getSelections');
    if(one != ''){
        var devces = [];
        for(var i=0;i<one.length;i++){
            if(ybdsbs.indexOf(one[i].device_mac) == -1){
                var pone = {};
                pone.yj_id = c_yj_id;
                pone.serial_num = one[i].device_mac;
                pone.equip_name = one[i].device_name;
                devces.push(pone);
            }
        }
        var params = {
            bond_list:devces
        }
        $("body").mLoading("show");//显示loading组件
        $.ajax({
            url:sbBindUrl,    //请求的url地址
            dataType:"json",   //返回格式为json
            contentType: 'application/json',
            async:true,//请求是否异步，默认为异步，这也是ajax重要特性
            data: JSON.stringify(params),    //参数值
            type:"POST",   //请求方式
            success:function(json){
                $("body").mLoading("hide");
                if(json.resultCode == 'success'){
                    showMsg("绑定成功！");
                    $("#demoBox").hide();
                    $('#pager').pageInit(getList);
                }else{
                    showMsg(json.resultMsg);
                }
            }
        });

    }else{
        showMsg("请先选择设备！");
    }

}


//鼠标移上去事件
function to_over(id) {
    $('#dele_div'+id).css('display','block');
    $('#buttom_div'+id).css('display','block');
}

//鼠标移开事件
function to_out(id) {
    $('#dele_div'+id).css('display','none');
    $('#buttom_div'+id).css('display','none');
}

// 预览元件，加载预览页面
function modelShow(mxfj,yj_id) {
    if (mxfj == null || mxfj == '') {
        showMsg("元件附件不存在！");
    } else {
        var ylurl = d3url + "/otheAPI/viewYJ";
        var array = new Array();
        array.push(mxfj)
        mxfj = encodeURI(encodeURI(JSON.stringify(array)));
        var params = {
            url:mxfj
        }
        $("#yjylBox").css('display', "block");
        $("body").mLoading("show");//显示loading组件
        $.ajax({
            url:ylurl,    //请求的url地址
            dataType:"json", //返回格式为json
            contentType: 'application/json',
            async:true,//请求是否异步，默认为异步，这也是ajax重要特性
            data: JSON.stringify(params),    //参数值
            type:"POST",   //请求方式
            success:function(json){
                $("body").mLoading("hide");//显示loading组件
                if(json.status == 200){
                    var url = d3url+json.data.viewurl;
                    var t=document.getElementById("Example2");
                    t.contentWindow.location.href = url;
                }
            }
        });

        // $.post(viewYjUrl, params, function(json){
        //     if(json.resultCode == 'success'){
        //
        //     }else{
        //         showMsg("原件预览失败！");
        //     }
        // },'json');
    }
}


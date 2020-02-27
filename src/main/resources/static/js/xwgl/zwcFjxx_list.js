//初始列表
function findByOnpag(){
    $('#fjxx_table').bootstrapTable({
        method : 'post',
        url : selfjlistUrl,//请求路径
        striped : true, //是否显示行间隔色
        pageNumber : 1, //初始化加载第一页
        pagination : true,//是否分页
        sidePagination : 'server',//server:服务器端分页|client：前端分页
        sortable: true,    //是否启用排序
        height: $(window).height() - 75, //自定义表格的高度
        pageSize : 10,//单页记录数
        pageList : [ 10, 20, 30, 50 ],//可选择单页记录数
        queryParams : function(params) {//上传服务器的参数
            var temp = {//如果是在服务器端实现分页，limit、offset这两个参数是必须的
                pageSize : params.limit, // 每页显示数量
                pageNo : (params.offset / params.limit) + 1, //当前页码
                sort: 'xqjbxx_xqmc',      //排序列名
                sortOrder: 'asc', //排位命令（desc，asc）
                belong_org_id : beone_org_id,
                xqjbxx_id : $("#xqjbxx_id").val(),
                jzwjbxx_id : $("#jzwjbxx_id").val(),
                fjxx_fjlc : $("#fjxx_fjlc").val()
            };
            return temp;
        },
        onDblClickRow: function(row) {
            $.ajax({
                url:detailRoomUrl,    //请求的url地址
                dataType:"json",   //返回格式为json
                //contentType: 'application/json',
                async:true,//请求是否异步，默认为异步，这也是ajax重要特性
                data: {'fjid':row.fjxx_id},    //参数值
                type:"POST",   //请求方式
                success:function(req){
                    if(req.resultCode == 'SUCCESS'){
                        var row = req.row;
                        $("#detailXQ").html(row.XQ);
                        $("#detailLY").html(row.LY);
                        $("#detailLC").html(row.LC);
                        $("#detailFJ").html(row.FJ);
                        if (row.DEVICE!=null&&row.DEVICE!=''){
                            var html="";
                            for(var i=0;i<row.DEVICE.length;i++){
                                html=html+"<li style='list-style-type: none'>"+row.DEVICE[i].DEVICE_NAME+"</li>";
                            }

                            $("#detaileDevice").html(html);
                        }else{
                            $("#detaileDevice").html("无");
                        }

                        $('#detailFjModal').modal({backdrop:'static', keyboard:false});
                    }else{
                        showMsg("操作失败！");
                    }

                }
            });
        },
        columns : [
        	{width : '100', title : '校区', field : 'xqjbxx_xqmc', sortable : false,
                formatter : function(di_key, row, index) {
                    if(row.xqjbxx_sfzb == '1'){
                        return di_key+'<font>(已停办)</font>';
                    }else{
                        return di_key;
                    }
                }
            }
            , {width : '100', title : '楼宇名称', field : 'jzwjbxx_jzwmc', sortable : false}
            , {width : '100', title : '房间编号', field : 'fjxx_bjbh', sortable : false}
            , {width : '100', title : '房间名称', field : 'fjxx_fjmc', sortable : false}
            , {width : '100', title : '房间所处楼层', field : 'lcbh', sortable : false}
            ,{width:100,title:'操作',field:'fjxx_id',align:'center',sortable:false,
                formatter:function(value, row, index){
                    var str = '<div class="operator_div">';
                    str += '<a class="btn btn-success btn-xs" href="javascript:void(0);" onclick=editFjxx("'+ index +'")>编辑</a>';
                    str += '&nbsp<a class="btn btn-success btn-xs" href="javascript:void(0);" onclick=bindLcxx("'+ value +'")>设备绑定</a>';
                    str += '&nbsp;<a class="btn btn-danger btn-xs" href="javascript:void(0);" onclick=delFjxx("'+ value +'")>删除</a>';
                    str += '</div>';
                    return str;
                }
            }
        ]
    });
}

function editFjxx(index){
    var row = $('#fjxx_table').bootstrapTable('getData')[index];
    var fjxx_id = row.fjxx_id;
    var xqjbxx_id = row.xqjbxx_id;
    var jzwjbxx_id = row.jzwjbxx_id;
    var fjxx_fjlc = row.fjxx_fjlc;
    var fjxx_bjbh = row.fjxx_bjbh;
    var fjxx_fjmc = row.fjxx_fjmc;

    removeValidator();
    //查询校区
    var params = {belong_org_id : beone_org_id, xqjbxx_sfzb:'2'};
    $.ajaxSetup({async: false});
    selXqlist(params, 'xqjbxx_id_edit');
    selLyxxByXqId(xqjbxx_id, 'jzwjbxx_id_edit');
    selLcxxByJzw(jzwjbxx_id, 'fjxx_fjlc_edit');
    $.ajaxSetup({async: true});

    $("#fjxx_id").val(fjxx_id);
    $("#xqjbxx_id_edit").val(xqjbxx_id);
    $("#jzwjbxx_id_edit").val(jzwjbxx_id);
    $("#fjxx_fjlc_edit").val(fjxx_fjlc);
    $("#fjxx_bjbh_edit").val(fjxx_bjbh);
    $("#fjxx_fjmc_edit").val(fjxx_fjmc);
    $('#addFjModal').modal({backdrop:'static', keyboard:false});

}

function delFjxx(fjxx_id){
    showConfirm("确定要删除该房间信息？", function() {
        $.ajax({
            url:delUrl,    //请求的url地址
            dataType:"json",   //返回格式为json
            contentType: 'application/json',
            async:true,//请求是否异步，默认为异步，这也是ajax重要特性
            data: JSON.stringify({fjxx_id:fjxx_id,belong_org_id : beone_org_id}),    //参数值
            type:"POST",   //请求方式
            success:function(req){
                if(req.resultCode == 'success'){
                    showMsg("删除成功！", function () {
                        chongzhi();
                    });
                }else{
                    showMsg(req.resultMsg);
                }

            }
        });
    });
}

function chaxun(){
    $("#fjxx_table").bootstrapTable('destroy');
    findByOnpag();
}

function chongzhi(){
    $("#xqjbxx_id").val('');
    $("#jzwjbxx_id").val('');
    $("#fjxx_fjlc").val('');
    $("#fjxx_table").bootstrapTable('destroy');
    findByOnpag();
}

function addFjxx(){
    removeValidator();
    $("#jzwjbxx_id_edit").empty();
    $("#fjxx_fjlc_edit").empty();
    $("#fjxx_id").val('');
    $("#fjxx_bjbh_edit").val('');
    $("#fjxx_fjmc_edit").val('');
    //查询校区
    var params = {belong_org_id : beone_org_id, xqjbxx_sfzb:'2'};
    selXqlist(params, 'xqjbxx_id_edit');
    $('#addFjModal').modal({backdrop:'static', keyboard:false});
}

function addFjxxTj(){
    $('#addFjxxForm').data('bootstrapValidator').validate();//启用验证
    // var flag = $('#addFjxxForm').data('bootstrapValidator').isValid();

}

function removeValidator(){
    //移除上次的校验配置
    $("#addFjxxForm").data('bootstrapValidator').destroy();
    $('#addFjxxForm').data('bootstrapValidator',null);
    //重新添加校验
    ValidatorFjxxInfo();
}
function ValidatorFjxxInfo(){
    $('#addFjxxForm').bootstrapValidator({
        fields: {
            xqjbxx_id: {
                validators: {
                    notEmpty: {
                        message: '校区不能为空！'
                    }
                }
            },
            jzwjbxx_id: {
                validators: {
                    notEmpty: {
                        message: '楼宇信息不能为空！'
                    }
                }
            },
            fjxx_fjlc: {
                validators: {
                    notEmpty: {
                        message: '楼层信息不能为空！'
                    }
                }
            },
            fjxx_bjbh: {
                validators: {
                    notEmpty: {
                        message: '房间编号不能为空！'
                    },
                    stringLength: {
                        min: 0,
                        max: 6,
                        message: '房间编号长度在1 - 6之间！'
                    },
                    regexp: {
                        regexp: /(^\d+$)|(^\+?\d+$)/,
                        message: '只能填写正整数！'
                    }
                    ,
                    remote: { // ajax校验，获得一个json数据（{'valid': true or false}）
                        url: checkUrl,       //验证地址
                        message: '房间编号已存在',   //提示信息
                        // delay :  1000,
                        type: 'POST',          //请求方式
                        data: function(validator){  //自定义提交数据，默认为当前input name值
                            return {
                                fjxx_bjbh: $("#fjxx_bjbh_edit").val(),
                                jzwjbxx_id: $("#jzwjbxx_id_edit").val(),
                                fjxx_id: $("#fjxx_id").val(),
                                fjxx_fjlc: $("#fjxx_fjlc_edit").val(),
                                belong_org_id : beone_org_id
                            };
                        }
                    }
                }
            },
            fjxx_fjmc: {
                validators: {
                    notEmpty: {
                        message: '房间名称不能为空！'
                    },
                    stringLength: {
                        min: 0,
                        max: 10,
                        message: '楼层说明长度在0 - 10之间！'
                    },
                    remote: { // ajax校验，获得一个json数据（{'valid': true or false}）
                        url: checkUrl,       //验证地址
                        message: '房间编号已存在',   //提示信息
                        // delay :  1000,
                        type: 'POST',          //请求方式
                        data: function(validator){  //自定义提交数据，默认为当前input name值
                            return {
                                fjxx_fjmc: $("#fjxx_fjmc_edit").val(),
                                jzwjbxx_id: $("#jzwjbxx_id_edit").val(),
                                fjxx_id: $("#fjxx_id").val(),
                                fjxx_fjlc: $("#fjxx_fjlc_edit").val(),
                                belong_org_id : beone_org_id
                            };
                        }
                    }
                }
            }
        }
    }).on('success.form.bv', function(e) {
        $("body").mLoading("show");//显示loading组件
        //提交数据
        var fjxx_id = $("#fjxx_id").val();
        var xqjbxx_id = $("#xqjbxx_id").val();
        var jzwjbxx_id_edit = $("#jzwjbxx_id_edit").val();
        var fjxx_fjlc_edit = $("#fjxx_fjlc_edit").val();
        var fjxx_bjbh_edit = $("#fjxx_bjbh_edit").val();
        var fjxx_fjmc_edit = $("#fjxx_fjmc_edit").val();

        var params = {
            fjxx_id : fjxx_id,
            xqjbxx_id : xqjbxx_id,
            jzwjbxx_id : jzwjbxx_id_edit,
            fjxx_fjlc : fjxx_fjlc_edit,
            fjxx_bjbh : fjxx_bjbh_edit,
            fjxx_fjmc : fjxx_fjmc_edit,
            belong_org_id : beone_org_id,
            editor_id : user_id
        };
        var url = addUrl;
        if(fjxx_id != null && fjxx_id != ''){
            url = editUrl;
        }
        $.ajax({
            url:url,    //请求的url地址
            dataType:"json",   //返回格式为json
            contentType: 'application/json',
            async:true,//请求是否异步，默认为异步，这也是ajax重要特性
            data: JSON.stringify(params),    //参数值
            type:"POST",   //请求方式
            success:function(req){
                $("body").mLoading("hide");//显示loading组件
                if(req.resultCode == 'success'){
                    $("#addFjModal").modal('hide');  //手动关闭
                    showMsg("操作成功！", function () {
                        chongzhi();
                    });
                }else{
                    showMsg("操作失败！");
                }
            }
        });
    });
}

//设备绑定弹窗
function bindLcxx(fjid){
    SBBDfjid=fjid;
    findDeviceType();
    $("#inTheRoom").val('全部');
    $("#outTheRoom").val('全部');
    selInTheRoom();
    selOutTheRoom();

    $("#demoBox").css('display', "block");
}

//查询设备类型
function findDeviceType() {
    $.ajax({
        url:findDeviceTypeUrl,    //请求的url地址
        contentType: 'application/json',
        async:true,//请求是否异步，默认为异步，这也是ajax重要特性
        type:"POST",   //请求方式
        success:function(list){
            $("#inTheRoom option").remove();
            $("#outTheRoom option").remove();

            $("#inTheRoom").append(selectOption(list));
            $("#outTheRoom").append(selectOption(list));
        }
    });
}
//通过返回的设备类型的list，生成Option字符串
function selectOption(list) {
    var value="<option selected='selected' value=''>全部</option>";
    for(var i=0;i<list.length;i++) {
        var MLDM = list[i].MLDM;
        var MLMC = list[i].MLMC;
        value+="<option value='"+MLDM+"'>"+MLMC+"</option>"
    }
    return value;
}

//查询该房间已经拥有的设备
function selInTheRoom(){
    $("#inTheRoom_table").bootstrapTable('destroy');
    $('#inTheRoom_table').bootstrapTable({
        method : 'post',
        url : selInTheRoomUrl,//请求路径
        striped : true, //是否显示行间隔色
        pageNumber : 1, //初始化加载第一页
        pagination : true,//是否分页
        sidePagination : 'server',//server:服务器端分页|client：前端分页
        paginationDetailHAlign:"right",
        height : 490,
        pageSize : 10,//单页记录数
        pageList : [ 5, 10, 20, 30 ],//可选择单页记录数
        clickToSelect : true, //是否启用点击选中行
        queryParams : function(params) {//上传服务器的参数
            params.pageSize=params.limit;
            params.pageNo=(params.offset / params.limit) + 1;
            params.fjid=SBBDfjid;
            params.sblx=$('#inTheRoom option:selected') .val();
            // console.info(params);
            return params;
        },
        columns : [
            {
                checkbox: true,                          // 显示复选框
                formatter: function (i,row) {            // 每次加载 checkbox 时判断当前 row 的 id 是否已经存在全局 Set() 里
                    if($.inArray(row.sbid,inSB)!=-1){// 因为 判断数组里有没有这个 id
                        return {
                            checked : true               // 存在则选中
                        }
                    }
                }

            },{
                title : '设备名称',
                field : 'DEVICE_NAME',
                sortable : false
            }
        ]
    });
}

//查询所有未绑定房间的设备
function selOutTheRoom(){
    $("#outTheRoom_table").bootstrapTable('destroy');
    $('#outTheRoom_table').bootstrapTable({
        method : 'post',
        url : selOutTheRoomUrl,//请求路径
        striped : true, //是否显示行间隔色
        pageNumber : 1, //初始化加载第一页
        pagination : true,//是否分页
        sidePagination : 'server',//server:服务器端分页|client：前端分页
        paginationDetailHAlign:"right",
        height : 490,
        clickToSelect : true, //是否启用点击选中行
        pageSize : 10,//单页记录数
        pageList : [ 5, 10, 20, 30 ],//可选择单页记录数
        queryParams : function(params) {//上传服务器的参数
            params.pageSize=params.limit;
            params.pageNo=(params.offset / params.limit) + 1;
            params.sblx=$('#outTheRoom option:selected') .val();
            // console.info(params);
            return params;
        },
        columns : [
            {
                checkbox: true,                          // 显示复选框
                formatter: function (i,row) {            // 每次加载 checkbox 时判断当前 row 的 id 是否已经存在全局 Set() 里
                    if($.inArray(row.sbid,outSB)!=-1){// 因为 判断数组里有没有这个 id
                        return {
                            checked : true               // 存在则选中
                        }
                    }
                }

            },{
                title : '设备名称',
                field : 'DEVICE_NAME',
                sortable : false
            }
        ]
    });
}

function setDevice(){
    var sbid = outSB.join(",");
    if(sbid == null || sbid == ''){
        showMsg("未选择设备！");
        return false;
    }
    $.ajax({
        url:addDeviceToRoomUrl,    //请求的url地址
        //contentType: 'application/json',
        async:true,//请求是否异步，默认为异步，这也是ajax重要特性
        type:"POST",   //请求方式
        dataType:"json",   //返回格式为json
        data:{'outSB':sbid,'SBBDfjid':SBBDfjid},
        success:function(map){
            if(map.resultCode='SUCCESS'){
                showMsg("操作成功！", function () {
                    inSB.length=0;
                    outSB.length=0;
                    $("#inTheRoom").val('');
                    $("#outTheRoom").val('');
                    selOutTheRoom();
                    selInTheRoom();
                });
            }else {
                showMsg("操作失败！");
            }
        }
    });
}

function removeDevice(){
    var sbid = inSB.join(",");
    if(sbid == null || sbid == ''){
        showMsg("未选择设备！");
        return false;
    }
    $.ajax({
        url:removeDeviceFromRoomUrl,    //请求的url地址
        //contentType: 'application/json',
        async:true,//请求是否异步，默认为异步，这也是ajax重要特性
        type:"POST",   //请求方式
        dataType:"json",   //返回格式为json
        data:{'outSB':sbid,'SBBDfjid':SBBDfjid},
        success:function(map){
            if(map.resultCode='SUCCESS'){
                showMsg("操作成功！", function () {
                    inSB.length=0;
                    outSB.length=0;
                    $("#inTheRoom").val('');
                    $("#outTheRoom").val('');
                    selOutTheRoom();
                    selInTheRoom();
                });
            }else{
                showMsg("操作失败！")
            }
        }
    });
}

function examine1(type,datas){
    if(type.indexOf('uncheck')==-1){
        $.each(datas,function(i,v){
            // 添加时，判断一行或多行的 id 是否已经在数组里 不存则添加　
            inSB.indexOf(v.ID) == -1 ? inSB.push(v.ID) : -1;
        });
    }else{
        $.each(datas,function(i,v){
            inSB.splice(inSB.indexOf(v.ID),1);    //删除取消选中行
        });
    }
}

function examine2(type,datas){
    if(type.indexOf('uncheck')==-1){
        $.each(datas,function(i,v){
            // 添加时，判断一行或多行的 id 是否已经在数组里 不存则添加　
            outSB.indexOf(v.DEVICE_ID) == -1 ? outSB.push(v.DEVICE_ID) : -1;
        });
    }else{
        $.each(datas,function(i,v){
            outSB.splice(outSB.indexOf(v.DEVICE_ID),1);    //删除取消选中行
        });
    }
}
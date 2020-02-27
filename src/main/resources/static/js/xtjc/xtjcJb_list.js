//初始列表
function findByOnpag(){
    $('#jb_table').bootstrapTable({
        method : 'post',
        url : selJblistUrl,//请求路径
        striped : true, //是否显示行间隔色
        pageNumber : 1, //初始化加载第一页
        pagination : true,//是否分页
        sidePagination : 'server',//server:服务器端分页|client：前端分页
        sortable: true,    //是否启用排序
        pageSize : 10,//单页记录数
        pageList : [ 10, 20, 30, 50 ],//可选择单页记录数
        queryParams : function(params) {//上传服务器的参数
            var temp = {//如果是在服务器端实现分页，limit、offset这两个参数是必须的
                pageSize : params.limit, // 每页显示数量
                pageNo : (params.offset / params.limit) + 1, //当前页码
                sort: 'jb_mc',   //排序列名
                sortOrder: 'asc', //排位命令（desc，asc）
                belong_org_id : beone_org_id,
                jb_mc : $("#jb_mc").val()
            };
            return temp;
        },
        columns : [
        	  {width : '110', title : '届别名称', field : 'jb_mc', sortable : false}
            , {width : '80', title : '学段', field : 'xd_mc', sortable : false}
            , {width : '160', title : '入学学期', field : 'xq_mc', sortable : false}
            , {width : '100', title : '毕业年份', field : 'jb_bynf', sortable : false}
            , {width : '100', title : '当前所属年级', field : 'njname',
                formatter : function(value, row) {
                    if(row.jb_sfjs==2){
                        return '已结束';
                    }else if(value == undefined || value == ''){
                        return '已结束';
                    }else{
                        return value;
                    }
                }
            }
            , {width : '100', title : '是否已结束', field : 'jb_sfjs', sortable : false,
				formatter : function(value, row) {
            		if(value == '1'){
            			return '否';
					}else {
            			return '是';
					}
            	}
            }
            , {width : '100', title : '修改人', field : 'editor_username', sortable : false}
            , {width : '130', title : '修改时间', field : 'edited_time', sortable : false,
                formatter : function(value, row) {return timeFormat(value,"yyyy-MM-dd hh:mm:ss");}
        	},{
                width : '130',
                title:'操作',
                align:'center',
                sortable:false,
                field : 'cz',
                formatter:function(value, row, index){
                    var str="<a class='btn btn-success btn-xs' href='javascript:void(0);' onclick=edit('"+index+"')>修改</a>";
                    str+="&nbsp;<a class='btn btn-danger btn-xs' href='javascript:void(0);' onclick=del('"+row.jb_id+"')>删除</a>";
                    if(row.jb_sfjs == '1'){
                        str+="&nbsp;<a class='btn btn-danger btn-xs' href='javascript:void(0);' onclick=updZT('"+row.jb_id+"','2')>结束</a>";
                    }else {
                        str+="&nbsp;<a class='btn btn-danger btn-xs' href='javascript:void(0);' onclick=updZT('"+row.jb_id+"','1')>开始</a>";
                    }

                    return str;
                }
            }
        ]
        ,onLoadSuccess: function () {
            if (bslx == '2') {
                $('#jb_table').bootstrapTable('hideColumn', 'cz');//隐藏上述variablevalue列
            }
        }
    });
}

function chaxun(){
    $("#jb_table").bootstrapTable('destroy');
    findByOnpag();
}

function chongzhi(){
    $("#jb_mc").val('');
    $('#addSubBut').removeAttr("disabled");
    $("#jb_table").bootstrapTable('destroy');
    findByOnpag();
}

function loadXD() {
    var jbgl_xd = $("#jbgl_xd");//根据id获取select的jquery对象
    jbgl_xd.empty();
    $.ajax({
        url:findAllXD,    //请求的url地址
        dataType:"json",   //返回格式为json
        // contentType: 'application/json',
        async:false,//请求是否异步，默认为异步，这也是ajax重要特性
        // data: JSON.stringify(params),    //参数值
        type:"POST",   //请求方式
        success:function(req){
            if(req.list != 'ERROR'){
                jbgl_xd.append("<option value='0'>请选择</option>");
                for(var i = 0; i < req.list.length; i++) {
                    //添加option元素
                    jbgl_xd.append("<option value='" + req.list[i].XD_ID + "'>" + req.list[i].XD_MC + "</option>");
                }
            }

        }
    });
}

function loadXQ() {
    var jbgl_xd = $("#jbgl_rxxq");//根据id获取select的jquery对象
    jbgl_xd.empty();
    $.ajax({
        url:findAllXQ,    //请求的url地址
        dataType:"json",   //返回格式为json
        // contentType: 'application/json',
        async:false,//请求是否异步，默认为异步，这也是ajax重要特性
        // data: JSON.stringify(params),    //参数值
        type:"POST",   //请求方式
        success:function(req){
            if(req.list != 'ERROR'){
                jbgl_xd.append("<option value='0'>请选择</option>");
                for(var i = 0; i < req.list.length; i++) {
                    //添加option元素
                    jbgl_xd.append("<option value='" + req.list[i].XQ_ID + "'>" + req.list[i].XQ_MC + "</option>");
                }
            }

        }
    });
}

function openAddModal(){
    loadXD();
    loadXQ();
    removeValidator();
    $("#xqgl_id").val('');
    $("#jbgl_xd").val('0');
    $("#jbgl_rxxq").val('0');
    $('#addModal').modal({backdrop:'static', keyboard:false});
}

function removeValidator(){
    //移除上次的校验配置
    $("#addForm").data('bootstrapValidator').destroy();
    $('#addForm').data('bootstrapValidator',null);
    //重新添加校验
    ValidatorAddJB();
}

function ValidatorAddJB(){
    $('#addForm').bootstrapValidator({
        fields: {
            jbgl_xd: {
                validators: {
                    notEmpty: {
                        message: '学段不能为空！'
                    },
                    callback:{
                        message: '请选择一个学段！',
                        callback: function (value,validator) {
                            if (value==0){
                                return false;
                            }else {
                                return true;
                            }
                        }
                    }
                }
            },
            jbgl_rxxq: {
                validators: {
                    notEmpty: {
                        message: '入学学期不能为空！'
                    },
                    callback:{
                        message: '请选择一个入学学期！',
                        callback: function (value,validator) {
                            if (value==0){
                                return false;
                            }else {
                                return true;
                            }
                        }
                    },
                    remote: { // ajax校验，获得一个json数据（{'valid': true or false}）
                        url: checkJBCZ,       //验证地址
                        message: '该届别已被创建！',   //提示信息
                        type: 'POST',          //请求方式
                        data: function(validator) {  //自定义提交数据，默认为当前input name值
                            return {
                                id: $("#xqgl_id").val(),
                                XD_ID: $("#jbgl_xd").val(),
                                RXXQ_ID: $("#jbgl_rxxq").val(),
                                BELONG_ORG_ID: beone_org_id
                            };
                        }
                    }
                }
            }
        }
    }).on('success.form.bv', function(e) {

        var xqgl_id = $("#xqgl_id").val();
        var jbgl_xd = $("#jbgl_xd").val();
        var jbgl_rxxq = $("#jbgl_rxxq").val();
        var params = {
            JB_ID:"'"+xqgl_id+"'",
            XD_ID:"'"+jbgl_xd+"'",
            RXXQ_ID:"'"+jbgl_rxxq+"'",
            BELONG_ORG_ID:"'"+beone_org_id+"'"
        }
        var url=addJBUrl;
        $("#addSubBut").attr("disabled", true);
        if(xqgl_id != null && xqgl_id != ''){
            url = editJBUrl;
            $("#addSubBut").attr("disabled", false);
        }

        $.ajax({
            url:url,    //请求的url地址
            dataType:"json",   //返回格式为json
            contentType: 'application/json',
            async:true,//请求是否异步，默认为异步，这也是ajax重要特性
            data: JSON.stringify(params),    //参数值
            type:"POST",   //请求方式
            success:function(req){
                if(req.resultCode == 'SUCCESS'){
                    $("#addModal").modal('hide');  //手动关闭
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

function addSubmit(){
    $('#addForm').data('bootstrapValidator').validate();//启用验证
}

function edit(index){
    loadXD();
    loadXQ();
    var row = $('#jb_table').bootstrapTable('getData')[index];

    removeValidator();

    $("#xqgl_id").val(row.jb_id);
    $("#jbgl_xd").val(row.xd_id);
    $("#jbgl_rxxq").val(row.rxxq_id);

    $('#addSubBut').removeAttr("disabled");
    $('#addModal').modal({backdrop:'static', keyboard:false});
}

function del(id) {
    showConfirm("确定要删除该届别？", function() {
        $.ajax({
            url:delJBUrl,    //请求的url地址
            dataType:"json",   //返回格式为json
            contentType: 'application/json',
            async:true,//请求是否异步，默认为异步，这也是ajax重要特性
            data: JSON.stringify({id:id}),    //参数值
            type:"POST",   //请求方式
            success:function(req){
                if(req.resultCode == 'SUCCESS'){
                    showMsg("删除成功！", function () {
                        chongzhi();
                    });
                }else if (req.resultCode == 'ERRORYS'){
                    showMsg(req.resultMsg);
                } else{
                    showMsg("删除失败！");
                }

            }
        });
    });
}

function updZT(id,par) {
    showConfirm("确定要修改当前届别的状态？", function() {
        $.ajax({
            url:updJBZTUrl,    //请求的url地址
            dataType:"json",   //返回格式为json
            contentType: 'application/json',
            async:true,//请求是否异步，默认为异步，这也是ajax重要特性
            data: JSON.stringify({id:id,par:par}),    //参数值
            type:"POST",   //请求方式
            success:function(req){
                if(req.resultCode == 'SUCCESS'){
                    showMsg("修改成功！", function () {
                        chongzhi();
                    });
                }else if (req.resultCode == 'ERROR'){
                    showMsg(req.resultMsg);
                } else{
                    showMsg("修改失败！");
                }

            }
        });
    });
}
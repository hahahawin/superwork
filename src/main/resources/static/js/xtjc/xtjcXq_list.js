//初始列表
function findByOnpag(){
    $('#xq_table').bootstrapTable({
        method : 'post',
        url : selXqlistUrl,//请求路径
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
                sort: 'xq_mc',      //排序列名
                sortOrder: 'desc', //排位命令（desc，asc）
                belong_org_id : beone_org_id,
                xq_mc : $("#xq_mc").val()
            };
            return temp;
        },
        columns : [
        	  {width : '170', title : '学期名称', field : 'xq_mc', sortable : false}
            , {width : '120', title : '学年（度）', field : 'xnd_mc', sortable : false}
            , {width : '100', title : '学期码', field : 'xq_xqm', sortable : false,
				formatter : function(value, row, index) {
            		if(value == '1'){
            			return '秋季学期';
					}else {
            			return '春季学期';
					}
            	}
            }
            , {width : '100', title : '开始日期', field : 'xq_kssj', sortable : false,
				formatter : function(value, row) {return timeFormat(value,"yyyy-MM-dd");}}
            , {width : '100', title : '结束日期', field : 'xq_jssj', sortable : false,
				formatter : function(value, row) {return timeFormat(value,"yyyy-MM-dd");}}
            , {width : '100', title : '是否当前学期', field : 'xq_sfdqxq', sortable : false,
                formatter : function(di_key, row, index) {
                    if(di_key == 2){
                        return '是';
                    }else{
                        return '否';
                    }
                }
            }
            , {width : '100', title : '是否已结束', field : 'sfjs', sortable : false,
				formatter : function(value, row) {
            		if(value == '1'){
            			return '否';
					}else {
            			return '是';
					}
				}}
            , {width : '100', title : '修改人', field : 'editor_username', sortable : false}
            , {width : '150', title : '修改时间', field : 'edited_time', sortable : false,
				formatter : function(value, row) {return timeFormat(value,"yyyy-MM-dd hh:mm:ss");}
        	},{
                width : '100',
                title:'操作',
                align:'center',
                field : 'cz',
                sortable:false,
                formatter:function(value, row, index){
                    var str="<a class='btn btn-success btn-xs' href='javascript:void(0);' onclick=edit('"+index+"')>修改</a>";
                    str+="&nbsp;<a class='btn btn-danger btn-xs' href='javascript:void(0);' onclick=del('"+row.xq_id+"')>删除</a>";
                    return str;
                }
            }
        ]
        ,onLoadSuccess: function () {
            if (bslx == '2') {
                $('#xq_table').bootstrapTable('hideColumn', 'cz');//隐藏上述variablevalue列
            }
        }
    });
}

function chaxun(){
    $("#xq_table").bootstrapTable('destroy');
    findByOnpag();
}

function chongzhi(){
    $("#xq_mc").val('');
    $("#xq_table").bootstrapTable('destroy');
    findByOnpag();
}

function openAddModal(){
    loadXND();
    removeValidator();
    $("#xqgl_id").val('');
    $("#xqgl_xnd").val('0');
    $("#xqgl_xqm").val('0');
    $("#xqgl_xqmc").val('');
    $("#xqgl_ksrq").val('');
    $("#xqgl_jsrq").val('');
    $('#addSubBut').removeAttr("disabled");
    $('#addModal').modal({backdrop:'static', keyboard:false});
}

function removeValidator(){
    //移除上次的校验配置
    $("#addForm").data('bootstrapValidator').destroy();
    $('#addForm').data('bootstrapValidator',null);
    //重新添加校验
    ValidatorAddXQ();
}

function ValidatorAddXQ(){
    $('#addForm').bootstrapValidator({
        fields: {
            xqgl_xnd: {
                validators: {
                    notEmpty: {
                        message: '学年（度）不能为空！'
                    },
                    callback:{
                        message: '请选择一个学年（度）！',
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
            xqgl_xqm: {
                validators: {
                    notEmpty: {
                        message: '学期码不能为空！'
                    },
                    callback:{
                        message: '请选择一个学期码！',
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
            xqgl_xqmc: {
                message: '学期名称不能为空！',
                validators: {
                    notEmpty: {
                        message: '学期名称不能为空！'
                    },
                    stringLength: {
                        min: 1,
                        max: 20,
                        message: '长度在1--20之间！'
                    },
                    remote: { // ajax校验，获得一个json数据（{'valid': true or false}）
                        url: checkXQMC,       //验证地址
                        message: '该学年度已被创建！',   //提示信息
                        type: 'POST',          //请求方式
                        data: function(validator) {  //自定义提交数据，默认为当前input name值
                            return {
                                id: $("#xqgl_id").val(),
                                XQ_MC: $("#xqgl_xqmc").val(),
                                BELONG_ORG_ID: beone_org_id
                            };
                        }
                    }
                }
            },
            xqgl_ksrq: {
                message: '开始日期不能为空！',
                validators: {
                    notEmpty: {
                        message: '开始日期不能为空！'
                    }
                }
            },
            xqgl_jsrq: {
                message: '结束日期不能为空！',
                validators: {
                    notEmpty: {
                        message: '结束日期不能为空！'
                    },
                    callback:{
                        message: '开始日期必须小于结束日期！',
                        callback: function (value,validator) {
                            var ks = $("#xqgl_ksrq").val();
                            var js = $("#xqgl_jsrq").val();
                            if (ks>js){
                                return false;
                            }else {
                                return true;
                            }
                        }
                    }
                }
            }
        }
    }).on('success.form.bv', function(e) {
        var xqgl_id = $("#xqgl_id").val();
        var xqgl_xnd = $("#xqgl_xnd").val();
        var xqgl_xqm = $("#xqgl_xqm").val();
        var xqgl_xqmc = $("#xqgl_xqmc").val();
        var xqgl_ksrq = $("#xqgl_ksrq").val();
        var xqgl_jsrq = $("#xqgl_jsrq").val();
        var params = {
            XQ_ID:"'"+xqgl_id+"'",
            XQ_XN:"'"+xqgl_xnd+"'",
            XQ_XQM:"'"+xqgl_xqm+"'",
            XQ_MC:"'"+xqgl_xqmc+"'",
            XQ_KSSJ:"to_date('"+xqgl_ksrq+"','yyyy-MM-dd')",
            XQ_JSSJ:"to_date('"+xqgl_jsrq+"','yyyy-MM-dd')",
            BELONG_ORG_ID:"'"+beone_org_id+"'"
        }
        var url=addXQUrl;
        $("#addSubBut").attr("disabled", true);
        if(xqgl_id != null && xqgl_id != ''){
            url = editXQUrl;
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

//确定添加按钮
function addSubmit(){
    $('#addForm').data('bootstrapValidator').validate();//启用验证
}

function edit(index){
    loadXND();
    var row = $('#xq_table').bootstrapTable('getData')[index];

    removeValidator();

    $("#xqgl_id").val(row.xq_id);
    $("#xqgl_xnd").val(row.xq_xn);
    $("#xqgl_xqm").val(row.xq_xqm);
    $("#xqgl_xqmc").val(row.xq_mc);
    $("#xqgl_ksrq").val(timeFormat(row.xq_kssj,"yyyy-MM-dd"));
    $("#xqgl_jsrq").val(timeFormat(row.xq_jssj,"yyyy-MM-dd"));

    $('#addSubBut').removeAttr("disabled");
    $('#addModal').modal({backdrop:'static', keyboard:false});
}
function del(id) {
    showConfirm("确定要删除该学期？", function() {
        $.ajax({
            url:delXQUrl,    //请求的url地址
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

function loadXND() {
    var jbgl_xd = $("#xqgl_xnd");//根据id获取select的jquery对象
    jbgl_xd.empty();
    $.ajax({
        url:findAllXND,    //请求的url地址
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
                    jbgl_xd.append("<option value='" + req.list[i].XND_ID + "'>" + req.list[i].XND_MC + "</option>");
                }
            }

        }
    });
}
//初始列表
function findByOnpag(){
    $('#xnd_table').bootstrapTable({
        method : 'post',
        url : selXndlistUrl,//请求路径
        striped : true, //是否显示行间隔色
        pageNumber : 1, //初始化加载第一页
        pagination : true,//是否分页
        sidePagination : 'server',//server:服务器端分页|client：前端分页
        pageSize : 10,//单页记录数
        pageList : [ 10, 20, 30, 50 ],//可选择单页记录数
        queryParams : function(params) {//上传服务器的参数
            var temp = {//如果是在服务器端实现分页，limit、offset这两个参数是必须的
                pageSize : params.limit, // 每页显示数量
                pageNo : (params.offset / params.limit) + 1, //当前页码
                belong_org_id : beone_org_id,
                xnd_mc : $("#xnd_mc").val()
            };
            return temp;
        },
        columns : [
              {width : '100', title : '学年度代码', field : 'xnd_dm', sortable : false}
            , {width : '120', title : '学年度名称', field : 'xnd_mc', sortable : false}
            , {width : '100', title : '开始日期', field : 'xnd_kssj', sortable : false,
				formatter : function(value, row) {return timeFormat(value,"yyyy-MM-dd");}
			}
            , {width : '100', title : '结束日期', field : 'xnd_jssj', sortable : false,
				formatter : function(value, row) {return timeFormat(value,"yyyy-MM-dd");}
			}
            , {width : '100', title : '是否当前学年度', field : 'xnd_sfdqxnd', sortable : false,
                formatter : function(di_key, row) {
                    if(di_key == '2'){
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
				}
			}
            , {width : '100', title : '修改人', field : 'editor_username', sortable : false}
            , {width : '130', title : '修改时间', field : 'edited_time', sortable : false,
				formatter : function(value, row) {return timeFormat(value,"yyyy-MM-dd hh:mm:ss");}
        	},{
                width : '100',
                title:'操作',
                field : 'cz',
                align:'center',
                sortable:false,
                formatter:function(value, row, index){
                    var str="<a class='btn btn-success btn-xs' href='javascript:void(0);' onclick=edit('"+index+"')>修改</a>";
                    str+="&nbsp;<a class='btn btn-danger btn-xs' href='javascript:void(0);' onclick=del('"+row.xnd_id+"')>删除</a>";
                    return str;
                }
            }
        ]
        ,onLoadSuccess: function () {
            if (bslx == '2') {
                $('#xnd_table').bootstrapTable('hideColumn', 'cz');//隐藏上述variablevalue列
            }
        }
    });
}

function chaxun(){
    $("#xnd_table").bootstrapTable('destroy');
    findByOnpag();
}

function chongzhi(){
    $("#xnd_mc").val('');
    $("#xnd_table").bootstrapTable('destroy');
    findByOnpag();
}

function openAddModal(){
    removeValidator();
    $("#xndgl_id").val('');
    $("#xndgl_xnddm").val('');
    $("#xndgl_xndmc").val('');
    $("#xndgl_ksrq").val('');
    $("#xndgl_jsrq").val('');
    $('#addSubBut').removeAttr("disabled");
    $('#addModal').modal({backdrop:'static', keyboard:false});
}

function removeValidator(){
    //移除上次的校验配置
    $("#addForm").data('bootstrapValidator').destroy();
    $('#addForm').data('bootstrapValidator',null);
    //重新添加校验
    ValidatorAddXND();
}

//确定添加按钮
function addSubmit(){
    $('#addForm').data('bootstrapValidator').validate();//启用验证
}

function ValidatorAddXND(){
    $('#addForm').bootstrapValidator({
        fields: {
            xndgl_xnddm: {
                validators: {
                    notEmpty: {
                        message: '学年度代码不能为空！'
                    },
                    regexp:{
                        regexp: '^([2-9])\\d{3}-([2-9])\\d{3}$',
                        message:'例如：2012-2013'
                    },
                    callback:{
                        message: '格式不对，【-】前后只能相差1年',
                        callback: function (value) {
                            var a = value.split('-');
                            if ((a[1]-a[0])!=1) {
                                return false;
                            }else {
                                return true;
                            }
                        }
                    },
                    remote: { // ajax校验，获得一个json数据（{'valid': true or false}）
                        url: checkXNDDM,       //验证地址
                        message: '该学年度代码已被创建！',   //提示信息
                        type: 'POST',          //请求方式
                        data: function(validator) {  //自定义提交数据，默认为当前input name值
                            return {
                                id: $("#xndgl_id").val(),
                                XND_DM: $("#xndgl_xnddm").val(),
                                BELONG_ORG_ID: beone_org_id
                            };
                        }
                    }
                }
            },
            xndgl_xndmc: {
                message: '学年度不能为空！',
                validators: {
                    notEmpty: {
                        message: '学年度不能为空！'
                    },
                    stringLength: {
                        min: 1,
                        max: 20,
                        message: '长度在1--20之间！'
                    },
                    remote: { // ajax校验，获得一个json数据（{'valid': true or false}）
                        url: checkXNDMC,       //验证地址
                        message: '该学年度已被创建！',   //提示信息
                        type: 'POST',          //请求方式
                        data: function(validator) {  //自定义提交数据，默认为当前input name值
                            return {
                                id: $("#xndgl_id").val(),
                                XND_MC: $("#xndgl_xndmc").val(),
                                BELONG_ORG_ID: beone_org_id
                            };
                        }
                    }
                }
            },
            xndgl_ksrq: {
                message: '开始日期不能为空！',
                validators: {
                    notEmpty: {
                        message: '开始日期不能为空！'
                    }
                }
            },
            xndgl_jsrq: {
                message: '结束日期不能为空！',
                validators: {
                    notEmpty: {
                        message: '结束日期不能为空！'
                    },
                    callback:{
                        message: '开始日期必须小于结束日期！',
                        callback: function (value,validator) {
                            var ks = $("#xndgl_ksrq").val();
                            var js = $("#xndgl_jsrq").val();
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
        var xndgl_id = $("#xndgl_id").val();
        var xndgl_xnddm = $("#xndgl_xnddm").val();
        var xndgl_xndmc = $("#xndgl_xndmc").val();
        var xndgl_ksrq = $("#xndgl_ksrq").val();
        var xndgl_jsrq = $("#xndgl_jsrq").val();
        var params = {
            XND_ID:"'"+xndgl_id+"'",
            XND_DM:"'"+xndgl_xnddm+"'",
            XND_MC:"'"+xndgl_xndmc+"'",
            XND_KSSJ:"to_date('"+xndgl_ksrq+"','yyyy-MM-dd')",
            XND_JSSJ:"to_date('"+xndgl_jsrq+"','yyyy-MM-dd')",
            BELONG_ORG_ID:"'"+beone_org_id+"'"
        }
        var url=addXNDUrl;
        $("#addSubBut").attr("disabled", true);
        if(xndgl_id != null && xndgl_id != ''){
            url = editXNDUrl;
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

function edit(index){
    var row = $('#xnd_table').bootstrapTable('getData')[index];

    removeValidator();
    var xndgl_id = $("#xndgl_id").val(row.xnd_id);
    var xndgl_xnddm = $("#xndgl_xnddm").val(row.xnd_dm);
    var xndgl_xndmc = $("#xndgl_xndmc").val(row.xnd_mc);
    var xndgl_ksrq = $("#xndgl_ksrq").val(timeFormat(row.xnd_kssj,"yyyy-MM-dd"));
    var xndgl_jsrq = $("#xndgl_jsrq").val(timeFormat(row.xnd_jssj,"yyyy-MM-dd"));
    $('#addSubBut').removeAttr("disabled");
    $('#addModal').modal({backdrop:'static', keyboard:false});
}

function del(id) {
    showConfirm("确定要删除该学年度？", function() {
        $.ajax({
            url:delXNDUrl,    //请求的url地址
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

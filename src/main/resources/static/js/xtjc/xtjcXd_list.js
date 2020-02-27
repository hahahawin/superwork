//初始列表
function findByOnpag(){
    $('#xd_table').bootstrapTable({
        method : 'post',
        url : selXdlistUrl,//请求路径
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
                xd_mc : $("#xd_mc").val(),
                xd_sfky : $("#xd_sfky").val()
            };
            return temp;
        },
        columns : [ {
            title : '学段名称',
            field : 'xd_mc',
            sortable : true
        }, {
            width : '100',
            title : '学制',
            field : 'xd_xns',
            sortable : true
        }, {
            width : '100',
            title : '入学年龄',
            field : 'xd_rxnl',
            sortable : true
        }, {
            width : '100',
            title : '学段简称',
            field : 'xd_jc',
            sortable : true
        }, {
            width : '100',
            title : '是否可用',
            field : 'xd_sfky',
            sortable : true,
            formatter : function(value) {
                if(value == '1'){
                	return '否';
				}else{
                	return '是';
				}
            }
        }, {
            width : '100',
            title : '修改人',
            field : 'editor_username',
            sortable : true
        }, {
            width : '200',
            title : '修改时间',
            field : 'edited_time',
            sortable : true,
            formatter : function(value) {
                return timeFormat(value,"yyyy-MM-dd hh:mm:ss");
            }
        },{
            title:'操作',
            align:'center',
            field : 'cz',
            sortable:false,
            formatter:function(value, row, index){
                var str = '';
                if(bslx!='2'){
                    str += "<a class='btn btn-success btn-xs' href='javascript:void(0);' onclick=edit('"+index+"')>修改</a>";
                    str+="&nbsp;<a class='btn btn-danger btn-xs' href='javascript:void(0);' onclick=del('"+row.xd_id+"')>删除</a>";
                }
                str+="&nbsp;<a class='btn btn-success btn-xs' href='javascript:void(0);' onclick=toNjModel('"+row.xd_id+"')>年级列表</a>";
                return str;
            }
        }]
        // ,onLoadSuccess: function () {
        //     if (bslx == '2') {
        //         $('#xd_table').bootstrapTable('hideColumn', 'cz');//隐藏上述variablevalue列
        //     }
        // }
    });
}

function chaxun(){
    $("#xd_table").bootstrapTable('destroy');
    findByOnpag();
}

function chongzhi(){
	$("#xd_mc").val('');
    $("#xd_sfky").val('');
    $("#xd_table").bootstrapTable('destroy');
    findByOnpag();
}

function operationMenu(xd_id){
    var str = '<div class="operator_div">';
    str += '<a href="javascript:void(0);" onclick=toNjModel("'+ xd_id +'")>年级管理</a>';
    str += '</div>';
    return str;
};

function toNjModel(xd_id){
    c_xd_id = xd_id;
    $('#njglModal').modal({backdrop:'static', keyboard:false});
    selNjlist();
}
//年级列表查询
function selNjlist(){
    $("#nj_table").bootstrapTable('destroy');
    $('#nj_table').bootstrapTable({
        method : 'post',
        url : selNjlistUrl,//请求路径
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
                xd_id : c_xd_id,
                nj_sfky:2
            };
            return temp;
        },
        columns : [ {
            width : '150',
            title : '年级名称',
            field : 'nj_njmc',
            sortable : false
        }, {
            width : '100',
            title : '是否可用',
            field : 'nj_sfky',
            sortable : false,
            formatter : function(value) {
                if(value == '1'){
                    return '否';
                }else{
                    return '是';
                }
            }
        }, {
            width : '100',
            title : '年级顺序',
            field : 'nj_seq',
            sortable : true
        }, {
            width : '100',
            title : '修改人',
            field : 'editor_username',
            sortable : true
        }, {
            width : '200',
            title : '修改时间',
            field : 'edited_time',
            sortable : true,
            formatter : function(value) {
                return timeFormat(value,"yyyy-MM-dd hh:mm:ss");
            }
        }]
    });
}

//添加学段 弹窗打开
function openAddModal(){
    loadBXLX();
    removeValidator();
    $("#xdgl_id").val('');
    $("#xdgl_xddm").val('0');
    $("#xdgl_xdjc").val('');
    $("#xdgl_rxnl").val('');
    $("#xdgl_xz").val('');
    $("#xdgl_xdmc").val('');
    $('#addSubBut').removeAttr("disabled");
    $('#addModal').modal({backdrop:'static', keyboard:false});
}

function edit(index){
    loadBXLX();
    var row = $('#xd_table').bootstrapTable('getData')[index];

    var xd_id = row.xd_id;
    var xd_dm = row.xd_dm;
    var xd_jc = row.xd_jc;
    var xd_rxnl = row.xd_rxnl;
    var xd_xns = row.xd_xns;
    var xd_mc = row.xd_mc;
    removeValidator();

    $("#xdgl_id").val(xd_id);
    $("#xdgl_xddm").val(xd_dm);
    $("#xdgl_xdjc").val(xd_jc);
    $("#xdgl_rxnl").val(xd_rxnl);
    $("#xdgl_xz").val(xd_xns);
    $("#xdgl_xdmc").val(xd_mc);
    $('#addSubBut').removeAttr("disabled");
    $('#addModal').modal({backdrop:'static', keyboard:false});
}


//确定添加按钮
function addSubmit(){
    $('#addForm').data('bootstrapValidator').validate();//启用验证
}

function removeValidator(){
    //移除上次的校验配置
    $("#addForm").data('bootstrapValidator').destroy();
    $('#addForm').data('bootstrapValidator',null);
    //重新添加校验
    ValidatorAddXD();
}

//添加学段时的验证
function ValidatorAddXD(){
    $('#addForm').bootstrapValidator({
        fields: {
            xdgl_xddm: {
                validators: {
                    notEmpty: {
                        message: '学段代码不能为空！'
                    },
                    callback:{
                        message: '请选择一个学段代码！',
                        callback: function (value,validator) {
                            if (value==0){
                                return false;
                            }else {
                                return true;
                            }
                        }
                    },
                    remote: { // ajax校验，获得一个json数据（{'valid': true or false}）
                        url: checkXDDM,       //验证地址
                        message: '该学段代码已被创建！',   //提示信息
                        type: 'POST',          //请求方式
                        data: function(validator) {  //自定义提交数据，默认为当前input name值
                            return {
                                id: $("#xdgl_id").val(),
                                XD_DM: $("#xdgl_xddm").val(),
                                BELONG_ORG_ID: beone_org_id
                            };
                        }
                    }
                }
            },
            xdgl_xdjc: {
                message: '学段简称不能为空！',
                validators: {
                    notEmpty: {
                        message: '学段简称不能为空！'
                    },
                    stringLength: {
                        min: 1,
                        max: 10,
                        message: '长度在1--10之间！'
                    },
                    remote: { // ajax校验，获得一个json数据（{'valid': true or false}）
                        url: checkXDJC,       //验证地址
                        message: '该学段简称已被创建！',   //提示信息
                        type: 'POST',          //请求方式
                        data: function(validator) {  //自定义提交数据，默认为当前input name值
                            return {
                                id: $("#xdgl_id").val(),
                                XD_JC: $("#xdgl_xdjc").val(),
                                BELONG_ORG_ID: beone_org_id
                            };
                        }
                    }
                }
            },
            xdgl_rxnl: {
                message: '入学年龄不能为空！',
                validators: {
                    notEmpty: {
                        message: '入学年龄不能为空！'
                    },
                    regexp:{
                        regexp: '^[1-9]\\d*$',
                        message:'正整数！'
                    }
                }
            },
            xdgl_xz: {
                message: '学制不能为空！',
                validators: {
                    notEmpty: {
                        message: '学制不能为空！'
                    },
                    regexp:{
                        regexp: '^[1-9]\\d*$',
                        message:'正整数！'
                    }
                }
            },
            xdgl_xdmc: {
                message: '学段名称不能为空！',
                validators: {
                    notEmpty: {
                        message: '学段名称不能为空！'
                    },
                    stringLength: {
                        min: 1,
                        max: 20,
                        message: '长度在1--20之间！'
                    },
                    remote: { // ajax校验，获得一个json数据（{'valid': true or false}）
                        url: checkXDMC,       //验证地址
                        message: '该学段名称已被创建！',   //提示信息
                        type: 'POST',          //请求方式
                        data: function(validator) {  //自定义提交数据，默认为当前input name值
                            console.info($("#xdgl_id").val());
                            return {
                                id: $("#xdgl_id").val(),
                                XD_MC: $("#xdgl_xdmc").val(),
                                BELONG_ORG_ID: beone_org_id
                            };
                        }
                    }
                }
            }
        }
    }).on('success.form.bv', function(e) {

        var xdgl_id = $("#xdgl_id").val();
        var xdgl_xddm = $("#xdgl_xddm").val();
        var xdgl_xdjc = $("#xdgl_xdjc").val();
        var xdgl_rxnl = $("#xdgl_rxnl").val();
        var xdgl_xz = $("#xdgl_xz").val();
        var xdgl_xdmc = $("#xdgl_xdmc").val();
        var params = {
            XD_ID:"'"+xdgl_id+"'",
            XD_DM:"'"+xdgl_xddm+"'",
            XD_JC:"'"+xdgl_xdjc+"'",
            XD_RXNL:"'"+xdgl_rxnl+"'",
            XD_XNS:"'"+xdgl_xz+"'",
            XD_MC:"'"+xdgl_xdmc+"'",
            BELONG_ORG_ID:"'"+beone_org_id+"'"
        }
        var url=addXDUrl;
        $("#addSubBut").attr("disabled", true);
        if(xdgl_id != null && xdgl_id != ''){
            url = editXDUrl;
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

function del(id) {
    showConfirm("确定要删除该学段？", function() {
        $.ajax({
            url:delXDUrl,    //请求的url地址
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

function loadBXLX() {
    var jbgl_xd = $("#xdgl_xddm");//根据id获取select的jquery对象
    jbgl_xd.empty();
    $.ajax({
        url:findAllBXLX,    //请求的url地址
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
                    jbgl_xd.append("<option value='" + req.list[i].DI_KEY + "'>" + req.list[i].DI_VALUE + "</option>");
                }
            }

        }
    });
}
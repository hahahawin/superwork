//初始列表
function findByOnpag(){
    $('#bj_table').bootstrapTable({
        method : 'post',
        url : selBjlistUrl,//请求路径
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
                bj_mc : $("#bj_mc").val()
            };
            return temp;
        },
        columns : [
          {width : 120, title : '所属届别', field : 'jb_mc', sortable : false}
        , {width : 200, title : '班级名称', field : 'bj_mc', sortable : false}
        , {width : 100, title : '班号', field : 'bj_bh', sortable : false}
		, {width : 120, title : '所属年级', field : 'nj_njmc', sortable : false,
			formatter : function(di_key, row) {
				if(row.bj_sfyjs==2){
					return '已结束';
				}else if(di_key == undefined){
					return '已结束';
				}else{
					return di_key;
				}
			}
		}
		, {width : '80', title : '是否结束', field : 'bj_sfyjs', sortable : false
			, formatter : function(value, row) {
				if(value == '1'){
					return '否';
				}else{
					return '是';
				}
			}
		}
		, {width : '100', title : '班主任', field : 'bzr_name', sortable : false}
		, {width : '150', title : '校区', field : 'xqjbxx_name', sortable : false,
			formatter : function(di_key, row) {
				if(row.xqjbxx_sfzb == '1'){
					return di_key+'<font>(已停办)</font>';
				}else{
					return di_key;
				}
			}
		}
		, {width : '150', title : '教室名称', field : 'fjxx_fjmc', sortable : false}
        ,{
            width : '100',
            title:'操作',
            align:'center',
            field : 'cz',
            sortable:false,
            formatter:function(value, row, index){
                var str="<a class='btn btn-success btn-xs' href='javascript:void(0);' onclick=edit('"+index+"')>修改</a>";
                str+="&nbsp;<a class='btn btn-danger btn-xs' href='javascript:void(0);' onclick=del('"+row.bj_id+"')>删除</a>";
                return str;
            }
        }]
        ,onLoadSuccess: function () {
            if (bslx == '2') {
                $('#bj_table').bootstrapTable('hideColumn', 'cz');//隐藏上述variablevalue列
            }
        }
    });
}

function chaxun(){
    $("#bj_table").bootstrapTable('destroy');
    findByOnpag();
}

function chongzhi(){
    $("#bj_mc").val('');
    $("#bj_table").bootstrapTable('destroy');
    findByOnpag();
}

function openAddModal(){
    loadJB();
    loadXQJBXX();
    loadBJLX();
    removeValidator();
    $("#bjgl_id").val('');
    $("#bjgl_ssjb").val('0');
    $("#bjgl_bjqz").val('');
    $("#bjgl_bjgs").val('');
    $("#bjgl_jbny").val('');
    $("#bjgl_sfyjs").val('0');
    $("#bjgl_bjlx").val('0');
    $("#bjgl_fklx").val('0');
    $("#bjgl_bjdjlx").val('0');
    $("#bjgl_xxrs").val('');
    $("#bjgl_bzrs").val('');
    $("#bjgl_sxrs").val('');
    $("#bjgl_sfssmzsyjxb").val('0');
    $("#bjgl_ssxq").val('0');
    $('#addSubBut').removeAttr("disabled");
    $('#addModal').modal({backdrop:'static', keyboard:false});
}

function removeValidator(){
    //移除上次的校验配置
    $("#addForm").data('bootstrapValidator').destroy();
    $('#addForm').data('bootstrapValidator',null);
    //重新添加校验
    ValidatorAddBJ();
}

function ValidatorAddBJ(){
    $('#addForm').bootstrapValidator({
        fields: {
            bjgl_ssjb: {
                validators: {
                    notEmpty: {
                        message: '所属届别不能为空！'
                    },
                    callback:{
                        message: '请选择一个所属届别！',
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
            bjgl_ssnj: {
                message: '所属年级不能为空！',
                validators: {
                    notEmpty: {
                        message: '所属年级不能为空！'
                    },
                    stringLength: {
                        min: 1,max: 20,message: '长度在1--20之间！'
                    }
                }
            },
            bjgl_bjqz: {
                message: '班号前缀不能为空！',
                validators: {
                    notEmpty: {
                        message: '班号前缀不能为空！'
                    },
                    stringLength: {
                        min: 1,max: 20,message: '长度在1--20之间！'
                    },
                    remote: { // ajax校验，获得一个json数据（{'valid': true or false}）
                        url: checkBJQZ,       //验证地址
                        message: '该班级前缀已被创建！',   //提示信息
                        type: 'POST',          //请求方式
                        data: function(validator) {  //自定义提交数据，默认为当前input name值
                            return {
                                id:$("#bjgl_id").val(),
                                BJ_MC: $("#bjgl_bjqz").val(),
                                BELONG_ORG_ID: beone_org_id
                            };
                        }
                    }
                }
            },
            bjgl_jbny: {
                message: '建班年月不能为空！',
                validators: {
                    notEmpty: {
                        message: '建班年月不能为空！'
                    },
                    stringLength: {
                        min: 1,max: 20,message: '长度在1--20之间！'
                    }
                }
            },
            bjgl_sfyjs: {
                message: '是否已结束不能为空！',
                validators: {
                    notEmpty: {
                        message: '是否已结束不能为空！'
                    },
                    callback:{
                        message: '请选择一个状态！',
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
            bjgl_bjlx: {
                message: '班级类型不能为空！',
                validators: {
                    notEmpty: {
                        message: '班级类型不能为空！'
                    },
                    callback:{
                        message: '请选择一个班级类型！',
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
            bjgl_fklx: {
                message: '分科类型不能为空！',
                validators: {
                    notEmpty: {
                        message: '分科类型不能为空！'
                    },
                    callback:{
                        message: '请选择一个分科类型！',
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
            bjgl_bjdjlx: {
                message: '班级等级类型不能为空！',
                validators: {
                    notEmpty: {
                        message: '班级等级类型不能为空！'
                    },
                    callback:{
                        message: '请选择一个班级等级类型！',
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
            bjgl_xxrs: {
                message: '下限人数不能为空！',
                validators: {
                    notEmpty: {
                        message: '下限人数不能为空！'
                    },
                    regexp:{
                        regexp: '^[1-9]\\d*$',
                        message:'正整数！'
                    }
                }
            },
            bjgl_bzrs: {
                message: '标准人数不能为空！',
                validators: {
                    notEmpty: {
                        message: '标准人数不能为空！'
                    },
                    regexp:{
                        regexp: '^[1-9]\\d*$',
                        message:'正整数！'
                    }
                }
            },
            bjgl_sxrs: {
                message: '上限人数不能为空！',
                validators: {
                    notEmpty: {
                        message: '上限人数不能为空！'
                    },
                    regexp:{
                        regexp: '^[1-9]\\d*$',
                        message:'正整数！'
                    }
                }
            } ,
            bjgl_sfssmzsyjxb: {
                message: '该选项不能为空！',
                validators: {
                    notEmpty: {
                        message: '该选项不能为空！'
                    },
                    callback:{
                        message: '请选择一个类型！',
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
            bjgl_ssxq: {
                message: '所属校区不能为空！',
                validators: {
                    notEmpty: {
                        message: '所属校区不能为空！'
                    },
                    callback:{
                        message: '请选择一个所属校区！',
                        callback: function (value,validator) {
                            if (value==0){
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
        var bjgl_id = $("#bjgl_id").val();
        var bjgl_ssjb = $("#bjgl_ssjb").val();
        var bjgl_ssnj = $("#bjgl_ssnj").val();
        var bjgl_bjqz = $("#bjgl_bjqz").val();
        // var bjgl_bjgs = $("#bjgl_bjgs").val();
        var bjgl_jbny = $("#bjgl_jbny").val();
        var bjgl_sfyjs = $("#bjgl_sfyjs").val();
        var bjgl_bjlx = $("#bjgl_bjlx").val();
        var bjgl_fklx = $("#bjgl_fklx").val();
        var bjgl_bjdjlx = $("#bjgl_bjdjlx").val();
        var bjgl_xxrs = $("#bjgl_xxrs").val();
        var bjgl_bzrs = $("#bjgl_bzrs").val();
        var bjgl_sxrs = $("#bjgl_sxrs").val();
        var bjgl_sfssmzsyjxb = $("#bjgl_sfssmzsyjxb").val();
        var bjgl_ssxq = $("#bjgl_ssxq").val();
        var params = {
            BJ_ID:"'"+bjgl_id+"'",
            JB_ID:"'"+bjgl_ssjb+"'",
            BJ_BH:"'"+bjgl_ssnj+"'",
            BJ_MC:"'"+bjgl_bjqz+"'",
            // XQJBXX_XZQDM:"'"+bjgl_bjgs+"'",
            BJ_JBNY:"'"+bjgl_jbny+"'",
            BJ_SFYJS:"'"+bjgl_sfyjs+"'",
            BJ_BJLXM:"'"+bjgl_bjlx+"'",
            BJ_WLLX:"'"+bjgl_fklx+"'",
            BJ_LX:"'"+bjgl_bjdjlx+"'",
            MIN_NUMBER:"'"+bjgl_xxrs+"'",
            PNUMBER:"'"+bjgl_bzrs+"'",
            MAX_NUMBER:"'"+bjgl_sxrs+"'",
            BJ_SFSSMZSYJXB:"'"+bjgl_sfssmzsyjxb+"'",
            XQJBXX_ID:"'"+bjgl_ssxq+"'",
            BELONG_ORG_ID:"'"+beone_org_id+"'"
        }
        var url=addXQUrl;
        $("#addSubBut").attr("disabled", true);
        if(bjgl_id != null && bjgl_id != ''){
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

function loadJB() {
    var jbgl_xd = $("#bjgl_ssjb");//根据id获取select的jquery对象
    jbgl_xd.empty();
    $.ajax({
        url:findAllJB,    //请求的url地址
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
                    jbgl_xd.append("<option value='" + req.list[i].JB_ID + "'>" + req.list[i].JB_MC + "</option>");
                }
            }

        }
    });
}

function loadXQJBXX() {
    var jbgl_xd = $("#bjgl_ssxq");//根据id获取select的jquery对象
    jbgl_xd.empty();
    $.ajax({
        url:findAllXQJBXX,    //请求的url地址
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
                    jbgl_xd.append("<option value='" + req.list[i].XQJBXX_ID + "'>" + req.list[i].XQJBXX_XQMC + "</option>");
                }
            }

        }
    });
}

function loadBJLX() {
    var jbgl_xd = $("#bjgl_bjlx");//根据id获取select的jquery对象
    jbgl_xd.empty();
    $.ajax({
        url:findAllBJLX,    //请求的url地址
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

function edit(index){
    loadJB();
    loadXQJBXX();

    var row = $('#bj_table').bootstrapTable('getData')[index];
    loadBJLX();
    removeValidator();


    var bjgl_id = $("#bjgl_id").val(row.bj_id);
    var bjgl_ssjb = $("#bjgl_ssjb").val(row.jb_id);
    var bjgl_ssnj = $("#bjgl_ssnj").val(row.bj_bh);
    var bjgl_bjqz = $("#bjgl_bjqz").val(row.bj_mc);
    // var bjgl_bjgs = $("#bjgl_bjgs").val(row.xqjbxx_id);
    var bjgl_jbny = $("#bjgl_jbny").val(row.bj_jbny);
    var bjgl_sfyjs = $("#bjgl_sfyjs").val(row.bj_sfyjs);
    var bjgl_bjlx = $("#bjgl_bjlx").val(row.bj_bjlxm);
    var bjgl_fklx = $("#bjgl_fklx").val(row.bj_wllx);
    var bjgl_bjdjlx = $("#bjgl_bjdjlx").val(row.bj_lx);
    var bjgl_xxrs = $("#bjgl_xxrs").val(row.min_number);
    var bjgl_bzrs = $("#bjgl_bzrs").val(row.pnumber);
    var bjgl_sxrs = $("#bjgl_sxrs").val(row.max_number);
    var bjgl_sfssmzsyjxb = $("#bjgl_sfssmzsyjxb").val(row.bj_sfssmzsyjxb);
    var bjgl_ssxq = $("#bjgl_ssxq").val(row.xqjbxx_id);

    $('#addSubBut').removeAttr("disabled");
    $('#addModal').modal({backdrop:'static', keyboard:false});
}

function del(id) {
    showConfirm("确定要删除该班级？", function() {
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
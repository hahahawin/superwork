//初始列表
function findByOnpag(){
    $('#xqxx_table').bootstrapTable({
        method : 'post',
        url : selXqxxlistUrl,//请求路径
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
                sort: 'edited_time',      //排序列名
                sortOrder: 'desc', //排位命令（desc，asc）
                belong_org_id : beone_org_id,
                xqjbxx_xqmc : $("#xqjbxx_xqmc").val()
            };
            return temp;
        },
        columns : [
              {width : '130', title : '所属学校', field : 'xxjbxx_mc', sortable : false}
            , {width : '150', title : '校区名称', field : 'xqjbxx_xqmc', sortable : false}
            , {width : '100', title : '校区组织码', field : 'xqjbxx_xqh', sortable : false}
            // , {width : '100', title : '行政区划码', field : 'xqjbxx_xzqdm', sortable : false, formatter : function(di_key, row) {return JXCore.getDictItemNameByItemKey('XZQHDM', di_key);}}
            , {width : '120', title : '校区地址', field : 'xqjbxx_xqdz', sortable : false}
            , {width : '100', title : '校区负责人', field : 'xqjbxx_fzrh', sortable : false}
            , {width : '100', title : '是否在办', field : 'xqjbxx_sfzb', sortable : false,
				formatter : function(value, row) {
            		if (value == '1'){
            			return '停办';
					}else {
            			return '在办';
					}
            	}
        	},{
                width : '100',
                title:'操作',
                field : 'cz',
                align:'center',
                sortable:false,
                formatter:function(value, row, index){
                    var str="<a class='btn btn-success btn-xs' href='javascript:void(0);' onclick=edit('"+index+"')>修改</a>";
                    str+="&nbsp;<a class='btn btn-danger btn-xs' href='javascript:void(0);' onclick=del('"+row.xqjbxx_id+"')>删除</a>";
                    return str;
                }
            }
        ]
        ,onLoadSuccess: function () {
            if (bslx == '2') {
                $('#xqxx_table').bootstrapTable('hideColumn', 'cz');//隐藏上述variablevalue列
            }
        }
    });
}

function chaxun(){
    $("#xqxx_table").bootstrapTable('destroy');
    findByOnpag();
}

function chongzhi(){
    $("#xqjbxx_xqmc").val('');
    $("#xqxx_table").bootstrapTable('destroy');
    findByOnpag();
}

function loadXx() {
    var jbgl_xd = $("#xqgl_ssxx");//根据id获取select的jquery对象
    jbgl_xd.empty();
    $.ajax({
        url:findAllxx,    //请求的url地址
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
                    jbgl_xd.append("<option value='" + req.list[i].XXJBXX_ID + "'>" + req.list[i].XXJBXX_MC + "</option>");
                }
            }

        }
    });
}

function openAddModal(){
    loadXx();
    removeValidator();
    $("#xqgl_id").val('');
    $("#xqgl_ssxx").val('0');
    $("#xqgl_xqzzm").val('');
    $("#xqgl_xqmc").val('');
    $("#xqgl_xzqhm").val('');
    $("#xqgl_xqyzbm").val('');
    $("#xqgl_xqlxdh").val('');
    $("#xqgl_xqczdh").val('');
    $("#xqgl_xqfzr").val('');
    $("#xqgl_sfzb").val('0');
    $("#xqgl_dtwz").val('');
    $("#xqgl_bj").val('');
    $("#xqgl_xxdz").val('');
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
            xqgl_ssxx: {
                validators: {
                    notEmpty: {
                        message: '所属学校不能为空！'
                    },
                    callback:{
                        message: '请选择一个所属学校！',
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
            xqgl_xqzzm: {
                message: '校区组织码不能为空！',
                validators: {
                    notEmpty: {
                        message: '校区组织码不能为空！'
                    },
                    stringLength: {
                        min: 1,max: 20,message: '长度在1--20之间！'
                    },
                    remote: { // ajax校验，获得一个json数据（{'valid': true or false}）
                        url: checkXQZZM,       //验证地址
                        message: '该校区组织码已被创建！',   //提示信息
                        type: 'POST',          //请求方式
                        data: function(validator) {  //自定义提交数据，默认为当前input name值
                            return {
                                id: $("#xqgl_id").val(),
                                XQJBXX_XQH: $("#xqgl_xqzzm").val(),
                                BELONG_ORG_ID: beone_org_id
                            };
                        }
                    }
                }
            },
            xqgl_xqmc: {
                message: '校区名称不能为空！',
                validators: {
                    notEmpty: {
                        message: '校区名称不能为空！'
                    },
                    stringLength: {
                        min: 1,max: 20,message: '长度在1--20之间！'
                    },
                    remote: { // ajax校验，获得一个json数据（{'valid': true or false}）
                        url: checkXQMC,       //验证地址
                        message: '该校区名称已被创建！',   //提示信息
                        type: 'POST',          //请求方式
                        data: function(validator) {  //自定义提交数据，默认为当前input name值
                            return {
                                id: $("#xqgl_id").val(),
                                XQJBXX_XQMC: $("#xqgl_xqmc").val(),
                                BELONG_ORG_ID: beone_org_id
                            };
                        }
                    }
                }
            },
            xqgl_xzqhm: {
                message: '行政区划码不能为空！',
                validators: {
                    notEmpty: {
                        message: '行政区划码不能为空！'
                    },
                    stringLength: {
                        min: 1,max: 20,message: '长度在1--20之间！'
                    }
                }
            },
            xqgl_xqyzbm: {
                message: '邮政编码不能为空！',
                validators: {
                    notEmpty: {
                        message: '邮政编码不能为空！'
                    },
                    stringLength: {
                        min: 1,max: 10,message: '长度在1--10之间！'
                    },
                    regexp:{
                        regexp:'^([0-9\-]*)$',
                        message:'邮政编码格式错误！'
                    }
                }
            },
            xqgl_xqlxdh: {
                message: '联系电话不能为空！',
                validators: {
                    notEmpty: {
                        message: '联系电话不能为空！'
                    },
                    regexp: {
                        regexp: /^(\+86)?(1[3-9][0-9]{9}|1[7-8][0-9]{9})$/,
                        message: '联系电话格式错误！'
                    },
                    regexp:{
                        regexp:'^([0-9\-]*)$',
                        message:'格式错误！'
                    }
                }
            },
            xqgl_xqczdh: {
                message: '校区传真不能为空！',
                validators: {
                    notEmpty: {
                        message: '校区传真不能为空！'
                    },
                    stringLength: {
                        min: 1,max: 20,message: '长度在1--20之间！'
                    },
                    regexp: {
                        regexp: /^(\d{3,4}-)?\d{7,8}$/,
                        message: '校区传真格式错误！'

                    }
                }
            },
            xqgl_xqfzr: {
                message: '校区负责人不能为空！',
                validators: {
                    notEmpty: {
                        message: '校区负责人不能为空！'
                    },
                    stringLength: {
                        min: 1,max: 10,message: '长度在1--10之间！'
                    }
                }
            },
            xqgl_sfzb: {
                message: '是否在办不能为空！',
                validators: {
                    notEmpty: {
                        message: '是否在办不能为空！'
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
            xqgl_dtwz: {
                message: '地图位置不能为空！',
                validators: {
                    notEmpty: {
                        message: '地图位置不能为空！'
                    },
                    stringLength: {
                        min: 1,max: 50,message: '长度在1--50之间！'
                    }
                }
            },
            xqgl_bj: {
                message: '半径不能为空！',
                validators: {
                    notEmpty: {
                        message: '半径不能为空！'
                    },
                    regexp:{
                        regexp: '^[1-9]\\d*$',
                        message:'正整数！'
                    },
                    stringLength: {
                        min: 1,max: 20,message: '长度在1--20之间！'
                    }
                }
            },
            xqgl_xxdz: {
                message: '详细地址不能为空！',
                validators: {
                    notEmpty: {
                        message: '详细地址不能为空！'
                    },
                    stringLength: {
                        min: 1,max: 50,message: '长度在1--50之间！'
                    }
                }
            }
        }
    }).on('success.form.bv', function(e) {
        var xqgl_id = $("#xqgl_id").val();
        var xqgl_ssxx = $("#xqgl_ssxx").val();
        var xqgl_xqzzm = $("#xqgl_xqzzm").val();
        var xqgl_xqmc = $("#xqgl_xqmc").val();
        var xqgl_xzqhm = $("#xqgl_xzqhm").val();
        var xqgl_xqyzbm = $("#xqgl_xqyzbm").val();
        var xqgl_xqlxdh = $("#xqgl_xqlxdh").val();
        var xqgl_xqczdh = $("#xqgl_xqczdh").val();
        var xqgl_xqfzr = $("#xqgl_xqfzr").val();
        var xqgl_sfzb = $("#xqgl_sfzb").val();
        var xqgl_dtwz = $("#xqgl_dtwz").val();
        var xqgl_bj = $("#xqgl_bj").val();
        var xqgl_xxdz = $("#xqgl_xxdz").val();
        var params = {
            XQJBXX_ID:"'"+xqgl_id+"'",
            XXJBXX_ID:"'"+xqgl_ssxx+"'",
            XQJBXX_XQH:"'"+xqgl_xqzzm+"'",
            XQJBXX_XQMC:"'"+xqgl_xqmc+"'",
            XQJBXX_XZQDM:"'"+xqgl_xzqhm+"'",
            XQJBXX_XQYZBM:"'"+xqgl_xqyzbm+"'",
            XQJBXX_LXDH:"'"+xqgl_xqlxdh+"'",
            XQJBXX_CZDH:"'"+xqgl_xqczdh+"'",
            XQJBXX_FZRH:"'"+xqgl_xqfzr+"'",
            XQJBXX_SFZB:"'"+xqgl_sfzb+"'",
            GD_DLZB:"'"+xqgl_dtwz+"'",
            RADIUS:"'"+xqgl_bj+"'",
            XQJBXX_XQDZ:"'"+xqgl_xxdz+"'",
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
    loadXx();
    var row = $('#xqxx_table').bootstrapTable('getData')[index];

    removeValidator();

    $("#xqgl_id").val(row.xqjbxx_id);
    $("#xqgl_ssxx").val(row.xxjbxx_id);
    $("#xqgl_xqzzm").val(row.xqjbxx_xqh);
    $("#xqgl_xqmc").val(row.xqjbxx_xqmc);
    $("#xqgl_xzqhm").val(row.xqjbxx_xzqdm);
    $("#xqgl_xqyzbm").val(row.xqjbxx_xqyzbm);
    $("#xqgl_xqlxdh").val(row.xqjbxx_lxdh);
    $("#xqgl_xqczdh").val(row.xqjbxx_czdh);
    $("#xqgl_xqfzr").val(row.xqjbxx_fzrh);
    $("#xqgl_sfzb").val(row.xqjbxx_sfzb);
    $("#xqgl_dtwz").val(row.gd_dlzb);
    $("#xqgl_bj").val(row.radius);
    $("#xqgl_xxdz").val(row.xqjbxx_xqdz);

    $('#addSubBut').removeAttr("disabled");
    $('#addModal').modal({backdrop:'static', keyboard:false});
}

function del(id) {
    showConfirm("确定要删除该校区？", function() {
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
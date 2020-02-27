//初始列表
function findByOnpag(){
    $('#jzly_table').bootstrapTable({
        method : 'post',
        url : seljzlylistUrl,//请求路径
        striped : true, //是否显示行间隔色
        pageNumber : 1, //初始化加载第一页
        pagination : true,//是否分页
        sidePagination : 'server',//server:服务器端分页|client：前端分页
        height: $(window).height() - 75, //自定义表格的高度
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
                xqjbxx_id : $("#xqjbxx_id").val(),
                jzwjbxx_jzwh : $("#jzwjbxx_jzwh").val(),
                jzwjbxx_jzwmc : $("#jzwjbxx_jzwmc").val()
            };
            return temp;
        },
        columns : [
              {width : '100', title : '楼宇编号', field : 'jzwjbxx_jzwh', sortable : false}
            , {width : '100', title : '楼宇名称', field : 'jzwjbxx_jzwmc', sortable : false}
            , {width : '100', title : '校区', field : 'xqjbxx_xqmc', sortable : false,
                formatter : function(di_key, row, index) {
                    if(row.xqjbxx_sfzb == '1'){
                        return di_key+'<font>(已停办)</font>';
                    }else{
                        return di_key;
                    }
                }
            }
            , {width : '100', title : '楼宇层数', field : 'jzwjbxx_jzwcs', sortable : false}
            , {width : '100', title : '总建筑面积', field : 'jzwjbxx_zjzmj', sortable : false}
            ,{width:100,title:'操作',field:'jzwjbxx_id',align:'center',sortable:false,
                formatter:function(value,row,index){
                    var str = '<div class="operator_div">';
                    str += '<a class="btn btn-success btn-xs" href="javascript:void(0);" onclick=editJzly("'+index+'")>编辑</a>';
                    str += '&nbsp;<a class="btn btn-danger btn-xs" href="javascript:void(0);" onclick=delJzly("'+ value +'")>删除</a>';
                    str += '</div>';
                    return str;
                }
            }
        ]
    });
}


function editJzly(index){
    $("#myModalLabel").html("编辑楼宇");
    var row = $('#jzly_table').bootstrapTable('getData')[index];
    var jzwjbxx_id = row.jzwjbxx_id;
    var xqjbxx_id = row.xqjbxx_id;
    var jzwjbxx_jzwmc = row.jzwjbxx_jzwmc;
    var jzwjbxx_jzwh = row.jzwjbxx_jzwh;
    var jzwjbxx_jzwcs = row.jzwjbxx_jzwcs;
    var jzwjbxx_zjzmj = row.jzwjbxx_zjzmj;
    removeValidator();
    if(jzwjbxx_zjzmj == null || jzwjbxx_zjzmj == 'null'){
        jzwjbxx_zjzmj = '';
    }
    //查询校区
    var params = {belong_org_id : beone_org_id, xqjbxx_sfzb:'2'};
    $.ajaxSetup({async: false});
    selXqlist(params, 'xqjbxx_id_edit');
    $("#jzwjbxx_id").val(jzwjbxx_id);
    $("#xqjbxx_id_edit").val(xqjbxx_id);
    $("#jzwjbxx_jzwmc2").val(jzwjbxx_jzwmc);
    $("#jzwjbxx_jzwh2").val(jzwjbxx_jzwh);
    $("#jzwjbxx_jzwcs2").val(jzwjbxx_jzwcs);
    $("#jzwjbxx_zjzmj2").val(jzwjbxx_zjzmj);
    $('#addModal').modal({backdrop:'static', keyboard:false});
}

function delJzly(jzwjbxx_id){
    showConfirm("确定要删除该楼宇信息？", function() {
        $.ajax({
            url:delUrl,    //请求的url地址
            dataType:"json",   //返回格式为json
            contentType: 'application/json',
            async:true,//请求是否异步，默认为异步，这也是ajax重要特性
            data: JSON.stringify({jzwjbxx_id:jzwjbxx_id,belong_org_id : beone_org_id}),    //参数值
            type:"POST",   //请求方式
            success:function(req){
                if(req.resultCode == 'success'){
                    showMsg("操作成功！", function () {
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
    $("#jzly_table").bootstrapTable('destroy');
    findByOnpag();
}

function chongzhi(){
    $("#xqjbxx_id").val('');
    $("#jzwjbxx_jzwh").val('');
    $("#jzwjbxx_jzwmc").val('');
    $("#jzly_table").bootstrapTable('destroy');
    findByOnpag();
}

function removeValidator(){
    //移除上次的校验配置
    $("#addLyForm").data('bootstrapValidator').destroy();
    $('#addLyForm').data('bootstrapValidator',null);
    //重新添加校验
    ValidatorUserInfo();
}

function addJzly(){
    $("#myModalLabel").html("添加楼宇");
    removeValidator();
    $("#jzwjbxx_id").val('');
    $("#xqjbxx_id_edit").val('');
    $("#jzwjbxx_jzwmc2").val('');
    $("#jzwjbxx_jzwh2").val('');
    $("#jzwjbxx_jzwcs2").val('');
    $("#jzwjbxx_zjzmj2").val('');
    //查询校区
    var params = {belong_org_id : beone_org_id, xqjbxx_sfzb:'2'};
    selXqlist(params, 'xqjbxx_id_edit');
    $('#addModal').modal({backdrop:'static', keyboard:false});
}

function addJzlyTj(){
    $('#addLyForm').data('bootstrapValidator').validate();//启用验证
    // var flag = $('#addLyForm').data('bootstrapValidator').isValid();

}

function ValidatorUserInfo(){
    $('#addLyForm').bootstrapValidator({
        // feedbackIcons: {
        //     valid: 'glyphicon glyphicon-ok',
        //     invalid: 'glyphicon glyphicon-remove',
        //     validating: 'glyphicon glyphicon-refresh'
        // },
        fields: {
            xqjbxx_id: {
                validators: {
                    notEmpty: {
                        message: '校区不能为空！'
                    }
                }
            },
            jzwjbxx_jzwmc: {
                message: '楼宇名称不能为空！',
                validators: {
                    notEmpty: {
                        message: '楼宇名称不能为空！'
                    },
                    stringLength: {
                        min: 0,
                        max: 10,
                        message: '楼宇名称长度在0 - 10之间！'
                    },
                    remote: { // ajax校验，获得一个json数据（{'valid': true or false}）
                        url: checkUrl,       //验证地址
                        message: '楼宇名称已存在',   //提示信息
                        delay :  1000,
                        type: 'POST',          //请求方式
                        data: function(validator){  //自定义提交数据，默认为当前input name值
                            return {
                                jzwjbxx_jzwmc: $("#jzwjbxx_jzwmc2").val(),
                                jzwjbxx_id: $("#jzwjbxx_id").val(),
                                belong_org_id : beone_org_id
                            };
                        }
                    }
                }
            },
            jzwjbxx_jzwh: {
                message: '楼宇编号不能为空！',
                validators: {
                    notEmpty: {
                        message: '楼宇编号不能为空！'
                    },
                    stringLength: {
                        min: 0,
                        max: 10,
                        message: '楼宇编号长度在0 - 10之间！'
                    },
                    regexp: {
                        regexp: /^\w+$/,
                        message: '只能填写字母或数字或下划线！'
                    }
                }
            },
            jzwjbxx_jzwcs: {
                message: '楼宇层数不能为空！',
                validators: {
                    notEmpty: {
                        message: '楼宇层数不能为空！'
                    },
                    stringLength: {
                        min: 0,
                        max: 3,
                        message: '楼宇层数在1 - 999之间！'
                    },
                    regexp: {
                        // regexp: /^[1-9]\d*?$/,
                        // regexp: /(^\d+$)|(^\+?\d+$)/,
                        regexp: '^[1-9]\d*$',
                        message: '只能填写正整数！'
                    }
                }
            },
            jzwjbxx_zjzmj: {
                message: '楼宇编号不能为空！',
                validators: {
                    stringLength: {
                        min: 0,
                        max: 6,
                        message: '楼宇面积长度在0 - 6之间！'
                    },
                    regexp: {
                        regexp: /(^\+?|^\d?)\d*\.?\d+$/,
                        message: '只能填写正数！'
                    }
                }
            }

        }
    }).on('success.form.bv', function(e) {
        $("body").mLoading("show");//显示loading组件
        //提交数据
        var jzwjbxx_id = $("#jzwjbxx_id").val();
        var xqjbxx_id_edit = $("#xqjbxx_id_edit").val();
        var jzwjbxx_jzwmc2 = $("#jzwjbxx_jzwmc2").val();
        var jzwjbxx_jzwh2 = $("#jzwjbxx_jzwh2").val();
        var jzwjbxx_jzwcs2 = $("#jzwjbxx_jzwcs2").val();
        var jzwjbxx_zjzmj2 = $("#jzwjbxx_zjzmj2").val();
        var params = {
            jzwjbxx_id : jzwjbxx_id,
            xqjbxx_id : xqjbxx_id_edit,
            jzwjbxx_jzwmc : jzwjbxx_jzwmc2,
            jzwjbxx_jzwh : jzwjbxx_jzwh2,
            jzwjbxx_jzwcs : jzwjbxx_jzwcs2,
            jzwjbxx_zjzmj : jzwjbxx_zjzmj2,
            belong_org_id : beone_org_id,
            editor_id : user_id
        }
        var url = addUrl;
        if(jzwjbxx_id != null && jzwjbxx_id != ''){
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

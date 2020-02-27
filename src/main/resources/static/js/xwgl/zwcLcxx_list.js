//初始列表
function findByOnpag(){
    $('#lcxx_table').bootstrapTable({
        method : 'post',
        url : sellclistUrl,//请求路径
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
                jzwjbxx_id : $("#jzwjbxx_id").val(),
                lcbh : $("#lcbh").val()
            };
            return temp;
        },
        columns : [
              {width : '100', title : '楼宇名称', field : 'jzwjbxx_jzwmc', sortable : false}
            , {width : '100', title : '校区', field : 'xqjbxx_xqmc', sortable : false,
                formatter : function(di_key, row, index) {
                    if(row.xqjbxx_sfzb == '1'){
                        return di_key+'<font>(已停办)</font>';
                    }else{
                        return di_key;
                    }
                }
            }
            // , {width : '100', title : '楼层序号', field : 'lcxh', sortable : false}
            , {width : '100', title : '楼层编号/名称', field : 'lcbh', sortable : false}
            , {width : '80', title : '房间数', field : 'fjs', sortable : false}
            , {title : '楼层说明', field : 'lcsm', sortable : false,
                formatter: function (value, row, index) {
                    if (getLength(value) > 50) {
                        value = cutstr(value, 50);
                    }
                    return value;
                }
            }
            , {width : '100', title : '编辑人', field : 'editor_username', sortable : false}
            , {width : '100', title : '编辑时间', field : 'edited_time', sortable : false,
				formatter : function(value, row) {return timeFormat(value,"yyyy-MM-dd");}
			}
            ,{width:100,title:'操作',field:'lxxx_id',align:'center',sortable:false,
                formatter:function(value,row,index){
                    var str = '<div class="operator_div">';
                    str += '<a class="btn btn-success btn-xs" href="javascript:void(0);" onclick=editLcxx("'+ index +'")>编辑</a>';
                    str += '&nbsp<a class="btn btn-danger btn-xs" href="javascript:void(0);" onclick=delLcxx("'+ value +'")>删除</a>';
                    str += '</div>';
                    return str;
                }
            }
        ]
    });
}

function editLcxx(index){
    $("#myModalLabel").html("编辑楼层");
    var row = $('#lcxx_table').bootstrapTable('getData')[index];
    var lxxx_id = row.lxxx_id;
    var xqjbxx_id = row.xqjbxx_id;
    var jzwjbxx_id = row.jzwjbxx_id;
    var lcbh = row.lcbh;
    var fjs = row.fjs;
    var lcsm = row.lcsm;
    removeValidator();
    if(lcsm == null || lcsm == "null"){
        lcsm = '';
    }
    //查询校区
    var params = {belong_org_id : beone_org_id, xqjbxx_sfzb:'2'};
    $.ajaxSetup({async: false});
    selXqlist(params, 'xqjbxx_id_edit');
    selLyxxByXqId(xqjbxx_id, 'jzwjbxx_id_edit');
    $.ajaxSetup({async: true});

    $("#lxxx_id").val(lxxx_id);
    $("#xqjbxx_id_edit").val(xqjbxx_id);
    $("#jzwjbxx_id_edit").val(jzwjbxx_id);
    $("#lcbh_edit").val(lcbh);
    $("#fjs_edit").val(fjs);
    $("#lcsm_edit").val(lcsm);
    $('#addLcModal').modal({backdrop:'static', keyboard:false});

}

function delLcxx(lxxx_id){
    showConfirm("确定要删除该楼层信息？", function() {
        $.ajax({
            url:delUrl,    //请求的url地址
            dataType:"json",   //返回格式为json
            contentType: 'application/json',
            async:true,//请求是否异步，默认为异步，这也是ajax重要特性
            data: JSON.stringify({lxxx_id:lxxx_id,belong_org_id : beone_org_id}),    //参数值
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
    $("#lcxx_table").bootstrapTable('destroy');
    findByOnpag();
}

function chongzhi(){
    $("#xqjbxx_id").val('');
    $("#jzwjbxx_id").val('');
    $("#lcbh").val('');
    $("#lcxx_table").bootstrapTable('destroy');
    findByOnpag();
}

function addLcxx(){
    $("#myModalLabel").html("添加楼层");
    removeValidator();
    $("#jzwjbxx_id_edit").empty();
    $("#lxxx_id").val('');
    $("#lcbh_edit").val('');
    $("#fjs_edit").val('');
    $("#lcsm_edit").val('');
    //查询校区
    var params = {belong_org_id : beone_org_id, xqjbxx_sfzb:'2'};
    selXqlist(params, 'xqjbxx_id_edit');
    $('#addLcModal').modal({backdrop:'static', keyboard:false});
}

function addLcxxTj(){
    $('#addLcxxForm').data('bootstrapValidator').validate();//启用验证
    // var flag = $('#addLcxxForm').data('bootstrapValidator').isValid();

}

function removeValidator(){
    //移除上次的校验配置
    $("#addLcxxForm").data('bootstrapValidator').destroy();
    $('#addLcxxForm').data('bootstrapValidator',null);
    //重新添加校验
    ValidatorLcxxInfo();
}

//验证楼层信息
function ValidatorLcxxInfo(){
    $('#addLcxxForm').bootstrapValidator({
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
            lcbh: {
                validators: {
                    notEmpty: {
                        message: '楼层编号/名称不能为空！'
                    },
                    stringLength: {
                        min: 0,
                        max: 3,
                        message: '楼层编号/名称长度在1 - 3之间！'
                    },
                    regexp: {
                        regexp: /^\w+$/,
                        message: '只能填写字母或数字或下划线！'
                    }
                    // ,
                    // remote: { // ajax校验，获得一个json数据（{'valid': true or false}）
                    //     url: checkUrl,       //验证地址
                    //     message: '楼层编号/名称已存在',   //提示信息
                    //     // delay :  1000,
                    //     type: 'POST',          //请求方式
                    //     data: function(validator){  //自定义提交数据，默认为当前input name值
                    //         return {
                    //             jzwjbxx_id: $("#jzwjbxx_id_edit").val(),
                    //             lxxx_id: $("#lxxx_id").val(),
                    //             lcbh: $("#lcbh_edit").val(),
                    //             belong_org_id : beone_org_id
                    //         };
                    //     }
                    // }
                }
            },
            fjs: {
                message: '房间数不能为空！',
                validators: {
                    notEmpty: {
                        message: '房间数不能为空！'
                    },
                    stringLength: {
                        min: 0,
                        max: 3,
                        message: '房间数长度在1 - 3之间！'
                    },
                    regexp: {
                        regexp: /(^\d+$)|(^\+?\d+$)/,
                        message: '只能填写正整数！'
                    }
                }
            },
            lcsm: {
                validators: {
                    stringLength: {
                        min: 0,
                        max: 64,
                        message: '楼层说明长度在0 - 64之间！'
                    }
                }
            }
        }
    }).on('success.form.bv', function(e) {
        $("body").mLoading("show");//显示loading组件
        //提交数据
        var lxxx_id = $("#lxxx_id").val();
        var jzwjbxx_id_edit = $("#jzwjbxx_id_edit").val();
        var lcbh_edit = $("#lcbh_edit").val();
        var fjs_edit = $("#fjs_edit").val();
        var lcsm_edit = $("#lcsm_edit").val();

        var params = {
            lxxx_id : lxxx_id,
            jzwjbxx_id : jzwjbxx_id_edit,
            lcbh : lcbh_edit,
            fjs : fjs_edit,
            lcsm : lcsm_edit,
            belong_org_id : beone_org_id,
            editor_id : user_id
        };

        var url = addUrl;
        if(lxxx_id != null && lxxx_id != ''){
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
                    $("#addLcModal").modal('hide');  //手动关闭
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
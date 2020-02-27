//初始列表
function findByOnpag(){
    $('#xxjbxx_table').bootstrapTable({
        method : 'post',
        url : selXxjbxxlistUrl,//请求路径
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
                xxjbxx_mc : $("#xxjbxx_mc").val()
            };
            return temp;
        },
        columns : [
            {width : '100', title : '学校代码', field : 'xxjbxx_dm', sortable : false}
            , {width : '200', title : '学校名称', field : 'xxjbxx_mc', sortable : false}
            , {width : '100', title : '是否在办', field : 'xxjbxx_sfzb', sortable : false,
				formatter : function(value, row) {
            		if(value == '1'){
            			return '停办';
					}else {
            			return '在办';
					}
            	}
            }
            // , {width : '100', title : '行政区划码', field : 'xxjbxx_xzqdm', sortable : false, formatter : function(di_key, row) {return JXCore.getDictItemNameByItemKey('XZQHDM', di_key);}}
            , {width : '100', title : '建校年月', field : 'xxjbxx_jxny', sortable : false}
            , {width : '200', title : '办学类型', field : 'xxjbxx_bxlxm', sortable : false,
                formatter : function(value, row, index) {
                    var fs = value.split(',');
                    var text = '';
                    for(var i=0;i<fs.length;i++){
                    	if(fs[i] == '1'){
                            text += '学前教育，';
						}else if(fs[i] == '2'){
                            text += '小学，';
                        }else if(fs[i] == '3'){
                            text += '普通初中，';
                        }else if(fs[i] == '4'){
                            text += '普通高中，';
                        }else if(fs[i] == '5'){
                            text += '职业初中，';
                        }else if(fs[i] == '6'){
                            text += '职业高中，';
                        }else if(fs[i] == '6'){
                            text += '其它，';
                        }
                    }
                    if(text != ''){
                        text = text.substring(0, text.length-1);
                    }
                    return text;
                }
            }
            , {width : '100', title : '校长姓名', field : 'xxjbxx_xzmc', sortable : false}
            , {width : '100', title : '党委负责人', field : 'xxjbxx_dwfzrh', sortable : false}
            // , {width : '100', title : '所属主管单位', field : 'xxjbxx_sszgdwm', sortable : false, formatter : function(di_key, row) {return JXCore.getDictItemNameByItemKey('XZQHDM', di_key);}}
        ]});
}

function chaxun(){
    $("#xxjbxx_table").bootstrapTable('destroy');
    findByOnpag();
}

function chongzhi(){
    $("#xxjbxx_mc").val('');
    $("#xxjbxx_table").bootstrapTable('destroy');
    findByOnpag();
}
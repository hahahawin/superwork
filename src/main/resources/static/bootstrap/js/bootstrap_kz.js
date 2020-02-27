/**
 * 关闭（隐藏）警告窗口
 */
close_bootstrap_alert = function (obj) {
    obj.removeClass('in').addClass('hide');
}

/**
 * 打开（显示）警告窗口
 */
open_bootstrap_alert = function (obj,tobj,tobj_text) {
    obj.removeClass('hide').addClass('in');
    if(tobj!=undefined && tobj!=null && tobj!=''){
        tobj.text(tobj_text);
    }

}

/**
 * 页面跳转
 */
bootstrap_iframes_src = function(iframe,url){

    $(iframe).attr('src', url);
}

function add(id, name, uri){
    var item = {'id':id,'name':name,'url':uri,'closable':true};
    closableTab.addTab(item);
}

/**
 * 退出
 */
login_out = function(){
    window.location.href="login_out";
}

/**
 * 将"2018-05-19T08:04:52.000+0000"这种格式的时间转化为正常格式
 * @param time
 */
timeFormat = function(time, fmt) {
    if(fmt == null || fmt==""){
        fmt = "yyyy-MM-dd hh:mm:ss";
    }
    var d = new Date(time).format(fmt);
    return d;
}

Date.prototype.format = function (format) {
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
            ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }
    return format;
 }

/**
 * 显示提示消息（自动关闭）
 * @param msg
 * @param sec 显示时间（毫秒）
 * @param callback 回调函数
 */
function showTip(msg, sec, callback){
    if(!sec) {
        sec = 1000;
    }
    Modal.tip({
        title:'提示',
        msg: msg
    }, sec);
    setTimeout(callback, sec);
}

/**
 * 显示消息
 * @param msg
 */
function showMsg(msg, callback){
    Modal.alert({
        title:'提示',
        msg: msg,
        btnok: '确定'
    }).on(function (e) {
        if(callback){
            callback();
        }
    });
}

/**
 * 模态对话框
 * @param msg
 * @returns
 */
function showConfirm(msg,callback){
    //var res = false;
    Modal.confirm(
        {
            title:'提示',
            msg: msg,
        }).on( function (e) {
        callback();
        //res=true;
    });
    //return res;
}

/***
 * 模态框封装
 */
$(function () {
    window.Modal = function () {
        var reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm');
        var alr = $("#com-alert");
        var ahtml = alr.html();

        var _tip = function (options, sec) {
            alr.html(ahtml);    // 复原
            alr.find('.ok').hide();
            alr.find('.cancel').hide();
            alr.find('.modal-content').width(500);
            _dialog(options, sec);

            return {
                on: function (callback) {
                }
            };
        };

        var _alert = function (options) {
            alr.html(ahtml);  // 复原
            alr.find('.ok').removeClass('btn-success').addClass('btn-primary');
            alr.find('.cancel').hide();
            _dialog(options);

            return {
                on: function (callback) {
                    if (callback && callback instanceof Function) {
                        alr.find('.ok').click(function () { callback(true) });
                    }
                }
            };
        };

        var _confirm = function (options) {
            alr.html(ahtml); // 复原
            alr.find('.ok').removeClass('btn-primary').addClass('btn-success');
            alr.find('.cancel').show();
            _dialog(options);

            return {
                on: function (callback) {
                    if (callback && callback instanceof Function) {
                        alr.find('.ok').click(function () { callback(true) });
                        alr.find('.cancel').click(function () { return; });
                    }
                }
            };
        };

        var _dialog = function (options) {
            var ops = {
                msg: "提示内容",
                title: "操作提示",
                btnok: "确定",
                btncl: "取消"
            };

            $.extend(ops, options);

            var html = alr.html().replace(reg, function (node, key) {
                return {
                    Title: ops.title,
                    Message: ops.msg,
                    BtnOk: ops.btnok,
                    BtnCancel: ops.btncl
                }[key];
            });

            alr.html(html);
            alr.modal({
                width: 250,
                backdrop: 'static'
            });
        }

        return {
            tip: _tip,
            alert: _alert,
            confirm: _confirm
        }

    }();
});

//转换数据
jQuery.prototype.serializeObject = function () {
    var obj = new Object();
    $.each(this.serializeArray(), function (index, param) {
        if (!(param.name in obj)) {
            obj[param.name] = param.value;
        }
    });
    return obj;
};

//获得纯文本内容of html内容
function getPureTextOfHtml(html){
    if(/<\/?[^>]*>/.test(html)){//有标签
        var a=$("<div class='tmpdivforgetpuretext'>"+html+"</div>").appendTo($("body"));
        var v = getText($(".tmpdivforgetpuretext"));
        a.remove();
        return v;
    }else{
        return html;
    }
}

//获取字符串的长度
function getLength(str) {
    if(str == undefined){
        str = '';
    }
    //<summary>获得字符串实际长度，中文2，英文1</summary>
    var realLength = 0, len = str.length, charCode = -1;
    for (var i = 0; i < len; i++) {
        charCode = str.charCodeAt(i);
        if (charCode >= 0 && charCode <= 128) realLength += 1;
        else realLength += 2;
    }
    return realLength;
};

//截取字符串长度
function cutstr(str, len) {
    // str=getPureTextOfHtml(str);
    var str_length = 0;
    var str_len = 0;
    str_cut = new String();
    str_len = str.length;
    for (var i = 0; i < str_len; i++) {
        a = str.charAt(i);
        str_length++;
        if (escape(a).length > 4) {
            //中文字符的长度经编码之后大于4
            str_length++;
        }
        str_cut = str_cut.concat(a);
        if (str_length >= len) {
            str_cut = str_cut.concat("...");
            return str_cut;
        }
    }
    //如果给定字符串小于指定长度，则返回源字符串；
    if (str_length < len) {
        return str;
    }
}
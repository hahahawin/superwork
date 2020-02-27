// 接口地址
//var serviceUrl = "http://localhost:8082/";
var serviceUrl = "/";
//地图部分
var map;
// 自定义坐标点图标
var myIcon1 = new BMap.Icon("../img/mapIcon1.png", new BMap.Size(56, 30));
var myIcon2 = new BMap.Icon("../img/mapIcon2.png", new BMap.Size(56, 30));

// 初始化地图
function initMap(longitude, latitude, mapGrade, enableScrollWheelZoom) {

    var point = new BMap.Point(longitude, latitude);
    map = new BMap.Map("allmap", {
        minZoom: 6,
        maxZoom: 100,
        enableMapClick: false
    }); // 创建Map实例
    map.centerAndZoom(point, mapGrade); // 初始化地图,设置中心点坐标和地图级别

    var mapType = [BMAP_NORMAL_MAP, BMAP_HYBRID_MAP];
    /**添加地图类型控件*/
    // map.addControl(new BMap.MapTypeControl({
    // 	mapTypes: mapType
    // }));
    map.enableScrollWheelZoom(enableScrollWheelZoom); //开启鼠标滚轮缩放
    /**添加地图类型控件结束*/

    /** 添加圆形遮罩 */
    var circle = new BMap.Circle(point, 5000000, {
        strokeColor: "blue",
        fillColor: "#0c1e46",
        fillOpacity: 0.5,
        strokeWeight: 2,
        strokeOpacity: 0.5
    });
    //map.addOverlay(circle); //增加圆
    circle.disableMassClear() //禁止清除
}

// 设置地图样式为全蓝色
$.getJSON('../json/map_config.json', function (data) {
    // console.log(data);
    map.setMapStyleV2({
        styleJson: data
    });
});

// 在地图上面添加标注
function addMarker(type) {
    map.clearOverlays();

    let list = deviceData.online;
    let i, arr;
    if (type != 2) {
        for (i in list) {
            let markerStr = list[i].attr3;
            // console.log(markerStr);
            if (markerStr != null && markerStr != "") {
                arr = markerStr.split(",");
                if (checkMarker(arr)) {
                    let point = new BMap.Point(arr[0], arr[1]);
                    addMarkerPoint(point, list[i], 1);
                }
            }
        }
    }
    if (type != 1) {
        list = deviceData.unline;
        for (i in list) {
            let marker = list[i].attr3;
            if (marker != null && marker != "") {
                arr = marker.split(",");
                if (checkMarker(arr)) {
                    let point = new BMap.Point(arr[0], arr[1]);
                    addMarkerPoint(point, list[i], 2);
                }
            }
        }
    }
}
function addMarkerPoint(point, deviceObj, iconType) {
    let marker = new BMap.Marker(point, {icon: iconType == 1 ? myIcon1 : myIcon2});
    map.addOverlay(marker);

    marker.addEventListener("click", function () {
        // 信息框中间的信息
        let _html = '<div style="width: 95%;margin-top: 12px;margin-left: 8px;">' +
            '<div title="设备序列号：' + deviceObj.serialNum + '"><span class="mapContentTitle">设备序列号：</span>' + deviceObj.serialNum + '</div>' +
            '<div title="设备类型码：' + deviceObj.productCode + '" style="margin-top: 5px;"><span class="mapContentTitle">设备类型码：</span>' + deviceObj.productCode + '</div>' +
            '</div>'
        let infoWindow = new BMap.InfoWindow(_html, {
            width: 350,             // 信息窗口宽度
            height: 80,             // 信息窗口高度
            title: "",              // 信息窗口标题
            enableAutoPan: true,    // 自动平移
        });
        map.openInfoWindow(infoWindow, point); //开启信息窗口

        setTimeout(function () {
            // 重置信息框的样式
            $(".BMap_pop").find("div").find("div").each(function () {
                $(this).css("background", "rgba(0,0,0,0)");
                $(this).css("border-top", "rgba(0,0,0,0)");
                $(this).css("border-right", "rgba(0,0,0,0)");
                $(this).css("border-left", "rgba(0,0,0,0)");
                $(this).css("border-bottom", "rgba(0,0,0,0)");
            });
            $(".BMap_pop").find("div").each(function () {
                $(this).css("background", "rgba(0,0,0,0)");
                $(this).css("border-top", "rgba(0,0,0,0)");
                $(this).css("border-right", "rgba(0,0,0,0)");
                $(this).css("border-left", "rgba(0,0,0,0)");
                $(this).css("border-bottom", "rgba(0,0,0,0)");
            });

            // 设备名称和区域信息（底部的信息框)
            let __html = '<div><div class="mapDeviceImg2"></div><div class="mapDeviceText1" title="' + deviceObj.deviceName + '">' + deviceObj.deviceName + '</div></div> ' +
                '<div style="width:50px"></div>' +
                '<div><div class="mapDeviceImg1"></div> <div class="mapDeviceText2" title="' + deviceObj.attr2 + '">' + deviceObj.attr2 + '</div></div>';
            $(".BMap_bottom").html(__html);

            $($(".BMap_pop").find("div")[18]).css("display", "none");    // 隐藏地图的居居
            $($(".BMap_pop").find("img")[1]).css("display", "none")      // 隐藏地图的关闭按钮
            $($(".BMap_pop").find("div")[3]).css("left", "300px");       // 设置更多按钮
            $($(".BMap_pop").find("div")[3]).css("width", "50px");       // 设置更多按钮
            $($(".BMap_pop").find("div")[3]).css("width", "50px");       // 设置更多按钮
            $($(".BMap_pop").find("div")[3]).css("cursor", "pointer");   // 设置更多按钮
            $($(".BMap_pop").find("div")[3]).click(function () {
                gotoIndex('productManage');
            });   // 设置更多按钮
            $(".BMap_pop").css("display", "block");  // 最后再打开并且设置延时开启的目的是为了不会展示以前的样式（会一闪而过）
        }, 100);
    });
}

// 检查坐标是否正确
function checkMarker(arr) {
    if (typeof arr[0] != 'undefined' && arr[0] != '' && typeof arr[1] != 'undefined' && arr[1] != '') {
        return true;
    }
    return false;
}

// 初始化地图
initMap(106.374705, 29.607805, 12, true);

// 温湿度
function initguageCharts(id, data, min, max) {
    var myChart = echarts.init(document.getElementById(id));
    var option = {
        series: [{
            type: 'gauge',
            radius: '100%',
            min: min,
            max: max,
            axisLine: {
                show: true,
                lineStyle: {
                    color: [
                        [0.2, '#4AFEA3'],
                        [0.8, '#00A2FF'],
                        [1, '#CA0821']
                    ],
                    width: 3
                }
            },
            axisLabel: {
                distance: 1,
                textStyle: {
                    color: '#999FAA',
                    fontSize: fontSize(0.1)
                }
            },
            splitLine: { //分割线样式（及10、20等长线样式）
                length: 10,
                lineStyle: { // 属性lineStyle控制线条样式
                    color: 'auto',
                    width: 2
                }
            },
            axisTick: {
                length: 6,
                lineStyle: {
                    color: 'auto'
                }
            },
            itemStyle: {
                color: '#999FAA'
            },
            pointer: {
                width: 2,
                length: '50%'
            },
            detail: {
                show: false
            },
            data: [{
                value: data
            }]
        }]

    };
    myChart.setOption(option);
}

$(function () {
    $("#echarts3").contents().css("width", "1.5rem");
    $("#echarts3").contents().css("height", "1.5rem");
    $("#echarts3").contents().contents().css("width", "1.5rem");
    $("#echarts3").contents().contents().css("height", "1.5rem");
    $("#echarts4").contents().css("width", "1.5rem");
    $("#echarts4").contents().css("height", "1.5rem");
    $("#echarts4").contents().contents().css("width", "1.5rem");
    $("#echarts4").contents().contents().css("height", "1.5rem");

    $("#echarts5").contents().css("width", "0.59rem");
    $("#echarts5").contents().css("height", "0.59rem");
    $("#echarts5").contents().contents().css("width", "0.59rem");
    $("#echarts5").contents().contents().css("height", "0.59rem");
    $("#echarts6").contents().css("width", "0.59rem");
    $("#echarts6").contents().css("height", "0.59rem");
    $("#echarts6").contents().contents().css("width", "0.59rem");
    $("#echarts6").contents().contents().css("height", "0.59rem");
    $("#echarts7").contents().css("width", "0.59rem");
    $("#echarts7").contents().css("height", "0.59rem");
    $("#echarts7").contents().contents().css("width", "0.59rem");
    $("#echarts7").contents().contents().css("height", "0.59rem");
    $("#echarts8").contents().css("width", "0.59rem");
    $("#echarts8").contents().css("height", "0.59rem");
    $("#echarts8").contents().contents().css("width", "0.59rem");
    $("#echarts8").contents().contents().css("height", "0.59rem");
    $("#echarts9").contents().css("width", "0.59rem");
    $("#echarts9").contents().css("height", "0.59rem");
    $("#echarts9").contents().contents().css("width", "0.59rem");
    $("#echarts9").contents().contents().css("height", "0.59rem");

    $("#echarts_right").contents().css("width", "4rem");
    $("#echarts_right").contents().css("height", "2rem");
    $("#echarts_right").contents().contents().css("width", "4rem");
    $("#echarts_right").contents().contents().css("height", "2rem");
});

//设备下拉选项框
$('#cd-dropdown').dropdown({
    gutter: 2,
    onOptionSelect: function (obj) {
        // console.log(obj[0].dataset.value);
        addMarker(obj[0].dataset.value);
    }
});

// 解决bootstrap下拉插件高度的问题
var __dropdownFlag1 = true;
var __dropdownFlag2 = true;
$('.cd-dropdown > ul').hide();
$($('.cd-dropdown')[0]).click(function () {
    if (__dropdownFlag1) {
        $($('.cd-dropdown > ul')[0]).css("overflow", "auto");
        $($('.cd-dropdown > ul')[0]).show();
        __dropdownFlag1 = false;
    } else {
        $($('.cd-dropdown > ul')[0]).css("height", "0");
        $($('.cd-dropdown > ul')[0]).css("overflow", "auto");
        $($('.cd-dropdown > ul')[0]).hide();
        __dropdownFlag1 = true;
    }
});

//	设备列表滚动
$(function () {
    //获得当前<ul>
    var $uList1 = $(".device-list-box1 ul");

    $('.center-one .left-btn').on('click', function () {
        scrollList($uList1, 'left');
    });

    $('.center-one .right-btn').on('click', function () {
        scrollList($uList1, 'right');
    });

    function scrollList(obj, direction) {
        //获得当前<li>的高度
        var scrollWidth = $("ul li:first").width();
        //滚动出一个<li>的高度
        if (direction == 'left') {
            obj.stop().animate({
                marginLeft: scrollWidth
            }, 10, function () {
                //动画结束后，将当前<ul>marginTop置为初始值0状态，再将第一个<li>拼接到末尾。
                obj.css({
                    marginLeft: 0
                }).find("li:first").appendTo(obj);
            });
        } else {
            obj.stop().animate({
                marginLeft: -scrollWidth
            }, 10, function () {
                //动画结束后，将当前<ul>marginTop置为初始值0状态，再将第一个<li>拼接到末尾。
                obj.css({
                    marginLeft: 0
                }).find("li:last").prependTo(obj);
            });
        }
    }
});


var Info = {};
// vue
var vue_left;
var content_one;
var vue_right1;
var vue_right2;
var vue_right3;
var deviceData = {}; // 设备数据

vue_left = new Vue({
    el: "#left",
    data: {
        orgInfo: {
            orgName: "重庆金鑫",
            orgAddr: "-",
            userName: "-",
            userCell: "-"
        },
        city: "重庆",
        weatherList: [
            {
                air: 72,
                air_level: "良",
                air_tips: "",
                wea_img: "yun",
                tem: "16℃",
                humidity: "75",
                wea: "多云转阴"
            }
        ]
    },
    methods: {
        getOrgInfo(){
            $.ajax({
                url: serviceUrl + "serviceInfo/getInfo.json",
                type: "get",
                async: false,
                success: function (res) {
                    // console.log(res);
                    if (res.status == 200) {
                        vue_left.orgInfo.orgName = res.data.orgName;
                        vue_left.orgInfo.orgAddr = res.data.address;
                        vue_left.orgInfo.userCell = res.data.companyPhone;
                    }
                }
            });
        }
    },
});

content_one = new Vue({
    el: ".content-deviceList",
    data: {
        num: 0,
        len: 13,
        list: []
    },
    methods: {
        getDeviceInfo() {
            $.ajax({
                url: serviceUrl + "sbgl/listsb.json",
                type: "post",
                contentType: 'application/json',
                async: false,
                data: JSON.stringify({page: 1, limit: 99999}),
                success: function (res) {
                    // console.log(res);
                    if (res.status == 200) {
                        content_one.list = res.data;
                        content_one.len = res.data.length;
                    }
                }
            });
        },
        left() {
            content_one.num -= 1;
            if (content_one.num >= 0) {
                let left = 0.85 * content_one.num;
                $('.box-content-content-item').css('margin-left', '-' + left + 'rem');
            } else {
                content_one.num = 0;
            }
        },
        right() {
            content_one.num += 1;
            if (content_one.num + 10 <= content_one.len) {
                let left = 0.85 * content_one.num;
                $('.box-content-content-item').css('margin-left', '-' + left + 'rem');
            } else {
                content_one.num = content_one.len - 10;
            }
        },
        control(obj){
            Info = obj;
            layer.open({
                type: 2,
                title: '控制',
                content: '../../productManage/productHand.html',
                shadeClose: false,
                shade: 0.5,
                area: ['60%', '50%'],
                maxmin: true
            });
        }
    },
});

vue_right1 = new Vue({
    el: ".right-one",
    data: {
        num: 0,
        len: 8,
        list: []
    },
    methods: {
        getModeInfo() {
            $.ajax({
                url: serviceUrl + "sbgl/listMs.json",
                type: "post",
                contentType: 'application/json',
                async: false,
                data: JSON.stringify({page: 1, limit: 9999}),
                success: function (res) {
                    // console.log(res);
                    if (res.status == 200) {
                        vue_right1.list = res.data;
                        vue_right1.len = res.data.length;
                    }
                }
            });
        },
        left() {
            vue_right1.num -= 1;
            if (vue_right1.num >= 0) {
                let left = 0.7 * vue_right1.num;
                $('.right-one-content2').css('left', '-' + left + 'rem');
            } else {
                vue_right1.num = 0;
            }
        },
        right() {
            vue_right1.num += 1;
            if (vue_right1.num + 5 <= vue_right1.len) {
                let left = 0.7 * vue_right1.num;
                $('.right-one-content2').css('left', '-' + left + 'rem');
            } else {
                vue_right1.num = vue_right1.len - 5;
            }
        },
        control(obj){
            $.ajax({
                url: serviceUrl + "sbgl/msControl.json?msId=" + obj.sbmoglId,
                type: "get",
                async: false,
                success: function (res) {
                    // console.log(res);
                    if (res.status == 200) {
                        layer.msg("控制成功！", {icon: 1});
                    } else {
                        layer.msg(res.msg, {icon: 2});
                    }
                }
            });
        }
    },
});

vue_right2 = new Vue({
    el: ".right-two",
    data: {},
    methods: {
        getDevice() {
            $.ajax({
                url: serviceUrl + "data/getProductStatus.json",
                type: "get",
                async: false,
                success: function (res) {
                    // console.log(res);
                    if (res.status == 200) {
                        deviceData = res.data;

                        // 显示设备状态的数量
                        var online = deviceData.online;
                        var unline = deviceData.unline;
                        let sum = 0; // 设备总数
                        sum = online.length + unline.length;
                        initPieCharts2('echarts5', sum, 0, '#4CA7FD', '#174178');
                        initPieCharts2('echarts6', online.length, sum - online.length, '#4AFEA3', '#165959');
                        initPieCharts2('echarts7', unline.length, sum - unline.length, '#e6a600', '#825800');

                        // 创建地图标注点
                        addMarker(0);
                    }
                }
            });
        }
    },
});

vue_right3 = new Vue({
    el: ".right-three",
    data: {
        list: []
    },
    methods: {
        getUserInfo(){
            $.ajax({
                url: serviceUrl + "pSysUser/listUser.json",
                type: "post",
                contentType: 'application/json',
                async: false,
                data: JSON.stringify({page: 1, limit: 16}),
                success: function (res) {
                    console.log(res);
                    if (res.status == 200) {
                        vue_right3.list = res.data;
                    }
                }
            });
        },
        detail(obj) {
            // console.log(obj);
            gotoIndex('userList', JSON.stringify(obj));
        }
    },
});

// 获取7天的天气信息
function getWeekWeather() {
    $.ajax({
        url: "https://www.tianqiapi.com/api?version=v1&appid=79587942&appsecret=R4B9ci0z",
        type: "get",
        async: false,
        success: function (res) {
            // console.log(res);
            if (typeof (res.data) != 'undefined' && res.data.length > 0) {
                vue_left.weatherList = res.data.splice(0, 4);
                vue_left.city = res.city;
            }
        }
    });
}

getWeekWeather();
// 温湿度
initguageCharts('echarts3', parseInt(vue_left.weatherList[0].tem), -50, 50);
initguageCharts('echarts4', parseInt(vue_left.weatherList[0].humidity), 0, 100);
$(".left-two-color").css("display", "block");

// 初始化设备数量
initPieCharts2('echarts5', 0, 0, '#4CA7FD', '#174178');
initPieCharts2('echarts6', 0, 0, '#4AFEA3', '#165959');
initPieCharts2('echarts7', 0, 0, '#e6a600', '#825800');

function initPieCharts2(id, count1, count2, color1, color2) {
    var myChart = echarts.init(document.getElementById(id));
    var option = {
        title: {
            text: count1,
            x: 'center',
            y: 'center',
            itemGap: 60,
            textStyle: {
                color: '#ffffff',
                fontFamily: 'orbitronBold',
                fontSize: fontSize(0.2)
            }
        },
        series: [{
            type: 'pie',
            clockWise: false,
            radius: ['80%', '100%'],
            hoverAnimation: false,
            itemStyle: {
                normal: {
                    label: {
                        show: false
                    },
                    labelLine: {
                        show: false
                    }
                },
            },
            data: [
                {
                    value: count1,
                    name: '',
                    itemStyle: {
                        color: color1
                    }
                },
                {
                    value: count2,
                    name: '',
                    itemStyle: {
                        color: color2
                    }
                }
            ]
        }]
    };
    myChart.setOption(option);
}

vue_right1.getModeInfo();   // 获取模式信息
content_one.getDeviceInfo();// 获取设备信息
vue_right2.getDevice();     // 获取设备在线离线信息
vue_right3.getUserInfo();   // 获取用户信息
vue_left.getOrgInfo();

addMarker(0);

function gotoIndex(type, paramet) {
    localStorage.setItem("muenType", type);
    localStorage.setItem("_paramet", paramet);
    window.location.href = '/pSysUser/toIndex';
}
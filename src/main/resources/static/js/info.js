

$(function () {
    $.ajax({
        url: '/hotel/info',
        type: 'post',
        dataType: 'json',
        data: {
            hotel_id: $.cookie('hotel_id'),
        },
        success: function (res) {
            console.log(res)
            console.log(res.data)
            $("#img1").attr("src", res.data.photo1);
            $("#img2").attr("src", res.data.photo2);
            $("#img3").attr("src", res.data.photo3);
            $("#img4").attr("src", res.data.photo4);
            $("#img5").attr("src", res.data.photo5);
            $("#hotel_name").html(res.data.hotelTranslatedName);
            $("#En-name").html(res.data.hotelName);
            $("#hotel-intro").html(res.data.overview);
            $("#star-level").html(res.data.starRating);
            if(res.data.ratingAverage == 0){
                $("#reserve_money").html("￥ 200" + "&nbsp&nbsp&nbsp<span id=\"every\">每晚/人</span>")
                $("#money").html("￥ " +  200  +  ".00");
            }else{
                $("#reserve_money").html("￥ " + res.data.ratingAverage * 40 + "&nbsp&nbsp&nbsp<span id=\"every\">每晚/人</span>")
                $("#money").html("￥ " +  res.data.ratingAverage * 40 +  ".00");
            }

            if(res.data.checkin == null || res.data.checkout ==null){
                $("#check_time").html("In: 14:00:00"  + " , Out: 12:00:00")

            }else{
                $("#check_time").html("In: " + res.data.checkin + " , Out: "+ res.data.checkout)

            }

            $("#back").click(function (){
                if($.cookie('index') == "index"){
                    window.location = "/"
                    $.cookie("index",null)
                }else{
                    var index = layer.load(2, {time: 2 * 1000});
                    setTimeout(function (){
                        layer.close(index);
                    },2000)
                    window.location = "/search_result"
                }

            })

            var a = 1;

            layui.use('slider', function(){
                var slider = layui.slider;

                //渲染
                slider.render({
                    elem: '#slideTest1',  //绑定元素
                    min: 1,
                    max: 3,
                    showstep: true,
                    value: 1,
                    change: function(value){
                        if(during == "no"){
                            alert("需先选择日期,请刷新页面")
                        }else{
                            this.disable = true;
                            if(res.data.ratingAverage == 0){
                                $("#money").html("￥ " +  200  * value * during+  ".00");
                            }else{
                                $("#money").html("￥ " +  res.data.ratingAverage * 40 * value * during +  ".00");
                            }
                        }
                    }
                });
            });

            $("#reserve").click(function () {
                if($.cookie('username') ==null){
                    layui.use('layer', function () {
                        layer.msg('用户未登录！请先登陆账号', {
                            icon: 5,
                            time: 1500,
                            anim: 2,
                        });
                    })
                }else{
                    var a = $("#money").html()
                    var b = a.indexOf(".")
                    var space = a.indexOf(" ")
                    var l = a.substring(0, b);
                    var money = l.substring(space,l.length)
                    $("#confirm_pay").html(money)


                    layer.open({
                        content:  res.data.hotelTranslatedName + '<strong>提醒您:</strong ></br></br>' + '<strong id="confirm_pay" style="font-size: 20px">是否确认支付</strong>' + money  + '元'

                        ,btn: ['确认支付', '取消支付'],
                        area: ['400px', '300px']
                        ,yes: function(index, layero){
                            $.ajax({
                                url: 'hotel/pay_yes',
                                type: 'post',
                                dataType: 'json',
                                data: {
                                    hotelId: res.data.hotelId,
                                    pay: 1,
                                    username: $.cookie('username'),
                                    totalNumber: money,
                                },
                                success: function (res) {
                                    layer.msg('成功支付哦~已加入我的订单', {
                                        icon: 1,
                                        time: 2500,
                                        anim: 2,
                                    });
                                    layer.close(index);
                                },
                                error:function(xhr,state,errorThrown){
                                    alert("支付失败!")
                                }
                            })
                        }
                        ,btn2: function(index, layero){
                            $.ajax({
                                url: 'hotel/pay_no',
                                type: 'post',
                                dataType: 'json',
                                data: {
                                    hotelId: res.data.hotelId,
                                    pay: 0,
                                    username: $.cookie('username'),
                                    totalNumber: money,
                                },
                                success: function (res) {
                                    layer.msg('支付成功取消~ 加入未付款订单', {
                                        icon: 5,
                                        time: 2500,
                                        anim: 4,
                                    });
                                    layer.close(index);
                                },
                                error:function(xhr,state,errorThrown){
                                    alert("操作失败!")
                                }
                            })
                        }
                        ,cancel: function(){
                        }
                    });
                }
            })

            var during = "no";
            laydate.render({
                elem: '#test6'
                //设置开始日期、日期日期的 input 选择器
                //数组格式为 2.6.6 开始新增，之前版本直接配置 true 或任意分割字符即可
                , range: ['#test-startDate-1', '#test-endDate-1'],
                theme: "#FF6A00",
                min: 0,
                max: 30,
                isInitValue: true,
                done: function(value, date, endDate) {

                    if(date.month == endDate.month){
                        during = endDate.date - date.date;
                    }else{
                        during = endDate.date + 31 - date.date;
                    }
                    if(a == 1){
                        if(res.data.ratingAverage == 0){
                            $("#money").html("￥ " +  200  * during+  ".00");
                        }else{
                            $("#money").html("￥ " +  res.data.ratingAverage * 40  * during +  ".00");
                        }
                        a = 222
                    }
                    console.log(during)
                }
            });


            layui.use('rate', function(){
                var rate = layui.rate;
                //渲染
                var ins1 = rate.render({
                    elem: '#numStar',  //绑定元素
                    readonly:true,
                    half:true,
                    value:res.data.starRating
                });
            });


            var points = [
                { "lng": res.data.longitude, "lat": res.data.latitude },
            ];
            //创建标注点并添加到地图中
            function addMarker(points) {
                //循环建立标注点
                for (var i = 0, pointsLen = points.length; i < pointsLen; i++) {
                    var point = new BMap.Point(points[i].lng, points[i].lat); //将标注点转化成地图上的点
                    var marker = new BMap.Marker(point); //将点转化成标注点
                    map.addOverlay(marker);  //将标注点添加到地图

                }
            }
            function showInfo(thisMarker, point) {
                //获取点的信息
                var sContent =
                    '<ul style="margin:0 0 5px 0;padding:0.2em 0">'
                    + '<li style="line-height: 26px;font-size: 15px;">'
                    + '<span style="width: 50px;display: inline-block;">id：</span>' + point.id + '</li>'
                    + '<li style="line-height: 26px;font-size: 15px;">'
                    + '<span style="width: 50px;display: inline-block;">名称：</span>' + point.name + '</li>'
                    + '<li style="line-height: 26px;font-size: 15px;"><span style="width: 50px;display: inline-block;">查看：</span><a href="' + point.url + '">详情</a></li>'
                    + '</ul>';
                var infoWindow = new BMap.InfoWindow(sContent); //创建信息窗口对象
                thisMarker.openInfoWindow(infoWindow); //图片加载完后重绘infoWindow
            }
            //创建地图
            var map = new BMap.Map("allmap");
            map.centerAndZoom(new BMap.Point(104.07, 30.67), 12);  // 设置中心点

            map.setCurrentCity("成都");          //设置为成都
            map.addControl(new BMap.MapTypeControl());
            map.enableDoubleClickZoom(true);
            addMarker(points);
            $("#num-views").text(res.data.numberOfReviews);
            $("#rating-views").html(res.data.ratingAverage);
        },
    })


    layui.use('table', function(){
        var table = layui.table;
        //第一个实例
        table.render({
            elem: '#demo'
            ,height: '400px'
            ,page: true
            ,url: '/comment/showCommentByhotel' //数据接口,
            , where: {hotelId: $.cookie("hotel_id")},
            skin: 'line',
            limit: 4,
            limits: [4, 8, 12],
            request: {
                pageName: 'pageNo' //页码的参数名称，默认：page
                , limitName: 'pageSize' //每页数据量的参数名，默认：limit
            },
            parseData: function (res) { //res 即为原始返回的数据
                pageNo = res.data.current
                pageSize = res.data.size
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "count": res.data.total, //解析数据长度
                    "data": res.data.records //解析数据列表
                };
            },
            initSort: {
                field: 'time' //排序字段，对应 cols 设定的各字段名
                , type: 'desc' //排序方式  asc: 升序、desc: 降序、null: 默认排序
            }
            ,page: true //开启分页
            ,cols: [
                [ //表头
                {field: 'time', title: '时间', width:'20%'}
                ,{field: 'username', title: '用户', width:'20%'}
                ,{field: 'comment', title: '评论内容', width:'60%'}
            ]
            ]
        });
        $("#send").click(function () {
            if($.cookie('username') ==null){
                layui.use('layer', function () {
                    layer.msg('用户未登录！请先登陆账号', {
                        icon: 5,
                        time: 1500,
                        anim: 2,
                    });
                })
            }else{
                if($("#owncomment").val() == ""){
                    layer.msg('评论不能为空!', {
                        icon: 5,
                        time: 1500,
                        anim: 2,
                    });
                }else{
                    $.ajax({
                        url: '/comment/addComment',
                        type: 'post',
                        dataType: 'json',
                        data:{
                            username: $.cookie('username'),
                            hotelId: $.cookie('hotel_id'),
                            comment: $("#owncomment").val(),
                        },
                        success: function (res) {
                            layer.msg("评论成功！", {icon: 1, time: 1500},function (){
                                table.reload('demo', {
                                    url: '/comment/showCommentByhotel',
                                    request: {
                                        pageName: 'pageNo' //页码的参数名称，默认：page
                                        , limitName: 'pageSize' //每页数据量的参数名，默认：limit
                                    },
                                    where: {hotelId: $.cookie("hotel_id")},
                                    page: true,
                                    page: {
                                        curr: 1 //重新从第 1 页开始
                                    }
                                });
                            })
                            $("#owncomment").val("")
                        },
                        error: function () {
                            alert("评论失败！")
                        }
                    })
                }

            }
        })
    });

    $("#enter").click(function (){
        var pwd = prompt("请输入管理员admin的密码","")
        if (pwd == "123456")
        {
            location.href = "/management"
        }else if(pwd == "" ){
            alert("密码不为空")
        }else{
            alert("密码错误")
        }
    })


})




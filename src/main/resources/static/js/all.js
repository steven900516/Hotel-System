layui.use('layer', function () {
    $("#lohr").click(function () {
        var index = layer.load(2, {time: 2 * 1000});
    })


    $(function () {
        if ($.cookie('username') != null) {
            $("#lohr").text("欢迎你，" + $.cookie('username') + "用户")
            $("#lohr").removeAttr("href");
            $('#lohr').unbind('click');
            $("#lohr").css('color', 'black');
            $("#lohr").css({"font-size": 18 + 'px'});
        }
    });


    $("#own_order").click(function (e) {
        if ($.cookie('username') == null) {
            e.preventDefault();
            layer.msg('用户未登录！请先登陆账号', {
                icon: 5,
                time: 1500,
                anim: 2,
            }, function(){
                window.location = "/login"
            });
        }

    })

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

    $("#comm").click(function (e){
        if ($.cookie('username') == null) {
            e.preventDefault();
            layer.msg('用户未登录！请先登陆账号', {
                icon: 5,
                time: 1500,
                anim: 2,
            }, function(){
                window.location = "/login"
            });
        }
    })

})
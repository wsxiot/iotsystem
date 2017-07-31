/**
 * Created by wsx on 2017-03-25.
 */
$(function () {
    var curgid = -1;
    $.ajax({
        type: "post",
        url: "/devices/gidlist",
        dataType:"json",
        success: function (data) {
            for(var i=0; i<data.length; i++){
                $("#gatewaytbody").append("<tr><td name='gidlist'>"+data[i].gid+"</td><td>正常</td></tr>");
                $("#gatewaytbody td[name='gidlist']").eq(i).click(function () {
                    curgid = $(this).text();
                    $("#tab-gateway > div > div.col-md-7 > form > div > table > caption").text("已选择网关"+$(this).text());
                });
            }
        }
    });

    $("#signout").click(function () {
        $.ajax({
            type: "post",
            url: "/users/signout",
            dataType:"text",
            success: function (data) {
                if (data == "true") {
                    location.href="/pages/signin";
                }else{
                    alert("请重新退出");
                }
            }
        });
    });

    $("#lledon").click(function () {
        if(curgid == -1){
            alert("您还未选择网关");
            return;
        }
        $.ajax({
            type: "POST",
            url: "/devices/livingled",
            data: {
                gid: curgid,
                ctrl: 1
            },
            dataType: "text",
            success: function (data) {
                console.log(data);
            }
        });
    });

    $("#lledoff").click(function () {
        if(curgid == -1){
            alert("您还未选择网关");
            return;
        }
        $.ajax({
            type: "POST",
            url: "/devices/livingled",
            data: {
                gid: curgid,
                ctrl: 0
            },
            dataType: "text",
            success: function (data) {
                console.log(data);
            }
        });
    });
    
    $("#bledon").click(function () {
        if(curgid == -1){
            alert("您还未选择网关");
            return;
        }
        $.ajax({
            type: "POST",
            url: "/devices/bedled",
            data: {
                gid: curgid,
                ctrl: 1
            },
            dataType: "text",
            success: function (data) {
                console.log(data);
            }
        });
    });
    
    $("#bledoff").click(function () {
        if(curgid == -1){
            alert("您还未选择网关");
            return;
        }
        $.ajax({
            type: "POST",
            url: "/devices/bedled",
            data: {
                gid: curgid,
                ctrl: 0
            },
            dataType: "text",
            success: function (data) {
                console.log(data);
            }
        });
    });
    setInterval(function(){
        if(curgid != -1) {
            $.ajax({
                type: "POST",
                url: "/devices/temhumlig",
                data: {
                    gid: curgid
                },
                dataType: "json",
                success: function (data) {
                    if(data == "")
                        return;
                    $("#temp").text(data.temp);
                    $("#humi").text(data.humi);
                    $("#light").text(data.light);
                }
            });
        }
    }, 1800);

});
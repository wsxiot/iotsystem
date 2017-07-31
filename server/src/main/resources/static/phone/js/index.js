jQuery(document).ready(function () {
    $.mobile.page.prototype.options.addBackBtn = true;
    $("#signout").click(function () {
        $.ajax({
            type: "post",
            url: "/users/signout",
            dataType: "text",
            success: function (data) {
                if (data == "true") {
                    location.href = "/m/pages/signin";
                } else {
                    alert("请重新退出");
                }
            }
        });
    });
    var curgid = -1;
    $.ajax({
        type: "post",
        url: "/devices/gidlist",
        dataType:"json",
        success: function (data) {
            for(var i=0; i<data.length; i++){
                $("#glistul").append("<li class=\"ui-li ui-li-static ui-btn-up-c\">"+data[i].gid+"</li>");
                $("#glistul").find("li").eq(i).click(function () {
                    curgid = $(this).text();
                    $("body > div.ui-page.ui-body-e.ui-page-active > div.ui-content > ul > li > div > div > a").text("已选择网关"+curgid);
                });
            }
        }
    });
    // function addClickglistListener() {
    //     for(var i=0; i<$("#glistul").find("li").length; i++){
    //         $(this).click(function () {
    //             curgid = $(this).text();
    //             console.log(curgid);
    //         });
    //     }
    // }
    //
    // addClickglistListener();

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
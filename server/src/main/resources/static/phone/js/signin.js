$(function () {
    $("#submit").click(function () {
        var uemailval = $("#uemail").val();
        var upasswdval = $("#upasswd").val();
        if(uemailval.length < 5){
            alert("电子邮箱格式错误");
            return;
        }
        if(upasswdval.length < 6){
            alert("密码位数不得少于6位");
            return;
        }
        $.post("/users/signin",
            {
                uemail: $("#uemail").val(),
                upasswd: sha1($('#upasswd').val())
            },
            function (data) {
                if (data == "true") {
                    location.href = "/m/";
                } else {
                    $("#uemail").val("");
                    $('#upasswd').val("");
                    alert("用户名或密码错误");
                }
            });
    });
});
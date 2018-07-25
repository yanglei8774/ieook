jQuery.extend({

    getCookie: function (c_name) {
        if (document.cookie.length > 0) {
            c_start = document.cookie.indexOf(c_name + "=")
            if (c_start != -1) {
                c_start = c_start + c_name.length + 1
                c_end = document.cookie.indexOf(";", c_start)
                if (c_end == -1) c_end = document.cookie.length
                return unescape(document.cookie.substring(c_start, c_end))
            }
        }
        return null;
    }

});
$.dopost = function (config) {
    var postData = {};
    var o_async = true;
    var timeout = 60000;
    if (config.async != null) {
        o_async = config.async;
    }
    if (config.param) {
        postData = config.param;
    }
    var action = "";
    if (config.url) {
        action = config.url;
    }
    if (config.timeout){
        timeout = config.timeout;
    }

    if (config.type == "body") {
        $.ajax({
            type: "POST",
            contentType: 'application/json',
            url: action,
            timeout: timeout,
            async: o_async,
            cache: false,
            data: postData,
            beforeSend: function (XMLHttpRequest) {
                if(config.beforeSend){
                    config.beforeSend(XMLHttpRequest);
                }
            },
            success: function (msg) {
                if (!msg.success && msg.code == "-991000") {
                    $.supers.alert("当前网络不给力，等一等再试试！");
                    return;
                }
                if (config.success) {
                    config.success.call(this, msg);
                }
            },
            error: function (http) {
                var code = http.status;
                if (config.error){
                    config.error.call(this, http);
                    return;
                }
                if (code == 404) {
                    $.supers.alert("服务地址错误。");//404
                } else if (code == 500) {
                    $.supers.alert("服务异常，请稍后再试。");//500
                } else {
                    if (code == 0) return;
                    $.supers.alert("服务异常[" + code + "]。");//other
                }
            }
        });
    } else {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: action,
            timeout: 60000,
            async: o_async,
            data: postData,
            beforeSend: function (XMLHttpRequest) {
                if(config.beforeSend){
                    config.beforeSend(XMLHttpRequest);
                }
            },
            success: function (msg) {
                if (!msg.success && msg.code == "-991000") {
                    $.supers.alert("当前网络不给力，等一等再试试！");
                    return;
                }
                if (config.success) {
                    config.success.call(this, msg);
                }
            },
            error: function (http) {
                var code = http.status;
                if (code == 404) {
                    $.supers.alert("服务地址错误。");//404
                } else if (code == 500) {
                    $.supers.alert("服务异常，请稍后再试。");//500
                } else {
                    if (code == 0) return;
                    $.supers.alert("服务异常[" + code + "]。");//other
                }
            }
        });

    }

};
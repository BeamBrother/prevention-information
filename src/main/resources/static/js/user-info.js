
//判断变量是否为空
function isEmpty(v) {
    switch (typeof v) {
        case 'undefined':
            return true;
        case 'string':
            if (v.replace(/(^[ \t\n\r]*)|([ \t\n\r]*$)/g, '').length == 0) return true;
            break;
        case 'boolean':
            if (!v) return true;
            break;
        case 'number':
            if (0 === v || isNaN(v)) return true;
            break;
        case 'object':
            if (null === v || v.length === 0) return true;
            for (var i in v) {
                return false;
            }
            return true;
    }
    return false;
}

function uInfo(id) {
    $.ajax({
        url: '/access/info',
        type: "POST",
        data:{
            "id": id
        },
        success: function (res){
            accessInfo = JSON.parse(res);

        }

    })
}
$(function () {
    $("#btn").click(function () {
        $.ajax({
            type:"post",
            url:"showAllUserList",
            data:{},
            datatype:"json",
            success:function (data) {
                //转换为javascript对象
                var user = eval('('+data+')');
                var str = "<table>";
                var strcontent = "";
                for(var i=0;i<user.length;i++){
                    strcontent+="<tr><td>"+user[i].id+"</td><td>"+user[i].name+"</td><td>"+user[i].address+"</td></tr>"
                }
                str+=strcontent+"</table>";
                $("#content").html(str);
            },error:function () {
                alert("error!");
            }
        })
    })
})

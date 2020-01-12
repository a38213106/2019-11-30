function post() {
    var questionId = $("#questionId").val();
    var content = $("#content").val();

    if(!content){
        alert("回复内容不能为空");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parent_id": questionId,
            "content": content,
            "type": 1
        }),
        success: function (response) {
            if (response.statusCode == 200) {
                window.location.reload();
            } else {
                if (response.statusCode == 400) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=ef4e75e24c7c9b1abb63&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}
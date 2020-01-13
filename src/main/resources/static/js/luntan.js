function post() {
    var questionId = $("#questionId").val();
    var content = $("#content").val();

    comment(questionId,1,content);
}

function commentSub(e){
    var commentId=e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();
    console.log(commentId);
    console.log(content);
    comment(commentId,2,content);
}

function comment(targetId,type,content){
    if(!content){
        alert("回复内容不能为空");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parent_id": targetId,
            "content": content,
            "type": type
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

function commentCollapse(e){
    var id=e.getAttribute("data-id");
    var comments=$("#comment-"+id);
    //获取二级评论的展开状态
    var collapse=e.getAttribute("data-collapse");
    if(collapse){
        //折叠二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.add("active");
    }else{
        var subCommentContainer = $("#comment-" + id);
        if(subCommentContainer.children().length!=1){
            //展开二级评论
            comments.addClass("in");
            //标记二级评论展开状态
            e.setAttribute("data-collapse", "in");
        }else {
            $.getJSON("/comment/" + id, function (data) {
                debugger;
                console.log(data);
                $.each(data.data.reverse(), function (index, comment) {
                    var c = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments",
                        html: comment.content
                    });
                    console.log(c);
                    subCommentContainer.prepend(c);
                });
                //展开二级评论
                comments.addClass("in");
                //标记二级评论展开状态
                e.setAttribute("data-collapse", "in");
                e.classList.add("active");
            });
        }
    }
}
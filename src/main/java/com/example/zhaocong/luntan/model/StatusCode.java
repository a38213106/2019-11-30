package com.example.zhaocong.luntan.model;



public enum StatusCode {

    HTTP_OK("成功","200"),
    USER_NULL("请登陆后在进行评论","400"),
    SYS_ERROR("服务冒烟了，要不然你稍后再试试！！！","2000"),
    TARGET_QUESTION_NOT_FOUND("你评论的问题不在了,请稍后再试","999"),
    COMMENT_TYPE_PARAM("评论参数类型有问题","110"),
    COMMENT_NOT_FOUND("评论不存在","119"),
    QUESTION_NOT_FOUND("评论的问题不存在","120"),
    COMMENT_NOT_BLANK("评论内容不能为空","121");



    private String index;
    private String name;

    StatusCode( String name,String index) {
        this.index = index;
        this.name = name;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

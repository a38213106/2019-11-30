package com.example.zhaocong.luntan.model;



public enum StatusCode {

    HTTP_OK("成功","200");

    private String index;
    private String name;

    StatusCode(String index, String name) {
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

package com.example.zhaocong.luntan.model;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionPageList {
    private boolean firstPage;       //第一页
    private boolean endPage;         //末尾页
    private boolean previousPage;   //上一页
    private boolean nextPage;      //下一页
    private List<QuestionDto> questionData;
    private List<Integer> pages=new ArrayList<>();
    private Integer totalPage;  //总的页数
    private Integer currentPage;  //当前页数

    public void PageSet(Integer totalPage, Integer currentPage){
           this.totalPage=totalPage;
           this.currentPage=currentPage;


          pages.add(currentPage);
          for(int i=1;i<=3;i++){
           if(currentPage-i>0){
               pages.add(0,currentPage-i);
           }

           if(currentPage+i<=totalPage){
               pages.add(currentPage+i);
           }
        }

        //是否有上一页
        previousPage=currentPage==1?false:true;

        //是否有下一页
        nextPage=currentPage==totalPage?false:true;

        //是否有首页
        firstPage=pages.contains(1)?false:true;

        //是否有末尾页
        endPage=pages.contains(totalPage)?false:true;
    }
}

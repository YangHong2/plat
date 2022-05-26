package com.dhlk.bi.util;

import com.dhlk.entity.bi.PageBean;

public class PageUtil {
    public static PageBean checkPagenow(PageBean pageBean){
        int pageNow = pageBean.getPageNow();
        if(pageNow < 1){
            pageBean.setPageNow(1);
        }
        if(pageNow > pageBean.getPageCount() && pageBean.getPageCount() != 0){
            pageBean.setPageNow(pageBean.getPageCount());
        }
        return pageBean;
    }
}

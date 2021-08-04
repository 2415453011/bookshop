package com.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Xjm
 * @date 2021/6/26   12:01
 */
@Component
public class Page<T> {
    public static final Integer PAGE_SIZE = 4;
    private Integer pageNo;//当前页码
    private Integer pageTotal;//总页码
    private Integer showTotal = PAGE_SIZE;//当前页展示数量
    private Integer pageTotalCount;//总记录数
    private List<T> item;//当前页数据
    private String url;

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", showTotal=" + showTotal +
                ", pageTotalCount=" + pageTotalCount +
                ", item=" + item +
                ", url='" + url + '\'' +
                '}';
    }

    public static Integer getPageSize() {
        return PAGE_SIZE;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        if (pageNo < 1){
            pageNo = 1;
        }
        if (pageNo > pageTotal){
            pageNo = pageTotal;
        }
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getShowTotal() {
        return showTotal;
    }

    public void setShowTotal(Integer showTotal) {
        this.showTotal = showTotal;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItem() {
        return item;
    }

    public void setItem(List<T> item) {
        this.item = item;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Page() {
    }

    public Page(Integer pageNo, Integer pageTotal, Integer showTotal, Integer pageTotalCount, List<T> item, String url) {
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        this.showTotal = showTotal;
        this.pageTotalCount = pageTotalCount;
        this.item = item;
        this.url = url;
    }
}

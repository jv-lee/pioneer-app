package com.lee.pioneer.model.base;

/**
 * @author jv.lee
 * @date 2020/3/25
 * @description
 */
public class PageData<T> extends BaseData<T> {
    private int page;
    private int page_count;
    private int total_counts;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public int getTotal_counts() {
        return total_counts;
    }

    public void setTotal_counts(int total_counts) {
        this.total_counts = total_counts;
    }
}

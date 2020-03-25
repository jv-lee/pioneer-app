package com.lee.pioneer.model.base;

/**
 * @author jv.lee
 * @date 2020/3/25
 * @description
 */
public class HotData<T> extends BaseData<T> {
    private String category;
    private String hot;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }
}

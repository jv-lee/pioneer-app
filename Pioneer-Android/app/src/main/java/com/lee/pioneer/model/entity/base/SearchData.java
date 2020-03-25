package com.lee.pioneer.model.entity.base;

/**
 * @author jv.lee
 * @date 2020/3/25
 * @description
 */
public class SearchData<T> extends BaseData<T> {
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

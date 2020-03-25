package com.lee.pioneer.model.base;

/**
 * @author jv.lee
 * @date 2020/3/25
 * @description
 */
public class BaseData<T> {
    private T data;
    private int status;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

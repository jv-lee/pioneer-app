package com.lee.library.ioc.annotation;

import com.lee.library.adapter.base.BaseViewAdapter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventBase(listenerSetter = "setAutoLoadMoreListener",listenerType = BaseViewAdapter.AutoLoadMoreListener.class,callBackListener = "autoLoadMore")
public @interface AutoLoadMore {
    String value();
}

package com.wiggins.json.callback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Description 回调抽象基类
 * @Author 一花一世界
 */
public abstract class HttpCallBack<T> {

    private Type mGenericSuperclass;

    public HttpCallBack() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            mGenericSuperclass = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        } else {
            mGenericSuperclass = Object.class;
        }
    }

    public abstract void onResolve(T t);

    public abstract void onFailed(int code, String msg);

    public Type getType() {
        return mGenericSuperclass;
    }
}
package com.wiggins.json.callback;

import com.wiggins.json.utils.ToastUtil;

/**
 * @Description 不解析HTTP返回的Json数据
 * @Author 一花一世界
 */
public abstract class OnCommonCallBack<T> extends HttpCallBack<T> {

    @Override
    public void onResolve(T t) {
        onSuccess(t);
    }

    @Override
    public void onFailed(int code, String msg) {
        if (enableShowToast()) {
            ToastUtil.showText(msg);
        } else {
            onFailure(code, msg);
        }
    }

    public abstract void onSuccess(T data);

    public abstract void onFailure(int code, String msg);

    public boolean enableShowToast() {
        return false;
    }
}
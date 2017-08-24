package com.wiggins.json.callback;

import com.wiggins.json.utils.ToastUtil;

/**
 * @Description 解析HTTP返回的Json数据
 * @Author 一花一世界
 */
public abstract class OnServerCallBack<T, V> extends HttpCallBack<T> {

    @Override
    public void onResolve(T t) {
        if (t instanceof HttpResult) {
            HttpResult<V> callbackData = (HttpResult) t;
            V result = callbackData.getData();
            if (callbackData.getCode() == 200) {
                onSuccess(result);
            } else {
                onFailed(callbackData.getCode(), callbackData.getMsg());
            }
        } else {
            onSuccess((V) t);
        }
    }

    @Override
    public void onFailed(int code, String msg) {
        if (enableShowToast()) {
            ToastUtil.showText(msg);
        } else {
            onFailure(code, msg);
        }
    }

    public abstract void onSuccess(V data);

    public abstract void onFailure(int code, String msg);

    public boolean enableShowToast() {
        return false;
    }
}
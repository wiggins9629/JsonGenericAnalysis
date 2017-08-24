package com.wiggins.json.http;

import com.google.gson.Gson;
import com.wiggins.json.callback.HttpCallBack;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * @Description 网络封装工具类
 * @Author 一花一世界
 */
public class HttpUtils {

    private static Gson mGson;

    public static void getRequest(String url, Map<String, String> params, HttpCallBack callBack) {
        if (callBack == null) {
            return;
        }

        if (mGson == null) {
            mGson = new Gson();
        }

        boolean returnJson = false;
        Type type = callBack.getType();

        if (type instanceof Class) {
            switch (((Class) type).getSimpleName()) {
                case "Object":
                case "String":
                    returnJson = true;
                    break;
                default:
                    break;
            }
        }

        if (returnJson) {
            try {
                callBack.onResolve(url);
            } catch (Exception e) {
                callBack.onFailed(-1, e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
                callBack.onResolve(mGson.fromJson(url, type));
            } catch (Exception e) {
                callBack.onFailed(-1, e.getMessage());
                e.printStackTrace();
            }
        }
    }
}

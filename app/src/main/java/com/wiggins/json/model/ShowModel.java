package com.wiggins.json.model;

import com.wiggins.json.callback.OnCommonCallBack;
import com.wiggins.json.callback.OnServerCallBack;

import java.util.Map;

/**
 * @Description 数据操作接口
 * @Author 一花一世界
 */
public interface ShowModel {

    <T> void ShowStringData(String url, Map<String, String> params, OnCommonCallBack<T> callBack);

    <T, V> void ShowUser(String url, Map<String, String> params, OnServerCallBack<T, V> callBack);

    <T, V> void ShowListUser(String url, Map<String, String> params, OnServerCallBack<T, V> callBack);

    <T, V> void ShowUserInfo(String url, Map<String, String> params, OnServerCallBack<T, V> callBack);
}

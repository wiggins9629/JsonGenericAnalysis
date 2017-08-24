package com.wiggins.json.bean;

import java.util.List;

/**
 * @Description 用户信息
 * @Author 一花一世界
 */
public class UserInfo {

    private String level;
    private List<User> list;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "level='" + level + '\'' +
                ", list=" + list +
                '}';
    }
}

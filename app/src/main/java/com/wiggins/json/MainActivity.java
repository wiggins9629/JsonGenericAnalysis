package com.wiggins.json;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wiggins.json.base.BaseActivity;
import com.wiggins.json.bean.User;
import com.wiggins.json.bean.UserInfo;
import com.wiggins.json.callback.HttpResult;
import com.wiggins.json.callback.OnCommonCallBack;
import com.wiggins.json.callback.OnServerCallBack;
import com.wiggins.json.model.ShowModel;
import com.wiggins.json.model.impl.IShowModel;
import com.wiggins.json.utils.Constant;
import com.wiggins.json.utils.LogUtil;
import com.wiggins.json.utils.UIUtils;
import com.wiggins.json.widget.TitleView;

import java.util.List;

/**
 * @Description 泛型解析JSON数据封装
 * @Author 一花一世界
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private TitleView titleView;
    private TextView mTvDataFormat;//JSON数据格式
    private TextView mTvRawData;//原始数据
    private TextView mTvParsingData;//解析数据
    private Button mBtnJson1;
    private Button mBtnJson2;
    private Button mBtnJson3;

    private ShowModel showModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListener();
        initData();
    }

    private void initView() {
        titleView = (TitleView) findViewById(R.id.titleView);
        titleView.setAppTitle(UIUtils.getString(R.string.title));
        titleView.setLeftImageVisibility(View.GONE);
        mTvDataFormat = (TextView) findViewById(R.id.tv_data_format);
        mTvRawData = (TextView) findViewById(R.id.tv_raw_data);
        mTvParsingData = (TextView) findViewById(R.id.tv_parsing_data);
        mBtnJson1 = (Button) findViewById(R.id.btn_json1);
        mBtnJson2 = (Button) findViewById(R.id.btn_json2);
        mBtnJson3 = (Button) findViewById(R.id.btn_json3);
    }

    private void setListener() {
        mBtnJson1.setOnClickListener(this);
        mBtnJson2.setOnClickListener(this);
        mBtnJson3.setOnClickListener(this);
    }

    private void initData() {
        showModel = new IShowModel();
        mTvDataFormat.setText(String.format(UIUtils.getString(R.string.data), "一") + "\n" + Constant.object1 + "\n" +
                String.format(UIUtils.getString(R.string.data), "二") + "\n" + Constant.object2 + "\n" +
                String.format(UIUtils.getString(R.string.data), "三") + "\n" + Constant.arrayList);
    }

    private void setText(int type, String str) {
        if (type == 0) {
            mTvRawData.setText("");
            mTvRawData.setText(UIUtils.getString(R.string.raw_data) + "\n" + str);
        } else {
            mTvParsingData.setText("");
            mTvParsingData.setText(UIUtils.getString(R.string.parsing_data) + "\n" + str);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_json1:
                setText(0, Constant.object1);
                //解析Http返回的Json数据，返回data字段中封装的内容
                showModel.ShowUser(Constant.object1, null, new OnServerCallBack<HttpResult<User>, User>() {
                    @Override
                    public void onSuccess(User data) {
                        LogUtil.e("ShowUser-->" + data.toString());
                        setText(1, data.toString());
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        LogUtil.e("ShowUser failure-->" + code + " : " + msg);
                        setText(1, "ShowUser failure-->" + code + " : " + msg);
                    }
                });
                //不解析Http返回的Json数据，返回Http返回的内容
                showModel.ShowStringData(Constant.object1, null, new OnCommonCallBack<String>() {
                    @Override
                    public void onSuccess(String data) {
                        LogUtil.e("ShowStringData-->" + data);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        LogUtil.e("ShowStringData failure-->" + code + " : " + msg);
                    }
                });
                break;
            case R.id.btn_json2:
                setText(0, Constant.object2);
                showModel.ShowUserInfo(Constant.object2, null, new OnServerCallBack<HttpResult<UserInfo>, UserInfo>() {
                    @Override
                    public void onSuccess(UserInfo data) {
                        LogUtil.e("ShowUserInfo-->" + data.toString());
                        setText(1, data.toString());
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        LogUtil.e("ShowUserInfo failure-->" + code + " : " + msg);
                        setText(1, "ShowUserInfo failure-->" + code + " : " + msg);
                    }
                });
                showModel.ShowStringData(Constant.object2, null, new OnCommonCallBack<String>() {
                    @Override
                    public void onSuccess(String data) {
                        LogUtil.e("ShowStringData-->" + data);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        LogUtil.e("ShowStringData failure-->" + code + " : " + msg);
                    }
                });
                break;
            case R.id.btn_json3:
                setText(0, Constant.arrayList);
                showModel.ShowListUser(Constant.arrayList, null, new OnServerCallBack<HttpResult<List<User>>, List<User>>() {
                    @Override
                    public void onSuccess(List<User> data) {
                        LogUtil.e("ShowListUser-->" + data.toString());
                        setText(1, data.toString());
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        LogUtil.e("ShowListUser failure-->" + code + " : " + msg);
                        setText(1, "ShowListUser failure-->" + code + " : " + msg);
                    }
                });
                showModel.ShowStringData(Constant.arrayList, null, new OnCommonCallBack<String>() {
                    @Override
                    public void onSuccess(String data) {
                        LogUtil.e("ShowStringData-->" + data);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        LogUtil.e("ShowStringData failure-->" + code + " : " + msg);
                    }
                });
                break;
        }
    }
}

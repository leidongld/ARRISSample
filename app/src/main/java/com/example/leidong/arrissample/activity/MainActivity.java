package com.example.leidong.arrissample.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.leidong.arrissample.R;
import com.example.leidong.arrissample.constants.Constants;

import org.apache.commons.validator.routines.UrlValidator;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Button button;
    private EditText editText;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String preUrl = "";

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences(Constants.URL_SP_PARAMS, Context.MODE_PRIVATE);
        editor = sp.edit();
        preUrl = sp.getString(Constants.URL_SP, "");

        initWidgets();
        initAction();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("确认要退出吗？");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.create().show();
        }

        return false;

    }

    /**
     * 获取组件
     */
    private void initWidgets() {
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);
        if (preUrl != null && preUrl.length() > 0) {
            editText.setText(preUrl);
        }
    }

    /**
     * 初始化动作
     * 按钮点击逻辑
     */
    private void initAction() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editText.getText().toString().trim();
                //如果输入的url包含协议地址
                if (url.length() >= 4 && url.substring(0, 4).equals("http")) {
                    if (isUrlValid(url)) {
                        editor.putString(Constants.URL_SP, url);
                        editor.apply();
                        /*Uri uri = Uri.parse(url);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);*/
                        Intent intent = new Intent(MainActivity.this, WebActivity.class);
                        intent.putExtra(Constants.URL, url);
                        startActivity(intent);
                    } else {
                        editor.putString(Constants.URL_SP_PARAMS, "");
                        editor.apply();
                        Toast.makeText(MainActivity.this, "输入Url不合法", Toast.LENGTH_LONG).show();
                    }
                }
                //如果输入的url不包含协议地址
                else {
                    String url1 = "http://" + url;
                    String url2 = "https://" + url;
                    if (isUrlValid(url1)) {
                        editor.putString(Constants.URL_SP, url);
                        editor.apply();
                        /*Uri uri = Uri.parse(url1);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);*/
                        Intent intent = new Intent(MainActivity.this, WebActivity.class);
                        intent.putExtra(Constants.URL, url1);
                        startActivity(intent);
                    } else if (isUrlValid(url2)) {
                        editor.putString(Constants.URL_SP, url);
                        editor.apply();
                        /*Uri uri = Uri.parse(url2);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);*/
                        Intent intent = new Intent(MainActivity.this, WebActivity.class);
                        intent.putExtra(Constants.URL, url2);
                        startActivity(intent);
                    } else {
                        editor.putString(Constants.URL_SP_PARAMS, "");
                        editor.apply();
                        Toast.makeText(MainActivity.this, "输入Url不合法", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    /**
     * 判断url是否合法
     * @param url url
     * @return url是否合法
     */
    private boolean isUrlValid(String url) {
        String[] schemas = {"http", "https"};
        UrlValidator urlValidator = new UrlValidator(schemas);
        return urlValidator.isValid(url);
    }
}


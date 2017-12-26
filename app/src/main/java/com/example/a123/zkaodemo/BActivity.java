package com.example.a123.zkaodemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private String url = "http://huixinguiyu.cn/Assets/js/competitive.js";
    private List<MyBeans.DataBean.ObjectsBean> objects;
    private MyrecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        initView();
        initData();
    }

    private void initData() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response;
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                final String str = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        MyBeans myBeans = gson.fromJson(str, MyBeans.class);
                        objects = myBeans.getData().getObjects();
                        initAdapter();
                        initOncolock();
                    }
                });
            }
        });
    }

    private void initOncolock() {
        adapter.setcolock(new MyrecyclerAdapter.colock() {
            @Override
            public void colock(View view, int position) {
                Intent intent = new Intent(BActivity.this,CActivity.class);
                intent.putExtra("image",objects.get(position).getGmall_product().getUrl());

                startActivity(intent);
            }
        });
    }

    private void initAdapter() {
        adapter = new MyrecyclerAdapter(objects,BActivity.this);
        recycler.setAdapter(adapter);
    }

    private void initView() {
        recycler = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(BActivity.this);
        recycler.setLayoutManager(manager);
    }
}

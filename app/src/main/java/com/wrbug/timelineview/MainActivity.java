package com.wrbug.timelineview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TimeLineView mView1;
    TimeLineView mView2;
    TimeLineView mView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView1 = (TimeLineView) findViewById(R.id.timeLineView1);
        mView2 = (TimeLineView) findViewById(R.id.timeLineView2);
        mView3 = (TimeLineView) findViewById(R.id.timeLineView3);
        List<String> data = new ArrayList<>();
        data.add("等候支付");
        data.add("等候商家接单");
        data.add("等候配送");
        data.add("等候送达");
        mView1.setData(data, 1);
        mView2.setData(data, 2);
        data = new ArrayList<>();
        data.add("第一步");
        data.add("第二步");
        data.add("第三步");
        mView3.setData(data, 3);
    }
}

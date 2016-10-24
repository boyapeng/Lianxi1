package bawei.com.lianxi1;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import bawei.com.lianxi1.Adapter.MAdapter;
import bawei.com.lianxi1.Bean.CommunityBean;
import bawei.com.lianxi1.tools.Url;
import bawei.com.lianxi1.util.NetWorkUtils;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private CommunityBean cb;
    Handler handle=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e("communtyBean+++++",cb.toString()+"");
            recycler.setAdapter(new MAdapter(MainActivity.this,cb));
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //得到控件
        initview();
        //得到数据
        initdata();
    }
    private void initdata(){
        new Thread(){
            @Override
            public void run() {
                String json= NetWorkUtils.getstr(Url.HOME_ONE_URL);
                jsondata(json);
                Log.e("communtyBean+++++",json+"");
                handle.sendEmptyMessage(0);
                super.run();
            }
        }.start();
    }

    private void jsondata(String json) {
        Gson gson=new Gson();
        cb = gson.fromJson(json,CommunityBean.class);
    }
    private void initview() {
        recycler = (RecyclerView) findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }
}

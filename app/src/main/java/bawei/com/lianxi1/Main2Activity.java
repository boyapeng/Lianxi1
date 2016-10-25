package bawei.com.lianxi1;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bawei.com.lianxi1.Fragment.Fragment1;
import bawei.com.lianxi1.Fragment.Fragment2;
import bawei.com.lianxi1.Fragment.Fragment3;

public class Main2Activity extends FragmentActivity implements View.OnClickListener {

    private ViewPager vp;
    private List<Fragment> listData;
    private TextView tv_main_bumen;
    private TextView tv_main_lianbo;
    private View view1;
    private View view2;
    private TextView tv_main_zhibo;
    private View view3;
    private TextView[] tvs;
    private View[] vis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //找到控件
        initview();
        //2.创建数据源
        initData();
        //3.设置适配器
        vp.setAdapter(new MyAdapter(getSupportFragmentManager()));
        //设置滑动监听
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                   checked(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        listData = new ArrayList<>();
        Fragment1 f1=new Fragment1();
        listData.add(f1);

        Fragment2 f2=new Fragment2();
        listData.add(f2);

        Fragment3 f3=new Fragment3();
        listData.add(f3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_main_bumen:
                vp.setCurrentItem(0);
                checked(0);
                break;
            case R.id.tv_main_lianbo:
                vp.setCurrentItem(1);
                checked(1);

                break;
            case R.id.tv_main_zhibo:
                vp.setCurrentItem(2);
                checked(2);
                break;
        }
    }

    private void checked(int index) {
        for (int i = 0; i <vis.length; i++) {
            if (i == index) {
                tvs[i].setTextColor(Color.BLUE);
                vis[i].setVisibility(View.VISIBLE);
            } else {
                tvs[i].setTextColor(Color.BLACK);
                vis[i].setVisibility(View.GONE);
            }
        }
    }

    //定义一个内部类继承自FragmentPagerAdapter
    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
            // TODO Auto-generated constructor stub
        }

        @Override
        public Fragment getItem(int arg0) {
            return listData.get(arg0);
        }

        @Override
        public int getCount() {
            return listData.size();
        }

    }

    private void initview() {
        vp = (ViewPager) findViewById(R.id.vp);
        tv_main_bumen = (TextView) findViewById(R.id.tv_main_bumen);
        tv_main_lianbo = (TextView) findViewById(R.id.tv_main_lianbo);
        tv_main_zhibo = (TextView) findViewById(R.id.tv_main_zhibo);
        tvs = new TextView[]{tv_main_bumen,tv_main_lianbo,tv_main_zhibo};
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);
        vis = new View[]{view1,view2,view3};
        tv_main_bumen.setOnClickListener(this);
        tv_main_lianbo.setOnClickListener(this);
        tv_main_zhibo.setOnClickListener(this);
    }
}

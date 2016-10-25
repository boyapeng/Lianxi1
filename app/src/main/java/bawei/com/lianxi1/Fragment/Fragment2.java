package bawei.com.lianxi1.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import bawei.com.lianxi1.Bean.CommunityBean;
import bawei.com.lianxi1.R;
import bawei.com.lianxi1.tools.Url;
import bawei.com.lianxi1.util.NetWorkUtils;

/**
 * Created by pc on 2016/10/25.
 */
public class Fragment2 extends Fragment{

    private ListView lv;
    private List<CommunityBean.DataEntity.ForumListEntity.GroupEntity> list;
    private boolean isViewCreated;
    private boolean isLoadDataCompleted;
    Handler handle=new Handler(){


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            list= (List<CommunityBean.DataEntity.ForumListEntity.GroupEntity>) msg.obj;
            Log.e("sssssssssssss",list.get(0).name);
            lv.setAdapter(new Myadapter());
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v2=inflater.inflate(R.layout.fragment2,null);
        lv = (ListView) v2.findViewById(R.id.lv);
       initdata();
        return v2;
    }
    private void initdata() {
        new Thread(){
            @Override
            public void run() {
                String json= NetWorkUtils.getstr(Url.HOME_ONE_URL);
                Gson gson=new Gson();
                CommunityBean cb = gson.fromJson(json,CommunityBean.class);
                List<CommunityBean.DataEntity.ForumListEntity.GroupEntity> listdata=cb.data.forum_list.get(0).group;
                Message message=new Message();
                message.what=0;
                message.obj=listdata;
                handle.sendMessage(message);
                super.run();
            }
        }.start();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isViewCreated)//只有在用户可见以及初始化之后才加载数据
        {
            lazyLoad();
        }
    }
    protected void lazyLoad() {
        if (!isLoadDataCompleted) {
            initdata();
            isLoadDataCompleted = true;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        isLoadDataCompleted = false;//这里需要重置状态，不然加载了之后就没办法再重新加载了
    }

    class Myadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView=View.inflate(getActivity(),R.layout.fragment2_item2,null);
            TextView tv1= (TextView) convertView.findViewById(R.id.tv_item2_fragment2);
            tv1.setText(list.get(position).name);
            return convertView;
        }
    }
}

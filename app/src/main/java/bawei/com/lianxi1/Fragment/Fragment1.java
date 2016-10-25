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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;
import bawei.com.lianxi1.Bean.CommunityBean;
import bawei.com.lianxi1.R;
import bawei.com.lianxi1.tools.Url;
import bawei.com.lianxi1.util.ImageUtil;
import bawei.com.lianxi1.util.NetWorkUtils;

/**
 * Created by pc on 2016/10/25.
 */
public class Fragment1 extends Fragment{

    private GridView gv;
    private List<CommunityBean.DataEntity.ForumListEntity.GroupEntity> list;
    Handler handle=new Handler(){


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            list= (List<CommunityBean.DataEntity.ForumListEntity.GroupEntity>) msg.obj;
            Log.e("sssssssssssss",list.get(0).name);
            gv.setAdapter(new Myadapter());
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment1,null);
        gv = (GridView) view.findViewById(R.id.gv);
       //得到数据
        initdata();

        return view;
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
    class Myadapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
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
            convertView=View.inflate(getActivity(),R.layout.fragment1_item1,null);
            ImageView iv= (ImageView) convertView.findViewById(R.id.iv_item_fragment1);
            TextView tv= (TextView) convertView.findViewById(R.id.tv_item_fragment1);
            ImageUtil.newInstance(getActivity()).displayImage(list.get(position).photo, iv);
            tv.setText(list.get(position).name);
            return convertView;
        }
    }
}

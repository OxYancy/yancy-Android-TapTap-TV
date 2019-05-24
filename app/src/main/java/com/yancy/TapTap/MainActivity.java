package com.yancy.TapTap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

// 主页
public class MainActivity extends AppCompatActivity {
    private RecyclerView channelList;
    private ChannelListAdapter listAdapter;
    private List<Channel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        初始化数据
        initData();

        channelList =  findViewById(R.id.channelList);

        listAdapter = new ChannelListAdapter(this.data, new ChannelClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.i("FFPLAY", "Clicked "+view+" on "+position);
                if (position < data.size()) {
                    Channel c = data.get(position);
                    Intent intent = new Intent(MainActivity.this, LiveActivity.class);
                    intent.putExtra("channel", c);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "无效的频道",Toast.LENGTH_SHORT);
                }
            }
        });
        channelList.setAdapter(listAdapter);
        channelList.setLayoutManager(new LinearLayoutManager(this));
    }
// 初始化数据
    private void initData() {
        DataLab lab = new DataLab(this);
        this.data = lab.getChannels("data.json");
    }

}

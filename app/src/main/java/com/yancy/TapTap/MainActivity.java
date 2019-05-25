package com.yancy.TapTap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

// 主页
public class MainActivity extends AppCompatActivity {
    private FloatingActionButton floatButton;
    private BottomAppBar bottomAppBar;
    private RecyclerView channelList;
    private ChannelListAdapter listAdapter;
    private List<Channel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        初始化数据
        initData();
        floatButton = findViewById(R.id.floatButtom);
        bottomAppBar = findViewById(R.id.bottomAppBar);
        channelList = findViewById(R.id.channelList);
        setSupportActionBar(bottomAppBar);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "你点击了", Snackbar.LENGTH_SHORT).setAction("UNDO", null).show();
            }
        });


        listAdapter = new ChannelListAdapter(this.data, new ChannelClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.i("FFPLAY", "Clicked " + view + " on " + position);
                if (position < data.size()) {
                    Channel c = data.get(position);
                    Intent intent = new Intent(MainActivity.this, LiveActivity.class);
                    intent.putExtra("channel", c);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "无效的频道", Toast.LENGTH_SHORT);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_app_bar, menu);
        return true;
    }
}

package com.vicmob.fly.newview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private RefreshLayout refreshLayout;
    private RecyclerView recycler_view;
    private List<Fruit> fruitList = new ArrayList<>();
    private FruitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        refreshLayout.setRefreshHeader(new MaterialHeader(this));//设置头刷新风格
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));//设置尾部刷新风格

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//将recycleview变成横向滑动方式
        recycler_view.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(fruitList);
        recycler_view.setAdapter(adapter);

        refresh();
        initFruits();
//item的点击事件响应
        adapter.setOnItemClickListener(new FruitAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(MainActivity.this,"您点击了"+position+"行",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(int position) {
                Toast.makeText(MainActivity.this,"您点击了"+position+"行",Toast.LENGTH_SHORT).show();
            }
        });
    }
//添加数据源
    private void initFruits() {
        for (int i = 0; i < 10; i++) {
            Fruit apple = new Fruit("Apple"+i, R.drawable.apple);
            fruitList.add(apple);
            Fruit banana = new Fruit("红富士", R.drawable.appleone);
            fruitList.add(banana);
        }
    }
//添加头尾刷新加载
    private void refresh() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                //模拟网络请求到的数据
                fruitList.clear();
                ArrayList<Fruit> newList = new ArrayList<Fruit>();
                for (int i = 0; i <10; i++) {
                    newList.add(new Fruit("Apple"+i, R.drawable.apple));
                    newList.add(new Fruit("苹果"+i, R.drawable.appleone));
                }
                adapter.refresh(newList);
                //结束加载
                refreshlayout.finishRefresh(2000/*,false*/);

            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                adapter.add(fruitList);
                refreshlayout.finishLoadmore(2000);

            }
        });
    }
}

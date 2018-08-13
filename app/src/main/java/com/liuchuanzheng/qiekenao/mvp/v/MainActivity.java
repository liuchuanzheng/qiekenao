package com.liuchuanzheng.qiekenao.mvp.v;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.liuchuanzheng.qiekenao.R;
import com.liuchuanzheng.qiekenao.mvp.v.adapter.MyMainPagerAdapter;
import com.liuchuanzheng.qiekenao.mvp.v.fragment.HomeFragment;
import com.liuchuanzheng.qiekenao.mvp.v.fragment.MineFragment;
import com.liuchuanzheng.qiekenao.mvp.v.fragment.VideoFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.BezierPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public List<String> mTitleDataList;
    private final int[] normalResId = new int[]{
            R.drawable.ic_explore_normal,
            R.drawable.ic_live_normal,
            R.drawable.ic_user_normal
    };
    private final int[] pressedResId = new int[]{
            R.drawable.ic_explore_pressed,
            R.drawable.ic_live_pressed,
            R.drawable.ic_user_pressed
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        mTitleDataList = new ArrayList<>();
        mTitleDataList.add("主页");
        mTitleDataList.add("视频");
        mTitleDataList.add("我的");
    }

    private void initView() {
        //设置viewpager
        final ViewPager viewPager  = findViewById(R.id.vp);
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new VideoFragment());
        fragmentList.add(new MineFragment());
        MyMainPagerAdapter myMainPagerAdapter = new MyMainPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(myMainPagerAdapter);
        //设置drawer
        DrawerLayout drawerLayout  = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawers();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //设置底部导航栏
        final MagicIndicator magicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {

                return mTitleDataList == null ? 0 : mTitleDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {

                CommonPagerTitleView titleView = new CommonPagerTitleView(context);
                titleView.setContentView(R.layout.item_main_tab_indicator_layout);//加载自定义布局作为Tab

                final Button tab_btn = (Button) titleView.findViewById(R.id.tab_btn);
                TextView tv_title = titleView.findViewById(R.id.tv_title);
                tv_title.setText(mTitleDataList.get(index));
                titleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {
                    @Override
                    public void onSelected(int i, int i1) {
                        tab_btn.setBackgroundResource(pressedResId[i]);
                        viewPager.setCurrentItem(index);
                    }

                    @Override
                    public void onDeselected(int i, int i1) {
                        tab_btn.setBackgroundResource(normalResId[i]);
                    }

                    @Override
                    public void onLeave(int i, int i1, float v, boolean b) {

                    }

                    @Override
                    public void onEnter(int i, int i1, float v, boolean b) {

                    }
                });
                titleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        magicIndicator.onPageSelected(index);
                        magicIndicator.onPageScrolled(index, 0.0F, 0);
                    }
                });

                return titleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                BezierPagerIndicator indicator = new BezierPagerIndicator(context);
                indicator.setColors(Color.RED);
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}

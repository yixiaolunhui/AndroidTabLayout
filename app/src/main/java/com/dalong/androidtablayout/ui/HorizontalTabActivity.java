package com.dalong.androidtablayout.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dalong.androidtablayout.R;
import com.dalong.androidtablayout.ui.fragment.TabFourFragment;
import com.dalong.androidtablayout.ui.fragment.TabOneFragment;
import com.dalong.androidtablayout.ui.fragment.TabThreeFragment;
import com.dalong.androidtablayout.ui.fragment.TabTwoFragment;
import com.dalong.tablayout.Tab;
import com.dalong.tablayout.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 水平tab demo
 */
public class HorizontalTabActivity extends AppCompatActivity {
    public final String TAG=HorizontalTabActivity.class.getSimpleName();

    /******************************可以服务器获取这些数据动态添加tab*************************************/
    private String[] mTabNames = {"首页", "消息", "搜索", "我的"};

    /**
     * 默认状态下的本地图片
     */
    private int[] mUnSelectIcons = {
            R.mipmap.tabbar_home,
            R.mipmap.tabbar_message_center,
            R.mipmap.tabbar_discover,
            R.mipmap.tabbar_profile};

    /**
     * 默认状态下的本地图片
     */
    private int[] mSelectIcons = {
            R.mipmap.tabbar_home_highlighted,
            R.mipmap.tabbar_message_center_highlighted,
            R.mipmap.tabbar_discover_highlighted,
            R.mipmap.tabbar_profile_highlighted};

    /**
     * 默认状态下的网络图片
     */
    private String[] mUnSelectUrls={
            "http://www.androidstudy.cn/img/tabbar_home.png",
            "http://www.androidstudy.cn/img/tabbar_message_center.png",
            "http://www.androidstudy.cn/img/tabbar_discover.png",
            "http://www.androidstudy.cn/img/tabbar_profile.png"};

    /**
     * 选中状态下的网络图片
     */
    private String[] mSelectUrls={
            "http://www.androidstudy.cn/img/tabbar_home_selected.png",
            "http://www.androidstudy.cn/img/tabbar_message_center_highlighted.png",
            "http://www.androidstudy.cn/img/tabbar_discover_highlighted.png",
            "http://www.androidstudy.cn/img/tabbar_profile_highlighted.png"};


    /**
     * 未选中的颜色
     */
    private int mUnSelectColor = R.color.unSelectColor;

    /**
     * 选中的颜色
     */
    private int mSelectColor = R.color.SelectColor;


    /********************************可以服务器获取这些数据动态添加tab***********************************/



    //tab数据
    private List<Tab> mBottomTabs = new ArrayList<>();
    private ArrayList<Fragment> fragmentsList = new ArrayList<Fragment>();

    private ViewPager mHorViewpager;
    private TabLayout mTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_tab);
        initView();
        initData();
    }

    private void initView() {
        mHorViewpager=(ViewPager)findViewById(R.id.horizontal_viewpager);
        mTabLayout=(TabLayout)findViewById(R.id.horizontal_bottomtab);
        mTabLayout.setOnTabChangeListener(new TabLayout.OnTabChangeListener() {
            @Override
            public void onTabSelect(int position) {
                Log.v(TAG, "您选择了"+position);
                mHorViewpager.setCurrentItem(position);
            }

            @Override
            public void onTabSelected(int position) {
                Log.v(TAG, "您已经选择了"+position);
            }
        });
        mTabLayout.setOnSetTabIconListener(new TabLayout.OnSetTabNetworkIconListener() {
            @Override
            public void setTabNetworkIcon(ImageView mTabIcon, int defaultIcon, String iconUrl, int position, boolean isSelect) {
                Glide.with(HorizontalTabActivity.this)
                        .load(iconUrl)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(defaultIcon)
                        .into(mTabIcon);
            }
        });
    }

    private void initData() {
        fragmentsList.add(new TabOneFragment());
        fragmentsList.add(new TabTwoFragment());
        fragmentsList.add(new TabThreeFragment());
        fragmentsList.add(new TabFourFragment());
        mHorViewpager.setAdapter(new GuideAdapter(getSupportFragmentManager(), fragmentsList));
        mTabLayout.setViewPager(mHorViewpager);
        initData(false);
    }

    public class GuideAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragmentsList;

        public GuideAdapter(FragmentManager fm) {
            super(fm);
        }

        public GuideAdapter(FragmentManager fm, ArrayList<Fragment> fragmentsList) {
            super(fm);
            this.fragmentsList = fragmentsList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentsList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentsList.size();
        }

    }


    /**
     * 初始化数据
     * @param isUrl
     */
    private void initData(boolean isUrl) {
        mBottomTabs.clear();
        for (int i=0;i<mUnSelectIcons.length;i++){
            Tab mBottomTab=new Tab(mTabNames[i],mUnSelectColor,
                    mSelectColor,mUnSelectIcons[i],mSelectIcons[i],isUrl?mUnSelectUrls[i]:null,isUrl?mSelectUrls[i]:null);
            mBottomTabs.add(mBottomTab);
        }
        mTabLayout.setTabData(mBottomTabs);
    }

    /**
     * 设置本地图片
     */
    public void onLocalIcon(){
        initData(false);
    }

    /**
     * 设置网络图片
     */
    public void onNetIcon(){
        initData(true);
    }

    /**
     * 设置小红点
     */
    public void onTabNum(){
        mTabLayout.setTabNum(new Random().nextInt(4),new Random().nextInt(100));
    }

    /**
     * 取消小红点
     */
    public void onClearTabNum(){
        mTabLayout.clearAllTabNum();
    }

    /**
     * 显示
     */
    public void onShow(){
        mTabLayout.showTab();
    }

    /**
     * 隐藏
     */
    public void onHide(){
        mTabLayout.hideTab();
    }
}

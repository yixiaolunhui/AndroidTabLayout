package com.dalong.tablayout;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Android官方底部Tab栏设计规范
 *
 * 1、推荐底部可以放置3到5个。
 * 2、推荐选中的图标或者文字为APP的主色调，如果Tab栏本身就是彩色，推荐黑色和白色作为图标或者文字
 * 3、选中的Tab同时显示icon和text。
 *    如果只有三个Tab，无论选中未选中，一直显示icon和文字。
 *    如果有四到五个Tab，选中的Tab显示文字和icon，未选中的Tab只显示icon
 * 4、文字要求言简意赅
 * 5、Bottom navigation bars的高度推荐为56dp，icon的尺寸为24*24，这种Google一般推荐使用8的倍数。选中tab的字体大小为14sp,未选中为12sp
 *
 * 底部tab切换布局
 * Created by zhouweilong on 2016/11/10.
 */

public class TabLayout extends LinearLayout {
    //上下文
    private  Context mContext;
    //底部tab数据
    private List<Tab> mBottomTabs=new ArrayList<>();
    //底部所有itemViews
    private List<View> mBottomTabViews=new ArrayList<>();
    //当前选择的tab index
    private int currentTab;
    //tab切换回调接口
    private OnTabChangeListener mOnTabChangeListener;
    //viewpager
    private ViewPager viewPager;
    //显示隐藏动画的时间值
    private int scrollDuration=500;

    public TabLayout(Context context) {
        this(context,null);
    }

    public TabLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        init();
    }

    private void init() {
    }


    /**
     * 设置tab数据
     * @param mBottomTabs
     */
    public void setTabData(List<Tab> mBottomTabs){
        if(mBottomTabs==null||mBottomTabs.size()==0){
            return;
        }
        this.mBottomTabViews.clear();
        //清除所有的子view
        removeAllViews();
        this.mBottomTabs=mBottomTabs;
        initTabViews();
    }

    /**
     * 设置底部小红点的数量
     * @param index
     * @param num
     */
    public void setTabNum(int index,int num){
        for (int i=0;i<mBottomTabViews.size();i++){
            TextView mTabNum= (TextView) mBottomTabViews.get(i).findViewById(R.id.tab_item_num);
            if(index==i&&num>0){
                mTabNum.setVisibility(VISIBLE);
                mTabNum.setText(num>=100?"99+":String.valueOf(num));
            }
        }
    }

    /**
     * 清除对应位置的小红点的数量
     * @param index
     */
    public void clearTabNum(int index){
        if(index>=mBottomTabViews.size()||mBottomTabViews==null||index<0)return;
        TextView mTabNum= (TextView) mBottomTabViews.get(index).findViewById(R.id.tab_item_num);
        mTabNum.setText("");
        mTabNum.setVisibility(GONE);
    }


    /**
     * 清除所有的小红点的数量
     */
    public void clearAllTabNum(){
        for (int i=0;i<mBottomTabViews.size();i++){
            TextView mTabNum= (TextView) mBottomTabViews.get(i).findViewById(R.id.tab_item_num);
            mTabNum.setText("");
            mTabNum.setVisibility(GONE);
        }
    }

    /**
     * 初始化tabviews
     */
    public void initTabViews(){
        View tabView;
        for (int index=0;index<mBottomTabs.size();index++){
            tabView= LayoutInflater.from(mContext).inflate(R.layout.view_bottom_tab,null);
            tabView.setTag(index);
            addTabView(index,tabView);
            mBottomTabViews.add(tabView);
        }
        setCurrentTab(0);
    }

    /**
     * 添加tabview
     * @param index
     * @param tabView
     */
    private void addTabView(int index, View tabView) {
        TextView mTabName= (TextView) tabView.findViewById(R.id.tab_item_name);
        ImageView mTabIcon= (ImageView) tabView.findViewById(R.id.tab_item_icon);
        mTabName.setText(mBottomTabs.get(index).getTabName());

        int tabUnSelectIcon=mBottomTabs.get(index).getTabUnSelectIcon();
        String tabUnSelectUrl=mBottomTabs.get(index).getTabUnSelectUrl();
        String tabSelectUrl=mBottomTabs.get(index).getTabSelectUrl();

        //如果网络图片中选中或者不选中的图片其中之一为空都使用本地图片
        if(TextUtils.isEmpty(tabUnSelectUrl)||TextUtils.isEmpty(tabSelectUrl)){
            mTabIcon.setImageResource(tabUnSelectIcon);
        }else{
//            Glide.with(mContext)
//                    .load(tabUnSelectUrl)
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .placeholder(tabUnSelectIcon)//使用本地对应的图片作为默认加载图片
//                    .into(mTabIcon);
            if(mOnSetTabNetworkIconListener!=null){
                mOnSetTabNetworkIconListener.setTabNetworkIcon(mTabIcon,
                        tabUnSelectIcon,
                        tabUnSelectUrl,
                        index,
                        false);
            }
        }

        //设置tab点击事件 其中多加了一个重复点击的回调 为了有的需求是点击多次也要刷新的奇葩需求  比如我们
        tabView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                if (currentTab != position) {
                    setCurrentTab(position);
                    if (mOnTabChangeListener != null) {
                        mOnTabChangeListener.onTabSelect(position);
                    }
                } else {
                    if (mOnTabChangeListener != null) {
                        mOnTabChangeListener.onTabSelected(position);
                    }
                }
            }
        });

        LayoutParams params ;
        if(getOrientation()==HORIZONTAL){
            params = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);
            setGravity(Gravity.CENTER_VERTICAL);
        }else{
            params = new LayoutParams(LayoutParams.MATCH_PARENT, 0, 1.0f);
            setGravity(Gravity.CENTER_HORIZONTAL);
        }
        addView(tabView, index, params);
    }

    /**
     * 设置viewpager
     * @param viewPager
     */
    public void setViewPager(final ViewPager viewPager) {
        this.viewPager = viewPager;
        setUpListener();
    }

    /**
     * 实现回调设置选中tab
     */
    private void setUpListener() {
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentTab(position);
            }
        });
    }


    /**
     * 设置选中
     * @param currentTab
     */
    public void setCurrentTab(int currentTab) {
        this.currentTab = currentTab;
        updateTabState(currentTab);
    }

    /**
     * 获取当前选中的tab的下标
     * @return
     */
    public int  getCurrentTab(){
        return currentTab;
    }

    /**
     * 隐藏tab
     */
    public void hideTab() {
        if(getOrientation()==HORIZONTAL){
            smoothScroll(this,getMeasuredHeight(),scrollDuration,"translationY");
        }else{
            smoothScroll(this,-getMeasuredWidth(),scrollDuration,"translationX");
        }

    }

    /**
     * 显示tab
     */
    public void showTab(){
        if(getOrientation()==HORIZONTAL){
            smoothScroll(this,0,scrollDuration,"translationY");
        }else{
            smoothScroll(this,0,scrollDuration,"translationX");
        }

    }
    /**
     * 显示隐藏动画
     * @param target
     * @param delta
     * @param duration
     */
    private void smoothScroll(Object target, float delta,int duration,String trans) {
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(target,trans,delta);
        objectAnimator.setDuration(duration);
        objectAnimator.start();
    }

    /**
     * 更新选中的和未选中的样式
     * @param currentTab
     */
    private void updateTabState(int currentTab) {
        for (int index=0;index<mBottomTabs.size();index++){
            View tabView = getChildAt(index);
            final boolean isSelect = index == currentTab;
            Tab mBottomTab = mBottomTabs.get(index);
            TextView mTabName = (TextView) tabView.findViewById(R.id.tab_item_name);
            ImageView mTabIcon = (ImageView) tabView.findViewById(R.id.tab_item_icon);

            //根据判别是否是选择的哪一个来设置对应的颜色值
            mTabName.setTextColor(isSelect ? getResources().getColor(mBottomTab.getTabNameSelectColor())
                    : getResources().getColor(mBottomTab.getTabNameUnSelectColor()));

            //判别网络图片url是不是为空要是为空的话就是使用本地的图片如果不为空就用网络图片  默认加载的图片为对应的本地图片 避免加载空白问题
            if(TextUtils.isEmpty(mBottomTab.getTabSelectUrl())||
                    TextUtils.isEmpty(mBottomTab.getTabUnSelectUrl())){
                mTabIcon.setImageResource(isSelect ?mBottomTab.getTabSelectIcon(): mBottomTab.getTabUnSelectIcon());
            }else{
//                Glide.with(mContext).load(isSelect?mBottomTab.getTabSelectUrl():mBottomTab.getTabUnSelectUrl()).
//                        diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .placeholder(isSelect?mBottomTab.getTabSelectIcon():mBottomTab.getTabUnSelectIcon())
//                        .into(mTabIcon);

                if(mOnSetTabNetworkIconListener!=null){
                    mOnSetTabNetworkIconListener.setTabNetworkIcon(mTabIcon,
                            isSelect?mBottomTab.getTabSelectIcon():mBottomTab.getTabUnSelectIcon(),
                            isSelect?mBottomTab.getTabSelectUrl():mBottomTab.getTabUnSelectUrl(),
                            index,
                            isSelect);
                }
            }
        }
    }


    /**
     * 设置回调实现
     * @param mOnTabChangeListener
     */
    public void setOnTabChangeListener(OnTabChangeListener mOnTabChangeListener){
        this.mOnTabChangeListener=mOnTabChangeListener;
    }

    /**
     * tab切换回调接口
     */
    public interface OnTabChangeListener {
        //tab选择回调
        void onTabSelect(int position);
        //tab重复选择回调
        void onTabSelected(int position);
    }

    /**
     * 设置网络图片回调
     */
    public OnSetTabNetworkIconListener mOnSetTabNetworkIconListener;
    public void setOnSetTabIconListener(OnSetTabNetworkIconListener mOnSetTabNetworkIconListener){
        this.mOnSetTabNetworkIconListener=mOnSetTabNetworkIconListener;
    }


    public interface OnSetTabNetworkIconListener {
        /**
         * 设置网络icon
         * @param mTabIcon     tabview
         * @param defaultIcon  默认的icon
         * @param iconUrl      网络图片的url
         * @param position     tab的index
         * @param isSelect     是否选中状态
         */
        void setTabNetworkIcon(ImageView mTabIcon,int defaultIcon,String iconUrl,int position,boolean isSelect);
    }

}

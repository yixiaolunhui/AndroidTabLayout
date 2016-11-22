# tablayout
app底部tab切换布局

#Effect

![image](https://github.com/dalong982242260/AndroidTabLayout/blob/master/img/hortab.gif?raw=true)            ![image](https://github.com/dalong982242260/AndroidTabLayout/blob/master/img/vertab.gif?raw=true)

#strengths
        1、动态设置tab，方便，易于使用。
        2、可以加载网络图标。
        3、支持设置消息的数量以清除消息的数量。
        4、支持水平和垂直布局。
        5、支持动画显示和隐藏。

#Edition

        1.0.1版本：
        1、添加底部tab布局显示和隐藏方法。
        2、抽离glide，回调设置网络图片便于使用其他图片框架加载。


        1.0.0版本
        1、动态设置tab，方便使用。
        2、可以设置本地图片和网络图片。
        3、支持水平和竖直布局。
        4、支持设置消息的数量以清除消息的数量。

#use

 gradle：
 
                 dependencies {
                      compile 'com.dalong:tablayout:1.0.1'
                 }

 xml：
 
                <com.dalong.tablayout.TabLayout
                       android:id="@+id/horizontal_bottomtab"
                       android:layout_width="match_parent"
                       android:orientation="horizontal"
                       android:layout_alignParentBottom="true"
                       android:layout_height="wrap_content"/>             


 java：
 
       mTabLayout=(BottomTabLayout)findViewById(R.id.mBottomTabLayout);
       mTabLayout.setOnTabChangeListener(new TabLayout.OnTabChangeListener() {
                  @Override
                  public void onTabSelect(int position) {
                      Toast.makeText(MainActivity.this, "您选择了"+position, Toast.LENGTH_SHORT).show();
                  }
      
                  @Override
                  public void onTabSelected(int position) {
                      Toast.makeText(MainActivity.this, "您已经选择了"+position, Toast.LENGTH_SHORT).show();
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

       //Set the number of red points corresponding to index
       mTabLayout.setTabNum(new Random().nextInt(4),new Random().nextInt(100));
       
       //Clear all messages
       mTabLayout.clearAllTabNum();
       
       //Clear the corresponding location of the message
       mTabLayout.clearTabNum(1);
       
       //show tab
       mTabLayout.showTab();
       
       //hide tab
       mTabLayout.hideTab();

#Thank
[glide](https://github.com/bumptech/glide)
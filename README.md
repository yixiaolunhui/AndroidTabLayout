# tablayout
App switch tab control, the code to load the network image using the open source glide framework.

#Effect

![image](https://github.com/dalong982242260/AndroidTabLayout/blob/master/img/hortab.gif?raw=true)![image](https://github.com/dalong982242260/AndroidTabLayout/blob/master/img/vertab.gif?raw=true)

#
        1、Dynamic set tab, convenient and easy to use
        2、Can load the network Icon
        3、Supports setting the number of messages to clear the number of messages.
        4、Support horizontal and vertical layout

#use

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

       //Set the number of red points corresponding to index
       mTabLayout.setTabNum(new Random().nextInt(4),new Random().nextInt(100));
       
       //Clear all messages
       mTabLayout.clearAllTabNum();
       
       //Clear the corresponding location of the message
       mTabLayout.clearTabNum(1);

#Thank
[glide](https://github.com/bumptech/glide)
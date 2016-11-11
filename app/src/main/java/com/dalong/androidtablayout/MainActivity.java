package com.dalong.androidtablayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dalong.androidtablayout.ui.HorizontalTabActivity;
import com.dalong.androidtablayout.ui.VerticalTabActivity;

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
 * Created by zhouweilong on 2016/11/10.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 水平
     * @param view
     */
    public  void horizontalBtn(View view){
        startActivity(new Intent(this, HorizontalTabActivity.class));

    }


    /**
     * 竖直
     * @param view
     */
    public  void verticalBtn(View view){
        startActivity(new Intent(this, VerticalTabActivity.class));
    }
}

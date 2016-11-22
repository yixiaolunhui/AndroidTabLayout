package com.dalong.androidtablayout.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dalong.androidtablayout.R;
import com.dalong.androidtablayout.ui.HorizontalTabActivity;
import com.dalong.androidtablayout.ui.VerticalTabActivity;


public class TabOneFragment extends Fragment implements View.OnClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tab_one, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        view.findViewById(R.id.onClearTabNum).setOnClickListener(this);
        view.findViewById(R.id.onTabNum).setOnClickListener(this);
        view.findViewById(R.id.onNetIcon).setOnClickListener(this);
        view.findViewById(R.id.onLocalIcon).setOnClickListener(this);
        view.findViewById(R.id.onShow).setOnClickListener(this);
        view.findViewById(R.id.onHide).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.onLocalIcon:
                onLocalIcon(v);
                break;
            case R.id.onNetIcon:
                onNetIcon(v);
                break;
            case R.id.onTabNum:
                onTabNum(v);
                break;
            case R.id.onClearTabNum:
                onClearTabNum(v);
                break;
            case R.id.onShow:
                onShow(v);
                break;
            case R.id.onHide:
                onHide(v);
                break;
        }

    }

    public void onShow(View v) {
        if(getActivity() instanceof HorizontalTabActivity){
            HorizontalTabActivity horizontalTabActivity= (HorizontalTabActivity) getActivity();
            horizontalTabActivity.onShow();
        }else if(getActivity() instanceof VerticalTabActivity){
            VerticalTabActivity verticalTabActivity= (VerticalTabActivity) getActivity();
            verticalTabActivity.onShow();
        }
    }
    public void onHide(View v) {
        if(getActivity() instanceof HorizontalTabActivity){
            HorizontalTabActivity horizontalTabActivity= (HorizontalTabActivity) getActivity();
            horizontalTabActivity.onHide();
        }else if(getActivity() instanceof VerticalTabActivity){
            VerticalTabActivity verticalTabActivity= (VerticalTabActivity) getActivity();
            verticalTabActivity.onHide();
        }
    }

    /**
     * 设置本地图片
     * @param view
     */
    public void onLocalIcon(View view){
        if(getActivity() instanceof HorizontalTabActivity){
            HorizontalTabActivity horizontalTabActivity= (HorizontalTabActivity) getActivity();
            horizontalTabActivity.onLocalIcon();
        }else if(getActivity() instanceof VerticalTabActivity){
            VerticalTabActivity verticalTabActivity= (VerticalTabActivity) getActivity();
            verticalTabActivity.onLocalIcon();
        }

    }

    /**
     * 设置网络图片
     * @param view
     */
    public void onNetIcon(View view){
        if(getActivity() instanceof HorizontalTabActivity){
            HorizontalTabActivity horizontalTabActivity= (HorizontalTabActivity) getActivity();
            horizontalTabActivity.onNetIcon();
        }else if(getActivity() instanceof VerticalTabActivity){
            VerticalTabActivity verticalTabActivity= (VerticalTabActivity) getActivity();
            verticalTabActivity.onNetIcon();
        }
    }

    /**
     * 设置小红点
     * @param view
     */
    public void onTabNum(View view){
        if(getActivity() instanceof HorizontalTabActivity){
            HorizontalTabActivity horizontalTabActivity= (HorizontalTabActivity) getActivity();
            horizontalTabActivity.onTabNum();
        }else if(getActivity() instanceof VerticalTabActivity){
            VerticalTabActivity verticalTabActivity= (VerticalTabActivity) getActivity();
            verticalTabActivity.onTabNum();
        }
    }

    /**
     * 取消小红点
     * @param view
     */
    public void onClearTabNum(View view){
        if(getActivity() instanceof HorizontalTabActivity){
            HorizontalTabActivity horizontalTabActivity= (HorizontalTabActivity) getActivity();
            horizontalTabActivity.onClearTabNum();
        }else if(getActivity() instanceof VerticalTabActivity){
            VerticalTabActivity verticalTabActivity= (VerticalTabActivity) getActivity();
            verticalTabActivity.onClearTabNum();
        }
    }


}

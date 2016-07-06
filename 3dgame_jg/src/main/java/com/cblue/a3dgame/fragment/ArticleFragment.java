package com.cblue.a3dgame.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cblue.a3dgame.R;
import com.cblue.a3dgame.adapter.MainArticleFramentViewPagerAdapter;
import com.cblue.a3dgame.customview.MainArticleFragmentViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pavel on 2016/7/6.
 */
public class ArticleFragment extends Fragment {

    //定义文章类型
    private int typeid;

    MainArticleFramentViewPagerAdapter mainArticleFramentViewPagerAdapter;

    public ArticleFragment() {
    }

    //Annotation 注解
    @SuppressLint("ValidFragment")
    public ArticleFragment(int typeid) {
        this.typeid = typeid;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //获得Fragment中整体布局
        View view = inflater.inflate(R.layout.activity_main_articlefragment,null);
        //获得Fragment中的ViewPager
        MainArticleFragmentViewPager mainArticleFragmentViewPager =(MainArticleFragmentViewPager) view.findViewById(R.id.main_articlefragment_viewpager);

        int imageRsId [] = {R.drawable.default1,R.drawable.default2,R.drawable.default3};
        //初始化ViewPager的数据
        List<ImageView> imageViews = new ArrayList<ImageView>();
        for(int i=0;i<3;i++){
            ImageView imageView = new ImageView(getActivity());
            //设置图片的缩放类型  铺满全屏
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(imageRsId[i]);
            imageViews.add(imageView);
        }
        mainArticleFramentViewPagerAdapter = new MainArticleFramentViewPagerAdapter(imageViews);
        mainArticleFragmentViewPager.setAdapter(mainArticleFramentViewPagerAdapter);


        TextView tv = (TextView)view.findViewById(R.id.activity_main_articlefragment_tv);
        Log.i("aaa","typeid="+typeid);
        tv.setText(typeid+"");

        return view;
    }
}

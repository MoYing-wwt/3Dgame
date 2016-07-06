package com.moying.a3dgamedemo.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moying.a3dgamedemo.R;
import com.moying.a3dgamedemo.adapter.MainArticleFramentViewPagerAdapter;
import com.moying.a3dgamedemo.adapter.MainFragementPagerAdapter;
import com.moying.a3dgamedemo.customview.MainArticleFragmentViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MOYING on 2016/7/6.
 */
public class ArticleFragment extends Fragment {
    private int typeid;//定义文章类型
    MainArticleFramentViewPagerAdapter mainArticleFramentViewPagerAdapter;

    public ArticleFragment() {
    }

    //Annotation注解
    @SuppressLint("ValidFragment")
    public ArticleFragment(int typeid) {
        this.typeid = typeid;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //获取fragment中整体布局
        View view = inflater.inflate(R.layout.activity_main_articlefragment, null);
        //获取fragment中的viewpager
        MainArticleFragmentViewPager mainArticleFragmentViewPager = (MainArticleFragmentViewPager) view.findViewById(R.id.main_articlefragment_viewpager);
        int imageRsId[] = {R.drawable.default1, R.drawable.default2, R.drawable.default3};
        //初始化viewpager中的数据
        List<ImageView> imageViews = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(getActivity());
            //设置图片缩放类型 覆满全屏
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(imageRsId[i]);
            imageViews.add(imageView);
        }
        mainArticleFramentViewPagerAdapter = new MainArticleFramentViewPagerAdapter(imageViews);
        mainArticleFragmentViewPager.setAdapter(mainArticleFramentViewPagerAdapter);

        View view01 = view.findViewById(R.id.activity_main_articlefragment_v1);
        View view02 = view.findViewById(R.id.activity_main_articlefragment_v2);
        View view03 = view.findViewById(R.id.activity_main_articlefragment_v3);
        final List<View> viewList = new ArrayList<>();
        viewList.add(view01);
        viewList.add(view02);
        viewList.add(view03);

        mainArticleFragmentViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
               
            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < viewList.size(); i++) {
                    viewList.get(i).setBackgroundColor(Color.RED);
                }
                viewList.get(position).setBackgroundColor(Color.GREEN);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        TextView tv = (TextView) view.findViewById(R.id.activity_main_articlefragment_tv);
        Log.i("aaa", "typeidshi" + typeid);
        tv.setText(typeid + "");
        return view;
    }
}

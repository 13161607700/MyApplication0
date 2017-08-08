package com.example.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private List<Fragment> fragments;
    private MovieFragment movieFragment;
    private TvFragment tvFragment;
    private VarietyFragment varietyFragment;
    private CartoonFragment cartoonFragment;
    private List<TextView> views;
    private TextView movie;
    private TextView tv;
    private TextView variety;
    private TextView cartoon;
    //    底部中间凸起view
    private ImageView menuIv;
    //    当前选中的views的下标
    private int currentIndex = 0;
    //    旧的views下标
    private int oldIndex = 0;
    private boolean isMenuSelect = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            initFragments();
            initViews();
        }
    }

    /**
     * 初始化所用到的view；
     */
    private void initViews() {
        movie = (TextView) findViewById(R.id.movie);
        tv = (TextView) findViewById(R.id.tv);
        variety = (TextView) findViewById(R.id.variety);
        cartoon = (TextView) findViewById(R.id.cartoon);

        movie.setOnClickListener(this);
        tv.setOnClickListener(this);
        variety.setOnClickListener(this);
        cartoon.setOnClickListener(this);
//      默认第一个为选中状态
        movie.setSelected(true);

        views = new ArrayList<>();
        views.add(movie);
        views.add(tv);
        views.add(variety);
        views.add(cartoon);
        menuIv = (ImageView) findViewById(R.id.menu_iv);
        //底部中间凸起view点击
        menuIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                view.setSelected(isMenuSelect=(isMenuSelect==true?false:true));
                //父类的show方法
                showMessage("on click menuIv!");
            }
        });
    }

    /**
     * 初始化用到的Fragment
     */
    private void initFragments() {
        movieFragment = new MovieFragment();
        tvFragment = new TvFragment();
        varietyFragment = new VarietyFragment();
        cartoonFragment = new CartoonFragment();

        fragments = new ArrayList<>();
        fragments.add(movieFragment);
        fragments.add(tvFragment);
        fragments.add(varietyFragment);
        fragments.add(cartoonFragment);
//        默认加载前两个Fragment，其中第一个展示，第二个隐藏
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content,movieFragment)
                .add(R.id.content,tvFragment)
                .hide(tvFragment)
                .show(movieFragment)
                .commit();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.movie:
                currentIndex = 0;
                break;
            case R.id.tv:
                currentIndex = 1;
                break;
            case R.id.variety:
                currentIndex = 2;
                break;
            case R.id.cartoon:
                currentIndex = 3;
                break;
        }
//        规避策略将凸起的view还原
        menuIv.setSelected(false);
        isMenuSelect = false;

        showCurrentFragment(currentIndex);
    }

    /**
     * 展示当前选中的Fragment
     * @param currentIndex
     */
    private void showCurrentFragment(int currentIndex) {
        if (currentIndex != oldIndex){
            views.get(oldIndex).setSelected(false);
            views.get(currentIndex).setSelected(true);
            FragmentTransaction ft = getSupportFragmentManager()
                    .beginTransaction();
            ft.hide(fragments.get(oldIndex));
            if (!fragments.get(currentIndex).isAdded()){
                ft.add(R.id.content,fragments.get(currentIndex));
            }
            ft.show(fragments.get(currentIndex)).commit();
            oldIndex = currentIndex;
        }
    }
}

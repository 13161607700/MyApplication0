package com.example.myapplication;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

/**
 * Created by lenovo on 2017/8/7.
 */

public class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        TODO 可以在这里初始化一些东西
//  TODO 我的项目里初始化诸如ButterKnife和toolbar之类的东西
    }
    //一些子类都有可能用到的方法，等等……
    public void showMessage(String text){
        Toast.makeText(this,text, Toast.LENGTH_SHORT).show();
    }

    protected void showDialog(){

    }
    protected void dismissDialog(){

    }
}
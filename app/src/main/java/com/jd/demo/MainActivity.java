package com.jd.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private DrawerLayout drawerLayout;
    private TextView textView;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置状态栏透明
        setStatus();

        textView = (TextView) findViewById(R.id.tv_main);
        //设置Toolbar
        initToobar();

        //按钮点击事件
        initButton();

        //设置菜单打开关闭的监听
        initDrawerLayout();

        //菜单的设置
        initgNavagationView();
    }

    //菜单的设置
    private void initgNavagationView() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    //设置菜单打开关闭的监听
    private void initDrawerLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    //按钮点击事件
    private void initButton() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Snack", Snackbar.LENGTH_LONG)
                        .setAction("clickSnack", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "Snack被点击了", Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
            }
        });
    }

    //设置Toolbar
    private void initToobar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //设置状态栏透明
    private void setStatus() {
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }

    //添加Toolbar右上方的菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_toolbar_menu, menu);
        return true;
    }

    //Toolbar右上方菜单item的点击回调
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main_about:
            case R.id.menu_main_home_page:
            case R.id.menu_main_top_github:
                textView.setText(item.getTitle());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //左侧菜单item点击时回调
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favorite:
            case R.id.wallet:
            case R.id.photo:
            case R.id.file:
            case R.id.navigation_daily:
            case R.id.navigation_welfare:
            case R.id.navigation_android:
            case R.id.navigation_ios:
            case R.id.navigation_js:
            case R.id.navigation_video:
            case R.id.navigation_resources:
            case R.id.navigation_app:
                textView.setText(item.getTitle());
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //返回键的处理
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
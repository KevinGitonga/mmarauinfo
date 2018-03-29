package ke.co.ipandasoft.mmaraumobileinfo;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.yarolegovich.lovelydialog.LovelyInfoDialog;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import java.util.ArrayList;
import java.util.List;

import ke.co.ipandasoft.mmaraumobileinfo.adapters.MainFragmentsAdapter;
import ke.co.ipandasoft.mmaraumobileinfo.config.Utils;
import ke.co.ipandasoft.mmaraumobileinfo.fragments.EventsFragment;
import ke.co.ipandasoft.mmaraumobileinfo.fragments.NewsFragment;
import ke.co.ipandasoft.mmaraumobileinfo.fragments.NoticesFragment;
import ke.co.ipandasoft.mmaraumobileinfo.rest.RestInterface;
import ke.co.ipandasoft.mmaraumobileinfo.utils.SharedPrefs;


public class MainActivity extends AppCompatActivity {
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    android.support.v7.app.ActionBarDrawerToggle actionBarDrawerToggle;
    private List<Fragment> fragemnts = new ArrayList<>();
    ViewPager viewPager;
    MainFragmentsAdapter mainFragmentsAdapter;
    private SharedPrefs sharedPrefs;
    private RestInterface finalRestInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPrefs=new SharedPrefs(MainActivity.this);
        setViews();
        initData();


    }



    private void initData() {
        NoticesFragment noticesFragment=NoticesFragment.getInstance();
        EventsFragment eventsFragment=EventsFragment.getInstance();
        NewsFragment newsFragment=NewsFragment.getInstance();
        fragemnts.add(noticesFragment);
        fragemnts.add(eventsFragment);
        fragemnts.add(newsFragment);
        mainFragmentsAdapter=new MainFragmentsAdapter(getSupportFragmentManager(),fragemnts);
        viewPager.setAdapter(mainFragmentsAdapter);
        viewPager.setOffscreenPageLimit(2);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    private void setViews() {
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setDrawer();
        viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.main_tab);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer);
        NavigationView navigationView = (NavigationView) findViewById(R.id.main_drawer_navigation);
        View headerView=navigationView.getHeaderView(0);
        TextView emailUser=headerView.findViewById(R.id.emailTextview);
        String userEmail=sharedPrefs.getUserEmail();
        if (TextUtils.isEmpty(userEmail)){
            emailUser.setText("User Email Goes Here");
        }else {
            emailUser.setText(userEmail);
        }

       actionBarDrawerToggle=new android.support.v7.app.ActionBarDrawerToggle(MainActivity.this,drawerLayout,
               R.string.drawer_open,R.string.drawer_closed);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(select);
    }

    private NavigationView.OnNavigationItemSelectedListener select = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            drawerLayout.closeDrawers();
           int itemId=item.getItemId();
           if (itemId==R.id.menu_inquiries){
               Intent intent=new Intent(MainActivity.this,InquiriesActivity.class);
               startActivity(intent);
           }else  if (itemId==R.id.menu_share){
               Utils.ShareApp(MainActivity.this);
           }else  if (itemId==R.id.menu_help){
               Utils.sendEmail(MainActivity.this);
           } else if (itemId== R.id.menu_about_app
                   ) {
               Intent intent=new Intent(MainActivity.this,AboutActivity.class);
               startActivity(intent);

           }else  if (itemId==R.id.menu_app_libs){
               Intent intent=new Intent(MainActivity.this,AtribsActivity.class);
               startActivity(intent);

           }else if (itemId==R.id.menu_about_news){
               ShowAboutNews();
           }
           else if(itemId==R.id.menu_log_out){
               LogOut();
           }
            return true;
        }
    };

    private void ShowAboutNews() {
        final LovelyInfoDialog lovelyInfoDialog=new LovelyInfoDialog(this);
        lovelyInfoDialog.setTopColorRes(R.color.colorPrimary);
        lovelyInfoDialog.setIcon(R.drawable.ic_rss_feed_white_24dp);
        lovelyInfoDialog.setTitle("About the News Section");
        lovelyInfoDialog.setMessage("The Data in the news section is Parsed from newsapi.org in their free" +
                " for developers and for non-commercial usage read more from their Website newsapi.org");
        lovelyInfoDialog.show();
    }

    private void LogOut() {
       final LovelyStandardDialog lovelyStandardDialog =new LovelyStandardDialog(this, LovelyStandardDialog.ButtonLayout.HORIZONTAL);
              lovelyStandardDialog.setTopColorRes(R.color.colorPrimary);
              lovelyStandardDialog.setButtonsColorRes(R.color.colorAccent);
              lovelyStandardDialog.setIcon(R.drawable.ic_lock_white_24dp);
              lovelyStandardDialog.setTitle(R.string.logout_title);
              lovelyStandardDialog.setMessage(R.string.logout_message);
              lovelyStandardDialog.setPositiveButton(R.string.okay, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sharedPrefs=new SharedPrefs(MainActivity.this);
                        sharedPrefs.ClearPrefs(MainActivity.this);
                        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
               lovelyStandardDialog.setNegativeButton(R.string.no, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lovelyStandardDialog.dismiss();

                    }
                });
                lovelyStandardDialog.show();
                lovelyStandardDialog.show();

            }

            @Override
            public boolean onCreateOptionsMenu(Menu menu) {

                return true;
            }


            @Override
            protected void onPostCreate(@Nullable Bundle savedInstanceState) {
                actionBarDrawerToggle.syncState();
                super.onPostCreate(savedInstanceState);
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {

                if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
                    return true;
                }


                return super.onOptionsItemSelected(item);
            }
        }

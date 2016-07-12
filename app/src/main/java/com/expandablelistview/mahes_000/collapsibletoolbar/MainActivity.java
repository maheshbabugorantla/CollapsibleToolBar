package com.expandablelistview.mahes_000.collapsibletoolbar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    private ViewPager viewPager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Mahesh Gorantla");

        dynamicToolbarColor();

        toolbarTextAppearance();

        // Setup the ViewPager and Sliding Tabs
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        FragmentManager fragmentManager = getSupportFragmentManager();

        viewPager.setAdapter(new MyAdapter(fragmentManager));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        
        return super.onOptionsItemSelected(item);
    }

    private void dynamicToolbarColor() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.puppy_cute);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(ContextCompat.getColor(getApplicationContext(), R.color.primary)));
                collapsingToolbarLayout.setStatusBarScrimColor(palette.getMutedColor(ContextCompat.getColor(getApplicationContext(), R.color.primary_dark)));
            }
        });
    }

    private void toolbarTextAppearance() {
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.expandedappbar);
    }
}

class MyAdapter extends FragmentPagerAdapter {

    public MyAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        if (position == 0) {
            fragment = new Fragment1();
        } else if (position == 1) {
            fragment = new Fragment2();
        } else if (position == 2) {
            fragment = new Fragment3();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}

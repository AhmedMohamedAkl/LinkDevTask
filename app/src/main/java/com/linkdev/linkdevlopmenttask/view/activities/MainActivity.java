package com.linkdev.linkdevlopmenttask.view.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.linkdev.linkdevlopmenttask.R;
import com.linkdev.linkdevlopmenttask.view.fragments.HomeFragment;
import com.linkdev.linkdevlopmenttask.view.fragments.SideMenuFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private SideMenuFragment sideMenuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        sideMenuFragment = (SideMenuFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_fragment);
         setupToolbar();
        toggle.syncState();
        //Add home fragment to main container
        addRootFragment(HomeFragment.newInstance(), R.id.container, false, 0, 0);

    }

    protected void setupToolbar() {

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_website, R.string.open_website);
        toggle.setDrawerIndicatorEnabled(false);
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                toggle.onDrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                toggle.onDrawerOpened(drawerView);
                if (sideMenuFragment != null) {
                    sideMenuFragment.onDrawerOpened();
                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                toggle.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                toggle.onDrawerStateChanged(newState);
            }
        });
        sideMenuFragment.setUp(drawer);
        setSupportActionBar(toolbar);
        updateActionBar(false);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            updateActionBar(false);
            super.onBackPressed();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                drawer.openDrawer(GravityCompat.START);
            }
        }
        return super.onOptionsItemSelected(item);

    }

    /**
     * Update Action bar icons (Menu icon  / Back icon )
     * handle action for each scenario
     *
     * @param show flag for show back icon or not
     */
    public void updateActionBar(boolean show) {
        toggle.setDrawerIndicatorEnabled(!show);
        getSupportActionBar().setDisplayHomeAsUpEnabled(show);

        if (show) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        } else {
            toggle.syncState();
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawer.openDrawer(GravityCompat.START);
                }
            });
        }
    }
}

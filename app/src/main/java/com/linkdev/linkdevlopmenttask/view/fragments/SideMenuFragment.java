package com.linkdev.linkdevlopmenttask.view.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.linkdev.linkdevlopmenttask.R;
import com.linkdev.linkdevlopmenttask.model.SideMenuModel;
import com.linkdev.linkdevlopmenttask.view.adapter.ArticlesAdapter;
import com.linkdev.linkdevlopmenttask.view.adapter.SideMenuAdapter;
import com.linkdev.linkdevlopmenttask.view_model.HomeViewModel;
import com.linkdev.linkdevlopmenttask.view_model.SideMenuViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ahmed on 11/5/18.
 */

public class SideMenuFragment extends BaseFragment<SideMenuViewModel> {


    @BindView(R.id.menu_recycler_view)
    RecyclerView menuRecycler;

    private List<SideMenuModel> menuModelList = new ArrayList<>();
    private SideMenuAdapter sideMenuAdapter;
    private     DrawerLayout drawer;

    @Override
    protected void initializeViewModel() {
        baseViewModel = ViewModelProviders.of(this).get(SideMenuViewModel.class);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sideMenuAdapter = new SideMenuAdapter(getActivity(), baseViewModel.getMenuSectionList(getContext()));
        menuRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        menuRecycler.setAdapter(sideMenuAdapter);

    }

    @Override
    protected int getViewLayoutId() {
        return R.layout.fragment_side_menu;
    }

    public void onDrawerOpened() {
        // for knowing that it is opened to some bussiness logic like tagging
    }

    public void closeNavigationMenu() {
        drawer.closeDrawer(GravityCompat.START);
    }

    public void setUp(DrawerLayout drawerLayout) {
        this.drawer = drawerLayout;
    }

}

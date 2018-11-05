package com.linkdev.linkdevlopmenttask.view.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ProgressBar;


import com.linkdev.linkdevlopmenttask.R;
import com.linkdev.linkdevlopmenttask.utils.FragmentUtil;
import com.linkdev.linkdevlopmenttask.utils.NetworkUtils;
import com.linkdev.linkdevlopmenttask.view_model.BaseViewModel;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ahmed on 9/15/18.
 */

public abstract class BaseFragment<T extends BaseViewModel> extends Fragment {

    protected T baseViewModel;
    protected View rootView;
    private Unbinder unbinder;
    protected View view;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    protected void subscribeLiveData() {}

    protected abstract void initializeViewModel();

    protected abstract int getViewLayoutId();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViewModel();
        subscribeLiveData();
        if (baseViewModel != null)
            baseViewModel.start();
    }

    public void addFragment(Fragment fragment, int containerId, boolean addToBackStack, int enterAnimationResId, int exitAnimationResId) {
        FragmentUtil.addFragment(getActivity().getSupportFragmentManager(), containerId, fragment, enterAnimationResId, exitAnimationResId, addToBackStack);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.layout_fragment_frame, container, false);
        ViewStub stub = rootView.findViewById(R.id.content_vs);
        stub.setLayoutResource(getViewLayoutId());
        view = stub.inflate();
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (baseViewModel != null) {
            baseViewModel.stop();
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
    }


    public boolean onBackPressed() {
        if (getFragmentManager().getFragments().size() == 1) {
            getActivity().moveTaskToBack(true);
            return false;
        }
        return true;
    }


    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getActivity());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (baseViewModel != null)
        baseViewModel.stop();
    }




}

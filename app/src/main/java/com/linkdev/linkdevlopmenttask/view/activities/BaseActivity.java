package com.linkdev.linkdevlopmenttask.view.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.linkdev.linkdevlopmenttask.utils.FragmentUtil;


public abstract class BaseActivity extends AppCompatActivity {


    // TODO
    // this can probably depend on isLoading variable of BaseViewModel,
    // since its going to be common for all the activities

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
    }

     public void addRootFragment(Fragment fragment, int containerId, boolean addToBackStack, int enterAnimationResId, int exitAnimationResId) {
        getSupportFragmentManager().popBackStackImmediate();
        FragmentUtil.addFragment(getSupportFragmentManager(), containerId, fragment, enterAnimationResId, exitAnimationResId, addToBackStack);
    }

    public void addFragment(Fragment fragment, int containerId, boolean addToBackStack, int enterAnimationResId, int exitAnimationResId) {
        FragmentUtil.addFragment(getSupportFragmentManager(), containerId, fragment, enterAnimationResId, exitAnimationResId, addToBackStack);
    }



}

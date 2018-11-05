package com.linkdev.linkdevlopmenttask.utils;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentUtil {




    public static void addFragment(FragmentManager fragmentManager, int containerId, Fragment fragmentToBeAdded, int enterAnimationRes, int exitAnimationRes, boolean addToBackStask) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(containerId, fragmentToBeAdded, fragmentToBeAdded.getTag());
        if (addToBackStask) {
            transaction.addToBackStack(fragmentToBeAdded.getTag());
        }
        if (enterAnimationRes != -1 && exitAnimationRes != -1) { // TODO : default value ?
            transaction.setCustomAnimations(enterAnimationRes, exitAnimationRes);
        }
        transaction.commit();
    }



}
package com.linkdev.linkdevlopmenttask.view_model;

import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ahmed on 9/15/18.
 */

public abstract class BaseViewModel extends ViewModel {

    protected CompositeDisposable disposables = new CompositeDisposable();

    public void stop() {
        disposables.clear();
    }


    public abstract void start();
}

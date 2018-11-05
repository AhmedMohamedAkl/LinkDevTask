package com.linkdev.linkdevlopmenttask.view_model;

import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.linkdev.linkdevlopmenttask.model.AllArticlesModel;
import com.linkdev.linkdevlopmenttask.model.response.ArticalsModel;
import com.linkdev.linkdevlopmenttask.model.response.ArticleModel;
import com.linkdev.linkdevlopmenttask.network.ApiClient;
import com.linkdev.linkdevlopmenttask.network.EndPointInterfaces;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.linkdev.linkdevlopmenttask.utils.AppConstants.API_KEY_VALUE;
import static com.linkdev.linkdevlopmenttask.utils.AppConstants.ASSOCIATED_PRESS_ARTICALS;
import static com.linkdev.linkdevlopmenttask.utils.AppConstants.DISPLAY_DATE_FORMAT;
import static com.linkdev.linkdevlopmenttask.utils.AppConstants.NEXT_WEB_ARTICALS;
import static com.linkdev.linkdevlopmenttask.utils.AppConstants.TIMESTAMP_FORMAT;


public class DetailsViewModel extends BaseViewModel {


    @Override
    public void start() {
    }


    public String getsecificDateFormat(String dateString) {
        String dateStr = "";
        try {
            DateFormat srcDf = new SimpleDateFormat(TIMESTAMP_FORMAT);
            Date date = srcDf.parse(dateString);
            DateFormat destDf = new SimpleDateFormat(DISPLAY_DATE_FORMAT);
            // format the date into another format
            dateStr = destDf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

}

package com.linkdev.linkdevlopmenttask.view_model;

import android.arch.lifecycle.MutableLiveData;

import com.linkdev.linkdevlopmenttask.model.AllArticlesModel;
import com.linkdev.linkdevlopmenttask.model.response.ArticleModel;
import com.linkdev.linkdevlopmenttask.model.response.ArticalsModel;
import com.linkdev.linkdevlopmenttask.network.ApiClient;
import com.linkdev.linkdevlopmenttask.network.EndPointInterfaces;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.linkdev.linkdevlopmenttask.utils.AppConstants.API_KEY_VALUE;
import static com.linkdev.linkdevlopmenttask.utils.AppConstants.ASSOCIATED_PRESS_ARTICALS;
import static com.linkdev.linkdevlopmenttask.utils.AppConstants.NEXT_WEB_ARTICALS;



public class HomeViewModel extends BaseViewModel {
    MutableLiveData<List<ArticleModel>> listOfArticlsLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();


    @Override
    public void start() {
        loadingLiveData.setValue(true);
        Observable<ArticalsModel> nextWebArticlesObservable = ApiClient.getClient().create(EndPointInterfaces.class).getArticles(NEXT_WEB_ARTICALS, API_KEY_VALUE)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        Observable<ArticalsModel> nassociatedArticlesObservable = ApiClient.getClient().create(EndPointInterfaces.class).getArticles(ASSOCIATED_PRESS_ARTICALS, API_KEY_VALUE)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        Observable<AllArticlesModel> combined = Observable.zip(nextWebArticlesObservable, nassociatedArticlesObservable, new BiFunction<ArticalsModel, ArticalsModel, AllArticlesModel>() {
            @Override
            public AllArticlesModel apply(ArticalsModel o, ArticalsModel o2) throws Exception {
                return new AllArticlesModel(o, o2);
            }
        });


        DisposableObserver<AllArticlesModel> disposableObserver = combined.subscribeWith(new DisposableObserver<AllArticlesModel>() {

            @Override
            public void onNext(AllArticlesModel allArticlesModel) {
                loadingLiveData.setValue(false);
                 listOfArticlsLiveData.setValue(allArticlesModel.getAllArticalsList());

            }

            @Override
            public void onError(Throwable e) {
                loadingLiveData.setValue(false);
                errorLiveData.setValue(e);
            }

            @Override
            public void onComplete() {
                loadingLiveData.setValue(false);
            }
        });
        disposables.add(disposableObserver);
    }

    public MutableLiveData<List<ArticleModel>> getListOfArticlsLiveData() {
        return listOfArticlsLiveData;
    }

    public MutableLiveData<Throwable> getErrorLiveData() {
        return errorLiveData;
    }

    public MutableLiveData<Boolean> getLoadingLiveData() {
        return loadingLiveData;
    }


}

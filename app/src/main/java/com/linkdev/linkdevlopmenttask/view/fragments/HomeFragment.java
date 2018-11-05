package com.linkdev.linkdevlopmenttask.view.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.linkdev.linkdevlopmenttask.R;
import com.linkdev.linkdevlopmenttask.model.response.ArticleModel;
import com.linkdev.linkdevlopmenttask.view.activities.MainActivity;
import com.linkdev.linkdevlopmenttask.view.adapter.ArticleViewListener;
import com.linkdev.linkdevlopmenttask.view.adapter.ArticlesAdapter;
import com.linkdev.linkdevlopmenttask.view_model.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ahmed on 11/3/18.
 */

public class HomeFragment extends BaseFragment<HomeViewModel> implements ArticleViewListener {

    @BindView(R.id.tv_error)
    TextView tv_error;
    @BindView(R.id.articles_recycler_view)
    RecyclerView articlesRecycler;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private List<ArticleModel> articlesList = new ArrayList<>();
    private ArticlesAdapter articlesAdapter;

    @Override
    protected void initializeViewModel() {
        baseViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        articlesAdapter = new ArticlesAdapter(getActivity(), this);
        articlesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        articlesRecycler.setAdapter(articlesAdapter);

    }

    @Override
    protected int getViewLayoutId() {
        return R.layout.fragment_home;
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected void subscribeLiveData() {
        super.subscribeLiveData();
        baseViewModel.getErrorLiveData().observe(this, this::displayError);
        baseViewModel.getListOfArticlsLiveData().observe(this, this::updateAdapter);
        baseViewModel.getLoadingLiveData().observe(this, this::changeProgressBarState);
    }

    private void updateAdapter(List<ArticleModel> articlesList) {
        this.articlesList = articlesList;
        notifyAdapter();
    }

    private void notifyAdapter() {
        articlesRecycler.setVisibility(View.VISIBLE);
        tv_error.setVisibility(View.GONE);
        articlesAdapter.setData(articlesList);
        articlesAdapter.notifyDataSetChanged();
    }

    private void displayError(Throwable throwable) {
        articlesRecycler.setVisibility(View.GONE);
        tv_error.setVisibility(View.VISIBLE);
        tv_error.setText(!TextUtils.isEmpty(throwable.getMessage()) ? throwable.getMessage() : "Error");

    }

    private void changeProgressBarState(boolean show) {
        if (progressBar != null) {
            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void onItemClicked(ArticleModel articleModel) {

         addFragment(ArticleDetailsFragment.newInstance(articleModel) ,R.id.container , true , 0 , 0 );

        ((MainActivity)getActivity()).updateActionBar(true);
    }
}

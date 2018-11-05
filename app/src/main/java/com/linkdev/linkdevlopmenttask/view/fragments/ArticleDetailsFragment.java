package com.linkdev.linkdevlopmenttask.view.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.linkdev.linkdevlopmenttask.R;
import com.linkdev.linkdevlopmenttask.model.response.ArticleModel;
import com.linkdev.linkdevlopmenttask.view_model.DetailsViewModel;
import com.linkdev.linkdevlopmenttask.view_model.HomeViewModel;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ahmed on 11/4/18.
 */

public class ArticleDetailsFragment extends BaseFragment<DetailsViewModel> {

    private static final String ARTICLE_MODEL_PLACE_HOLDER = "articleModel";
    private ArticleModel articleModel;


    @BindView(R.id.img_article_photo)
    ImageView imgArticlePhoto;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    @BindView(R.id.tv_desc)
    TextView tvDesc;

    @Override
    protected void initializeViewModel() {
        baseViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);

    }

    public static ArticleDetailsFragment newInstance(ArticleModel articleModel) {
        ArticleDetailsFragment fragment = new ArticleDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARTICLE_MODEL_PLACE_HOLDER, articleModel);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.articleModel = (ArticleModel) getArguments().getSerializable(ARTICLE_MODEL_PLACE_HOLDER);
        }

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        updateUi();
    }

    private void updateUi() {
        if (articleModel != null) {
            if (!TextUtils.isEmpty(articleModel.getUrlToImage())) {
                Picasso.with(getActivity()).load(articleModel.getUrlToImage()).placeholder(R.drawable.ic_place_holder).error(R.drawable.ic_place_holder).into(imgArticlePhoto);
            }
            tvDate.setText(baseViewModel.getsecificDateFormat(articleModel.getPublishedAt()));
           tvAuthor.setText(!TextUtils.isEmpty(articleModel.getAuthor()) ? getString(R.string.by)+articleModel.getAuthor() : "");
           tvTitle.setText(!TextUtils.isEmpty(articleModel.getTitle()) ? articleModel.getTitle() : "");
           tvDesc.setText(!TextUtils.isEmpty(articleModel.getDescription()) ? articleModel.getDescription() : "");
        }
    }

    @Override
    protected int getViewLayoutId() {
        return R.layout.fragment_article_details;
    }

    @OnClick(R.id.btn_site)
    public void onOpenSiteClicked() {
        if (articleModel != null && !TextUtils.isEmpty(articleModel.getUrl())) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(articleModel.getUrl()));
            startActivity(intent);

        }
    }
}

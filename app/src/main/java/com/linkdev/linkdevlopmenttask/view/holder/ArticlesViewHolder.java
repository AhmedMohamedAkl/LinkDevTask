package com.linkdev.linkdevlopmenttask.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.linkdev.linkdevlopmenttask.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ArticlesViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_title)
    TextView tvtArticleTitle;
    @BindView(R.id.tv_author)
    TextView tvArticleAuthor;
    @BindView(R.id.tv_date)
    TextView tvArticleDate;
    @BindView(R.id.img_article_photo)
    ImageView imgArticlePhoto;
    @BindView(R.id.article_view)
    LinearLayout item;

    public ArticlesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public LinearLayout getItem() {
        return item;
    }

    public TextView getTvtArticleTitle() {
        return tvtArticleTitle;
    }

    public TextView getTvArticleAuthor() {
        return tvArticleAuthor;
    }

    public TextView getTvArticleDate() {
        return tvArticleDate;
    }

    public ImageView getImgArticlePhoto() {
        return imgArticlePhoto;
    }
}

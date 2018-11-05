package com.linkdev.linkdevlopmenttask.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linkdev.linkdevlopmenttask.R;
import com.linkdev.linkdevlopmenttask.model.response.ArticleModel;
import com.linkdev.linkdevlopmenttask.view.holder.ArticlesViewHolder;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.linkdev.linkdevlopmenttask.utils.AppConstants.DISPLAY_DATE_FORMAT;
import static com.linkdev.linkdevlopmenttask.utils.AppConstants.TIMESTAMP_FORMAT;


public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesViewHolder> {

    private Context context;
    private List<ArticleModel> articleModelList = new ArrayList<>();
    private ArticleViewListener listener;

    public ArticlesAdapter(Context context, ArticleViewListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setData(List<ArticleModel> articleModels) {
        this.articleModelList.clear();
        this.articleModelList.addAll(articleModels);

    }

    @Override
    public ArticlesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArticlesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_article, parent, false));
    }

    @Override
    public void onBindViewHolder(ArticlesViewHolder holder, int position) {
        ArticleModel articleModel = articleModelList.get(position);

        if (!TextUtils.isEmpty(articleModel.getUrlToImage())) {
            Picasso.with(context).load(articleModel.getUrlToImage()).placeholder(R.drawable.ic_place_holder).error(R.drawable.ic_place_holder).into(holder.getImgArticlePhoto());
        }
        holder.getTvArticleDate().setText(getsecificDateFormat(articleModel.getPublishedAt()));
        holder.getTvArticleAuthor().setText(!TextUtils.isEmpty(articleModel.getAuthor()) ? context.getString(R.string.by)+articleModel.getAuthor() : "");
        holder.getTvtArticleTitle().setText(!TextUtils.isEmpty(articleModel.getTitle()) ? articleModel.getTitle() : "");


        holder.getItem().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(articleModel);
            }
        });
    }


    @Override
    public int getItemCount() {
        return articleModelList.size();
    }

    private String getsecificDateFormat(String dateString) {
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

package com.linkdev.linkdevlopmenttask.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ahmed on 11/4/18.
 */

public class ArticalsModel {

    @SerializedName("status")
    private String status;
    @SerializedName("source")
    private String source;
    @SerializedName("articles")
    private List<ArticleModel> articalModelList;

    public String getStatus() {
        return status;
    }

    public String getSource() {
        return source;
    }

    public List<ArticleModel> getArticalModelList() {
        return articalModelList;
    }
}

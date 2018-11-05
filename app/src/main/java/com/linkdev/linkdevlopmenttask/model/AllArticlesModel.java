package com.linkdev.linkdevlopmenttask.model;

import com.linkdev.linkdevlopmenttask.model.response.ArticleModel;
import com.linkdev.linkdevlopmenttask.model.response.ArticalsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 11/4/18.
 */

public class AllArticlesModel {

    private List<ArticleModel> allArticalsList = new ArrayList<>();

    public AllArticlesModel(ArticalsModel nextWebArticlesModel, ArticalsModel nassociatedArticlesModel) {
        allArticalsList.clear();
        if (nextWebArticlesModel != null && nextWebArticlesModel.getArticalModelList() != null && nextWebArticlesModel.getArticalModelList().size() > 0) {
            allArticalsList.addAll(nextWebArticlesModel.getArticalModelList());
        }

        if (nassociatedArticlesModel != null && nassociatedArticlesModel.getArticalModelList() != null && nassociatedArticlesModel.getArticalModelList().size() > 0) {
            allArticalsList.addAll(nassociatedArticlesModel.getArticalModelList());
        }
    }


    public List<ArticleModel> getAllArticalsList() {
        return allArticalsList;
    }
}

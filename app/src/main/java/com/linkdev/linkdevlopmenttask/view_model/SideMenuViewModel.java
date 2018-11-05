package com.linkdev.linkdevlopmenttask.view_model;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.linkdev.linkdevlopmenttask.R;
import com.linkdev.linkdevlopmenttask.model.SideMenuModel;
import com.linkdev.linkdevlopmenttask.model.response.ArticleModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 11/5/18.
 */

public class SideMenuViewModel extends BaseViewModel {


    @Override
    public void start() {

    }

    public List<SideMenuModel> getMenuSectionList(Context  context){
          List<SideMenuModel> menuModelList = new ArrayList<>();
          menuModelList.add(new SideMenuModel(context.getString(R.string.explore) , R.drawable.ic_explor , true));
          menuModelList.add(new SideMenuModel(context.getString(R.string.live_chat) , R.drawable.ic_live , false));
          menuModelList.add(new SideMenuModel(context.getString(R.string.gallery) , R.drawable.ic_gallery , false));
          menuModelList.add(new SideMenuModel(context.getString(R.string.wish_list) , R.drawable.ic_wishlist , false));
          menuModelList.add(new SideMenuModel(context.getString(R.string.e_magazone) , R.drawable.ic_e_magazine , false));

          return menuModelList;


    }
}

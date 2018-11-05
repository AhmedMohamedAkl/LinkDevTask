package com.linkdev.linkdevlopmenttask.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.linkdev.linkdevlopmenttask.R;
import com.linkdev.linkdevlopmenttask.model.SideMenuModel;
import com.linkdev.linkdevlopmenttask.model.response.ArticleModel;
import com.linkdev.linkdevlopmenttask.view.holder.SideMenuViewHolder;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.linkdev.linkdevlopmenttask.utils.AppConstants.DISPLAY_DATE_FORMAT;
import static com.linkdev.linkdevlopmenttask.utils.AppConstants.TIMESTAMP_FORMAT;


public class SideMenuAdapter extends RecyclerView.Adapter<SideMenuViewHolder> {

    private Context context;
    private List<SideMenuModel> sideMenuList;
    private SideMenuModel oldSideMenuModel;
    private SideMenuViewHolder oldHolder;

    public SideMenuAdapter(Context context, List<SideMenuModel> sideMenuList) {
        this.context = context;
        this.sideMenuList = sideMenuList;
    }


    @Override
    public SideMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SideMenuViewHolder(LayoutInflater.from(context).inflate(R.layout.item_side_menu, parent, false));
    }

    @Override
    public void onBindViewHolder(SideMenuViewHolder holder, int position) {
        SideMenuModel model = sideMenuList.get(position);
        holder.getImgSectionIcon().setImageResource(model.getIcon());
        holder.getTvSectionTitle().setText(model.getTitle());
        if (model.isSelected()) {
            holder.getImgSelector().setVisibility(View.VISIBLE);
            this.oldHolder = holder;
            this.oldSideMenuModel = model;
        } else {
            holder.getImgSelector().setVisibility(View.INVISIBLE);

        }
        holder.getItem().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldHolder.getImgSelector().setVisibility(View.INVISIBLE);
                oldSideMenuModel.setSelected(false);
                model.setSelected(true);
                notifyDataSetChanged();
                Toast.makeText(context, model.getTitle() + context.getString(R.string.section), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return sideMenuList.size();
    }


}

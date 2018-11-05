package com.linkdev.linkdevlopmenttask.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.linkdev.linkdevlopmenttask.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SideMenuViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_section_title)
    TextView tvSectionTitle;
    @BindView(R.id.img_section_icon)
    ImageView imgSectionIcon;
    @BindView(R.id.img_selector)
    ImageView imgSelector;
    @BindView(R.id.item)
    RelativeLayout item;

    public SideMenuViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public TextView getTvSectionTitle() {
        return tvSectionTitle;
    }

    public ImageView getImgSectionIcon() {
        return imgSectionIcon;
    }

    public RelativeLayout getItem() {
        return item;
    }

    public ImageView getImgSelector() {
        return imgSelector;
    }
}

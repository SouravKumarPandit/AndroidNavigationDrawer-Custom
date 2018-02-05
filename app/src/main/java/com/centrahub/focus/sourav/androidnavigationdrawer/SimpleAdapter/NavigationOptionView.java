/*
 * Copyright 2018 Sourav Kumar Pandit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.centrahub.focus.sourav.androidnavigationdrawer.SimpleAdapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.centrahub.focus.sourav.androidnavigationdrawer.MainActivity;
import com.centrahub.focus.sourav.androidnavigationdrawer.R;
import com.centrahub.focus.sourav.androidnavigationdrawer.dto.CLOptionDTO;
import com.centrahub.focus.sourav.androidnavigationdrawer.dto.SelectableItem;

import java.util.ArrayList;

/**
 * Created by sourav on 14-Aug-17.
 */

public class NavigationOptionView extends RecyclerView.Adapter<NavigationOptionView.ViewHolder> {
    Context context;
    ArrayList<CLOptionDTO> clTestDTOArrayList;

    public NavigationOptionView(Context context, ArrayList<CLOptionDTO> clTestDTOArrayList) {
        this.clTestDTOArrayList = clTestDTOArrayList;
        this.context = context;
    }
    @Override
    public NavigationOptionView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = getSlimView();
        ImageView img = (ImageView) v.findViewById(R.id.nav_optionIcon);
        CheckedTextView  textViewName =  v.findViewById(R.id.text_name);
        ViewHolder viewHolder = new ViewHolder(v, img, textViewName);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(NavigationOptionView.ViewHolder holder, int position) {
        holder.textViewName.setText(clTestDTOArrayList.get(position).getOptionLable());
        holder.imageView.setImageDrawable(context.getResources().getDrawable(clTestDTOArrayList.get(position).getOptionImage()));
        if (position % 2 == 1){
            holder.lineaoption.setBackgroundColor(context.getResources().getColor(R.color.drawer_background2));
            holder.lineaoption.setId(404+position);
        }

    }

    @Override
    public int getItemCount() {
        return clTestDTOArrayList.size();
    }

    public LinearLayout getSlimView() {
        LinearLayout listOptionIndividual = new LinearLayout(context);
        listOptionIndividual.setGravity(Gravity.CENTER);
        listOptionIndividual.setOrientation(LinearLayout.VERTICAL);
        AbsListView.LayoutParams listOptionIndividualParam = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, MainActivity.getScreenWidth() / 4);
        listOptionIndividual.setLayoutParams(listOptionIndividualParam);

        ImageView iconTabView = new ImageView(context);
        iconTabView.setScaleType(ImageView.ScaleType.CENTER);
        LinearLayout.LayoutParams iconTabViewParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        iconTabView.setLayoutParams(iconTabViewParam);
        iconTabView.setId(R.id.nav_optionIcon);

        CheckedTextView iconTabText = new CheckedTextView (context);
        iconTabText.setTextColor(Color.WHITE);
        iconTabText.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        iconTabText.setGravity(Gravity.CENTER);
        iconTabText.setTextSize(12);
        LinearLayout.LayoutParams iconTabTextParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        iconTabText.setLayoutParams(iconTabTextParam);
        iconTabText.setId(R.id.text_name);

        listOptionIndividual.addView(iconTabView);
        listOptionIndividual.addView(iconTabText);
        return listOptionIndividual;

    }
    public class SelectableViewHolder extends RecyclerView.ViewHolder {

        public static final int MULTI_SELECTION = 2;
        public static final int SINGLE_SELECTION = 1;

        public ImageView imageView;
        public CheckedTextView  textViewName;
        public View lineaoption;

        CheckedTextView textView;
        SelectableItem mItem;
        OnItemSelectedListener itemSelectedListener;


        public SelectableViewHolder(View view, OnItemSelectedListener listener) {
            super(view);
            itemSelectedListener = listener;
            textView = (CheckedTextView) view.findViewById(R.id.text_name);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (mItem.isSelected() && getItemViewType() == MULTI_SELECTION) {
                        setChecked(false);
                    } else {
                        setChecked(true);
                    }
                    itemSelectedListener.onItemSelected(mItem);

                }
            });
        }

        public void setChecked(boolean value) {
            if (value) {
                textView.setBackgroundColor(Color.LTGRAY);
            } else {
                textView.setBackground(null);
            }
            mItem.setSelected(value);
            textView.setChecked(value);
        }


    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public CheckedTextView  textViewName;
        public View lineaoption;


        public ViewHolder(View v, ImageView img, CheckedTextView  itemView) {
            super(v);
            imageView = img;
            textViewName = itemView;
            lineaoption = v;
        }


    }

    public interface OnItemSelectedListener {

        void onItemSelected(SelectableItem item);
    }
}

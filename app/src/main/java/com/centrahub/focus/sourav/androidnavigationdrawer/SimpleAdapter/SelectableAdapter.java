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
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.centrahub.focus.sourav.androidnavigationdrawer.MainActivity;
import com.centrahub.focus.sourav.androidnavigationdrawer.R;
import com.centrahub.focus.sourav.androidnavigationdrawer.dto.CLOptionDTO;
import com.centrahub.focus.sourav.androidnavigationdrawer.dto.SelectableItem;

import java.util.ArrayList;
import java.util.List;

public class SelectableAdapter extends RecyclerView.Adapter implements SelectableViewHolder.OnItemSelectedListener {

    private final List<SelectableItem> mValues;
    private final Context clContext;
    private boolean isMultiSelectionEnabled = false;
    SelectableViewHolder.OnItemSelectedListener listener;

    public SelectableAdapter(Context context, SelectableViewHolder.OnItemSelectedListener listener,
                             List<CLOptionDTO> items, boolean isMultiSelectionEnabled) {
        this.clContext = context;
        this.listener = listener;
        this.isMultiSelectionEnabled = isMultiSelectionEnabled;

        mValues = new ArrayList<>();
        for (int i = 0; items.size() > i; i++) {
            CLOptionDTO item = items.get(i);
            if (i == 0)
                mValues.add(new SelectableItem(item, true));
            else
                mValues.add(new SelectableItem(item, false));
        }
    }

    @Override
    public SelectableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = getSlimView();
//                = LayoutInflater.from(parent.getContext()).inflate(R.layout.checked_item, parent, false);

        return new SelectableViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        SelectableViewHolder holder = (SelectableViewHolder) viewHolder;
        SelectableItem selectableItem = mValues.get(position);
        String name = selectableItem.getOptionLable();

        holder.imageView.setBackground(clContext.getResources().getDrawable(mValues.get(position).getOptionImage()));
//        holder.imageView.setBackground(clContext.getResources().getDrawable(selectableItem.getOptionImage()));
//        holder.imageView.setImageDrawable(context.getResources().getDrawable(clTestDTOArrayList.get(position).getOptionImage()));
        if (position % 2 == 1)
            holder.llLinear.setBackgroundColor(clContext.getResources().getColor(R.color.drawer_background2));

        holder.textView.setText(name);
        if (isMultiSelectionEnabled) {
            TypedValue value = new TypedValue();
            //            uncomment below line if want to change text bg

//            holder.textView.getContext().getTheme().resolveAttribute(android.R.attr.listChoiceIndicatorMultiple, value, true);
            int checkMarkDrawableResId = value.resourceId;
//            holder.textView.setCheckMarkDrawable(checkMarkDrawableResId);


        } else {
            TypedValue value = new TypedValue();
//            uncomment below line if want to change text bg
//            holder.textView.getContext().getTheme().resolveAttribute(android.R.attr.listChoiceIndicatorSingle, value, true);
            int checkMarkDrawableResId = value.resourceId;
//            holder.textView.setCheckMarkDrawable(checkMarkDrawableResId);
        }

        holder.mItem = selectableItem;
        holder.setChecked(holder.mItem.isSelected());
        if (holder.mItem.isSelected()) {
            holder.llLinear.setBackgroundColor(clContext.getResources().getColor(R.color.bg_screen1));

        } else {
            if (position % 2 == 1) 
                holder.llLinear.setBackgroundColor(clContext.getResources().getColor(R.color.drawer_background1));
                else
                holder.llLinear.setBackgroundColor(clContext.getResources().getColor(R.color.drawer_background2));

        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public List<CLOptionDTO> getSelectedItems() {

        List<CLOptionDTO> selectedItems = new ArrayList<>();
        for (SelectableItem item : mValues) {
            if (item.isSelected()) {
                selectedItems.add(item);
            }
        }
        return selectedItems;
    }

    @Override
    public int getItemViewType(int position) {
        if (isMultiSelectionEnabled) {
            return SelectableViewHolder.MULTI_SELECTION;
        } else {
            return SelectableViewHolder.SINGLE_SELECTION;
        }
    }

    @Override
    public void onItemSelected(SelectableItem item) {
        if (!isMultiSelectionEnabled) {
            for (SelectableItem selectableItem : mValues) {
                if (!selectableItem.equals(item)
                        && selectableItem.isSelected()) {
                    selectableItem.setSelected(false);
                } else if (selectableItem.equals(item)
                        && item.isSelected()) {
                    selectableItem.setSelected(true);
                }
            }
            notifyDataSetChanged();
        }
        listener.onItemSelected(item);
    }

    public LinearLayout getSlimView() {
        LinearLayout listOptionIndividual = new LinearLayout(clContext);
        listOptionIndividual.setGravity(Gravity.CENTER);
        listOptionIndividual.setId(R.id.listLinear);
        listOptionIndividual.setOrientation(LinearLayout.VERTICAL);
        AbsListView.LayoutParams listOptionIndividualParam = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, MainActivity.getScreenWidth() / 4);
        listOptionIndividual.setLayoutParams(listOptionIndividualParam);

        ImageView iconTabView = new ImageView(clContext);
        iconTabView.setScaleType(ImageView.ScaleType.FIT_XY);
        LinearLayout.LayoutParams iconTabViewParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        iconTabView.setLayoutParams(iconTabViewParam);
        iconTabView.setId(R.id.nav_optionIcon);

        TextView iconTabText = new TextView(clContext);
        iconTabText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        iconTabText.setTextColor(Color.WHITE);
        iconTabText.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        iconTabText.setGravity(Gravity.CENTER);
        iconTabText.setPadding(0,5,0,2);
        iconTabText.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
        LinearLayout.LayoutParams iconTabTextParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        iconTabText.setLayoutParams(iconTabTextParam);
        iconTabText.setId(R.id.text_name);

        listOptionIndividual.addView(iconTabView);
        listOptionIndividual.addView(iconTabText);
        return listOptionIndividual;

    }
}
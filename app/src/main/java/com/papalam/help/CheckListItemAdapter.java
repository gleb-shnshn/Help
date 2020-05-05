package com.papalam.help;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.papalam.help.model.CheckListItem;

import java.util.ArrayList;

public class CheckListItemAdapter extends RecyclerView.Adapter<CheckListItemAdapter.ViewHolder> {
    ArrayList<CheckListItem> items;
    LayoutInflater inflater;

    CheckListItemAdapter(Context context, ArrayList<CheckListItem> items) {
        inflater = LayoutInflater.from(context);
        this.items = items;
    }

    public void addItem(CheckListItem item) {
        items.add(item);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.layout_checklist_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CheckListItem item = items.get(position);
        holder.title.setText(item.getTitle());
        holder.info.setText(item.getDescription());
        int drawable = 0;
        switch (item.getType()) {
            case CheckListItem.TYPE_WATER:
                drawable = R.drawable.ic_water;
                break;
            case CheckListItem.TYPE_MEDIC:
                drawable = R.drawable.ic_medic;
                break;
            case CheckListItem.TYPE_FOOD:
                drawable = R.drawable.ic_food;
                break;
        }
        holder.item = item;
        holder.check(item.isChecked());
        holder.logo.setImageDrawable(inflater.getContext().getResources().getDrawable(drawable));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, info;
        ImageView logo;
        CheckBox checkBox;
        CheckListItem item;

        public ViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.title);
            info = view.findViewById(R.id.info);
            logo = view.findViewById(R.id.logo);
            checkBox = view.findViewById(R.id.checkBox);
            view.setOnClickListener(this);
            checkBox.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            check(!item.isChecked());
            item.setChecked(!item.isChecked());
        }


        public void check(boolean checked) {
            checkBox.setChecked(checked);
            if (!checkBox.isChecked()) {
                title.setPaintFlags(Paint.LINEAR_TEXT_FLAG);
                info.setPaintFlags(Paint.LINEAR_TEXT_FLAG);
            } else {
                title.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                info.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            }
        }
    }
}

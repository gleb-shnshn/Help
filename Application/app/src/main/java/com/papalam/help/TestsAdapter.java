package com.papalam.help;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.papalam.help.model.Test;

import java.util.ArrayList;

public class TestsAdapter extends RecyclerView.Adapter<TestsAdapter.ViewHolder> {
    ArrayList<Test> tests;
    LayoutInflater inflater;


    TestsAdapter(Context context, ArrayList<Test> tests) {
        inflater = LayoutInflater.from(context);
        this.tests = tests;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.layout_contact, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Test test = tests.get(position);
        holder.name.setText(test.getName());
        holder.description.setText(test.getDescription());
        holder.formId = test.getId();
        Glide.with(inflater.getContext()).load(test.getIcon()).into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return tests.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, description;
        ImageView icon;
        int formId;

        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            description = view.findViewById(R.id.description);
            icon = view.findViewById(R.id.icon);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) (inflater.getContext())).setFragment(name.getText().toString(), new TestFragment(formId));
                }
            });
        }

    }
}
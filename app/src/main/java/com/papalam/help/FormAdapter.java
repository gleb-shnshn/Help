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

import java.util.ArrayList;

public class FormAdapter extends RecyclerView.Adapter<FormAdapter.ViewHolder> {
    ArrayList<Form> forms;
    LayoutInflater inflater;


    FormAdapter(Context context, ArrayList<Form> forms) {
        inflater = LayoutInflater.from(context);
        this.forms = forms;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.layout_contact, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Form form = forms.get(position);
        holder.name.setText(form.getFormName());
        holder.description.setText(form.getDescription());
        holder.formId = form.getFormId();
        Glide.with(inflater.getContext()).load(form.getIcon()).into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return forms.size();
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

                }
            });
        }

    }
}
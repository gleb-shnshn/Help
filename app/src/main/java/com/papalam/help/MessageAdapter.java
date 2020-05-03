package com.papalam.help;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    ArrayList<Message> messages;
    LayoutInflater inflater;
    String[] colors = {"#00c13f",
            "#56c5ff",
            "#ff76bc",
            "#81ecec",
            "#00b894",
            "#0984e3",
            "#74b9ff",
            "#55efc4",
            "#6c5ce7",
            "#e17055",
            "#fd79a8",
            "#fdcb6e",
            "#a29bfe"};

    MessageAdapter(Context context, ArrayList<Message> messages) {
        inflater = LayoutInflater.from(context);
        this.messages = messages;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessageAdapter.ViewHolder(inflater.inflate(R.layout.layout_message, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.name.setText(message.name);
        holder.text.setText(message.msg);
        String[] shorted = message.name.split(" ");
        holder.logo.setText(shorted[0].charAt(0) + "" + shorted[1].charAt(0));
        ShapeDrawable sd = new ShapeDrawable();
        sd.setShape(new OvalShape());
        sd.setIntrinsicHeight(100);
        sd.setIntrinsicWidth(100);
        Random rand = new Random();
        int clr = Color.parseColor(colors[message.id % colors.length]);
        sd.getPaint().setColor(clr);
        holder.logo.setBackground(sd);
        holder.name.setTextColor(clr);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, text;
        TextView logo;

        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            text = view.findViewById(R.id.text);
            logo = view.findViewById(R.id.logo);
        }
    }
}

package com.papalam.help;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.papalam.help.model.Message;

import java.util.ArrayList;

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
        return new MessageAdapter.ViewHolder(inflater.inflate(R.layout.layout_msg, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        Message message = messages.get(position);
        if (message.isMine()) {
            holder.text1.setText(message.getMsg());
            holder.lay1.setVisibility(View.VISIBLE);
            holder.lay2.setVisibility(View.GONE);
        } else {
            holder.name.setText(message.getName());
            holder.text2.setText(message.getMsg());
            String[] shorted = message.getName().split(" ");
            holder.logo.setText(shorted[0].charAt(0) + "" + shorted[1].charAt(0));
            int clr = Color.parseColor(colors[message.getId() % colors.length]);
            if (message.getBd() == null) {
                ShapeDrawable sd = new ShapeDrawable();
                sd.setShape(new OvalShape());
                sd.setIntrinsicHeight(100);
                sd.setIntrinsicWidth(100);
                sd.getPaint().setColor(clr);
                message.setBd(sd);
            }
            holder.logo.setBackground(message.getBd());
            holder.name.setTextColor(clr);
            holder.lay2.setVisibility(View.VISIBLE);
            holder.lay1.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, text1, text2;
        TextView logo;
        LinearLayout lay1, lay2;

        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            text1 = view.findViewById(R.id.text1);
            text2 = view.findViewById(R.id.text2);
            lay1 = view.findViewById(R.id.lay1);
            lay2 = view.findViewById(R.id.lay2);
            logo = view.findViewById(R.id.logo);
        }
    }
}

package com.papalam.help;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    ArrayList<Question> questions;
    LayoutInflater inflater;

    QuestionAdapter(Context context, ArrayList<Question> questions) {
        this.questions = questions;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.layout_question, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.ViewHolder holder, int position) {
        Question question = questions.get(position);
        holder.name.setText(question.getQuestionText());
        for (String choice : question.choices) {
            RadioButton newRadioButton = new RadioButton(inflater.getContext());
            newRadioButton.setText(choice);
            newRadioButton.setTextSize(17f);
            newRadioButton.setTypeface(ResourcesCompat.getFont(inflater.getContext(), R.font.gilroyl));
            holder.radioGroup.addView(newRadioButton);
        }
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RadioGroup radioGroup;
        TextView name;

        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            radioGroup = view.findViewById(R.id.radio_group);
        }
    }
}

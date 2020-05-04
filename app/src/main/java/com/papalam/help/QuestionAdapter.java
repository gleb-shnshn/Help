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

import com.papalam.help.model.Question;

import java.util.ArrayList;
import java.util.HashMap;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    ArrayList<Question> questions;
    LayoutInflater inflater;
    HashMap<Integer, Integer> idMapping = new HashMap<>();

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
    public void onBindViewHolder(@NonNull final QuestionAdapter.ViewHolder holder, int position) {
        final Question question = questions.get(position);
        holder.name.setText(question.getQuestionText());
        for (int i = 0; i < question.getChoices().size(); i++) {
            String choice = question.getChoices().get(i);
            RadioButton newRadioButton = new RadioButton(inflater.getContext());
            newRadioButton.setId(View.generateViewId());
            idMapping.put(newRadioButton.getId(), i);
            newRadioButton.setText(choice);
            newRadioButton.setHint("" + i);
            newRadioButton.setTextSize(17f);
            newRadioButton.setTypeface(ResourcesCompat.getFont(inflater.getContext(), R.font.gilroyl));
            holder.radioGroup.addView(newRadioButton);
            holder.radioGroup.clearCheck();
            if (question.getCheckedId() != -1) {
                holder.radioGroup.check(question.getCheckedId());
            }
            holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    question.setCheckedId(group.getCheckedRadioButtonId());
                }
            });
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

    public int getSum() {
        int sum = 0;
        for (Question question : questions) {
            if (question.getCheckedId() == -1)
                return -1;
            else
                sum += idMapping.get(question.getCheckedId());
        }
        return sum;
    }
}

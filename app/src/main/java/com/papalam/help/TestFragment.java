package com.papalam.help;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestFragment extends Fragment {
    TextView name;
    RecyclerView questionView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        questionView = view.findViewById(R.id.question_view);
        name = view.findViewById(R.id.name);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ArrayList<Question> questions = new ArrayList<>();
        List<String> choices = Arrays.asList("Да", "Нет");
        List<Integer> scores = Arrays.asList(0, 1);
        questions.add(new Question(0,
                "Сколько вы зарабатываете?",
                "radio", choices, scores));
        questions.add(new Question(0,
                "Сколько вы зарабатываете?",
                "radio", choices, scores));
        questions.add(new Question(0,
                "Сколько вы зарабатываете?",
                "radio", choices, scores));
        QuestionAdapter questionAdapter = new QuestionAdapter(getContext(), questions);
        questionView.setAdapter(questionAdapter);
        questionView.setLayoutManager(new LinearLayoutManager(getContext()));
        super.onActivityCreated(savedInstanceState);
    }
}


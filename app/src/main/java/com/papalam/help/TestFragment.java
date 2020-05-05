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

import com.papalam.help.helpers.Errorer;
import com.papalam.help.model.Test;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestFragment extends Fragment implements View.OnClickListener {
    TextView name;
    RecyclerView questionView;
    QuestionAdapter questionAdapter;
    int id;

    TestFragment(int id) {
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        questionView = view.findViewById(R.id.question_view);
        name = view.findViewById(R.id.name);
        view.findViewById(R.id.approve).setOnClickListener(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        App.getInstance().getRetrofit().getTestById(id).enqueue(new Callback<Test>() {
            @Override
            public void onResponse(Call<Test> call, Response<Test> response) {
                questionAdapter = new QuestionAdapter(getContext(), response.body().getQuestions());
                questionView.setAdapter(questionAdapter);
                questionView.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<Test> call, Throwable t) {
                App.getInstance().getUtils().showError(Errorer.NO_INTERNET_CONNECTION);
            }
        });
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        int sum = questionAdapter.getSum();
        if (sum == -1) {
            App.getInstance().getUtils().showError(Errorer.CHECK_ALL_FIELDS);
        }
    }
}


package com.papalam.help;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.papalam.help.helpers.Errorer;
import com.papalam.help.responses.TestResultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestResultFragment extends Fragment {
    ImageView image;
    TextView text;
    int id;
    int sum;

    TestResultFragment(int id, int sum) {
        this.id = id;
        this.sum = sum;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        image = view.findViewById(R.id.image);
        text = view.findViewById(R.id.text);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        App.getInstance().getRetrofit().getTestResult(App.getInstance().getDataHandler().getLogin(), id, sum).enqueue(new Callback<TestResultResponse>() {
            @Override
            public void onResponse(Call<TestResultResponse> call, Response<TestResultResponse> response) {
                if (response.body() == null) {
                    App.getInstance().getUtils().showError(Errorer.SERVER_ERROR);
                } else {
                    text.setText(response.body().getText());
                    Glide.with(getContext()).load(response.body().getImage()).into(image);
                }
            }

            @Override
            public void onFailure(Call<TestResultResponse> call, Throwable t) {

            }
        });
    }
}

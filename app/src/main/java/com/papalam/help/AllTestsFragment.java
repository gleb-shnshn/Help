package com.papalam.help;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllTestsFragment extends Fragment {
    RecyclerView testsView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forms, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        testsView = view.findViewById(R.id.tests_view);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        App.getInstance().getRetrofit().getTests().enqueue(new Callback<TestsResponse>() {
            @Override
            public void onResponse(Call<TestsResponse> call, Response<TestsResponse> response) {
                TestsAdapter testsAdapter = new TestsAdapter(getContext(), response.body().getTests());
                testsView.setAdapter(testsAdapter);
                testsView.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<TestsResponse> call, Throwable t) {
                App.getInstance().getUtils().showError("Нет доступа к интернету");
            }
        });
        super.onActivityCreated(savedInstanceState);
    }

}

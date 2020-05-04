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

import com.papalam.help.model.Form;

import java.util.ArrayList;

public class FormsFragment extends Fragment {
    RecyclerView formsView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forms, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        formsView = view.findViewById(R.id.forms_view);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ArrayList<Form> forms = new ArrayList<>();
        forms.add(new Form("Кто ты из аниме?", "Тест на определение персонажа, которому ты соответствуешь", "https://klike.net/uploads/posts/2019-05/1558767882_28.jpg"));
        FormAdapter formAdapter = new FormAdapter(getContext(), forms);
        formsView.setAdapter(formAdapter);
        formsView.setLayoutManager(new LinearLayoutManager(getContext()));
        super.onActivityCreated(savedInstanceState);
    }

}

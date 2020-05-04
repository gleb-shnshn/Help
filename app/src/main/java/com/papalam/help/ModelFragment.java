package com.papalam.help;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.papalam.help.model.PainPoint;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelFragment extends Fragment implements View.OnTouchListener {
    ConstraintLayout layout;
    FloatingActionButton editButton;
    boolean isBlocked = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_3d, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        view.setOnTouchListener(this);
        editButton = view.findViewById(R.id.edit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBlocked) {
                    editButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_lock));
                } else {
                    editButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_edit));
                }
                isBlocked = !isBlocked;
            }
        });
        layout = view.findViewById(R.id.lay);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        App.getInstance().getRetrofit().getPainAreas("test", "2020-05-04").enqueue(new Callback<PainAreasResponse>() {
            @Override
            public void onResponse(Call<PainAreasResponse> call, Response<PainAreasResponse> response) {
                ArrayList<PainPoint> areas = response.body().getAreas();
                for (PainPoint point : areas) {
                    addView(point.getX(), point.getY());
                }
            }

            @Override
            public void onFailure(Call<PainAreasResponse> call, Throwable t) {
                App.getInstance().getUtils().showError("Нет доступа к интернету");
            }
        });
    }

    View lastView;
    final int SIZE = 130;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (isBlocked)
            return true;
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие
                lastView = addView(x, y);
                break;
            case MotionEvent.ACTION_MOVE: // движение
                lastView.setTranslationY(y - SIZE / 2f);
                lastView.setTranslationX(x - SIZE / 2f);
                break;
            case MotionEvent.ACTION_UP: // отпускание
            case MotionEvent.ACTION_CANCEL:
                ((MainActivity) getActivity()).setFragment("Новая запись", new PainFragment());
                break;
        }
        return true;
    }

    public View addView(float x, float y) {
        View view = new View(getContext());
        view.setBackground(getResources().getDrawable(R.drawable.drawable_point));
        view.setTranslationX(x - SIZE / 2f);
        view.setTranslationY(y - SIZE / 2f);
        view.setLayoutParams(new ConstraintLayout.LayoutParams(SIZE, SIZE));
        layout.addView(view);
        return view;
    }

}


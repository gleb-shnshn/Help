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

public class ModelFragment extends Fragment implements View.OnTouchListener {
    ConstraintLayout layout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_3d, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        view.setOnTouchListener(this);
        layout = view.findViewById(R.id.lay);
        super.onViewCreated(view, savedInstanceState);
    }

    View lastView;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие
                View view = new View(getContext());
                view.setBackground(getResources().getDrawable(R.drawable.drawable_point));
                view.setTranslationX(x - 65);
                view.setTranslationY(y - 65);
                view.setLayoutParams(new ConstraintLayout.LayoutParams(130, 130));
                layout.addView(view);
                lastView = view;
                break;
            case MotionEvent.ACTION_MOVE: // движение
                lastView.setTranslationY(y - 65);
                lastView.setTranslationX(x - 65);
                break;
            case MotionEvent.ACTION_UP: // отпускание
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }
}


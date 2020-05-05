package com.papalam.help;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.papalam.help.model.CheckListItem;

import java.util.ArrayList;


public class ScheduleFragment extends Fragment implements View.OnClickListener {
    RecyclerView itemListView;
    CheckListItemAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        itemListView = view.findViewById(R.id.itemlist_view);
        view.findViewById(R.id.add).setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<CheckListItem> checkListItems = new ArrayList<>();
        checkListItems.add(new CheckListItem("11.00", "Прием обезболивающих", CheckListItem.TYPE_MEDIC));
        checkListItems.add(new CheckListItem("12.00", "Умывание", CheckListItem.TYPE_WATER));
        checkListItems.add(new CheckListItem("13.00", "Измерение температуры", CheckListItem.TYPE_MEDIC));
        checkListItems.add(new CheckListItem("15.00", "Прием противовоспалительных во время еды", CheckListItem.TYPE_FOOD));
        adapter = new CheckListItemAdapter(getActivity(), checkListItems);
        itemListView.setAdapter(adapter);
        itemListView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onClick(View v) {
        ScheduleDialog dialog = new ScheduleDialog(getActivity(), this);
        dialog.create();
        Rect displayRectangle = new Rect();
        Window window = getActivity().getWindow();

        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        dialog.getWindow().setLayout((int) (displayRectangle.width() *
                0.9f), dialog.getWindow().getAttributes().height);
        dialog.show();
    }

    public void onFinishDialog(CheckListItem item) {
        adapter.addItem(item);
    }
}

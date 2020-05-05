package com.papalam.help;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.papalam.help.model.CheckListItem;

public class ScheduleDialog extends Dialog implements View.OnClickListener {
    EditText name, description;
    ImageView logo1, logo2, logo3, selected;
    Button save;
    ScheduleFragment fragment;

    public ScheduleDialog(@NonNull Context context, ScheduleFragment fragment) {
        super(context);
        this.fragment = fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_dialog_schedule);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        logo1 = findViewById(R.id.logo1);
        logo2 = findViewById(R.id.logo2);
        logo3 = findViewById(R.id.logo3);
        save = findViewById(R.id.save);
        logo1.setOnClickListener(this);
        logo2.setOnClickListener(this);
        logo3.setOnClickListener(this);
        save.setOnClickListener(this);
        selected = logo1;
        setSelected(R.id.logo1);
    }

    public void setSelected(int id) {
        ImageView newImage;
        switch (id) {
            case R.id.logo1:
                newImage = logo1;
                break;
            case R.id.logo2:
                newImage = logo2;
                break;
            case R.id.logo3:
                newImage = logo3;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + id);
        }
        selected.setBackground(App.getInstance().getResources().getDrawable(R.drawable.item_point));
        newImage.setBackground(App.getInstance().getResources().getDrawable(R.drawable.circled_item_point));
        selected = newImage;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.save) {
            if (name.getText().toString().length() == 0 || description.getText().toString().length() == 0) {
                App.getInstance().getUtils().showError("Поля не могут быть пустыми");
            } else {
                int tag = 0;
                switch (selected.getId()) {
                    case R.id.logo1:
                        tag = CheckListItem.TYPE_MEDIC;
                        break;
                    case R.id.logo2:
                        tag = CheckListItem.TYPE_WATER;
                        break;
                    case R.id.logo3:
                        tag = CheckListItem.TYPE_FOOD;
                        break;
                }
                fragment.onFinishDialog(new CheckListItem(name.getText().toString(), description.getText().toString(), tag));
                this.hide();
            }
        } else {
            setSelected(v.getId());
        }

    }
}

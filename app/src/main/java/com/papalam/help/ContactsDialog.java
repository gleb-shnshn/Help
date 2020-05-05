package com.papalam.help;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.papalam.help.helpers.Errorer;
import com.papalam.help.model.Contact;

public class ContactsDialog extends Dialog implements View.OnClickListener {
    private EditText name, description;
    private Button save;
    private ContactsFragment fragment;

    public ContactsDialog(@NonNull Context context, ContactsFragment fragment) {
        super(context);
        this.fragment = fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_dialog_contacts);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        save = findViewById(R.id.save);
        save.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (name.getText().toString().length() == 0 || description.getText().toString().length() == 0) {
            App.getInstance().getUtils().showError(Errorer.BLANK_FIELDS);
        } else {
            fragment.onFinishDialog(new Contact(name.getText().toString(), description.getText().toString()));
            this.hide();
        }

    }
}

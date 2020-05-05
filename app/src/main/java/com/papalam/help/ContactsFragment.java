package com.papalam.help;

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

import com.papalam.help.helpers.Errorer;
import com.papalam.help.model.Contact;
import com.papalam.help.responses.ContactsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactsFragment extends Fragment implements View.OnClickListener {
    private RecyclerView contactsView;
    private ContactAdapter contactAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        contactsView = view.findViewById(R.id.contacts_view);
        view.findViewById(R.id.add).setOnClickListener(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        App.getInstance().getRetrofit().getContacts().enqueue(new Callback<ContactsResponse>() {
            @Override
            public void onResponse(@NonNull Call<ContactsResponse> call,@NonNull Response<ContactsResponse> response) {
                contactAdapter = new ContactAdapter(getActivity(), response.body().getContacts());
                contactsView.setAdapter(contactAdapter);
                contactsView.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(@NonNull Call<ContactsResponse> call, @NonNull Throwable t) {
                App.getInstance().getUtils().showError(Errorer.NO_INTERNET_CONNECTION);
            }
        });
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        ContactsDialog dialog = new ContactsDialog(getContext(), this);

        dialog.create();
        Rect displayRectangle = new Rect();
        Window window = getActivity().getWindow();

        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        dialog.getWindow().setLayout((int) (displayRectangle.width() *
                0.9f), dialog.getWindow().getAttributes().height);
        dialog.show();
    }

    public void onFinishDialog(Contact contact) {
        contactAdapter.addContact(contact);
    }
}

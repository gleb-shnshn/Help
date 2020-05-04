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

import com.papalam.help.responses.ContactsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactsFragment extends Fragment {
    RecyclerView contactsView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        contactsView = view.findViewById(R.id.contacts_view);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        App.getInstance().getRetrofit().getContacts().enqueue(new Callback<ContactsResponse>() {
            @Override
            public void onResponse(Call<ContactsResponse> call, Response<ContactsResponse> response) {
                ContactAdapter contactAdapter = new ContactAdapter(getActivity(), response.body().getContacts());
                contactsView.setAdapter(contactAdapter);
                contactsView.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<ContactsResponse> call, Throwable t) {
                App.getInstance().getUtils().showError("Нет доступа к интернету" + t.toString());
            }
        });
        super.onActivityCreated(savedInstanceState);
    }
}

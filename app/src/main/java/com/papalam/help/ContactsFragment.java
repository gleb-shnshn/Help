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

import java.util.ArrayList;

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
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Горячая линия", "Номер телефона: 8-800-700-84-36", "https://upload.wikimedia.org/wikipedia/ru/thumb/e/e2/Vera_black_dan_copy.jpg/375px-Vera_black_dan_copy.jpg"));
        contacts.add(new Contact("Телефон доверия", "Номер телефона: 8-800-2000-122", "https://telefon-doveria.ru/wp-content/uploads/2016/08/logo.png"));
        ContactAdapter contactAdapter = new ContactAdapter(getActivity(), contacts);
        contactsView.setAdapter(contactAdapter);
        contactsView.setLayoutManager(new LinearLayoutManager(getContext()));
        super.onActivityCreated(savedInstanceState);
    }
}

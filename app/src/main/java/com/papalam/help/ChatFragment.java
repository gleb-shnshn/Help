package com.papalam.help;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatFragment extends Fragment {
    RecyclerView chatView;
    ImageView sendButton;
    EditText messageInput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        chatView = view.findViewById(R.id.chat_view);
        sendButton = view.findViewById(R.id.sendButton);
        messageInput = view.findViewById(R.id.messageInput);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(new Message("Сергеева Татьяна", "А я с малышом зарабатываю 20к из дома", 0, true));
        messages.add(new Message("Людимова Диана", "А я с малышом зарабатываю 20к из дома", 1, false));
        messages.add(new Message("Витальева Виктория", "А я с малышом зарабатываю 20к из дома", 2, false));
        MessageAdapter messageAdapter = new MessageAdapter(getContext(), messages);
        chatView.setAdapter(messageAdapter);
        chatView.setLayoutManager(new LinearLayoutManager(getContext()));
        super.onActivityCreated(savedInstanceState);
    }
}

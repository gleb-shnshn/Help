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

import com.papalam.help.helpers.Errorer;
import com.papalam.help.model.Message;
import com.papalam.help.responses.DefaultResponse;
import com.papalam.help.responses.MessagesResponse;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatFragment extends Fragment implements View.OnClickListener {
    private RecyclerView chatView;
    private ImageView sendButton;
    private EditText messageInput;
    private MessageAdapter messageAdapter;

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
        messageAdapter = new MessageAdapter(getContext(), new ArrayList<Message>());
        chatView.setAdapter(messageAdapter);
        chatView.setLayoutManager(new LinearLayoutManager(getContext()));
        super.onViewCreated(view, savedInstanceState);
    }


    private void updateMessages() {
        App.getInstance().getRetrofit().getChatMessages().enqueue(new Callback<MessagesResponse>() {
            @Override
            public void onResponse(@NonNull Call<MessagesResponse> call, @NonNull Response<MessagesResponse> response) {
                if (response.body() == null) {
                    App.getInstance().getUtils().showError(Errorer.SERVER_ERROR);
                } else {
                    if (getView() != null) {
                        Collections.reverse(response.body().getMessages());
                        messageAdapter.setMessages(response.body().getMessages());
                        chatView.scrollToPosition(response.body().getMessages().size() - 1);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<MessagesResponse> call, @NonNull Throwable t) {
                App.getInstance().getUtils().showError(Errorer.NO_INTERNET_CONNECTION);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateMessages();
                            }
                        });
                    }
                }
            }

        }).start();
        sendButton.setOnClickListener(this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        String msg = messageInput.getText().toString();
        if (msg.equals("")) {
            App.getInstance().getUtils().showError(Errorer.INPUT_MESSAGE);
            return;
        }

        App.getInstance().getRetrofit().sendMessage(App.getInstance().getDataHandler().getLogin(), msg).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(@NonNull Call<DefaultResponse> call, @NonNull Response<DefaultResponse> response) {

            }

            @Override
            public void onFailure(@NonNull Call<DefaultResponse> call, @NonNull Throwable t) {
                App.getInstance().getUtils().showError(Errorer.NO_INTERNET_CONNECTION);
            }
        });
        messageInput.setText("");

    }
}

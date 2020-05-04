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

import com.papalam.help.responses.DefaultResponse;
import com.papalam.help.responses.MessagesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatFragment extends Fragment implements View.OnClickListener {
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


    public void updateMessages() {
        App.getInstance().getRetrofit().getChatMessages().enqueue(new Callback<MessagesResponse>() {
            @Override
            public void onResponse(Call<MessagesResponse> call, Response<MessagesResponse> response) {
                if (getView() != null) {
                    MessageAdapter messageAdapter = new MessageAdapter(getContext(), response.body().getMessages());
                    chatView.setAdapter(messageAdapter);
                    chatView.setLayoutManager(new LinearLayoutManager(getContext()));
                }
            }

            @Override
            public void onFailure(Call<MessagesResponse> call, Throwable t) {
                App.getInstance().getUtils().showError("Нет доступа к интернету");
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
                        Thread.sleep(1500);
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
            App.getInstance().getUtils().showError("Введите сообщение");
            return;
        }

        App.getInstance().getRetrofit().sendMessage("test", msg).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                App.getInstance().getUtils().showError("Нет доступа к интернету");
            }
        });
        messageInput.setText("");

    }
}

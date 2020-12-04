package com.papalam.help.responses;

import com.papalam.help.model.Message;

import java.util.ArrayList;

public class MessagesResponse {
    ArrayList<Message> messages;

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

}

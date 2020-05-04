package com.papalam.help.responses;

import com.papalam.help.model.Contact;

import java.util.ArrayList;

public class ContactsResponse {
    ArrayList<Contact> contacts;

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

}

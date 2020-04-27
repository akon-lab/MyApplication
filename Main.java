package com.user;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        User user = new User();
        Message message = new Message();
        Chat chat = new Chat();
        Friend friend = new Friend();
        MyApplication application = new MyApplication();

        application.menu();        //starting app
    }
}


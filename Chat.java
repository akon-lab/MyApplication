package com.user;

import java.io.*;
import java.util.*;

public class Chat extends MyApplication {
    File chatFile = new File("C:\\Users\\ak_he\\IdeaProjects\\circle\\src\\com\\user\\chat.txt");

    Scanner sc = new Scanner(System.in);
    private String sms, name;
    private int choice;

    LinkedList<Chat> chatList = new LinkedList<>();

    Message message;
    MyApplication app;
    User sender, recipient;

    private int senderId, recepientId;

    public Chat() {

    }

    public Chat(int senderId, int recepientId) {
        setRecepientId(recepientId);
        setSenderId(senderId);
    }

    public Chat(int senderId, int recepientId, String sms) {
        setRecepientId(recepientId);
        setSenderId(senderId);
        setSms(sms);
    }

    public void open(int id) throws IOException {

        setSenderId(id);
        messageList();

        System.out.println(" ");
        System.out.println("Welcome to -Chat-!");
        System.out.println("You can:");
        System.out.println("1.check message");
        System.out.println("2.send message");
        System.out.println("3.Return to main page");

        choice = sc.nextInt();
        if (choice == 1) check();
        else if (choice == 2) send();
        else if (choice == 3) app.menu();
        else {
            System.out.println("Please write correct option");
            open(id);
        }
    }

    public void open() throws IOException {

        System.out.println("You can:");
        System.out.println("1.check message");
        System.out.println("2.send message");
        System.out.println("3.Return to main page");

        choice = sc.nextInt();
        if (choice == 1) check();
        else if (choice == 2) send();
        else if (choice == 3) app.menu();
        else {
            System.out.println("Please write correct option");
            open();
        }
    }

    public void send() throws IOException {
        if (chatList.isEmpty()) {
            System.out.println("Sorry u don't have any chat");
            System.out.println("Do you wanna go back?");
            System.out.println("1.Yes");
            System.out.println("2.Exit?");
            choice = sc.nextInt();
            if (choice == 1) open();
            else if (choice == 2) System.out.println("Good bye!");
            else {
                System.out.println("Please write correct option");
                check();
            }

        } else {
            for (Chat chat : chatList) {
                System.out.println(chat.getName());
            }

            System.out.println("Print your message:");
            sms = sc.nextLine();

            message.sendMessage(senderId, recepientId, sms);
            System.out.println("Message has been send!");
        }
    }

    public void check() throws IOException {
        if (chatList.isEmpty()) {
            System.out.println("Sorry u don't have any chat");
            System.out.println("Do you wanna go back?");
            System.out.println("1.Yes");
            System.out.println("2.Exit?");
            choice = sc.nextInt();
            if (choice == 1) open();
            else if (choice == 2) System.out.println("Good bye!");
            else {
                System.out.println("Please write correct option");
                check();
            }

        } else {
            for (Chat chat : chatList) {
                System.out.println(chat.getName());
            }
        }
    }

    //set
    public void setName(String name) {

        this.name = name;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setRecepientId(int recepientId) {
        this.recepientId = recepientId;
    }

    //get
    public String getName() {
        return name;
    }

    public String getSms() {
        return sms;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getRecepientId() {
        return recepientId;
    }

    //file
    public void messageList() throws FileNotFoundException {

        Scanner sc = new Scanner(chatFile);

        while (sc.hasNext()) {
            int idSender = sc.nextInt();
            int idRecipient = sc.nextInt();
            sms = sc.next();

            if (idSender == super.getSignedUser().getId() || idRecipient == super.getSignedUser().getId()) {
                chat = new Chat(idSender, idRecipient, sms);
                chatList.add(chat);
            }

        }
    }


}

package com.user;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Message extends Chat {
    Scanner sc = new Scanner(System.in);
    private int choice;

    private ArrayList<Message> messagesList = new ArrayList<>();
    private HashMap<Integer, String> user = new HashMap<>();

    private String sms, recipientName;
    private int recipientId, senderId;

    Friend friend;

    public Message() {

    }

    public Message(int idSender, int idRecipient, String mesage) {
        setSenderId(idSender);
        setRecipientId(idRecipient);
        setSms(mesage);
    }

    public void sendFriendRequest(int idFrom, int idTo, String senderName) throws IOException {

        String friendRequest = senderName + " want be your friend";

        message = new Message(idFrom, idTo, friendRequest);
        messagesList.add(message);

        send();
    }

    public void sendMessage(int idSender) throws IOException {
        setSenderId(idSender);

        System.out.println("Choose with whom you want to chat");
        System.out.println("1.Friends");
        System.out.println("2.Smn new");
        choice = sc.nextInt();
        if (choice == 1) chatWithFriend( idSender);
        else {
            System.out.println("Sorry you can chat just with friend, so you can send friend request");
            System.out.println("Do you wanna try?");
            System.out.println("1.Yes");
            System.out.println("2.No, go back");
            choice = sc.nextInt();
            if (choice == 1)
                chatWithSmnNew();
            else super.open(senderId);
        }
    }

    public void chatWithSmnNew( ) throws IOException {
        System.out.println("Choose user:");
        for (User user : friend.getNotFriends()) {
            System.out.println(user.getUsername());
        }
        recipientName = sc.next();

        for (User user : friend.getNotFriends()) {
            if (recipientName.equals(user.getUsername())) recipientId = user.getId();
        }

        sendFriendRequest(senderId, recipientId, recipientName);
    }

    public void chatWithFriend(int idSender) throws IOException {
        System.out.println("Choose user:");
        for (User user : friend.getFriendsList()) {
            System.out.println(user.getUsername());
        }
        recipientName = sc.next();

        for (User user : friend.getFriendsList()) {
            if (recipientName.equals(user.getUsername())) {
                recipientId = user.getId();
                break;
            }
        }

        System.out.println("Write message:");
        sms = sc.nextLine();

        sendMessage(senderId, recipientId, sms);
    }

    public void sendMessage(int idSender, int idRecipient, String mesage) throws IOException {
        message = new Message(idSender, idRecipient, mesage);
        messagesList.add(message);
        send();
    }

    //set

    public void setMessagesList(ArrayList<Message> messagesList) {
        this.messagesList = messagesList;
    }

    public void setUser(HashMap<Integer, String> user) {
        this.user = user;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    //get

    public ArrayList<Message> getMessagesList() {
        return messagesList;
    }

    public HashMap<Integer, String> getUser() {
        return user;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public String getSms() {
        return sms;
    }

    //file

    public void send() throws IOException {
        String content = " ";
        for (Message message : messagesList) {
            content += getSenderId() + " " + getRecipientId() + " " + getSms() + "\n";
        }
        Files.write(Paths.get("C:\\Users\\ak_he\\IdeaProjects\\circle\\src\\com\\user\\db.txt"), content.getBytes());
    }
}


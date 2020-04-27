package com.user;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Friend extends MyApplication {
    Scanner sc = new Scanner(System.in);

    private LinkedList<User> usersList = new LinkedList<>();
    private LinkedList<User> friendsList = new LinkedList<>();
    private LinkedList<User> notFriends = new LinkedList<>();

    MyApplication app;
    Message message;

    private int choice;
    private String choosingFriendByUsername;

    public Friend() {
        notFriends();
    }

    public void open() throws IOException {
        System.out.println("-! FRIENDS !-");
        System.out.println("1.Make friend");
        System.out.println("2.Show your friends");
        System.out.println("3.Return to main page");
        choice = sc.nextInt();
        if (choice == 1) {
            makeFriend();

        } else if (choice == 2) {
            showFriends();
        } else if (choice == 3) {
            app.menu();
        } else {
            System.out.println("Please write correct option");
            open();
        }
    }

    public void showFriends() throws IOException {
        if (friendsList.isEmpty()) {
            System.out.println("You don't have friends right now");
            System.out.println("Do you wanna make one?");
            System.out.println("1.Yes");
            System.out.println("2.No, go back ");
            choice = sc.nextInt();
            if (choice == 1) makeFriend();
            else if (choice == 2) app.menu();
            else {
                System.out.println("Please write correct option");
                showFriends();
            }
        } else {
            for (User user : friendsList) {
                System.out.println(user.getUsername() + " : " + user.getFname() + " " + user.getSname());
            }
            System.out.println("Do you wanna send message?");
            System.out.println("1.Yes");
            System.out.println("2.No, go back ");

            choice = sc.nextInt();
            if (choice == 1) message.chatWithFriend(super.getSignedUser().getId());
            else if (choice == 2) app.menu();
            else {
                System.out.println("Please write correct option");
                showFriends();
            }
        }
    }

    public void makeFriend() throws IOException {
        System.out.println("You can make new friend from that list");
        for (User user : notFriends) {
            System.out.println(user.getUsername());
        }

        System.out.println("With whom you wanna be friend?");
        choosingFriendByUsername = sc.next();

        for (User user : notFriends) {
            if (user.getUsername().equals(choosingFriendByUsername)) {
                message.sendFriendRequest(super.getSignedUser().getId(), user.getId(), choosingFriendByUsername);
                System.out.println("You sand friend request to " + user.getUsername());
            }
        }
    }

    public void notFriends() {
        for (User users : usersList) {
            for (User friends : friendsList)
                if (users.getId() != friends.getId() && users.getId() != super.getSignedUser().getId()) {
                    notFriends.add(users);
                }
        }
    }

    //set
    public void setFriendsList(LinkedList<User> friendsList) {
        this.friendsList = friendsList;
    }

    public void setNotFriends(LinkedList<User> notFriends) {
        this.notFriends = notFriends;
    }

    public void setUsersList(LinkedList<User> usersList) {
        this.usersList = usersList;
    }

    //get
    public LinkedList<User> getFriendsList() {
        return friendsList;
    }

    public LinkedList<User> getNotFriends() {
        return notFriends;
    }

    public LinkedList<User> getUsersList() {
        return usersList;
    }
}

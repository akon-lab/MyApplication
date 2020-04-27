package com.user;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class MyApplication {

    Scanner sc = new Scanner(System.in);
    private int choice;

    Chat chat;
    Friend friend;
    Password password1;

    User user;
    private User signedUser = null;
    ArrayList<User> users = new ArrayList<>();


    public void menu() throws IOException {
        userList();
        while (true) {
            if (signedUser == null) {
                System.out.println("You are not signed in.");
                System.out.println("1. Authentication");
                System.out.println("2. Exit");
                choice = sc.nextInt();
                if (choice == 1) authentication();
                else if (choice == 2) break;
                else {
                    System.out.println("Please write correct option");
                    menu();
                }
            } else {
                userProfile();
            }
        }
    }

    private void userProfile() {
        signedUser = null;
    }

    //sig in and sign up
    private void authentication() throws IOException {
        System.out.println("Do you want sign in/sign up ?");
        System.out.println("1.Sign in");
        System.out.println("2.Sign up");
        choice = sc.nextInt();
        if (choice == 1) signIn();
        else if (choice == 2) signUp();
        else {
            System.out.println("Please write correct option");
            authentication();
        }
    }

    private void signUp() throws IOException {
        System.out.println("Enter Firstname:");
        String fname = sc.next();
        System.out.println("Enter SecondName");
        String sname = sc.next();
        System.out.println("Create username:");
        String username = sc.next();
        System.out.println("Create password:");
        String password = sc.next();

        User user = new User(fname, sname, username, password);

        if (user.getFname() != null && user.getSname() != null && user.getPassword() != null && user.getUsername() != null) {
            users.add(user);
            signedUser = user;
        } else {
            System.out.println("Try again >:3!");
            menu();
        }

        saveUserList();
        System.out.println("You sign up !");
        openApp();
    }

    private void signIn() throws IOException {
        System.out.println("Enter username:");
        String username = sc.next();
        System.out.println("Enter password:");
        String password = sc.next();

        if (check(username, password)) {
            System.out.println("You Enter in app");
            openApp();
        } else {
            System.out.println("Your password or username incorrect!");
            System.out.println("Do you wanna try again?");
            System.out.println("1.Yes");
            System.out.println("2.No");
            choice = sc.nextInt();
            if (choice == 1) signIn();
            else if (choice == 2) menu();
            else {
                System.out.println("Please write correct option");
                signIn();
            }

        }

    }

    //check in sign in
    public boolean check(String username, String password) {
        password1 = new Password(password);
        for (User user : users) {
            if (username.equals(user.getUsername()) && password1.equals(user.getPassword())) {
                signedUser = user;
                return true;
            }
        }

        return false;

    }

    //app
    public void openApp() throws IOException {
        System.out.println("Welcome " + signedUser.getFname() + " " + signedUser.getSname() + " to JavaApp!!!");
        System.out.println("Choose option:");
        System.out.println("1.Fiends");
        System.out.println("2.Chat");
        System.out.println("3.Exit");
        choice = sc.nextInt();

        if (choice == 1) {
            friend = new Friend();
            friend.open();
        } else if (choice == 2) {
            chat = new Chat();
            chat.open(signedUser.getId());
        } else if (choice == 3) System.out.println("Good bye");
        else {
            System.out.println("Please write correct option");
            openApp();
        }

    }

    //working with file
    public void userList() throws FileNotFoundException {
        File db = new File("C:\\Users\\ak_he\\IdeaProjects\\circle\\src\\com\\user\\db.txt");
        Scanner sc = new Scanner(db);
        while (sc.hasNext()) {
            user = new User(sc.nextInt(), sc.next(), sc.next(), sc.next(), sc.next());
            users.add(user);
        }
    }

    private void saveUserList() throws IOException {
        String content = " ";
        for (User user : users) {
            content = content + user.getId() + " " + user.getFname() + " " + user.getSname() + " " + user.getUsername() + " " + user.getPassword() + "\n";
        }
        Files.write(Paths.get("C:\\Users\\ak_he\\IdeaProjects\\circle\\src\\com\\user\\db.txt"), content.getBytes());
    }

    //set
    public void setSignedUser(User signedUser) {
        this.signedUser = signedUser;
    }

    //get
    public User getSignedUser() {
        return signedUser;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}


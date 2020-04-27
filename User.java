package com.user;

import java.util.List;

public class User {
    List<User> users;
    private int id, id_gen = 1;
    private String fname, sname, username;
    Password password;


    public void genderID() {
        id = id_gen++;
    }

    //User structure
    public User(int id, String fname, String sname, String username, String password) {//user from txt file

        setId(id);
        if (id_gen < id + 1) id_gen = id + 1;
        setFname(fname);
        setSname(sname);
        setUsername(username);
        setPassword(password);
    }

    public User(String fname, String sname, String username, String password) {//sign up user

        if (id_gen < id + 1) id_gen = id + 1;
        this.id+=id_gen;
        setFname(fname);
        setSname(sname);
        setUsername(username);
        setPassword(password);
    }


    public User( ) {

    }

    //set
    public void setId(int id) {
        this.id=id+id_gen;
    }

    public void setSname(String sname) {
        if (checkName(sname))
            this.sname = sname;
        else {
            System.out.println("User.setSname:sname wrong");
            this.sname = null;
        }
    }

    public void setFname(String fname) {
        if (checkName(fname))
            this.fname = fname;
        else {
            System.out.println("User.setFname:fname wrong");
            this.fname = null;
        }

    }

    public void setPassword(String password) {
        this.password = new Password(password);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //get
    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getSname() {
        return sname;
    }

    public String getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }


    //check  fname and sname
    public boolean checkName(String name) {//for fname and sname

        if (name.charAt(0) >= 65 && name.charAt(0) <= 90) {//if the first letter is in upper case
            name = name.toLowerCase();

            int c = 0;
            for (int i = 0; i < name.length(); i++) {

                if (name.charAt(i) >= 97 && name.charAt(i) <= 122) c++;
                else {
                    System.out.println("Checking.checkName:your name have another symbol");
                    return false;
                }

            }
        } else {
            System.out.println("Checking.checkName:your name don't start with upper case letter");
            return false;
        }

        return true;
    }

    public void addUser(User user) {
        users.add(user);
    }


}

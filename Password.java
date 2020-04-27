package com.user;

public class Password {
    private String pasword;

    public Password(String p) {
        setPasword(p);
    }

    public void setPasword(String pasword) {
        if (checkPassword(pasword))
            this.pasword = pasword;
        else {
            this.pasword = null;
            System.out.println("change password please");
        }
    }

    // passwordStr
    // it should contain uppercase and lowercase letters and digits
    // and its length must be more than 9 symbols
    public boolean checkPassword(String password) {
        if (password.length() < 9) {
            System.out.println("Password.checkName:your password is short");
            return false;

        } else {

            if (!upperCase(password)) {
                System.out.println("Password.upperLetter:you don't have any upper case");//if we don't have any upper letter
            } else {
                if (!lowerCase(password)) {
                    System.out.println("Password.upperLetter:you don't have any upper case");
                } else {
                    return checkElement(password);
                }
            }

        }
        return false;
    }

    public boolean checkElement(String password) {
        int countNum = countNum(password);
        int countSymbol = countSymbol(password);
        int countLetter = countLetter(password);

        if (countNum < 1) System.out.println("Password.countNum: your password don't have any numbers");
        if (countSymbol < 1) System.out.println("Password.countSymbol: your password don't have any symbol");
        if (countLetter < 1) System.out.println("Password.countLetter: your password don't have any letter");


        return countLetter >= 1 && countNum >= 1 && countSymbol >= 1;
    }

    public boolean upperCase(String word) {
        int c = 0;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) >= 65 && word.charAt(i) <= 90) {
                c++;
            }
        }

        return (c > 0);
    }

    public boolean lowerCase(String p) {
        p = p.toLowerCase();
        int count = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) >= 97 && p.charAt(i) <= 122) count++;
        }
        return count > 0;
    }

    public int countNum(String p) {
        int count = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) >= 48 && p.charAt(i) <= 57) count++;
        }
        return count;
    }

    public int countSymbol(String p) {
        int count = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) >= 33 && p.charAt(i) <= 47) count++;
            if (p.charAt(i) >= 58 && p.charAt(i) <= 64) count++;
            if (p.charAt(i) >= 91 && p.charAt(i) <= 96) count++;
            if (p.charAt(i) >= 123 && p.charAt(i) <= 126) count++;
        }
        return count;
    }

    public int countLetter(String p) {
        p = p.toLowerCase();
        int count = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) >= 97 && p.charAt(i) <= 122) count++;
        }
        return count;
    }
}

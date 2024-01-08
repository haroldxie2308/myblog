package com.haroldxie.myblog.Domain;

import java.sql.Date;

public class User {
    private int user_id;
    private String adgangskode;
    private String username;
    private String nickname;
    private String avatarPath;
    private int gender;
    private Date birthday;
    private String emailAddr;
    private String personalStatement;
    private Date accountCreationDate;

    public User() {
    }

    public User(String adgangskode, String realName) {
        this.adgangskode = adgangskode;
        this.username = realName;
    }

    public User(int user_id, String adgangskode, String username, String nickname, String avatarPath, int gender, Date birthday, String emailAddr, String personalStatement, Date accountCreationDate) {
        this.user_id = user_id;
        this.adgangskode = adgangskode;
        this.username = username;
        this.nickname = nickname;
        this.avatarPath = avatarPath;
        this.gender = gender;
        this.birthday = birthday;
        this.emailAddr = emailAddr;
        this.personalStatement = personalStatement;
        this.accountCreationDate = accountCreationDate;
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAdgangskode() {
        return adgangskode;
    }

    public void setAdgangskode(String adgangskode) {
        this.adgangskode = adgangskode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public String getPersonalStatement() {
        return personalStatement;
    }

    public void setPersonalStatement(String personalStatement) {
        this.personalStatement = personalStatement;
    }

    public Date getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(Date accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }
}

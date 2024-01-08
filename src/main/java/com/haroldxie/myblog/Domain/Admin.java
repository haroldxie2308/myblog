package com.haroldxie.myblog.Domain;

public class Admin {
    private int admin_id;
    private String adgangskode;
    private String adminName;
    private String avatarPath;
    private int gender;
    private String emailAddr;
    private String personalStatement;

    public Admin () {}

    public Admin(int admin_id, String adminName, String avatarPath, int gender, String emailAddr, String personalStatement) {
        this.admin_id = admin_id;
        this.adminName = adminName;
        this.avatarPath = avatarPath;
        this.gender = gender;
        this.emailAddr = emailAddr;
        this.personalStatement = personalStatement;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdgangskode() {
        return adgangskode;
    }

    public void setAdgangskode(String adgangskode) {
        this.adgangskode = adgangskode;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
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

}

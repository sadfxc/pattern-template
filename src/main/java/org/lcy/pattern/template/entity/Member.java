package org.lcy.pattern.template.entity;

public class Member {

    private String username;
    private String passwd;
    private String nickName;
    public Member(){

    }

    public Member(String username, String passwd, String nickName) {
        this.username = username;
        this.passwd = passwd;
        this.nickName = nickName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}

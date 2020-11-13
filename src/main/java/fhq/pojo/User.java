package fhq.pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class User implements Serializable {
    private Integer userId;
    private String userName;
    private String userPassword;
    private Integer userVip;//VIP等级
    private String userFace;//用户头像地址
    private String salt;//盐值
    private Integer pushMoney;//充值金额
    private String email;
    private String sex;//性别
    private String tel;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getUserVip() {
        return userVip;
    }

    public void setUserVip(Integer userVip) {
        this.userVip = userVip;
    }

    public String getUserFace() {
        return userFace;
    }

    public void setUserFace(String userFace) {
        this.userFace = userFace;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getPushMoney() {
        return pushMoney;
    }

    public void setPushMoney(Integer pushMoney) {
        this.pushMoney = pushMoney;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + userPassword + '\'' +
                ", userVip=" + userVip +
                ", userFace='" + userFace + '\'' +
                ", salt='" + salt + '\'' +
                ", pushMoney=" + pushMoney +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}

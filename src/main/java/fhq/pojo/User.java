package fhq.pojo;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

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
    //将客户端字符串格式的日期按照指定的格式转成Date类型
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String industry;
    private String edu;
    private String address;
    private String signature;
    private Integer loPh;//最低充值金额
    private Integer hiPh;//最高金额
    private Integer loLimit;//分页起点


    public void setLoLimit(Integer loLimit) {
        this.loLimit = loLimit;
    }





    public void setLoPh(Integer loPh) {
        this.loPh = loPh;
    }



    public void setHiPh(Integer hiPh) {
        this.hiPh = hiPh;
    }

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userVip=" + userVip +
                ", userFace='" + userFace + '\'' +
                ", salt='" + salt + '\'' +
                ", pushMoney=" + pushMoney +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                ", birthday=" + birthday +
                ", industry='" + industry + '\'' +
                ", edu='" + edu + '\'' +
                ", address='" + address + '\'' +
                ", signature='" + signature + '\'' +
                ", loPh=" + loPh +
                ", hiPh=" + hiPh +
                '}';
    }
}


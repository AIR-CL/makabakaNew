package fhq.pojo;

import java.io.Serializable;

public class Comic implements Serializable {
    private Integer comicId;
    private String comicName;
    private Integer clickFreq;
    private Integer watchVip;
    private Integer scoreNum;
    private String broadcastTime;
    private String comicState;
    private String dubbing;
    private String comicType;
    private String comicFace;

    public Integer getComicId() {
        return comicId;
    }

    public void setComicId(Integer comicId) {
        this.comicId = comicId;
    }

    public String getComicName() {
        return comicName;
    }

    public void setComicName(String comicName) {
        this.comicName = comicName;
    }

    public Integer getClickFreq() {
        return clickFreq;
    }

    public void setClickFreq(Integer clickFreq) {
        this.clickFreq = clickFreq;
    }

    public Integer getWatchVip() {
        return watchVip;
    }

    public void setWatchVip(Integer watchVip) {
        this.watchVip = watchVip;
    }

    public Integer getScoreNum() {
        return scoreNum;
    }

    public void setScoreNum(Integer scoreNum) {
        this.scoreNum = scoreNum;
    }

    public String getBroadcastTime() {
        return broadcastTime;
    }

    public void setBroadcastTime(String broadcastTime) {
        this.broadcastTime = broadcastTime;
    }

    public String getComicState() {
        return comicState;
    }

    public void setComicState(String comicState) {
        this.comicState = comicState;
    }

    public String getDubbing() {
        return dubbing;
    }

    public void setDubbing(String dubbing) {
        this.dubbing = dubbing;
    }

    public String getComicType() {
        return comicType;
    }

    public void setComicType(String comicType) {
        this.comicType = comicType;
    }

    public String getComicFace() {
        return comicFace;
    }

    public void setComicFace(String comicFace) {
        this.comicFace = comicFace;
    }

    @Override
    public String toString() {
        return "Comic{" +
                "comicId=" + comicId +
                ", comicName='" + comicName + '\'' +
                ", clickFreq=" + clickFreq +
                ", watchVip=" + watchVip +
                ", scoreNum=" + scoreNum +
                ", broadcastTime='" + broadcastTime + '\'' +
                ", comicState='" + comicState + '\'' +
                ", Dubbing='" + dubbing + '\'' +
                ", comicType='" + comicType + '\'' +
                ", comicFace='" + comicFace + '\'' +
                '}';
    }
}

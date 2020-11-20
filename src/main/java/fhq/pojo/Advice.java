package fhq.pojo;

import org.springframework.stereotype.Repository;

@Repository
public class Advice {
    private Integer adviceId;
    private Integer userId;
    private String adviceType;
    private String adviceTitle;
    private String adviceContent;

    public Integer getAdviceId() {
        return adviceId;
    }

    public void setAdviceId(Integer adviceId) {
        this.adviceId = adviceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAdviceType() {
        return adviceType;
    }

    public void setAdviceType(String adviceType) {
        this.adviceType = adviceType;
    }

    public String getAdviceTitle() {
        return adviceTitle;
    }

    public void setAdviceTitle(String adviceTitle) {
        this.adviceTitle = adviceTitle;
    }

    public String getAdviceContent() {
        return adviceContent;
    }

    public void setAdviceContent(String adviceContent) {
        this.adviceContent = adviceContent;
    }

    @Override
    public String toString() {
        return "Advice{" +
                "adviceId=" + adviceId +
                ", userId=" + userId +
                ", adviceType='" + adviceType + '\'' +
                ", adviceTitle='" + adviceTitle + '\'' +
                ", adviceContent='" + adviceContent + '\'' +
                '}';
    }
}

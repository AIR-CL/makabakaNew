package fhq.pojo;

public class Subscribe {
    private Integer userId;//用户id
    private Integer comicId;//漫画id

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getComicId() {
        return comicId;
    }

    public void setComicId(Integer comicId) {
        this.comicId = comicId;
    }

    @Override
    public String toString() {
        return "Subscribe{" +
                "userId=" + userId +
                ", comicId=" + comicId +
                '}';
    }
}

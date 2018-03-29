package ke.co.ipandasoft.mmaraumobileinfo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kevin Gitonga on 2/18/2018.
 */

public class Noticeboard {

    @SerializedName("notice_id")
    @Expose
    private String noticeId;
    @SerializedName("school_id")
    @Expose
    private String schoolId;
    @SerializedName("notice_description")
    @Expose
    private String noticeDescription;
    @SerializedName("notice_image")
    @Expose
    private String noticeImage;
    @SerializedName("notice_type")
    @Expose
    private String noticeType;
    @SerializedName("notice_status")
    @Expose
    private String noticeStatus;
    @SerializedName("on_date")
    @Expose
    private String onDate;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getNoticeDescription() {
        return noticeDescription;
    }

    public void setNoticeDescription(String noticeDescription) {
        this.noticeDescription = noticeDescription;
    }

    public String getNoticeImage() {
        return noticeImage;
    }

    public void setNoticeImage(String noticeImage) {
        this.noticeImage = noticeImage;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public String getNoticeStatus() {
        return noticeStatus;
    }

    public void setNoticeStatus(String noticeStatus) {
        this.noticeStatus = noticeStatus;
    }

    public String getOnDate() {
        return onDate;
    }

    public void setOnDate(String onDate) {
        this.onDate = onDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}

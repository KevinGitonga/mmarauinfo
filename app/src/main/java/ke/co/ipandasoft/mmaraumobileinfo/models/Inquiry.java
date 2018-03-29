package ke.co.ipandasoft.mmaraumobileinfo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kevin Gitonga on 3/3/2018.
 */

public class Inquiry {

    @SerializedName("chat_id")
    @Expose
    private String chat_id;
    @SerializedName("student_id")
    @Expose
    private String student_id;
    @SerializedName("school_id")
    @Expose
    private String school_id;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("reply")
    @Expose
    private String reply;
    @SerializedName("on_date")
    @Expose
    private String on_date;

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getOn_date() {
        return on_date;
    }

    public void setOn_date(String on_date) {
        this.on_date = on_date;
    }
}

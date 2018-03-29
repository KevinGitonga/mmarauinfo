package ke.co.ipandasoft.mmaraumobileinfo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kevin Gitonga on 2/18/2018.
 */

public class NoticesCallback {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("errorCode")
    @Expose
    private Integer errorCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("noticeboard")
    @Expose
    private List<Noticeboard> noticeboard = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Noticeboard> getNoticeboard() {
        return noticeboard;
    }

    public void setNoticeboard(List<Noticeboard> noticeboard) {
        this.noticeboard = noticeboard;
    }
}

package ke.co.ipandasoft.mmaraumobileinfo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kevin Gitonga on 3/3/2018.
 */

public class InquiriesCallback {

    @SerializedName("response")
    @Expose
    private Boolean response;
    @SerializedName("data")
    @Expose
    private List<Inquiry> data = null;

    public Boolean getResponse() {
        return response;
    }

    public void setResponse(Boolean response) {
        this.response = response;
    }

    public List<Inquiry> getData() {
        return data;
    }

    public void setData(List<Inquiry> data) {
        this.data = data;
    }

}

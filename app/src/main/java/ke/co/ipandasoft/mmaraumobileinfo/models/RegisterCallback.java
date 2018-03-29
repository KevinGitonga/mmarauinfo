package ke.co.ipandasoft.mmaraumobileinfo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterCallback {

@SerializedName("response")
@Expose
private Boolean response;
@SerializedName("message")
@Expose
private String message;

public Boolean getResponse() {
        return response;
        }

public void setResponse(Boolean response) {
        this.response = response;
        }

public String getMessage() {
        return message;
        }

public void setMessage(String message) {
        this.message = message;
        }

        }


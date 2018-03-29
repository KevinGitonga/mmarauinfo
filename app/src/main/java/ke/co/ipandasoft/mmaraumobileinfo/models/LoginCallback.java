package ke.co.ipandasoft.mmaraumobileinfo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kevin Gitonga on 2/11/2018.
 */

public class LoginCallback {

        @SerializedName("response")
        @Expose
        private Boolean response;
        @SerializedName("data")
        @Expose
        private StudentData data;

        public Boolean getResponse() {
            return response;
        }

        public void setResponse(Boolean response) {
            this.response = response;
        }

        public StudentData getData() {
            return data;
        }
        public void setData(StudentData data) {
            this.data = data;
        }

    }

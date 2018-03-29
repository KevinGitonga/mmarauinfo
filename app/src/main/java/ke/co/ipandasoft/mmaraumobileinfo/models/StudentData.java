package ke.co.ipandasoft.mmaraumobileinfo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kevin Gitonga on 2/11/2018.
 */

public class StudentData {

        @SerializedName("student_id")
        @Expose
        private String studentId;
        @SerializedName("student_email")
        @Expose
        private String studentEmail;
        @SerializedName("user_adm_no")
        @Expose
        private String userAdmNo;

        public String getStudentId() {
            return studentId;
        }

        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }

        public String getStudentEmail() {
            return studentEmail;
        }

        public void setStudentEmail(String studentEmail) {
            this.studentEmail = studentEmail;
        }

        public String getUserAdmNo() {
            return userAdmNo;
        }

        public void setUserAdmNo(String userAdmNo) {
            this.userAdmNo = userAdmNo;
        }

    }



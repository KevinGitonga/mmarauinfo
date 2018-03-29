package ke.co.ipandasoft.mmaraumobileinfo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kevin Gitonga on 2/21/2018.
 */

public class Event {

        @SerializedName("event_id")
        @Expose
        private String event_id;
        @SerializedName("school_id")
        @Expose
        private String school_id;
        @SerializedName("event_title")
        @Expose
        private String event_title;
        @SerializedName("event_description")
        @Expose
        private String event_description;
        @SerializedName("event_image")
        @Expose
        private String event_image;
        @SerializedName("event_start")
        @Expose
        private String event_start;
        @SerializedName("event_end")
        @Expose
        private String event_end;
        @SerializedName("event_status")
        @Expose
        private String event_status;
        @SerializedName("on_date")
        @Expose
        private String on_date;
        @SerializedName("created_at")
        @Expose
        private String created_at;

        public String getEvent_id() {
            return event_id;
        }

        public void setEvent_id(String event_id) {
            this.event_id = event_id;
        }

        public String getSchool_id() {
            return school_id;
        }

        public void setSchool_id(String school_id) {
            this.school_id = school_id;
        }

        public String getEvent_title() {
            return event_title;
        }

        public void setEvent_title(String event_title) {
            this.event_title = event_title;
        }

        public String getEvent_description() {
            return event_description;
        }

        public void setEvent_description(String event_description) {
            this.event_description = event_description;
        }

        public String getEvent_image() {
            return event_image;
        }

        public void setEvent_image(String event_image) {
            this.event_image = event_image;
        }

        public String getEvent_start() {
            return event_start;
        }

        public void setEvent_start(String event_start) {
            this.event_start = event_start;
        }

        public String getEvent_end() {
            return event_end;
        }

        public void setEvent_end(String event_end) {
            this.event_end = event_end;
        }

        public String getEvent_status() {
            return event_status;
        }

        public void setEvent_status(String event_status) {
            this.event_status = event_status;
        }

        public String getOn_date() {
            return on_date;
        }

        public void setOn_date(String on_date) {
            this.on_date = on_date;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

    }

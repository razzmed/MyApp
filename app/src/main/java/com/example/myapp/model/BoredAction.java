package com.example.myapp.model;

//{
//        "activity": "Learn calligraphy",
//        "type": "education",
//        "participants": 1,
//        "price": 0.1,
//        "link": "",
//        "key": "4565537",
//        "accessibility": 0.1
//        }

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class BoredAction {

    @SerializedName("key")
    private String key;

    @SerializedName("activity")
    private String activity;

    @SerializedName("type")
    private String type;

    @SerializedName("participants")
    private Integer participants;

    @SerializedName("price")
    private Float price;

    @SerializedName("link")
    private String link;

    @SerializedName("accessibility")
    private Float accessibility;

    public BoredAction(String key, String activity, String type, Integer participants, Float price, String link, Float accessibility) {
        this.key = key;
        this.activity = activity;
        this.type = type;
        this.participants = participants;
        this.price = price;
        this.link = link;
        this.accessibility = accessibility;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getParticipants() {
        return participants;
    }

    public void setParticipants(Integer participants) {
        this.participants = participants;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Float getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(Float accessibility) {
        this.accessibility = accessibility;
    }

    @Override
    public String toString() {
        return "BoredAction{" +
                "key='" + key + '\'' +
                ", activity='" + activity + '\'' +
                ", type='" + type + '\'' +
                ", participants=" + participants +
                ", price=" + price +
                ", link='" + link + '\'' +
                ", accessibility=" + accessibility +
                '}';
    }
}

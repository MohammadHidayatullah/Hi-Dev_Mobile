package com.example.mobileiklanku.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WebinarModels implements Parcelable {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("pamflet_webinar")
    @Expose
    private String pamflet_webinar;
    @SerializedName("judul_webinar")
    @Expose
    private String judul_webinar;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("deadline")
    @Expose
    private String deadline;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("status")
    @Expose
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPamflet_webinar() {
        return pamflet_webinar;
    }

    public void setPamflet_webinar(String pamflet_webinar) {
        this.pamflet_webinar = pamflet_webinar;
    }

    public String getJudul_webinar() {
        return judul_webinar;
    }

    public void setJudul_webinar(String judul_webinar) {
        this.judul_webinar = judul_webinar;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.pamflet_webinar);
        dest.writeString(this.judul_webinar);
        dest.writeString(this.deskripsi);
        dest.writeString(this.deadline);
        dest.writeString(this.link);
        dest.writeString(this.status);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.pamflet_webinar = source.readString();
        this.judul_webinar = source.readString();
        this.deskripsi = source.readString();
        this.deadline = source.readString();
        this.link = source.readString();
        this.status = source.readString();
    }

    public WebinarModels() {
    }

    protected WebinarModels(Parcel in) {
        this.id = in.readInt();
        this.pamflet_webinar = in.readString();
        this.judul_webinar = in.readString();
        this.deskripsi = in.readString();
        this.deadline = in.readString();
        this.link = in.readString();
        this.status = in.readString();
    }

    public static final Parcelable.Creator<WebinarModels> CREATOR = new Parcelable.Creator<WebinarModels>() {
        @Override
        public WebinarModels createFromParcel(Parcel source) {
            return new WebinarModels(source);
        }

        @Override
        public WebinarModels[] newArray(int size) {
            return new WebinarModels[size];
        }
    };
}

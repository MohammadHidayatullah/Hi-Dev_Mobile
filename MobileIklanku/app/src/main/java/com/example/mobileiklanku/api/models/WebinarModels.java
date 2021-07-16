package com.example.mobileiklanku.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WebinarModels {
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

}

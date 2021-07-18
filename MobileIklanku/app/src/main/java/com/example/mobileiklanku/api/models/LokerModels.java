package com.example.mobileiklanku.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LokerModels {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("pamflet_loker")
    @Expose
    private String pamflet_loker;
    @SerializedName("judul_loker")
    @Expose
    private String judul_loker;
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

    public String getPamflet_loker() {
        return pamflet_loker;
    }

    public void setPamflet_loker(String pamflet_loker) {
        this.pamflet_loker = pamflet_loker;
    }

    public String getJudul_loker() {
        return judul_loker;
    }

    public void setJudul_loker(String judul_loker) {
        this.judul_loker = judul_loker;
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

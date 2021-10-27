package com.devpj.rajgharana.module;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bill {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("guest_name")
    @Expose
    private String guestName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("service_charge")
    @Expose
    private Integer serviceCharge;
    @SerializedName("vat")
    @Expose
    private Integer vat;
    @SerializedName("advance")
    @Expose
    private Integer advance;
    @SerializedName("finalRemaining")
    @Expose
    private Integer finalRemaining;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("billtype")
    @Expose
    private String billtype;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("tid")
    @Expose
    private Integer tid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(Integer serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Integer getVat() {
        return vat;
    }

    public void setVat(Integer vat) {
        this.vat = vat;
    }

    public Integer getAdvance() {
        return advance;
    }

    public void setAdvance(Integer advance) {
        this.advance = advance;
    }

    public Integer getFinalRemaining() {
        return finalRemaining;
    }

    public void setFinalRemaining(Integer finalRemaining) {
        this.finalRemaining = finalRemaining;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }
}

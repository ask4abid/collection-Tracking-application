package com.finca.ccwapp.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.finca.ccwapp.domain.Proosals} entity.
 */
public class ProosalsDTO implements Serializable {
    private Long id;

    @NotNull
    @Size(max = 10)
    private String unitid;

    @NotNull
    @Size(max = 10)
    private String businessproposal;

    @NotNull
    @Size(max = 10)
    private String subproposal;

    @NotNull
    @Size(max = 10)
    private String relationid;

    @NotNull
    @Size(max = 15)
    private String mobilenumber;

    @NotNull
    @Size(max = 20)
    private String accountnumber;

    @NotNull
    @Size(max = 40)
    private String accounttitle;

    @NotNull
    @Size(max = 10)
    private String numerofvisits;

    @NotNull
    @Size(max = 100)
    private String outstandingamount;

    @NotNull
    @Size(max = 100)
    private String outstandingprofit;

    @NotNull
    @Size(max = 10)
    private String oddays;

    @NotNull
    @Size(max = 10)
    private String loanofficer;

    private LocalDate promisetopay;

    @NotNull
    @Size(max = 100)
    private String remarks;

    @NotNull
    @Size(max = 100)
    private String delequencyreason;

    private Long visitsId;

    private Long deliquencyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnitid() {
        return unitid;
    }

    public void setUnitid(String unitid) {
        this.unitid = unitid;
    }

    public String getBusinessproposal() {
        return businessproposal;
    }

    public void setBusinessproposal(String businessproposal) {
        this.businessproposal = businessproposal;
    }

    public String getSubproposal() {
        return subproposal;
    }

    public void setSubproposal(String subproposal) {
        this.subproposal = subproposal;
    }

    public String getRelationid() {
        return relationid;
    }

    public void setRelationid(String relationid) {
        this.relationid = relationid;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getAccounttitle() {
        return accounttitle;
    }

    public void setAccounttitle(String accounttitle) {
        this.accounttitle = accounttitle;
    }

    public String getNumerofvisits() {
        return numerofvisits;
    }

    public void setNumerofvisits(String numerofvisits) {
        this.numerofvisits = numerofvisits;
    }

    public String getOutstandingamount() {
        return outstandingamount;
    }

    public void setOutstandingamount(String outstandingamount) {
        this.outstandingamount = outstandingamount;
    }

    public String getOutstandingprofit() {
        return outstandingprofit;
    }

    public void setOutstandingprofit(String outstandingprofit) {
        this.outstandingprofit = outstandingprofit;
    }

    public String getOddays() {
        return oddays;
    }

    public void setOddays(String oddays) {
        this.oddays = oddays;
    }

    public String getLoanofficer() {
        return loanofficer;
    }

    public void setLoanofficer(String loanofficer) {
        this.loanofficer = loanofficer;
    }

    public LocalDate getPromisetopay() {
        return promisetopay;
    }

    public void setPromisetopay(LocalDate promisetopay) {
        this.promisetopay = promisetopay;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDelequencyreason() {
        return delequencyreason;
    }

    public void setDelequencyreason(String delequencyreason) {
        this.delequencyreason = delequencyreason;
    }

    public Long getVisitsId() {
        return visitsId;
    }

    public void setVisitsId(Long visitsId) {
        this.visitsId = visitsId;
    }

    public Long getDeliquencyId() {
        return deliquencyId;
    }

    public void setDeliquencyId(Long deliquencyId) {
        this.deliquencyId = deliquencyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProosalsDTO)) {
            return false;
        }

        return id != null && id.equals(((ProosalsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProosalsDTO{" +
            "id=" + getId() +
            ", unitid='" + getUnitid() + "'" +
            ", businessproposal='" + getBusinessproposal() + "'" +
            ", subproposal='" + getSubproposal() + "'" +
            ", relationid='" + getRelationid() + "'" +
            ", mobilenumber='" + getMobilenumber() + "'" +
            ", accountnumber='" + getAccountnumber() + "'" +
            ", accounttitle='" + getAccounttitle() + "'" +
            ", numerofvisits='" + getNumerofvisits() + "'" +
            ", outstandingamount='" + getOutstandingamount() + "'" +
            ", outstandingprofit='" + getOutstandingprofit() + "'" +
            ", oddays='" + getOddays() + "'" +
            ", loanofficer='" + getLoanofficer() + "'" +
            ", promisetopay='" + getPromisetopay() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", delequencyreason='" + getDelequencyreason() + "'" +
            ", visitsId=" + getVisitsId() +
            ", deliquencyId=" + getDeliquencyId() +
            "}";
    }
}

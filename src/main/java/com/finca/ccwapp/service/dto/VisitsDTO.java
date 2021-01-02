package com.finca.ccwapp.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.finca.ccwapp.domain.Visits} entity.
 */
public class VisitsDTO implements Serializable {
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

    private LocalDate promisetopay;

    @NotNull
    @Size(max = 100)
    private String remarks;

    @NotNull
    @Size(max = 30)
    private String visitedby;

    @NotNull
    @Size(max = 10)
    private String employeeid;

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

    public String getVisitedby() {
        return visitedby;
    }

    public void setVisitedby(String visitedby) {
        this.visitedby = visitedby;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VisitsDTO)) {
            return false;
        }

        return id != null && id.equals(((VisitsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VisitsDTO{" +
            "id=" + getId() +
            ", unitid='" + getUnitid() + "'" +
            ", businessproposal='" + getBusinessproposal() + "'" +
            ", subproposal='" + getSubproposal() + "'" +
            ", promisetopay='" + getPromisetopay() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", visitedby='" + getVisitedby() + "'" +
            ", employeeid='" + getEmployeeid() + "'" +
            "}";
    }
}

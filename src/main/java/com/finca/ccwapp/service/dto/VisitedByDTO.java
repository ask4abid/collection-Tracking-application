package com.finca.ccwapp.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.finca.ccwapp.domain.VisitedBy} entity.
 */
public class VisitedByDTO implements Serializable {
    private Long id;

    @NotNull
    @Size(max = 10)
    private String unitid;

    @NotNull
    @Size(max = 10)
    private String firstname;

    @NotNull
    @Size(max = 10)
    private String lastname;

    @NotNull
    @Size(max = 30)
    private String status;

    @NotNull
    @Size(max = 10)
    private String employeeid;

    @NotNull
    @Size(max = 10)
    private String role;

    @NotNull
    @Size(max = 10)
    private String designation;

    private LocalDate vistdate;

    private Long proosalsId;

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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public LocalDate getVistdate() {
        return vistdate;
    }

    public void setVistdate(LocalDate vistdate) {
        this.vistdate = vistdate;
    }

    public Long getProosalsId() {
        return proosalsId;
    }

    public void setProosalsId(Long proosalsId) {
        this.proosalsId = proosalsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VisitedByDTO)) {
            return false;
        }

        return id != null && id.equals(((VisitedByDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VisitedByDTO{" +
            "id=" + getId() +
            ", unitid='" + getUnitid() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", status='" + getStatus() + "'" +
            ", employeeid='" + getEmployeeid() + "'" +
            ", role='" + getRole() + "'" +
            ", designation='" + getDesignation() + "'" +
            ", vistdate='" + getVistdate() + "'" +
            ", proosalsId=" + getProosalsId() +
            "}";
    }
}

package com.finca.ccwapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A VisitedBy.
 */
@Entity
@Table(name = "visited_by")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VisitedBy implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 10)
    @Column(name = "unitid", length = 10, nullable = false)
    private String unitid;

    @NotNull
    @Size(max = 10)
    @Column(name = "firstname", length = 10, nullable = false)
    private String firstname;

    @NotNull
    @Size(max = 10)
    @Column(name = "lastname", length = 10, nullable = false)
    private String lastname;

    @NotNull
    @Size(max = 30)
    @Column(name = "status", length = 30, nullable = false)
    private String status;

    @NotNull
    @Size(max = 10)
    @Column(name = "employeeid", length = 10, nullable = false)
    private String employeeid;

    @NotNull
    @Size(max = 10)
    @Column(name = "role", length = 10, nullable = false)
    private String role;

    @NotNull
    @Size(max = 10)
    @Column(name = "designation", length = 10, nullable = false)
    private String designation;

    @Column(name = "vistdate")
    private LocalDate vistdate;

    @ManyToOne
    @JsonIgnoreProperties(value = "visitedBies", allowSetters = true)
    private Proosals proosals;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnitid() {
        return unitid;
    }

    public VisitedBy unitid(String unitid) {
        this.unitid = unitid;
        return this;
    }

    public void setUnitid(String unitid) {
        this.unitid = unitid;
    }

    public String getFirstname() {
        return firstname;
    }

    public VisitedBy firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public VisitedBy lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getStatus() {
        return status;
    }

    public VisitedBy status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public VisitedBy employeeid(String employeeid) {
        this.employeeid = employeeid;
        return this;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    public String getRole() {
        return role;
    }

    public VisitedBy role(String role) {
        this.role = role;
        return this;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDesignation() {
        return designation;
    }

    public VisitedBy designation(String designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public LocalDate getVistdate() {
        return vistdate;
    }

    public VisitedBy vistdate(LocalDate vistdate) {
        this.vistdate = vistdate;
        return this;
    }

    public void setVistdate(LocalDate vistdate) {
        this.vistdate = vistdate;
    }

    public Proosals getProosals() {
        return proosals;
    }

    public VisitedBy proosals(Proosals proosals) {
        this.proosals = proosals;
        return this;
    }

    public void setProosals(Proosals proosals) {
        this.proosals = proosals;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VisitedBy)) {
            return false;
        }
        return id != null && id.equals(((VisitedBy) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VisitedBy{" +
            "id=" + getId() +
            ", unitid='" + getUnitid() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", status='" + getStatus() + "'" +
            ", employeeid='" + getEmployeeid() + "'" +
            ", role='" + getRole() + "'" +
            ", designation='" + getDesignation() + "'" +
            ", vistdate='" + getVistdate() + "'" +
            "}";
    }
}

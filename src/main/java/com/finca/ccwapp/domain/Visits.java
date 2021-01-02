package com.finca.ccwapp.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Visits.
 */
@Entity
@Table(name = "visits")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Visits implements Serializable {
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
    @Column(name = "businessproposal", length = 10, nullable = false)
    private String businessproposal;

    @NotNull
    @Size(max = 10)
    @Column(name = "subproposal", length = 10, nullable = false)
    private String subproposal;

    @Column(name = "promisetopay")
    private LocalDate promisetopay;

    @NotNull
    @Size(max = 100)
    @Column(name = "remarks", length = 100, nullable = false)
    private String remarks;

    @NotNull
    @Size(max = 30)
    @Column(name = "visitedby", length = 30, nullable = false)
    private String visitedby;

    @NotNull
    @Size(max = 10)
    @Column(name = "employeeid", length = 10, nullable = false)
    private String employeeid;

    @OneToMany(mappedBy = "visits")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Proosals> proosals = new HashSet<>();

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

    public Visits unitid(String unitid) {
        this.unitid = unitid;
        return this;
    }

    public void setUnitid(String unitid) {
        this.unitid = unitid;
    }

    public String getBusinessproposal() {
        return businessproposal;
    }

    public Visits businessproposal(String businessproposal) {
        this.businessproposal = businessproposal;
        return this;
    }

    public void setBusinessproposal(String businessproposal) {
        this.businessproposal = businessproposal;
    }

    public String getSubproposal() {
        return subproposal;
    }

    public Visits subproposal(String subproposal) {
        this.subproposal = subproposal;
        return this;
    }

    public void setSubproposal(String subproposal) {
        this.subproposal = subproposal;
    }

    public LocalDate getPromisetopay() {
        return promisetopay;
    }

    public Visits promisetopay(LocalDate promisetopay) {
        this.promisetopay = promisetopay;
        return this;
    }

    public void setPromisetopay(LocalDate promisetopay) {
        this.promisetopay = promisetopay;
    }

    public String getRemarks() {
        return remarks;
    }

    public Visits remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getVisitedby() {
        return visitedby;
    }

    public Visits visitedby(String visitedby) {
        this.visitedby = visitedby;
        return this;
    }

    public void setVisitedby(String visitedby) {
        this.visitedby = visitedby;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public Visits employeeid(String employeeid) {
        this.employeeid = employeeid;
        return this;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    public Set<Proosals> getProosals() {
        return proosals;
    }

    public Visits proosals(Set<Proosals> proosals) {
        this.proosals = proosals;
        return this;
    }

    public Visits addProosals(Proosals proosals) {
        this.proosals.add(proosals);
        proosals.setVisits(this);
        return this;
    }

    public Visits removeProosals(Proosals proosals) {
        this.proosals.remove(proosals);
        proosals.setVisits(null);
        return this;
    }

    public void setProosals(Set<Proosals> proosals) {
        this.proosals = proosals;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Visits)) {
            return false;
        }
        return id != null && id.equals(((Visits) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Visits{" +
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

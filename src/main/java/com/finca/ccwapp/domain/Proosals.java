package com.finca.ccwapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Proosals.
 */
@Entity
@Table(name = "proosals")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Proosals implements Serializable {
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

    @NotNull
    @Size(max = 10)
    @Column(name = "relationid", length = 10, nullable = false)
    private String relationid;

    @NotNull
    @Size(max = 15)
    @Column(name = "mobilenumber", length = 15, nullable = false)
    private String mobilenumber;

    @NotNull
    @Size(max = 20)
    @Column(name = "accountnumber", length = 20, nullable = false)
    private String accountnumber;

    @NotNull
    @Size(max = 40)
    @Column(name = "accounttitle", length = 40, nullable = false)
    private String accounttitle;

    @NotNull
    @Size(max = 10)
    @Column(name = "numerofvisits", length = 10, nullable = false)
    private String numerofvisits;

    @NotNull
    @Size(max = 100)
    @Column(name = "outstandingamount", length = 100, nullable = false)
    private String outstandingamount;

    @NotNull
    @Size(max = 100)
    @Column(name = "outstandingprofit", length = 100, nullable = false)
    private String outstandingprofit;

    @NotNull
    @Size(max = 10)
    @Column(name = "oddays", length = 10, nullable = false)
    private String oddays;

    @NotNull
    @Size(max = 10)
    @Column(name = "loanofficer", length = 10, nullable = false)
    private String loanofficer;

    @Column(name = "promisetopay")
    private LocalDate promisetopay;

    @NotNull
    @Size(max = 100)
    @Column(name = "remarks", length = 100, nullable = false)
    private String remarks;

    @NotNull
    @Size(max = 100)
    @Column(name = "delequencyreason", length = 100, nullable = false)
    private String delequencyreason;

    @OneToMany(mappedBy = "proosals")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<VisitedBy> visitedBies = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "proosals", allowSetters = true)
    private Visits visits;

    @ManyToOne
    @JsonIgnoreProperties(value = "proosals", allowSetters = true)
    private Deliquency deliquency;

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

    public Proosals unitid(String unitid) {
        this.unitid = unitid;
        return this;
    }

    public void setUnitid(String unitid) {
        this.unitid = unitid;
    }

    public String getBusinessproposal() {
        return businessproposal;
    }

    public Proosals businessproposal(String businessproposal) {
        this.businessproposal = businessproposal;
        return this;
    }

    public void setBusinessproposal(String businessproposal) {
        this.businessproposal = businessproposal;
    }

    public String getSubproposal() {
        return subproposal;
    }

    public Proosals subproposal(String subproposal) {
        this.subproposal = subproposal;
        return this;
    }

    public void setSubproposal(String subproposal) {
        this.subproposal = subproposal;
    }

    public String getRelationid() {
        return relationid;
    }

    public Proosals relationid(String relationid) {
        this.relationid = relationid;
        return this;
    }

    public void setRelationid(String relationid) {
        this.relationid = relationid;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public Proosals mobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
        return this;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public Proosals accountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
        return this;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getAccounttitle() {
        return accounttitle;
    }

    public Proosals accounttitle(String accounttitle) {
        this.accounttitle = accounttitle;
        return this;
    }

    public void setAccounttitle(String accounttitle) {
        this.accounttitle = accounttitle;
    }

    public String getNumerofvisits() {
        return numerofvisits;
    }

    public Proosals numerofvisits(String numerofvisits) {
        this.numerofvisits = numerofvisits;
        return this;
    }

    public void setNumerofvisits(String numerofvisits) {
        this.numerofvisits = numerofvisits;
    }

    public String getOutstandingamount() {
        return outstandingamount;
    }

    public Proosals outstandingamount(String outstandingamount) {
        this.outstandingamount = outstandingamount;
        return this;
    }

    public void setOutstandingamount(String outstandingamount) {
        this.outstandingamount = outstandingamount;
    }

    public String getOutstandingprofit() {
        return outstandingprofit;
    }

    public Proosals outstandingprofit(String outstandingprofit) {
        this.outstandingprofit = outstandingprofit;
        return this;
    }

    public void setOutstandingprofit(String outstandingprofit) {
        this.outstandingprofit = outstandingprofit;
    }

    public String getOddays() {
        return oddays;
    }

    public Proosals oddays(String oddays) {
        this.oddays = oddays;
        return this;
    }

    public void setOddays(String oddays) {
        this.oddays = oddays;
    }

    public String getLoanofficer() {
        return loanofficer;
    }

    public Proosals loanofficer(String loanofficer) {
        this.loanofficer = loanofficer;
        return this;
    }

    public void setLoanofficer(String loanofficer) {
        this.loanofficer = loanofficer;
    }

    public LocalDate getPromisetopay() {
        return promisetopay;
    }

    public Proosals promisetopay(LocalDate promisetopay) {
        this.promisetopay = promisetopay;
        return this;
    }

    public void setPromisetopay(LocalDate promisetopay) {
        this.promisetopay = promisetopay;
    }

    public String getRemarks() {
        return remarks;
    }

    public Proosals remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDelequencyreason() {
        return delequencyreason;
    }

    public Proosals delequencyreason(String delequencyreason) {
        this.delequencyreason = delequencyreason;
        return this;
    }

    public void setDelequencyreason(String delequencyreason) {
        this.delequencyreason = delequencyreason;
    }

    public Set<VisitedBy> getVisitedBies() {
        return visitedBies;
    }

    public Proosals visitedBies(Set<VisitedBy> visitedBies) {
        this.visitedBies = visitedBies;
        return this;
    }

    public Proosals addVisitedBy(VisitedBy visitedBy) {
        this.visitedBies.add(visitedBy);
        visitedBy.setProosals(this);
        return this;
    }

    public Proosals removeVisitedBy(VisitedBy visitedBy) {
        this.visitedBies.remove(visitedBy);
        visitedBy.setProosals(null);
        return this;
    }

    public void setVisitedBies(Set<VisitedBy> visitedBies) {
        this.visitedBies = visitedBies;
    }

    public Visits getVisits() {
        return visits;
    }

    public Proosals visits(Visits visits) {
        this.visits = visits;
        return this;
    }

    public void setVisits(Visits visits) {
        this.visits = visits;
    }

    public Deliquency getDeliquency() {
        return deliquency;
    }

    public Proosals deliquency(Deliquency deliquency) {
        this.deliquency = deliquency;
        return this;
    }

    public void setDeliquency(Deliquency deliquency) {
        this.deliquency = deliquency;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Proosals)) {
            return false;
        }
        return id != null && id.equals(((Proosals) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Proosals{" +
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
            "}";
    }
}

package com.finca.ccwapp.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Deliquency.
 */
@Entity
@Table(name = "deliquency")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Deliquency implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 10)
    @Column(name = "observatoinid", length = 10, nullable = false)
    private String observatoinid;

    @NotNull
    @Size(max = 20)
    @Column(name = "observation", length = 20, nullable = false)
    private String observation;

    @OneToMany(mappedBy = "deliquency")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Proosals> proosals = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObservatoinid() {
        return observatoinid;
    }

    public Deliquency observatoinid(String observatoinid) {
        this.observatoinid = observatoinid;
        return this;
    }

    public void setObservatoinid(String observatoinid) {
        this.observatoinid = observatoinid;
    }

    public String getObservation() {
        return observation;
    }

    public Deliquency observation(String observation) {
        this.observation = observation;
        return this;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Set<Proosals> getProosals() {
        return proosals;
    }

    public Deliquency proosals(Set<Proosals> proosals) {
        this.proosals = proosals;
        return this;
    }

    public Deliquency addProosals(Proosals proosals) {
        this.proosals.add(proosals);
        proosals.setDeliquency(this);
        return this;
    }

    public Deliquency removeProosals(Proosals proosals) {
        this.proosals.remove(proosals);
        proosals.setDeliquency(null);
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
        if (!(o instanceof Deliquency)) {
            return false;
        }
        return id != null && id.equals(((Deliquency) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Deliquency{" +
            "id=" + getId() +
            ", observatoinid='" + getObservatoinid() + "'" +
            ", observation='" + getObservation() + "'" +
            "}";
    }
}

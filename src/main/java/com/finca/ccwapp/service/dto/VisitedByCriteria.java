package com.finca.ccwapp.service.dto;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LocalDateFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the {@link com.finca.ccwapp.domain.VisitedBy} entity. This class is used
 * in {@link com.finca.ccwapp.web.rest.VisitedByResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /visited-bies?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class VisitedByCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter unitid;

    private StringFilter firstname;

    private StringFilter lastname;

    private StringFilter status;

    private StringFilter employeeid;

    private StringFilter role;

    private StringFilter designation;

    private LocalDateFilter vistdate;

    private LongFilter proosalsId;

    public VisitedByCriteria() {}

    public VisitedByCriteria(VisitedByCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.unitid = other.unitid == null ? null : other.unitid.copy();
        this.firstname = other.firstname == null ? null : other.firstname.copy();
        this.lastname = other.lastname == null ? null : other.lastname.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.employeeid = other.employeeid == null ? null : other.employeeid.copy();
        this.role = other.role == null ? null : other.role.copy();
        this.designation = other.designation == null ? null : other.designation.copy();
        this.vistdate = other.vistdate == null ? null : other.vistdate.copy();
        this.proosalsId = other.proosalsId == null ? null : other.proosalsId.copy();
    }

    @Override
    public VisitedByCriteria copy() {
        return new VisitedByCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getUnitid() {
        return unitid;
    }

    public void setUnitid(StringFilter unitid) {
        this.unitid = unitid;
    }

    public StringFilter getFirstname() {
        return firstname;
    }

    public void setFirstname(StringFilter firstname) {
        this.firstname = firstname;
    }

    public StringFilter getLastname() {
        return lastname;
    }

    public void setLastname(StringFilter lastname) {
        this.lastname = lastname;
    }

    public StringFilter getStatus() {
        return status;
    }

    public void setStatus(StringFilter status) {
        this.status = status;
    }

    public StringFilter getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(StringFilter employeeid) {
        this.employeeid = employeeid;
    }

    public StringFilter getRole() {
        return role;
    }

    public void setRole(StringFilter role) {
        this.role = role;
    }

    public StringFilter getDesignation() {
        return designation;
    }

    public void setDesignation(StringFilter designation) {
        this.designation = designation;
    }

    public LocalDateFilter getVistdate() {
        return vistdate;
    }

    public void setVistdate(LocalDateFilter vistdate) {
        this.vistdate = vistdate;
    }

    public LongFilter getProosalsId() {
        return proosalsId;
    }

    public void setProosalsId(LongFilter proosalsId) {
        this.proosalsId = proosalsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final VisitedByCriteria that = (VisitedByCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(unitid, that.unitid) &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(status, that.status) &&
            Objects.equals(employeeid, that.employeeid) &&
            Objects.equals(role, that.role) &&
            Objects.equals(designation, that.designation) &&
            Objects.equals(vistdate, that.vistdate) &&
            Objects.equals(proosalsId, that.proosalsId)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, unitid, firstname, lastname, status, employeeid, role, designation, vistdate, proosalsId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VisitedByCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (unitid != null ? "unitid=" + unitid + ", " : "") +
                (firstname != null ? "firstname=" + firstname + ", " : "") +
                (lastname != null ? "lastname=" + lastname + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (employeeid != null ? "employeeid=" + employeeid + ", " : "") +
                (role != null ? "role=" + role + ", " : "") +
                (designation != null ? "designation=" + designation + ", " : "") +
                (vistdate != null ? "vistdate=" + vistdate + ", " : "") +
                (proosalsId != null ? "proosalsId=" + proosalsId + ", " : "") +
            "}";
    }
}

package com.finca.ccwapp.service.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.finca.ccwapp.domain.Deliquency} entity.
 */
public class DeliquencyDTO implements Serializable {
    private Long id;

    @NotNull
    @Size(max = 10)
    private String observatoinid;

    @NotNull
    @Size(max = 20)
    private String observation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObservatoinid() {
        return observatoinid;
    }

    public void setObservatoinid(String observatoinid) {
        this.observatoinid = observatoinid;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeliquencyDTO)) {
            return false;
        }

        return id != null && id.equals(((DeliquencyDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeliquencyDTO{" +
            "id=" + getId() +
            ", observatoinid='" + getObservatoinid() + "'" +
            ", observation='" + getObservation() + "'" +
            "}";
    }
}

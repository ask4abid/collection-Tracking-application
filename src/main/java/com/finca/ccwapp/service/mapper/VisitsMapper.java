package com.finca.ccwapp.service.mapper;

import com.finca.ccwapp.domain.*;
import com.finca.ccwapp.service.dto.VisitsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Visits} and its DTO {@link VisitsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface VisitsMapper extends EntityMapper<VisitsDTO, Visits> {
    @Mapping(target = "proosals", ignore = true)
    @Mapping(target = "removeProosals", ignore = true)
    Visits toEntity(VisitsDTO visitsDTO);

    default Visits fromId(Long id) {
        if (id == null) {
            return null;
        }
        Visits visits = new Visits();
        visits.setId(id);
        return visits;
    }
}

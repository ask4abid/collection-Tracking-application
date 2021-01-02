package com.finca.ccwapp.service.mapper;

import com.finca.ccwapp.domain.*;
import com.finca.ccwapp.service.dto.ProosalsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Proosals} and its DTO {@link ProosalsDTO}.
 */
@Mapper(componentModel = "spring", uses = { VisitsMapper.class, DeliquencyMapper.class })
public interface ProosalsMapper extends EntityMapper<ProosalsDTO, Proosals> {
    @Mapping(source = "visits.id", target = "visitsId")
    @Mapping(source = "deliquency.id", target = "deliquencyId")
    ProosalsDTO toDto(Proosals proosals);

    @Mapping(target = "visitedBies", ignore = true)
    @Mapping(target = "removeVisitedBy", ignore = true)
    @Mapping(source = "visitsId", target = "visits")
    @Mapping(source = "deliquencyId", target = "deliquency")
    Proosals toEntity(ProosalsDTO proosalsDTO);

    default Proosals fromId(Long id) {
        if (id == null) {
            return null;
        }
        Proosals proosals = new Proosals();
        proosals.setId(id);
        return proosals;
    }
}

package com.finca.ccwapp.service.mapper;

import com.finca.ccwapp.domain.*;
import com.finca.ccwapp.service.dto.VisitedByDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link VisitedBy} and its DTO {@link VisitedByDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProosalsMapper.class })
public interface VisitedByMapper extends EntityMapper<VisitedByDTO, VisitedBy> {
    @Mapping(source = "proosals.id", target = "proosalsId")
    VisitedByDTO toDto(VisitedBy visitedBy);

    @Mapping(source = "proosalsId", target = "proosals")
    VisitedBy toEntity(VisitedByDTO visitedByDTO);

    default VisitedBy fromId(Long id) {
        if (id == null) {
            return null;
        }
        VisitedBy visitedBy = new VisitedBy();
        visitedBy.setId(id);
        return visitedBy;
    }
}

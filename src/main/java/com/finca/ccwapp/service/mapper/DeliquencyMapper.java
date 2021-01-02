package com.finca.ccwapp.service.mapper;

import com.finca.ccwapp.domain.*;
import com.finca.ccwapp.service.dto.DeliquencyDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Deliquency} and its DTO {@link DeliquencyDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DeliquencyMapper extends EntityMapper<DeliquencyDTO, Deliquency> {
    @Mapping(target = "proosals", ignore = true)
    @Mapping(target = "removeProosals", ignore = true)
    Deliquency toEntity(DeliquencyDTO deliquencyDTO);

    default Deliquency fromId(Long id) {
        if (id == null) {
            return null;
        }
        Deliquency deliquency = new Deliquency();
        deliquency.setId(id);
        return deliquency;
    }
}

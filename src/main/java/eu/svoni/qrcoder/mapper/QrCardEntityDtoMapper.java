package eu.svoni.qrcoder.mapper;

import eu.svoni.qrcoder.dto.QrCardDto;
import eu.svoni.qrcoder.model.QrCard;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface QrCardEntityDtoMapper {

    QrCard dtoToEntity(QrCardDto dto);
    QrCardDto entityToDto(QrCard entity);

    void updateEntity(@MappingTarget QrCard entity, QrCardDto dto);
    void updateDto(@MappingTarget QrCardDto dto, QrCard entity);

}

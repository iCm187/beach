package fr.hb.ibm.beach.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fr.hb.ibm.beach.business.LienDeParente;
import fr.hb.ibm.beach.dto.LienDeParenteDto;

@Mapper(componentModel = "spring")
public interface LienDeParenteMapper {

	LienDeParenteDto toDto(LienDeParente lienDeParente);
	
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "clients", ignore = true)
	
	LienDeParente toEntity(LienDeParenteDto lienDeParenteDto);
}

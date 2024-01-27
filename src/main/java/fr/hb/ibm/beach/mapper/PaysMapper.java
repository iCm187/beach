package fr.hb.ibm.beach.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fr.hb.ibm.beach.business.Pays;
import fr.hb.ibm.beach.dto.PaysDto;

@Mapper(componentModel = "spring")
public interface PaysMapper {

    /**
     * Méthode qui convertit un métier en Dto
     * 
     * @param pays
     * @return un objet Dto
     */
    PaysDto toDto(Pays pays);
    
    @Mapping(target = "clients", ignore = true)

    /**
     * Méthode qui convertit un Dto en métier
     * 
     * @param paysDto
     * @return un objet métier
     */
    Pays toEntity(PaysDto paysDto);
}

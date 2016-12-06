package com.camel.transform;

public interface GenericTransformer<DTO, ENTITY> {
    DTO convertToDto(ENTITY entity);
    ENTITY convertToEntity(DTO dto);
}

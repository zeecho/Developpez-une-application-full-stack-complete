package com.openclassrooms.mddapi.mapper;

import java.util.List;

/**
 * Interface for entity mappers.
 * @param <D> The DTO type.
 * @param <E> The entity type.
 */
public interface EntityMapper<D, E> {
    /**
     * Maps a DTO to an entity.
     * 
     * @param dto The DTO to be mapped.
     * @return The corresponding entity.
     */
    E toEntity(D dto);

    /**
     * Maps an entity to a DTO.
     * 
     * @param entity The entity to be mapped.
     * @return The corresponding DTO.
     */
    D toDto(E entity);

    /**
     * Maps a list of DTOs to a list of entities.
     * 
     * @param dtoList The list of DTOs to be mapped.
     * @return The corresponding list of entities.
     */
    List<E> toEntity(List<D> dtoList);

    /**
     * Maps a list of entities to a list of DTOs.
     * 
     * @param entityList The list of entities to be mapped.
     * @return The corresponding list of DTOs.
     */
    List<D> toDto(List<E> entityList);
}

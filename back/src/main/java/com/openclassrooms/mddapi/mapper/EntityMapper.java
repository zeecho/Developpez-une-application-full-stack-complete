package com.openclassrooms.mddapi.mapper;

import java.util.List;

/**
 * Interface for entity mappers.
 * @param <D> The DTO type.
 * @param <E> The entity type.
 */
public interface EntityMapper<D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);
}

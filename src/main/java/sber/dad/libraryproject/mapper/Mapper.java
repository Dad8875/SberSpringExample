package sber.dad.libraryproject.mapper;

import sber.dad.libraryproject.dto.GenericDTO;
import sber.dad.libraryproject.model.GenericModel;

import java.util.List;

public interface Mapper<E extends GenericModel, D extends GenericDTO> {

    E toEntity(D d);

    D toDTO(E e);

    List<E> toEntities(List<D> d);

    List<D> toDTOs(List<E> e);

}

package sber.dad.libraryproject.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sber.dad.libraryproject.dto.GenericDTO;
import sber.dad.libraryproject.model.GenericModel;

import java.util.List;
import java.util.Objects;
import java.util.Set;


@Component
public abstract class GenericMapper<E extends GenericModel, D extends GenericDTO> implements Mapper<E, D> {

    protected final ModelMapper modelMapper;

    private final Class<E> entityClass;

    private final Class<D> dtoClass;

    protected GenericMapper(ModelMapper modelMapper, Class<E> entityClass, Class<D> dtoClass) {
        this.modelMapper = modelMapper;
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    public E toEntity(D d) {
        return Objects.isNull(d) ? null : modelMapper.map(d, entityClass);
    }

    @Override
    public D toDTO(E e) {
        return Objects.isNull(e) ? null : modelMapper.map(e, dtoClass);
    }

    @Override
    public List<E> toEntities(List<D> d) {
        return d.stream().map(this::toEntity).toList();
    }

    @Override
    public List<D> toDTOs(List<E> e) {
        return e.stream().map(this::toDTO).toList();
    }

    Converter<D, E> toEntityConverter() {
        return context -> {
            D source =  context.getSource();
            E destination =  context.getDestination();
            mapSpecificField(source, destination);
            return context.getDestination();
        };
    }
    Converter<E, D> toDTOConverter() {
        return context -> {
            E source =  context.getSource();
            D destination =  context.getDestination();
            mapSpecificField(source, destination);
            return context.getDestination();
        };
    }

    protected abstract void mapSpecificField(D source, E destination);

    protected abstract void mapSpecificField(E source, D destination);

    protected abstract Set<Long> getIds(E entity);

    @PostConstruct
    protected abstract void setupMapper();

}



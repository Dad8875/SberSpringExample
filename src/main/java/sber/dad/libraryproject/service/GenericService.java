package sber.dad.libraryproject.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import sber.dad.libraryproject.dto.GenericDTO;
import sber.dad.libraryproject.mapper.GenericMapper;
import sber.dad.libraryproject.model.GenericModel;
import sber.dad.libraryproject.repository.GenericRepository;

import java.time.LocalDateTime;
import java.util.List;


@Service
public abstract class GenericService<T extends GenericModel, N extends GenericDTO> {

    protected final GenericRepository<T> genericRepository;
    protected final GenericMapper<T, N> genericMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public GenericService(GenericRepository<T> genericRepository, GenericMapper<T, N> genericMapper) {
        this.genericRepository = genericRepository;
        this.genericMapper = genericMapper;
    }

    public List<N> getAll() {
        return genericMapper.toDTOs(genericRepository.findAll());
    }

    public N getOne(Long id) {
        return genericMapper.toDTO(genericRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found!")));
    }

    public N create(N n) {
        n.setCreatedBy("ADMIN");
        n.setCreatedWhen(LocalDateTime.now());
        return genericMapper.toDTO(genericRepository.save(genericMapper.toEntity(n)));
    }

    public N update(N n) {
        return genericMapper.toDTO(genericRepository.save(genericMapper.toEntity(n)));
    }

    public void delete(Long id) {
        genericRepository.deleteById(id);
    }
}



package sber.dad.libraryproject.service;

import org.springframework.stereotype.Service;
import sber.dad.libraryproject.dto.RoleDTO;
import sber.dad.libraryproject.dto.UserDTO;
import sber.dad.libraryproject.mapper.UserMapper;
import sber.dad.libraryproject.model.User;
import sber.dad.libraryproject.repository.UserRepository;


@Service
public class UserService extends GenericService<User, UserDTO> {

    protected UserService(UserRepository userRepository, UserMapper userMapper) {
        super(userRepository, userMapper);
    }

    @Override
    public UserDTO create(UserDTO userDTO) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(1L);
        userDTO.setRole(roleDTO);
        return genericMapper.toDTO(genericRepository.save(genericMapper.toEntity(userDTO)));
    }
}

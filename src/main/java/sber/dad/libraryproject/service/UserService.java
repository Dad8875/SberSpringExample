package sber.dad.libraryproject.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sber.dad.libraryproject.dto.RoleDTO;
import sber.dad.libraryproject.dto.UserDTO;
import sber.dad.libraryproject.mapper.UserMapper;
import sber.dad.libraryproject.model.User;
import sber.dad.libraryproject.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Locale;


@Service
public class UserService extends GenericService<User, UserDTO> {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    protected UserService(UserRepository userRepository,
                          UserMapper userMapper,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(userRepository, userMapper);
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDTO create(UserDTO userDTO) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(1L);
        userDTO.setRole(roleDTO);
        userDTO.setCreatedBy("REGISTRATION FORM");
        userDTO.setCreatedWhen(LocalDateTime.now());
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        return genericMapper.toDTO(genericRepository.save(genericMapper.toEntity(userDTO)));
    }

    public UserDTO getUserByLogin(String login) {
        return genericMapper.toDTO(((UserRepository) genericRepository).findUserByLogin(login));
    }

    public UserDTO getUserByEmail(String email) {
        return genericMapper.toDTO(((UserRepository) genericRepository).findUserByEmail(email));
    }

}


package com.vma.userapi.service;

import com.vma.userapi.dto.UserResponseDTO;
import com.vma.userapi.model.User;
import com.vma.userapi.model.UserPhones;
import com.vma.userapi.repository.UserRepository;
import com.vma.userapi.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public UserResponseDTO createUser(User user) {
        if (!isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("El correo electrónico no tiene un formato válido.");
        }

        // Check if user ID already exists
        if (user.getId() != null && userRepository.existsById(user.getId())) {
            throw new IllegalArgumentException("El ID de usuario ya existe.");
        }

        // Check if email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("El correo electrónico ya está registrado.");
        }

        // Fecha actual para campos createDate y modifiedDate
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        user.setCreateDate(currentDate);
        user.setModifiedDate(currentDate);

        // Asignar un token JWT
        user.setTokenJwt(jwtTokenUtil.generateToken(user.getEmail()));

        // Asignar teléfonos al usuario
        if (user.getPhones() != null) {
            for (UserPhones phone : user.getPhones()) {
                phone.setCountryCode(phone.getCountryCode());
                phone.setCityCode(phone.getCityCode());
                phone.setNumber(phone.getNumber());

            }
        }

        // Guardar el usuario en la base de datos
        User savedUser = userRepository.save(user);

        // Create response DTO
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(savedUser.getId());
        responseDTO.setCreateDate(savedUser.getCreateDate());
        responseDTO.setModifiedDate(savedUser.getModifiedDate());
        responseDTO.setLastLogin(savedUser.getLastLogin() != null ? savedUser.getLastLogin() : savedUser.getCreateDate());
        responseDTO.setTokenJwt(savedUser.getTokenJwt());
        responseDTO.setIsActive(savedUser.getIsActive());

        return responseDTO;
    }

    private boolean isValidEmail(String email) {
        // Implement email validation logic
        return true;
    }
}
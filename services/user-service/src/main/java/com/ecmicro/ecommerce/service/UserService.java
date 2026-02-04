package com.ecmicro.ecommerce.service;

import com.ecmicro.ecommerce.dto.request.UserCreateDTO;
import com.ecmicro.ecommerce.dto.response.UserInfoDTO;
import com.ecmicro.ecommerce.mapper.UserMapper;
import com.ecmicro.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final List<String> ALLOWED_SORT_FIELDS = Arrays.asList("email", "username");

    public Page<UserInfoDTO> findAllByPagination(int page, int size, String sortBy, boolean ascending) {
        // validate the passed in orderBy value
        if (!ALLOWED_SORT_FIELDS.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sort field: " + sortBy);
        }
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        List<UserInfoDTO> detailDTOs = userRepository
                .findAll(pageable)    // must pass "pageable" -> to correctly apply pagination
                .stream()   // convert to stream - a sequence of elements supporting sequential operations
                .map(mapper::toUserInfo)
                .toList();

        // convert to Page<UserInfoDTO> with params: content, Pageable object, total elements
        return new PageImpl<>(detailDTOs, pageable, detailDTOs.size());
    }

    public UserInfoDTO findById(Long userId) {
        return mapper.toUserInfo(userRepository.findById(userId).get());
    }

    public Long createUser(UserCreateDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()) != null
            || userRepository.findByUsername(dto.getUsername()) != null) {
            // handle exception
            return null;
        }

        return userRepository.save(mapper.toUser(dto)).getId();
    }
}

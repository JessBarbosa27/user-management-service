package com.jesslabs.user_management.mapper;

import com.jesslabs.user_management.dto.AddUserRequestDTO;
import com.jesslabs.user_management.dto.UpdateUserRequestDTO;
import com.jesslabs.user_management.model.User;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserManagementMapper {

    User addUserRequestDTOToUser(AddUserRequestDTO userAddRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserRequestDTOToUser(UpdateUserRequestDTO userUpdateRequestDTO, @MappingTarget User user);

}

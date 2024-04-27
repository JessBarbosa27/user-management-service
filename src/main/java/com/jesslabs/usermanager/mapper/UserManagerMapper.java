package com.jesslabs.usermanager.mapper;

import com.jesslabs.usermanager.dto.AddUserRequestDTO;
import com.jesslabs.usermanager.dto.UpdateUserRequestDTO;
import com.jesslabs.usermanager.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserManagerMapper {

    User addUserRequestDTOToUser(AddUserRequestDTO userAddRequestDTO);

    void updateUserRequestDTOToUser(UpdateUserRequestDTO userUpdateRequestDTO, @MappingTarget User user);

}

package com.jesslabs.usermanager.mapper;

import com.jesslabs.usermanager.dto.AddUserRequestDTO;
import com.jesslabs.usermanager.dto.UpdateUserRequestDTO;
import com.jesslabs.usermanager.model.User;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserManagerMapper {

    User addUserRequestDTOToUser(AddUserRequestDTO userAddRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserRequestDTOToUser(UpdateUserRequestDTO userUpdateRequestDTO, @MappingTarget User user);

}

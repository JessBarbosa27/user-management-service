package com.jesslabs.usermanager.repository;

import com.jesslabs.usermanager.dto.GetUsersDTO;
import com.jesslabs.usermanager.query.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserManagerRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    Long totalCount = (long) 0;


    public Page<GetUsersDTO> getUsers(Pageable page, String name, String username, String role) {
        totalCount = (long) 0;
        RowMapper<GetUsersDTO> mapper = new RowMapper<GetUsersDTO>() {
            public GetUsersDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

                GetUsersDTO users = new GetUsersDTO();
                users.setId(rs.getLong("id"));
                users.setName(rs.getString("name"));
                users.setUsername(rs.getString("username"));
                users.setRole(rs.getString("role"));
                users.setEmail(rs.getString("email"));
                users.setPhone(rs.getString("phone"));

                totalCount = rs.getLong("total");
                return users;
            }
        };
        List<GetUsersDTO> tempList = jdbcTemplate.query(SQL.getUsers(page, name, username, role), mapper);
        return new PageImpl<>(tempList, page, totalCount);
    }
}

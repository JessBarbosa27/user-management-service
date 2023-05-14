package com.jesslabs.usermanager.query;

import com.jesslabs.usermanager.util.AppUtil;
import org.springframework.data.domain.Pageable;

/**
 * @author barbo on 22-03-2023
 */
public class SQL {

    public static final String getUsers(Pageable page, String name, String username, String role) {
        return "SELECT *, COUNT(1) OVER() as total  FROM app_user WHERE id IS NOT NULL"
                + (AppUtil.isNullString(name) ? "" : " AND name ILIKE '%" + name + "%' ")
                + (AppUtil.isNullString(username) ? "" : " AND username ILIKE '%" + username + "%' ")
                + (AppUtil.isNullString(role) ? "" : " AND role ILIKE '%" + role + "%' ")
                + " LIMIT " + page.getPageSize() + " " + " OFFSET " + page.getOffset();
    }
}

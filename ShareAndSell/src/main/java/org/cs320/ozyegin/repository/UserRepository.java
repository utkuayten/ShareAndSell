package org.cs320.ozyegin.repository;

import org.cs320.ozyegin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Repository
public class UserRepository extends JdbcDaoSupport {

    private final String findByEmailQuery = "SELECT * FROM main.users WHERE mail = ?";

    @Autowired
    public void setDatasource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    private final RowMapper<User> userRowMapper = (resultSet, i) ->
            new User()
                    .name(resultSet.getString("name"))
                    .password(resultSet.getString("password"))
                    .email(resultSet.getString("email"))
                    .role(resultSet.getString("role"));

    public User save(User user) {
        // TODO: Implement save functionality here
        return null;
    }

    public User findByEmail(String email) {
        if (email == null || email.isBlank()) {
            return null;
        } else {
            try {
                JdbcTemplate template = Objects.requireNonNull(getJdbcTemplate());
                return template.queryForObject(findByEmailQuery, new Object[]{email}, userRowMapper);
            } catch (Exception ex) {
                // Log the exception for debugging purposes
                ex.printStackTrace(); // Replace with your logging mechanism
                return null; // Return null or throw custom exception as per your error handling strategy
            }
        }
    }
}

package org.cs320.ozyegin.repository;

import org.cs320.ozyegin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Objects;

@Repository
public class UserRepository extends JdbcDaoSupport {

    final String registerUser = "INSERT INTO users * FROM users WHERE mail = ?";
    final String findByEmailQuery = "SELECT * FROM users WHERE email = ?";
    final String createUser = "INSERT INTO users (name, email,password , role) VALUES(?,?,?,?)";
    @Autowired
    public void setDatasource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    private final RowMapper<User> userRowMapper = (resultSet, i) ->
            new User()
                    .name(resultSet.getString("name"))
                    .password(resultSet.getString("password"))
                    .email(resultSet.getString("mail"))
                    .role(resultSet.getString("role"));

    public User save(User user) throws Exception{
        // TODO: Implement save functionality here

        System.out.println(user.getRole());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getName());

        if(user.getName() == null || user.getName().isEmpty()) {
            throw new Exception("Invalid User entry!!");
        }
        else {
            Objects.requireNonNull(getJdbcTemplate()).update(createUser, user.getName(),user.getEmail(),user.getPassword(),user.getRole());
            return user;
        }
    }

    public String create(User user) throws Exception {
        if(user.getName() == null || user.getName().isEmpty()) {
            throw new Exception("Invalid User entry!!");
        }
        else {
            Objects.requireNonNull(getJdbcTemplate()).update(createUser, user.getName(),user.getEmail(),user.getPassword(),user.getRole());
            return user.getName();
        }
    }

    public User findByEmail(String email) {
        if (email == null || email.isEmpty()) {
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

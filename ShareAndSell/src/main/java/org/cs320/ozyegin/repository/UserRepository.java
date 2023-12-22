package org.cs320.ozyegin.repository;

import org.cs320.ozyegin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserRepository extends JdbcDaoSupport {
    @Autowired
    public void setDatasource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    private final RowMapper<User> loginRowMapper = (resultSet, i) -> new User()
            .name(resultSet.getString("name"))
            .password(resultSet.getString("password"));

    public User save(User user) {
        //TODO: implement here.
        return null;
    }


    public User findByEmail(String username) {
        //TODO: implement here.
        return null;
    }
}

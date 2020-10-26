package la.kingtide.dao;


import la.kingtide.dto.UserDto;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Optional;
import java.util.UUID;

public interface UserDao {

    @SqlUpdate("INSERT INTO user (name, role_id) VALUES(:u.name, (SELECT id FROM roles WHERE name = :u.role LIMIT 1))")
    void createUser(@BindBean("u") UserDto userDto);

    @RegisterBeanMapper(UserDto.class)
    @SqlQuery("SELECT u.id, u.name, (SELECT name FROM roles WHERE id = u.role_id LIMIT 1) AS role FROM user u WHERE id = :id")
    Optional<UserDto> getUserById(@Bind("id") UUID id);



}

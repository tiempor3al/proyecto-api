package la.kingtide.dal;


import la.kingtide.dao.UserDao;
import la.kingtide.dto.ResponseDto;
import la.kingtide.dto.UserDto;
import la.kingtide.services.JdbiService;
import org.jboss.logging.Logger;
import org.jdbi.v3.core.Jdbi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;


@ApplicationScoped
public class UserDal {

    private static final Logger LOGGER = Logger.getLogger(UserDal.class);

    @Inject
    JdbiService jdbiService;



    public ResponseDto<String> createUser(UserDto userDto) {

        ResponseDto responseDto = new ResponseDto<String>();
        responseDto.setSuccess(false);

        Jdbi jdbi = jdbiService.getInstance();

        try {
            jdbi.withExtension(UserDao.class, dao -> {
                dao.createUser(userDto);
                responseDto.setSuccess(true);
                responseDto.setData(userDto.getId().toString());
                return true;
            });
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
            responseDto.setData(e.getMessage());

        }
        return responseDto;
    }

    public Optional<UserDto> getUser(UUID userId) {
        Jdbi jdbi = jdbiService.getInstance();

        return jdbi.withExtension(UserDao.class, dao -> {
            Optional<UserDto> userDtoOptional = dao.getUserById(userId);
            return userDtoOptional;
        });
    }


}

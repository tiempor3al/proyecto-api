package la.kingtide.resources;

import la.kingtide.dal.UserDal;
import la.kingtide.dto.ResponseDto;
import la.kingtide.dto.UserDto;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.UUID;

@Path("/user")
public class UsersResource {

    @Inject
    UserDal userDal;


    @POST
    @Tag(name = "users")
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Register a new user")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
    })
    public Response registerUser(UserDto userDto) {
        UUID userId = UUID.randomUUID();
        userDto.setId(userId);
        var responseDto = userDal.createUser(userDto);
        return Response.ok(responseDto).build();
    }

}
package fca.suayed.resources;


import fca.suayed.dal.StoreDal;
import fca.suayed.dto.ProductDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;


@Path("/")
public class HelloResource {

    @Inject
    StoreDal storeDal;


    @GET
    @Path("/hello")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Say hello")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
    })
    public Response sayHello() {


        return Response.ok("Hello from RESTEasy Reactive").build();
    }




}
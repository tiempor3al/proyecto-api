package fca.unam.mx.application;


import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title="Project API",
                version = "1.0.0"
        )
)
public class ApiApplication extends Application {
}

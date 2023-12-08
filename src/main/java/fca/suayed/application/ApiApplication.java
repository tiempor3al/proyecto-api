package fca.suayed.application;



import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title="Project API",
                version = "1.0.0"
        )
)
public class ApiApplication extends Application {
}

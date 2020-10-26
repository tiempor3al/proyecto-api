package la.kingtide.application;



import org.eclipse.microprofile.openapi.annotations.Components;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        tags = {
                @Tag(name="admin", description="Endpoints that require admin role"),
                @Tag(name="user", description="Endpoints that require user role"),
        },
        components = @Components(schemas = @Schema(title = "Starter-Quarkus"),
                securitySchemes = {@SecurityScheme(securitySchemeName = "bearerAuth",scheme = "bearer",bearerFormat = "JWT",type = SecuritySchemeType.HTTP)}
        ),
        info = @Info(
                title="Starter API",
                version = "1.0.0"
        )
)
public class ApiApplication extends Application {
}

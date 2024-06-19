package eu.catalkaya.pokertracker.view;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/app")
public class AppIndexView {
  @Inject
  @Location("app/index.html")
  Template template;

  @GET
  @Produces(MediaType.TEXT_HTML)
  @RolesAllowed({"user", "admin"})
  public String get() {
    return template.render();
  }
}

package eu.catalkaya.pokertracker.view;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class LoginView {
  @Inject
  @Location("login.html")
  Template template;

  @GET
  @Produces(MediaType.TEXT_HTML)
  public String get() {
    return template.render();
  }
}

package eu.catalkaya.pokertracker.view;

import eu.catalkaya.pokertracker.model.PlayerAmountDto;
import eu.catalkaya.pokertracker.service.TransactionService;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/app")
public class AppIndexView {
  @Inject
  @Location("app/index.html")
  Template template;

  @Inject
  TransactionService transactionService;

  @GET
  @Produces(MediaType.TEXT_HTML)
  @RolesAllowed({"user", "admin"})
  public String get() {
    List<PlayerAmountDto> amounts = transactionService.getCurrentBalances();
    return template.data("amounts", amounts).render();
  }
}

package eu.catalkaya.pokertracker.view;

import eu.catalkaya.pokertracker.model.Player;
import eu.catalkaya.pokertracker.service.PlayerService;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import org.jboss.resteasy.reactive.RestPath;

@Path("/app/player")
public class PlayerView {
  @Inject
  @Location("app/player.html")
  Template page;

  @Inject
  @Location("app/player_table.html")
  Template table;

  @Inject
  PlayerService playerService;

  @GET
  @Produces(MediaType.TEXT_HTML)
  @RolesAllowed({"admin", "user"})
  public String get(){
    List<Player> players = playerService.getAllPlayers();
    return page.data("players", players).render();
  }

  @POST
  @Produces(MediaType.TEXT_HTML)
  @RolesAllowed({"admin", "user"})
  public String postNewPlayer(@FormParam("name") String name) {
    playerService.createPlayer(name);
    List<Player> players = playerService.getAllPlayers();
    return table.data("players", players).render();
  }

  @DELETE
  @Produces(MediaType.TEXT_HTML)
  @RolesAllowed({"admin", "user"})
  @Path("/{playerId:\\d+}")
  public String deletePlayer(@PathParam("playerId") int playerId) {
    playerService.deletePlayer(playerId);
    return get();
  }
}

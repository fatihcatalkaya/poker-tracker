package eu.catalkaya.pokertracker.view;

import eu.catalkaya.pokertracker.model.LatestTransactionDto;
import eu.catalkaya.pokertracker.model.Player;
import eu.catalkaya.pokertracker.service.PlayerService;
import eu.catalkaya.pokertracker.service.TransactionService;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.List;

@Path("/app/transaction")
public class TransactionView {

  @Inject
  PlayerService playerService;

  @Inject
  TransactionService transactionService;

  @Inject
  @Location("app/transactions/add_transaction.html")
  Template template;

  @Inject
  @Location("app/transactions/last_transactions.html")
  Template lastTransaction;

  @GET
  @RolesAllowed({"admin", "user"})
  @Produces(MediaType.TEXT_HTML)
  public String get(){
    List<Player> players = playerService.getAllPlayers();
    List<LatestTransactionDto> latestTransactions = transactionService.getLatestTransactions();
    return template
        .data("players", players)
        .data("transactions", latestTransactions)
        .data("today", LocalDate.now())
        .render();
  }

  @POST
  @RolesAllowed({"admin", "user"})
  @Produces(MediaType.TEXT_HTML)
  public String addTransaction(@FormParam("player_id") int playerId, @FormParam("amount") float amount) {
    if(!playerService.existsPlayer(playerId)){
      throw new IllegalArgumentException("No player with player id %d exists".formatted(playerId));
    }
    transactionService.createTransaction(playerId, amount);
    List<LatestTransactionDto> latestTransactions = transactionService.getLatestTransactions();
    return lastTransaction.data("transactions", latestTransactions).render();
  }
}

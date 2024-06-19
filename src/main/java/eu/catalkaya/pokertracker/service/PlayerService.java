package eu.catalkaya.pokertracker.service;

import static eu.catalkaya.pokertracker.database.Tables.PLAYER;
import static org.jooq.Records.mapping;

import eu.catalkaya.pokertracker.model.Player;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import org.jooq.DSLContext;

@ApplicationScoped
public class PlayerService {

  @Inject
  DSLContext jooq;

  public List<Player> getAllPlayers() {
    return jooq.select(PLAYER.ID, PLAYER.NAME)
        .from(PLAYER)
        .orderBy(PLAYER.NAME.asc())
        .fetch(mapping(Player::new));
  }

  public void createPlayer(String name){
    jooq.insertInto(PLAYER)
        .columns(PLAYER.NAME)
        .values(name)
        .execute();
  }

  public void deletePlayer(int id) {
    jooq.deleteFrom(PLAYER).where(PLAYER.ID.eq(id)).execute();
  }
}

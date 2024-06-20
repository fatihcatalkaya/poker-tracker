package eu.catalkaya.pokertracker.service;

import static eu.catalkaya.pokertracker.database.Tables.PLAYER;
import static eu.catalkaya.pokertracker.database.Tables.TRANSACTIONS;
import static org.jooq.Records.mapping;
import static org.jooq.impl.DSL.sum;

import eu.catalkaya.pokertracker.model.LatestTransactionDto;
import eu.catalkaya.pokertracker.model.PlayerAmountDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import org.jooq.DSLContext;

@ApplicationScoped
public class TransactionService {
  @Inject
  DSLContext jooq;

  public void deleteAllTransactionsForPlayer(int playerId) {
    jooq.deleteFrom(TRANSACTIONS)
        .where(TRANSACTIONS.PLAYER_ID.eq(playerId))
        .execute();
  }

  public List<PlayerAmountDto> getCurrentBalances() {
    return jooq.select(PLAYER.NAME, sum(TRANSACTIONS.AMOUNT).as("amount"))
        .from(TRANSACTIONS)
        .join(PLAYER).on(TRANSACTIONS.PLAYER_ID.eq(PLAYER.ID))
        .groupBy(TRANSACTIONS.PLAYER_ID)
        .orderBy(sum(TRANSACTIONS.AMOUNT).as("amount").desc())
        .fetch(mapping(PlayerAmountDto::new));
  }

  public void createTransaction(int playerId, float amount) {
    jooq.insertInto(TRANSACTIONS)
        .columns(TRANSACTIONS.PLAYER_ID, TRANSACTIONS.AMOUNT)
        .values(playerId, amount)
        .execute();
  }

  public List<LatestTransactionDto> getLatestTransactions() {
    return jooq.select(TRANSACTIONS.ID, TRANSACTIONS.CREATED_AT, PLAYER.NAME, TRANSACTIONS.AMOUNT)
        .from(TRANSACTIONS)
        .join(PLAYER)
        .on(TRANSACTIONS.PLAYER_ID.eq(PLAYER.ID))
        .orderBy(TRANSACTIONS.CREATED_AT.desc())
        .limit(20)
        .fetch(mapping(LatestTransactionDto::new));
  }
}

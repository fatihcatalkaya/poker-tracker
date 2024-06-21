package eu.catalkaya.pokertracker.service;

import static eu.catalkaya.pokertracker.database.Tables.PLAYER;
import static eu.catalkaya.pokertracker.database.Tables.TRANSACTIONS;
import static org.jooq.Records.mapping;
import static org.jooq.impl.DSL.nvl;
import static org.jooq.impl.DSL.recordType;
import static org.jooq.impl.DSL.sum;

import eu.catalkaya.pokertracker.model.LatestTransactionDto;
import eu.catalkaya.pokertracker.model.Pair;
import eu.catalkaya.pokertracker.model.PlayerAmountDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

  public Pair<List<LocalDateTime>, Map<String, List<BigDecimal>>> getDataForTrendChart() {
    LocalDateTime firstTransaction = jooq.select(TRANSACTIONS.CREATED_AT)
        .from(TRANSACTIONS)
        .orderBy(TRANSACTIONS.CREATED_AT)
        .limit(1)
        .fetchOne(TRANSACTIONS.CREATED_AT);

    if (firstTransaction == null) {
      // Return empty set, since no entries exist
      return null;
    }
    firstTransaction = firstTransaction.minusDays(1);

    // Compute dates to check
    List<LocalDateTime> timesToCheck = new ArrayList<>();
    timesToCheck.add(firstTransaction);
    final LocalDateTime now = LocalDateTime.now();
    LocalDateTime runningDate = firstTransaction.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
    while (runningDate.isBefore(now)) {
      timesToCheck.add(runningDate);
      runningDate = runningDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
    }
    timesToCheck.add(now);

    Map<String, List<BigDecimal>> dataMatrix2 = new HashMap<>();
    for(LocalDateTime time : timesToCheck) {
      jooq.select(PLAYER.NAME, nvl(sum(TRANSACTIONS.AMOUNT), BigDecimal.ZERO))
          .from(PLAYER)
          .leftJoin(TRANSACTIONS).on(PLAYER.ID.eq(TRANSACTIONS.PLAYER_ID)).and(TRANSACTIONS.CREATED_AT.lessOrEqual(time))
          .groupBy(PLAYER.NAME)
          .orderBy(PLAYER.NAME)
          .fetch()
          .forEach(record -> {
            if(!dataMatrix2.containsKey(record.value1())){
              dataMatrix2.put(record.value1(), new ArrayList<>());
            }
            dataMatrix2.get(record.value1()).add(record.value2());
          });
    }

    return new Pair<>(timesToCheck, dataMatrix2);
  }
}

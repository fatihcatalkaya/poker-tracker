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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Path("/app")
public class AppIndexView {
  @Inject
  @Location("app/index.html")
  Template template;

  @Inject
  TransactionService transactionService;

  final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

  @GET
  @Produces(MediaType.TEXT_HTML)
  @RolesAllowed({"user", "admin"})
  public String get() {
    List<PlayerAmountDto> amounts = transactionService.getCurrentBalances();
    var data = transactionService.getDataForTrendChart();

    if(data == null){
      return template
          .data("amounts", amounts)
          .data("plotChart", false)
          .render();
    }

    // Compile Javascript Array String for Dates
    String dateStrings = data.first()
        .stream()
        .map(date -> date.format(dtf))
        .map(s -> String.format("'%s'", s))
        .collect(Collectors.joining(","));

    // Compile Javascript Object for Series
    String seriesString = data.second()
        .entrySet()
        .stream()
        .map(entry -> {
          String valueStrings = entry.getValue()
              .stream()
              .map(BigDecimal::toString)
              .collect(Collectors.joining(","));
          return String.format(""" 
                  { name: %s,
                    symbolSize: 20,
                    type: 'line',
                    smooth: true,
                    emphasis: {
                      focus: 'series'
                    },
                    endLabel: {
                      show: true,
                      formatter: '{a}',
                      distance: 20
                    },
                    lineStyle: {
                      width: 4
                    },
                    data: [ %s ] }""", entry.getKey(), valueStrings);
        }).collect(Collectors.joining(","));

    return template
        .data("amounts", amounts)
        .data("plotChart", true)
        .data("dateStrings", dateStrings)
        .data("series", seriesString)
        .render();
  }
}

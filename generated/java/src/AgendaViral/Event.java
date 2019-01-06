package AgendaViral;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Event extends Data {
  private String name;
  private Data.Date date;
  private Number capacity;
  private Number popularity = 0L;
  private VDMSet tickets = SetUtil.set();
  private Number ticketPrice;

  public void cg_init_Event_1(final String n, final Number c, final Number p, final Data.Date d) {

    name = n;
    capacity = c;
    ticketPrice = p;
    date = Utils.copy(d);
    return;
  }

  public Event(final String n, final Number c, final Number p, final Data.Date d) {

    cg_init_Event_1(n, c, p, Utils.copy(d));
  }

  public String getName() {

    return name;
  }

  public Number getCapacity() {

    return capacity;
  }

  public Number getTicketPrice() {

    return ticketPrice;
  }

  public Number getFillPercent() {

    return Utils.divide((1.0 * tickets.size()), capacity.longValue()) * 100L;
  }

  public VDMSet getTickets() {

    return Utils.copy(tickets);
  }

  public void addTicket(final Number ticket) {

    tickets = SetUtil.union(Utils.copy(tickets), SetUtil.set(ticket));
  }

  public void removeTicket(final Number i) {

    tickets = SetUtil.diff(Utils.copy(tickets), SetUtil.set(i));
  }

  public void promote() {

    popularity = popularity.longValue() + 10L;
  }

  public Number getPopularity() {

    return popularity;
  }

  public Number getEarnings() {

    return tickets.size() * ticketPrice.doubleValue();
  }

  public Data.Date getDate() {

    return Utils.copy(date);
  }

  public Event() {}

  public String toString() {

    return "Event{"
        + "name := "
        + Utils.toString(name)
        + ", date := "
        + Utils.toString(date)
        + ", capacity := "
        + Utils.toString(capacity)
        + ", popularity := "
        + Utils.toString(popularity)
        + ", tickets := "
        + Utils.toString(tickets)
        + ", ticketPrice := "
        + Utils.toString(ticketPrice)
        + "}";
  }
}

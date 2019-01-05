package AgendaViral;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Event {
  private String name;
  private Date date;
  private Number capacity;
  private Number popularity = 0L;
  private VDMSet tickets = SetUtil.set();
  private Number ticketPrice;

  public void cg_init_Event_1(final String n, final Number c, final Number p, final Date d) {

    name = n;
    capacity = c;
    ticketPrice = p;
    date = Utils.copy(d);
    return;
  }

  public Event(final String n, final Number c, final Number p, final Date d) {
  
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

  public Number getEarnings() {

    return tickets.size() * ticketPrice.doubleValue();
  }

  public Event() {}

  public static Number DaysOfMonth(final Number y, final Number m) {

    if (Utils.equals(m, 2L)) {
      if (isLeapYear(y)) {
        return 29L;

      } else {
        return 28L;
      }

    } else {
      Boolean orResult_1 = false;

      if (Utils.equals(m, 4L)) {
        orResult_1 = true;
      } else {
        Boolean orResult_2 = false;

        if (Utils.equals(m, 6L)) {
          orResult_2 = true;
        } else {
          Boolean orResult_3 = false;

          if (Utils.equals(m, 9L)) {
            orResult_3 = true;
          } else {
            orResult_3 = Utils.equals(m, 11L);
          }

          orResult_2 = orResult_3;
        }

        orResult_1 = orResult_2;
      }

      if (orResult_1) {
        return 30L;

      } else {
        return 31L;
      }
    }
  }

  public static Boolean isLeapYear(final Number y) {

    Boolean orResult_4 = false;

    Boolean andResult_5 = false;

    if (Utils.equals(Utils.mod(y.longValue(), 4L), 0L)) {
      if (!(Utils.equals(Utils.mod(y.longValue(), 100L), 0L))) {
        andResult_5 = true;
      }
    }

    if (andResult_5) {
      orResult_4 = true;
    } else {
      orResult_4 = Utils.equals(Utils.mod(y.longValue(), 400L), 0L);
    }

    return orResult_4;
  }

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

  public static class Date implements Record {
    public Number day;
    public Number month;
    public Number year;

    public Date(final Number _day, final Number _month, final Number _year) {

      day = _day;
      month = _month;
      year = _year;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof Date)) {
        return false;
      }

      Date other = ((Date) obj);

      return (Utils.equals(day, other.day))
          && (Utils.equals(month, other.month))
          && (Utils.equals(year, other.year));
    }

    public int hashCode() {

      return Utils.hashCode(day, month, year);
    }

    public Date copy() {

      return new Date(day, month, year);
    }

    public String toString() {

      return "mk_Event`Date" + Utils.formatFields(day, month, year);
    }
  }

  public static Boolean inv_Date(final Date d) {

    Boolean andResult_6 = false;

    if (d.year.longValue() > 2018L) {
      Boolean andResult_7 = false;

      if (d.month.longValue() <= 12L) {
        if (d.day.longValue() <= DaysOfMonth(d.year, d.month).longValue()) {
          andResult_7 = true;
        }
      }

      if (andResult_7) {
        andResult_6 = true;
      }
    }

    return andResult_6;
  }
}

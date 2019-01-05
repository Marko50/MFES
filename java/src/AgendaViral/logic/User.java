package AgendaViral;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class User {
  private String name;
  private Number funds = 0L;
  private String password;
  private VDMSet events = SetUtil.set();
  private VDMSet tickets = SetUtil.set();

  public void cg_init_User_1(final String n, final String p) {

    name = n;
    password = p;
    return;
  }

  public User(final String n, final String p) {

    cg_init_User_1(n, p);
  }

  public VDMSet getEvents() {

    return Utils.copy(events);
  }

  public void addEvent(final String e) {

    events = SetUtil.union(Utils.copy(events), SetUtil.set(e));
  }

  public void removeEvent(final String e) {

    events = SetUtil.diff(Utils.copy(events), SetUtil.set(e));
  }

  public String getName() {

    return name;
  }

  public Number getFunds() {

    return funds;
  }

  public String getPassword() {

    return password;
  }

  public void addFunds(final Number f) {

    funds = funds.longValue() + f.longValue();
  }

  public void removeFunds(final Number f) {

    funds = funds.longValue() - f.longValue();
  }

  public VDMSet getTickets() {

    return Utils.copy(tickets);
  }

  public void buyTicket(final Number ticketPrice, final Number t) {

    removeFunds(ticketPrice);
    addTicket(t);
  }

  public void addTicket(final Number ticket) {

    tickets = SetUtil.union(Utils.copy(tickets), SetUtil.set(ticket));
  }

  public void removeTicket(final Number i) {

    tickets = SetUtil.diff(Utils.copy(tickets), SetUtil.set(i));
  }

  public void promoteEvent() {

    removeFunds(10L);
  }

  public User() {}

  public String toString() {

    return "User{"
        + "name := "
        + Utils.toString(name)
        + ", funds := "
        + Utils.toString(funds)
        + ", password := "
        + Utils.toString(password)
        + ", events := "
        + Utils.toString(events)
        + ", tickets := "
        + Utils.toString(tickets)
        + "}";
  }
}

package AgendaViral;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TicketManager {
  private VDMMap tickets = MapUtil.map();

  public void cg_init_TicketManager_1(final VDMMap ts) {

    tickets = Utils.copy(ts);
    return;
  }

  public TicketManager(final VDMMap ts) {

    cg_init_TicketManager_1(Utils.copy(ts));
  }

  public VDMMap getTickets() {

    return Utils.copy(tickets);
  }

  public void addTicket(final Ticket ticket) {

    tickets =
        MapUtil.override(Utils.copy(tickets), MapUtil.map(new Maplet(ticket.getID(), ticket)));
  }

  public void removeTicket(final Number i) {

    tickets = MapUtil.domResBy(SetUtil.set(i), Utils.copy(tickets));
  }

  public TicketManager() {}

  public String toString() {

    return "TicketManager{" + "tickets := " + Utils.toString(tickets) + "}";
  }
}

package AgendaViral;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class EventManager {
  private VDMMap events = MapUtil.map();

  public void cg_init_EventManager_1(final VDMMap evs) {

    events = Utils.copy(evs);
    return;
  }

  public EventManager(final VDMMap evs) {

    cg_init_EventManager_1(Utils.copy(evs));
  }

  public VDMMap getEvents() {

    return Utils.copy(events);
  }

  public Event getEvent(final String e) {

    return ((Event) Utils.get(events, e));
  }

  public void addEvent(final Event event) {

    events = MapUtil.override(Utils.copy(events), MapUtil.map(new Maplet(event.getName(), event)));
  }

  public void removeEvent(final String e) {

    events = MapUtil.domResBy(SetUtil.set(e), Utils.copy(events));
  }

  private Boolean eventExists(final String e) {

    return SetUtil.inSet(e, MapUtil.dom(Utils.copy(events)));
  }

  public VDMSet getEventTickets(final String e) {

    return ((Event) Utils.get(events, e)).getTickets();
  }

  public VDMSet getEventTicketsUser(final String e, final String u, final TicketManager tm) {

    VDMSet tickets = SetUtil.set();
    for (Iterator iterator_1 = ((Event) Utils.get(events, e)).getTickets().iterator();
        iterator_1.hasNext();
        ) {
      Number ticket = (Number) iterator_1.next();
      if (Utils.equals(((Ticket) Utils.get(tm.getTickets(), ticket)).getOwner(), u)) {
        tickets = SetUtil.union(Utils.copy(tickets), SetUtil.set(ticket));
      }
    }
    return Utils.copy(tickets);
  }

  public Number getEventFillPercent(final String e) {

    return ((Event) Utils.get(events, e)).getFillPercent();
  }

  public void addTicket(final Number t, final String e) {

    ((Event) Utils.get(events, e)).addTicket(t);
  }

  public Number getEarnings(final String e) {

    return ((Event) Utils.get(events, e)).getEarnings();
  }

  public EventManager() {}

  public String toString() {

    return "EventManager{" + "events := " + Utils.toString(events) + "}";
  }
}

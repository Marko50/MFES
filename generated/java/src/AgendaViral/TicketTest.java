package AgendaViral;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TicketTest extends Data {
  private Event e = new Event("evento1", 15L, 10L, new Data.Date(21L, 2L, 2019L));
  private User u = new User("dank", "memes");
  private EventManager em = new EventManager(MapUtil.map(new Maplet("cenas", e)));
  private UserManager um = new UserManager(MapUtil.map(new Maplet("dank", u)));
  private TicketManager tm = new TicketManager(MapUtil.map());
  private Ticket t = new Ticket("dank", "evento1");

  private void assertTrue(final Boolean cond) {

    return;
  }

  private void testGetID() {

    assertTrue(Utils.equals(t.getID(), 1L));
  }

  private void testGetOwner() {

    assertTrue(Utils.equals(t.getOwner(), "dank"));
  }

  private void testGetEvent() {

    assertTrue(Utils.equals(t.getEvent(), "evento1"));
  }

  private void testRemoveTicket() {

    Number previousCard = MapUtil.dom(tm.getTickets()).size();
    Ticket ticketTest = new Ticket("dank", "evento3");
    tm.addTicket(ticketTest);
    tm.removeTicket(ticketTest.getID());
    assertTrue(Utils.equals(MapUtil.dom(tm.getTickets()).size(), previousCard));
  }

  public static void main() {

    new TicketTest().testGetID();
    new TicketTest().testGetOwner();
    new TicketTest().testGetEvent();
    new TicketTest().testRemoveTicket();
  }

  public TicketTest() {}

  public String toString() {

    return "TicketTest{"
        + "e := "
        + Utils.toString(e)
        + ", u := "
        + Utils.toString(u)
        + ", em := "
        + Utils.toString(em)
        + ", um := "
        + Utils.toString(um)
        + ", tm := "
        + Utils.toString(tm)
        + ", t := "
        + Utils.toString(t)
        + "}";
  }
}

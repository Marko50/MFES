package AgendaViral;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class EventTest extends Data {
  private Event e = new Event("evento1", 15L, 10L, new Data.Date(10L, 1L, 2019L));
  private Event e2 = new Event("evento2", 15L, 10L, new Data.Date(29L, 2L, 2020L));
  private Event e3 = new Event("evento3", 15L, 10L, new Data.Date(29L, 4L, 2020L));
  private Event e4 = new Event("evento4", 15L, 10L, new Data.Date(28L, 2L, 2021L));
  private User u = new User("dank", "memes");
  private EventManager em =
      new EventManager(
          MapUtil.map(
              new Maplet("evento1", e),
              new Maplet("evento2", e2),
              new Maplet("evento3", e3),
              new Maplet("evento4", e4)));
  private UserManager um = new UserManager(MapUtil.map(new Maplet("dank", u)));
  private TicketManager tm = new TicketManager(MapUtil.map());

  private void assertTrue(final Boolean cond) {

    return;
  }

  private void testGetDate() {

    assertTrue(Utils.equals(e.getDate(), new Data.Date(10L, 1L, 2019L)));
  }

  private void testGetName() {

    assertTrue(Utils.equals(e.getName(), "evento1"));
  }

  private void testCapacity() {

    assertTrue(Utils.equals(e.getCapacity(), 15L));
  }

  private void testGetTicketPrice() {

    assertTrue(Utils.equals(e.getTicketPrice(), 10L));
  }

  private void testGetTickets() {

    u.addFunds(100L);
    assertTrue(um.login("dank", "memes"));
    um.buyTicket("evento1", tm, em);
    um.buyTicket("evento1", tm, em);
    assertTrue(Utils.equals(e.getTickets(), SetUtil.set(6L, 7L)));
  }

  private void testRemoveTicket() {

    u.addFunds(100L);
    assertTrue(um.login("dank", "memes"));
    um.buyTicket("evento1", tm, em);
    e.removeTicket(8L);
    assertTrue(Utils.empty(e.getTickets()));
  }

  private void testGetFillPercent() {

    u.addFunds(100L);
    assertTrue(um.login("dank", "memes"));
    um.buyTicket("evento1", tm, em);
    assertTrue(
        Utils.equals(em.getEventFillPercent("evento1"), Utils.divide((1.0 * 1L), 15L) * 100L));
  }

  private void testGetEarnings() {

    u.addFunds(100L);
    assertTrue(um.login("dank", "memes"));
    um.buyTicket("evento1", tm, em);
    assertTrue(Utils.equals(em.getEarnings("evento1"), 10L));
  }

  private void testPromotion() {

    e.promote();
    assertTrue(Utils.equals(e.getPopularity(), 10L));
  }

  private void testManagerGetEvent() {

    assertTrue(Utils.equals(em.getEvent("evento1"), e));
  }

  private void testManagerAddEvent() {

    Event eventTest = new Event("eventTest", 10L, 10L, new Data.Date(1L, 1L, 2021L));
    em.addEvent(eventTest);
    assertTrue(Utils.equals(em.getEvent("eventTest"), eventTest));
  }

  private void testManagerRemoveEvent() {

    Event eventTest = new Event("eventTest", 10L, 10L, new Data.Date(1L, 1L, 2021L));
    em.addEvent(eventTest);
    em.removeEvent("eventTest");
    assertTrue(Utils.equals(MapUtil.dom(em.getEvents()).size(), 4L));
  }

  private void testManagerGetEventTickets() {

    u.addFunds(100L);
    assertTrue(um.login("dank", "memes"));
    um.buyTicket("evento1", tm, em);
    um.buyTicket("evento1", tm, em);
    assertTrue(Utils.equals(em.getEventTickets("evento1").size(), 2L));
  }

  private void testManagerGetEventTicketsUser() {

    u.addFunds(100L);
    assertTrue(um.login("dank", "memes"));
    um.buyTicket("evento1", tm, em);
    um.buyTicket("evento1", tm, em);
    um.buyTicket("evento2", tm, em);
    assertTrue(Utils.equals(em.getEventTicketsUser("evento1", "dank", tm).size(), 2L));
  }

  public static void main() {

    new EventTest().testGetDate();
    new EventTest().testGetName();
    new EventTest().testCapacity();
    new EventTest().testGetTicketPrice();
    new EventTest().testGetTickets();
    new EventTest().testRemoveTicket();
    new EventTest().testGetFillPercent();
    new EventTest().testGetEarnings();
    new EventTest().testPromotion();
    new EventTest().testManagerGetEvent();
    new EventTest().testManagerAddEvent();
    new EventTest().testManagerRemoveEvent();
    new EventTest().testManagerGetEventTickets();
    new EventTest().testManagerGetEventTicketsUser();
  }

  public EventTest() {}

  public String toString() {

    return "EventTest{"
        + "e := "
        + Utils.toString(e)
        + ", e2 := "
        + Utils.toString(e2)
        + ", e3 := "
        + Utils.toString(e3)
        + ", e4 := "
        + Utils.toString(e4)
        + ", u := "
        + Utils.toString(u)
        + ", em := "
        + Utils.toString(em)
        + ", um := "
        + Utils.toString(um)
        + ", tm := "
        + Utils.toString(tm)
        + "}";
  }
}

package AgendaViral;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class UserTest extends Data {
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
  private Ticket t = new Ticket("dank", "evento1");

  private void assertTrue(final Boolean cond) {

    return;
  }

  private void testGetName() {

    assertTrue(Utils.equals(u.getName(), "dank"));
  }

  private void testAddFunds() {

    u.addFunds(100L);
    assertTrue(Utils.equals(u.getFunds(), 100L));
  }

  private void testRemoveFunds() {

    u.addFunds(100L);
    u.removeFunds(10L);
    assertTrue(Utils.equals(u.getFunds(), 90L));
  }

  private void testGetPassword() {

    assertTrue(Utils.equals(u.getPassword(), "memes"));
  }

  private void testAddEvent() {

    u.addEvent("eventTest1");
    u.addEvent("eventTest2");
    assertTrue(Utils.equals(u.getEvents(), SetUtil.set("eventTest1", "eventTest2")));
  }

  private void testRemoveEvent() {

    u.addEvent("eventTest1");
    u.addEvent("eventTest2");
    u.removeEvent("eventTest1");
    assertTrue(Utils.equals(u.getEvents(), SetUtil.set("eventTest2")));
  }

  private void testAddTicket() {

    u.addTicket(0L);
    u.addTicket(1L);
    assertTrue(Utils.equals(u.getTickets(), SetUtil.set(0L, 1L)));
  }

  private void testRemoveTicket() {

    u.addTicket(0L);
    u.addTicket(1L);
    u.removeTicket(0L);
    assertTrue(Utils.equals(u.getTickets(), SetUtil.set(1L)));
  }

  private void testBuyTicket() {

    u.addFunds(100L);
    u.buyTicket(10L, 0L);
    assertTrue(Utils.equals(u.getTickets(), SetUtil.set(0L)));
    assertTrue(Utils.equals(u.getFunds(), 90L));
  }

  private void testLogout() {

    assertTrue(um.login("dank", "memes"));
    assertTrue(Utils.equals(um.getCurrentUser(), "dank"));
    um.logout();
    assertTrue(Utils.equals(um.getCurrentUser(), ""));
  }

  private void testWrongPass() {

    um.register("testUser", "testPass");
    assertTrue(!(um.login("testUser", "wrongpass")));
  }

  private void testPromote() {

    u.addFunds(100L);
    assertTrue(um.login("dank", "memes"));
    um.promoteEvent("evento3", em);
    assertTrue(Utils.equals(e3.getPopularity(), 10L));
  }

  private void testGetUser() {

    assertTrue(Utils.equals(um.getUser("dank"), u));
  }

  private void testGetUsers() {

    assertTrue(Utils.equals(((User) Utils.get(um.getUsers(), "dank")), u));
  }

  private void testGetUserTickets() {

    assertTrue(um.login("dank", "memes"));
    u.addFunds(100L);
    u.buyTicket(10L, 0L);
    assertTrue(Utils.equals(um.getUserTickets(), SetUtil.set(0L)));
    assertTrue(Utils.equals(u.getFunds(), 90L));
  }

  private void testGetUserTicketsEvent() {

    assertTrue(um.login("dank", "memes"));
    u.addFunds(100L);
    um.buyTicket("evento4", tm, em);
    um.buyTicket("evento4", tm, em);
    um.buyTicket("evento4", tm, em);
    um.buyTicket("evento3", tm, em);
    assertTrue(Utils.equals(um.getUserTicketsEvent("evento4", tm), SetUtil.set(33L, 34L, 35L)));
  }

  private void testAddUser() {

    User newUser = new User("addUserTest", "pass");
    um.addUser(newUser);
    assertTrue(Utils.equals(um.getUser("addUserTest"), newUser));
  }

  private void testCreateEvent() {

    Event newEvent = new Event("testEvent", 10L, 10L, new Data.Date(10L, 10L, 2019L));
    assertTrue(um.login("dank", "memes"));
    um.createEvent(newEvent, em);
    assertTrue(Utils.equals(em.getEvent("testEvent"), newEvent));
    assertTrue(Utils.equals(um.getUser("dank").getEvents(), SetUtil.set("testEvent")));
  }

  public static void main() {

    new UserTest().testGetName();
    new UserTest().testAddFunds();
    new UserTest().testRemoveFunds();
    new UserTest().testGetPassword();
    new UserTest().testAddEvent();
    new UserTest().testRemoveEvent();
    new UserTest().testAddTicket();
    new UserTest().testRemoveTicket();
    new UserTest().testBuyTicket();
    new UserTest().testLogout();
    new UserTest().testWrongPass();
    new UserTest().testPromote();
    new UserTest().testGetUser();
    new UserTest().testGetUsers();
    new UserTest().testGetUserTickets();
    new UserTest().testGetUserTicketsEvent();
    new UserTest().testAddUser();
    new UserTest().testCreateEvent();
  }

  public UserTest() {}

  public String toString() {

    return "UserTest{"
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
        + ", t := "
        + Utils.toString(t)
        + "}";
  }
}

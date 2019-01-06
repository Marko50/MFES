package AgendaViral;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class UserManager {
  private String current_user = "";
  private VDMMap users;

  public void cg_init_UserManager_1(final VDMMap uss) {

    users = Utils.copy(uss);
    return;
  }

  public UserManager(final VDMMap uss) {

    cg_init_UserManager_1(Utils.copy(uss));
  }

  public Boolean login(final String u, final String p) {

    if (Utils.equals(((User) Utils.get(users, u)).getPassword(), p)) {
      current_user = u;
      return true;

    } else {
      return false;
    }
  }

  public void logout() {

    current_user = "";
  }

  public void register(final String u, final String p) {

    users = MapUtil.override(Utils.copy(users), MapUtil.map(new Maplet(u, new User(u, p))));
  }

  public void addUser(final User user) {

    users = MapUtil.override(Utils.copy(users), MapUtil.map(new Maplet(user.getName(), user)));
  }

  public User getUser(final String u) {

    return ((User) Utils.get(users, u));
  }

  public String getCurrentUser() {

    return current_user;
  }

  public VDMMap getUsers() {

    return Utils.copy(users);
  }

  private Boolean isLoggedIn() {

    return !(Utils.equals(current_user, ""));
  }

  public void buyTicket(final String e, final TicketManager tm, final EventManager em) {

    Ticket ticket = new Ticket(current_user, ((Event) Utils.get(em.getEvents(), e)).getName());
    tm.addTicket(ticket);
    em.addTicket(ticket.getID(), e);
    ((User) Utils.get(users, current_user))
        .buyTicket(((Event) Utils.get(em.getEvents(), e)).getTicketPrice(), ticket.getID());
  }

  public VDMSet getUserTickets() {

    return ((User) Utils.get(users, current_user)).getTickets();
  }

  public VDMSet getUserTicketsEvent(final String e, final TicketManager tm) {

    VDMSet tickets = SetUtil.set();
    for (Iterator iterator_2 = ((User) Utils.get(users, current_user)).getTickets().iterator();
        iterator_2.hasNext();
        ) {
      Number ticket = (Number) iterator_2.next();
      if (Utils.equals(((Ticket) Utils.get(tm.getTickets(), ticket)).getEvent(), e)) {
        tickets = SetUtil.union(Utils.copy(tickets), SetUtil.set(ticket));
      }
    }
    return Utils.copy(tickets);
  }

  public void promoteEvent(final String e, final EventManager em) {

    ((Event) Utils.get(em.getEvents(), e)).promote();
    ((User) Utils.get(users, current_user)).promoteEvent();
  }

  public UserManager() {}

  public String toString() {

    return "UserManager{"
        + "current_user := "
        + Utils.toString(current_user)
        + ", users := "
        + Utils.toString(users)
        + "}";
  }
}

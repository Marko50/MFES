package AgendaViral;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Ticket {
  private String owner;
  private String event;
  private Number id;
  private static Number count = 0L;

  public void cg_init_Ticket_1(final String o, final String e) {

    owner = o;
    event = e;
    id = Ticket.count;
    count = Ticket.count.longValue() + 1L;
    return;
  }

  public Ticket(final String o, final String e) {

    cg_init_Ticket_1(o, e);
  }

  public String getOwner() {

    return owner;
  }

  public String getEvent() {

    return event;
  }

  public Number getID() {

    return id;
  }

  public Ticket() {}

  public String toString() {

    return "Ticket{"
        + "owner := "
        + Utils.toString(owner)
        + ", event := "
        + Utils.toString(event)
        + ", id := "
        + Utils.toString(id)
        + ", count := "
        + Utils.toString(count)
        + "}";
  }
}

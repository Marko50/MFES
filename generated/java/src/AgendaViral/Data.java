package AgendaViral;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Data {
  public Data() {}

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

    Boolean andResult_1 = false;

    if (Utils.equals(Utils.mod(y.longValue(), 4L), 0L)) {
      if (!(Utils.equals(Utils.mod(y.longValue(), 100L), 0L))) {
        andResult_1 = true;
      }
    }

    if (andResult_1) {
      orResult_4 = true;
    } else {
      orResult_4 = Utils.equals(Utils.mod(y.longValue(), 400L), 0L);
    }

    return orResult_4;
  }

  public String toString() {

    return "Data{}";
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

      return "mk_Data`Date" + Utils.formatFields(day, month, year);
    }
  }

  public static Boolean inv_Date(final Date d) {

    Boolean andResult_2 = false;

    if (d.year.longValue() > 2018L) {
      Boolean andResult_3 = false;

      if (d.month.longValue() <= 12L) {
        if (d.day.longValue() <= DaysOfMonth(d.year, d.month).longValue()) {
          andResult_3 = true;
        }
      }

      if (andResult_3) {
        andResult_2 = true;
      }
    }

    return andResult_2;
  }
}

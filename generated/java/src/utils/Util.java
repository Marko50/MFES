package utils;

public class Util {
    public static String INVALID_ID_TICKET = "Ticket has an invalid id";
    public static String INVALID_POPULARITY = "Event has invalid popularity";
    public static String INVALID_FUNDS_FILE = "User funds has invalid formatter"; 
    public static String LINE_SEPARATOR = "-";
    public static String KEY_SEPARATOR = ",";
    public static String DATE_SEPARATOR = "/";
    public static String FILE_NOT_FOUND = "File not Found";
    public static String ERROR_READING_FILE = "Error Reading File";
    public static String ERROR_WRITING_FILE = "Error Writing File";
    public static String SUCCESS_MESSAGE = "SUCCESS";
    public static String USER_FILE = "/users/users.txt";
    public static String EVENT_FILE = "/events/events.txt";
    public static String TICKET_FILE = "/tickets/tickets.txt";
    public static String NONE = "NONE";
    
    public static String REGISTER_OPTION = "1";
    public static String LOGIN_OPTION = "2";
    public static String INITIAL_MENU_LEAVE_OPTION = "3";
    public static String EVENT_EARNINGS_OPTION = "1";
    public static String PROMOTE_EVENT_OPTION = "2";
    public static String MY_EVENTS_OPTION = "3";
    public static String MY_PURCHASED_TICKETS_OPTION = "4";
    public static String MY_FUNDS_OPTION = "5";
    public static String BUY_TICKET_OPTION = "6";
    public static String SEARCH_TICKETS_EVENT_OPTION = "7";
    public static String CREATE_EVENT_OPTION = "8";
    public static String SHOW_POPULAR_EVENTS_OPTION = "9";
    public static String EVENT_OCUPATION_OPTION = "10";
    public static String ADD_FUNDS_OPTION = "11";
    public static String LOGOUT_OPTION = "12";

    
    public static String INITIAL_MENU = "0";
    public static String REGISTER_MENU = "1";
    public static String LOGIN_MENU = "2";
    public static String USER_MENU = "3";
    public static String EXIT = "4";

    public static String getUsersFilePath(){
        return System.getProperty("user.dir")+ USER_FILE;
    }
    
    public static String getEventsFilePath(){
        return System.getProperty("user.dir")+ EVENT_FILE;
    }
    
    public static String getTicketsFilePath(){
        return System.getProperty("user.dir")+ TICKET_FILE;
    }
}

package cli;

import org.overture.codegen.runtime.*;

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.io.FileReader;

import AgendaViral.*;
import utils.Util;

public class Interface{	
    private EventManager eventManager;
	private TicketManager ticketManager;
    private UserManager userManager;
    private String state = Util.INITIAL_MENU;
    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    
    public void stateMachine() {
		while(true) {
			if(this.state.equals(Util.INITIAL_MENU)){
				this.showInitialMenu();
			}
			
			else if(this.state.equals(Util.LOGIN_MENU)) {
				this.showLogin();
			}
			
			else if(this.state.equals(Util.REGISTER_MENU)) {
				this.showRegister();
			}
			
			else if(this.state.equals(Util.USER_MENU)) {
				this.showUserMenu();
			}
			
			else if(this.state.equals(Util.EXIT)) {
				break;
			}
			
		}
	}

    public Interface(){
        this.eventManager = new EventManager(new VDMMap());
        this.ticketManager = new TicketManager(new VDMMap());
        this.userManager = new UserManager(new VDMMap());
    }
    
    private void showUserMenu() {
    	System.out.println("AGENDA VIRAL - USER MENU");
    	System.out.println(Util.EVENT_EARNINGS_OPTION + " Check an event's earnings.");
    	System.out.println(Util.PROMOTE_EVENT_OPTION + " Promote an event.");
    	System.out.println(Util.MY_EVENTS_OPTION + " Check your events.");
    	System.out.println(Util.MY_PURCHASED_TICKETS_OPTION + " Logout.");
    	System.out.println(Util.MY_FUNDS_OPTION + " Logout.");
    	System.out.println(Util.BUY_TICKET_OPTION + " Logout.");
    	System.out.println(Util.SEARCH_TICKETS_EVENT_OPTION + " Logout.");
    	System.out.println(Util.LOGOUT_OPTION + " Logout.");
    	System.out.print("Opcao: ");
    	try {
			String option = this.bufferedReader.readLine();
			if(option.equals(Util.LOGOUT_OPTION)) {
				this.userManager.logout();
				this.state = Util.INITIAL_MENU;
			}
			else if(option.equals(Util.EVENT_EARNINGS_OPTION)) {
				this.showEventEarnings();
			}
			else if(option.equals(Util.PROMOTE_EVENT_OPTION)) {
				this.state = Util.PROMOTE_EVENT;
			}
			else if(option.equals(Util.MY_EVENTS_OPTION)) {
				this.state = Util.MY_EVENTS;
			}
			else if(option.equals(Util.MY_PURCHASED_TICKETS_OPTION)) {
				this.state = Util.MY_TICKETS;
			}
			else if(option.equals(Util.MY_FUNDS_OPTION)) {
				this.state = Util.MY_FUNDS;
			}
			else if(option.equals(Util.BUY_TICKET_OPTION)) {
				this.state = Util.BUY_TICKET;
			}
			else if(option.equals(Util.SEARCH_TICKETS_EVENT_OPTION)) {
				this.state = Util.SEARCH_TICKET_EVENT;
			}
		} catch (IOException e) {
			this.state = Util.EXIT; 
		}
    }
    
    private void showEventEarnings() {
    	System.out.println("Event: ");
    	try {
			String event = this.bufferedReader.readLine();
			Number earnings = this.eventManager.getEarnings(event);
			System.out.println("Earnings: " + earnings);
		} catch (IOException e) {
			this.state = Util.EXIT; 
		}
    }
    
    private void showInitialMenu() {
    	System.out.println("AGENDA VIRAL - MENU INICIAL");
    	System.out.println(Util.REGISTER_OPTION + " Registar.");
    	System.out.println(Util.LOGIN_OPTION + " Login.");
    	System.out.println(Util.INITIAL_MENU_LEAVE_OPTION + " Sair.");
    	System.out.print("Opcao: ");
    	
		try {
			String option = this.bufferedReader.readLine();
			if(option.equals(Util.REGISTER_OPTION)) {
				this.state = Util.REGISTER_MENU;
			}
			else if(option.equals(Util.LOGIN_OPTION)) {
				this.state = Util.LOGIN_MENU;
			}
			else if(option.equals(Util.INITIAL_MENU_LEAVE_OPTION)) {
				this.state = Util.EXIT; 
			}
		} catch (IOException e) {
			e.printStackTrace();
			this.state = Util.EXIT; 
		}
    }
    
    private void showLogin() {
    	
    	try {
    		System.out.print("Username: ");
			String username = this.bufferedReader.readLine();
			System.out.print("Password: ");
			String password = this.bufferedReader.readLine();
			if(this.userManager.login(username, password)) {
				System.out.println("Successfully logged in! ");
				this.state = Util.USER_MENU;
			}
			else {
				System.out.println("Invalid credentials! ");
				this.state = Util.EXIT;
			}
		} catch (IOException e) {
			System.out.println("Error loggin in! ");
			this.state = Util.EXIT;
		}	
    }
    
    
    private void showRegister() {
    	try {
    		System.out.print("Username: ");
			String username = this.bufferedReader.readLine();
			System.out.print("Password: ");
			String password = this.bufferedReader.readLine();
			this.userManager.register(username, password);
			this.state = Util.INITIAL_MENU;
			System.out.println("Registered successfully!");
		} catch (IOException e) {
			System.out.println("Error registering! ");
			this.state = Util.EXIT;
		}	
    }
    
   
    public boolean setup(){
    	String setupTickets = this.setupTickets();
    	String setupUsers = this.setupUsers();
    	String setupEvents = this.setupEvents();
    	System.out.println(setupUsers + "  " + setupTickets + "  " + setupEvents);
    	return setupUsers.equals(Util.SUCCESS_MESSAGE) && setupTickets.equals(Util.SUCCESS_MESSAGE) && setupEvents.equals(Util.SUCCESS_MESSAGE);
    }
    
    public boolean close() {
    	String closeTickets = this.closeTickets();
    	String closeUsers = this.closeUsers();
    	String closeEvents = this.closeEvents();
    	System.out.println(closeUsers + "  " + closeTickets + "  " + closeEvents);
    	try {
			this.bufferedReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
    	return closeUsers.equals(Util.SUCCESS_MESSAGE) && closeTickets.equals(Util.SUCCESS_MESSAGE) && closeEvents.equals(Util.SUCCESS_MESSAGE);
    }
    
    @SuppressWarnings("unchecked")
	private String closeUsers() {
    	String usersFilePath = Util.getUsersFilePath();	
    	try {
    		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(usersFilePath));
    		this.userManager.getUsers().forEach((k,v) -> {
    			// process the line.  name - funds - password - 1,2,3 - e1,e2,e3
    			User aux = (User) v;
    			String line = aux.getName() + Util.LINE_SEPARATOR + aux.getFunds() + Util.LINE_SEPARATOR + aux.getPassword() + Util.LINE_SEPARATOR;
    			for (Number s: (Set<Number>) aux.getTickets()) {
    				line+=s+Util.KEY_SEPARATOR;
    			}
    			if(aux.getTickets().size() == 0)
    				line+=Util.NONE;
    			else line = line.substring(0, line.length() - 1); // eliminate the last ,
    			line += Util.LINE_SEPARATOR;
    			for (String s: (Set<String>) aux.getEvents()) {
    				line+=s+Util.KEY_SEPARATOR;
    			}
    			if(aux.getEvents().size() == 0)
    				line+=Util.NONE;
    			else line = line.substring(0, line.length() - 1);// eliminate the last ,
    			line += "\n";
    			try {
					bufferedWriter.write(line);
				} catch (IOException e) {
					return;
				}
    		});
    		
    		bufferedWriter.close();
    		return Util.SUCCESS_MESSAGE;
			
		} catch (FileNotFoundException e) {
			 return Util.FILE_NOT_FOUND + "  " + usersFilePath;
		}
    	catch(IOException e){
            return Util.ERROR_WRITING_FILE + "  " + usersFilePath;
        } 
    }
    
    @SuppressWarnings("unchecked")
	private String closeTickets() {
    	String ticketsFilePath = Util.getTicketsFilePath();	
    	try {
    		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(ticketsFilePath));
    		this.ticketManager.getTickets().forEach((k,v) -> {
    			 // process the line.  owner - event
    			Ticket aux = (Ticket) v;
    			String line = aux.getOwner() + Util.LINE_SEPARATOR + aux.getEvent() + "\n";
    			try {
					bufferedWriter.write(line);
				} catch (IOException e) {
					return;
				}
    		});
    		
    		bufferedWriter.close();
    		return Util.SUCCESS_MESSAGE;
			
		} catch (FileNotFoundException e) {
			 return Util.FILE_NOT_FOUND + "  " + ticketsFilePath;
		}
    	catch(IOException e){
            return Util.ERROR_WRITING_FILE + "  " + ticketsFilePath;
        } 
    }
    
    
    @SuppressWarnings("unchecked")
   	private String closeEvents() {
       	String eventsFilePath = Util.getEventsFilePath();	
       	try {
       		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(eventsFilePath));
       		this.eventManager.getEvents().forEach((k,v) -> {
       		   // process the line.  name - capacity - price- day/month/year
       			Event aux = (Event) v;
       			String line = aux.getName() + Util.LINE_SEPARATOR + aux.getCapacity() + Util.LINE_SEPARATOR + aux.getTicketPrice() + Util.LINE_SEPARATOR;
       			AgendaViral.Data.Date date = aux.getDate();
       			line += date.day + Util.DATE_SEPARATOR + date.month + Util.DATE_SEPARATOR + date.year+ Util.LINE_SEPARATOR;
       			for (Number s: (Set<Number>) aux.getTickets()) {
    				line+=s+Util.KEY_SEPARATOR;
    			}
    			if(aux.getTickets().size() == 0)
    				line+=Util.NONE;
    			else line = line.substring(0, line.length() - 1); // eliminate the last ,
    			line +="\n";
       			try {
   					bufferedWriter.write(line);
   				} catch (IOException e) {
   					return;
   				}
       		});
       		
       		bufferedWriter.close();
       		return Util.SUCCESS_MESSAGE;
   			
   		} catch (FileNotFoundException e) {
   			 return Util.FILE_NOT_FOUND + "  " + eventsFilePath;
   		}
       	catch(IOException e){
               return Util.ERROR_WRITING_FILE + "  " + eventsFilePath;
           } 
       }
    
    

    private String setupUsers(){
        String usersFilePath = Util.getUsersFilePath();
        try {
            BufferedReader buff = new BufferedReader(new FileReader(usersFilePath));
            String line;
            while ((line = buff.readLine()) != null) {
               // process the line.  name - funds - password - 1,2,3 - e1,e2,e3
                String[] lineParts = line.split(Util.LINE_SEPARATOR);
                String name = lineParts[0];
                String funds = lineParts[1];
                String password = lineParts[2];
                String[] tickets = lineParts[3].split(Util.KEY_SEPARATOR);
                String[] events = lineParts[4].split(Util.KEY_SEPARATOR);
                User user = new User(name, password);
                user.addFunds(Integer.parseInt(funds));
                for(int i = 0; i < tickets.length && !tickets[0].equals(Util.NONE); i++){
                    user.addTicket(Integer.parseInt(tickets[i]));
                }
                for(int i = 0; i < events.length && !events[0].equals(Util.NONE); i++){
                	user.addEvent(events[i]);               
                }
                this.userManager.addUser(user);
            }
            buff.close();
            return Util.SUCCESS_MESSAGE;
            
        } catch (FileNotFoundException e) {
            return Util.FILE_NOT_FOUND + "  " + usersFilePath;
        } catch(IOException e){
            return Util.ERROR_READING_FILE + "  " + usersFilePath;
        } catch(NumberFormatException e){
            return Util.INVALID_FUNDS_FILE + " OR " + Util.INVALID_ID_TICKET;
        }
        
    }
    
    private String setupTickets(){
        String ticketsFilePath = Util.getTicketsFilePath();
        try {
            BufferedReader buff = new BufferedReader(new FileReader(ticketsFilePath));
            String line;
            while ((line = buff.readLine()) != null) {
               // process the line.  owner - event
                String[] lineParts = line.split(Util.LINE_SEPARATOR);
                String owner = lineParts[0];
                String event = lineParts[1];
                Ticket ticket = new Ticket(owner, event);
                this.ticketManager.addTicket(ticket);
            }
            buff.close();
            return Util.SUCCESS_MESSAGE;
            
        } catch (FileNotFoundException e) {
            return Util.FILE_NOT_FOUND + "  " + ticketsFilePath;
        } catch(IOException e){
            return Util.ERROR_READING_FILE + "  " + ticketsFilePath;
        } 
    }
    
    private String setupEvents(){
        String eventsFilePath = Util.getEventsFilePath();
        try {
            BufferedReader buff = new BufferedReader(new FileReader(eventsFilePath));
            String line;
            while ((line = buff.readLine()) != null) {
               // process the line.  name - capacity - price- day/month/year
                String[] lineParts = line.split(Util.LINE_SEPARATOR);
                String name = lineParts[0];
                String capacity = lineParts[1];
                String price = lineParts[2];
                String[] date_info = lineParts[3].split(Util.DATE_SEPARATOR);
                String[] tickets = lineParts[4].split(Util.KEY_SEPARATOR);
                AgendaViral.Data.Date date = new AgendaViral.Data.Date(Integer.parseInt(date_info[0]), Integer.parseInt(date_info[1]), Integer.parseInt(date_info[2]));
                Event event = new Event(name, Integer.parseInt(capacity), Integer.parseInt(price), date);
                for(int i = 0; i < tickets.length && !tickets[0].equals(Util.NONE); i++){
                	event.addTicket(Integer.parseInt(tickets[i]));               
                }
                this.eventManager.addEvent(event);;
            }
            buff.close();
            return Util.SUCCESS_MESSAGE;
            
        } catch (FileNotFoundException e) {
            return Util.FILE_NOT_FOUND + "  " + eventsFilePath;
        } catch(IOException e){
            return Util.ERROR_READING_FILE + "  " + eventsFilePath;
        } catch(NumberFormatException e){
            return Util.INVALID_FUNDS_FILE + " OR " + Util.INVALID_ID_TICKET;
        }
        
    }
}
// Custom Exception Class
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Ticket (Base Class)
class Ticket {
    protected String eventName;
    protected int totalTickets;
    protected int bookedTickets;

    public Ticket(String eventName, int totalTickets) {
        this.eventName = eventName;
        this.totalTickets = totalTickets;
        this.bookedTickets = 0;
    }

    public boolean AvailableTicket(int quantity) {
        if (bookedTickets + quantity <= totalTickets) {
            bookedTickets += quantity;
            return true;
        }
        return false;
    }

    public void showDetails() {
        System.out.println("Event: " + eventName);
        System.out.println("Total Tickets: " + totalTickets);
        System.out.println("Booked Tickets: " + bookedTickets);
    }
}

// MovieTicket (Subclass of Ticket)
class MovieTicket extends Ticket {
    public MovieTicket(String eventName, int totalTickets) {
        super(eventName, totalTickets);
    }

    @Override
    public void showDetails() {
        super.showDetails();
        System.out.println("This is a Movie Ticket.");
    }
}

// EventTicket (Subclass of Ticket)
class EventTicket extends Ticket {
    public EventTicket(String eventName, int totalTickets) {
        super(eventName, totalTickets);
    }

    @Override
    public void showDetails() {
        super.showDetails();
        System.out.println("This is an Event Ticket.");
    }
}

// User (Base Class)
abstract class User {
    protected String name;
    protected String userID;

    public User(String name, String userID) {
        this.name = name;
        this.userID = userID;
    }

    public abstract void bookTickets(Ticket ticket, int quantity) throws InvalidBookingException;
}

// RegularUser (Subclass of User)
class RegularUser extends User {
    public RegularUser(String name, String userID) {
        super(name, userID);
    }

    @Override
    public void bookTickets(Ticket ticket, int quantity) throws InvalidBookingException {
        if (!ticket.AvailableTicket(quantity)) {
            throw new InvalidBookingException("Not enough tickets available.");
        }
        System.out.println(name + " successfully booked " + quantity + " tickets.");
    }
}

// VIPUser (Subclass of User)
class VIPUser extends User {
    public VIPUser(String name, String userID) {
        super(name, userID);
    }

    @Override
    public void bookTickets(Ticket ticket, int quantity) throws InvalidBookingException {
        if (!ticket.AvailableTicket(quantity)) {
            throw new InvalidBookingException("Not enough tickets available for VIP.");
        }
        System.out.println(name + " (VIP) successfully booked " + quantity + " tickets.");
    }
}

// TicketBookingSystem 
class TicketBookingSystem {
    private Ticket ticket;

    public TicketBookingSystem(Ticket ticket) {
        this.ticket = ticket;
    }

    public synchronized void processBooking(User user, int quantity) {
        try {
            user.bookTickets(ticket, quantity);
        } catch (InvalidBookingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

// BookingTask 
class BookingTask extends Thread {
    private User user;
    private TicketBookingSystem bookingSystem;
    private int quantity;

    public BookingTask(User user, TicketBookingSystem bookingSystem, int quantity) {
        this.user = user;
        this.bookingSystem = bookingSystem;
        this.quantity = quantity;
    }

    @Override
    public void run() {
        bookingSystem.processBooking(user, quantity);
    }
}

// Main Class 
public class TicketBookingApp {
    public static void main(String[] args) {
        // Create event and ticket types
        Ticket movieTicket = new MovieTicket("GOT", 100);
        Ticket eventTicket = new EventTicket("Concert 2024", 50);

        // Create users
        User regularUser = new RegularUser("Burhan", "01");
        User vipUser = new VIPUser("Rafin", "v01");

        // Create a TicketBookingSystem
        TicketBookingSystem movieBookingSystem = new TicketBookingSystem(movieTicket);
        TicketBookingSystem eventBookingSystem = new TicketBookingSystem(eventTicket);

        // Create threads for simultaneous booking
        BookingTask task1 = new BookingTask(regularUser, movieBookingSystem, 3);
        BookingTask task2 = new BookingTask(vipUser, eventBookingSystem, 2);
        BookingTask task3 = new BookingTask(regularUser, movieBookingSystem, 5);
        BookingTask task4 = new BookingTask(vipUser, eventBookingSystem, 4);

        
        task1.start();
        task2.start();
        task3.start();
        task4.start();

        // Wait for all threads to finish
        try {
            task1.join();
            task2.join();
            task3.join();
            task4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Display remaining ticket details
        movieTicket.showDetails();
        eventTicket.showDetails();
    }
}

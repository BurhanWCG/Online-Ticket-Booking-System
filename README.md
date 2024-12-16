Online Ticket Booking System
This is a simple Java-based Online Ticket Booking System with support for Movie Tickets and Event Tickets. Users can book tickets, and the system ensures ticket availability and prevents overbooking using custom exception handling.

Features
Ticket Booking: Users can book tickets for events or movies.
User Types: Supports two types of users:
Regular Users: Can book tickets like any normal user.
VIP Users: Can also book tickets, but their bookings are treated as VIP.
Ticket Types: The system supports different types of tickets:
Movie Ticket
Event Ticket
Custom Exception Handling: Throws custom exceptions (InvalidBookingException) when there are insufficient tickets for booking.
Multi-threading: Utilizes Java threads to simulate simultaneous bookings from multiple users.



Classes and Functionality
1. Ticket
Represents a ticket for an event or movie.
Attributes: eventName, totalTickets, bookedTickets
Methods: AvailableTicket(quantity), showDetails()
2. MovieTicket and EventTicket
Subclasses of the Ticket class representing different types of tickets (Movies and Events).
Overriden showDetails() to specify the ticket type.
3. User
Abstract base class for users.
Attributes: name, userID
Method: bookTickets(Ticket ticket, int quantity)
4. RegularUser and VIPUser
Subclasses of the User class.
Handle ticket booking for regular and VIP users respectively.
5. TicketBookingSystem
Manages the booking process for a given ticket.
Ensures thread-safety by using synchronized methods for booking operations.
6. BookingTask
A thread that performs the task of booking tickets.
Simulates simultaneous bookings by different users using multiple threads.
7. InvalidBookingException
A custom exception class for handling invalid booking attempts.


How to Run the Project

Clone the repository:
git clone https://github.com/BurhanWCG/Online-Ticket-Booking-System.git
Navigate to the project directory:

cd Online-Ticket-Booking-System
Compile and Run the TicketBookingApp class:


javac TicketBookingApp.java
java TicketBookingApp

The system will simulate ticket booking by multiple users and display the remaining ticket details after the booking process is complete.

Example Output

Burhan successfully booked 3 tickets.
Rafin (VIP) successfully booked 2 tickets.
Burhan successfully booked 5 tickets.
Rafin (VIP) successfully booked 4 tickets.
Event: GOT
Total Tickets: 100
Booked Tickets: 8
This is a Movie Ticket.
Event: Concert 2024
Total Tickets: 50
Booked Tickets: 6
This is an Event Ticket.


Contributing
Fork the repository.
Create a new branch (git checkout -b feature-name).
Commit your changes (git commit -am 'Add feature').
Push to the branch (git push origin feature-name).
Open a pull request.

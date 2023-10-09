package GL_.TicketManagementApplication.service;

import java.util.List;

import GL_.TicketManagementApplication.model.Tickets;

public interface TicketService {
	List<Tickets> getAllTickets();

	void saveTicket(Tickets ticket);

	Tickets getTicketById(long id);

	void deleteTicketById(long id);

	void viewTicketById(long id);

	List<Tickets> searchTicketBytitle(String title, String short_desc);
}

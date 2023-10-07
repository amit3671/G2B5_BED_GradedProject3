package GL_.TicketManagementApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import GL_.TicketManagementApplication.model.Tickets;

public interface TicketsRepository extends JpaRepository<Tickets, Long> {

}

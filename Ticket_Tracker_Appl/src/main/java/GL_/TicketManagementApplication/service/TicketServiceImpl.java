package GL_.TicketManagementApplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import GL_.TicketManagementApplication.model.Tickets;
import GL_.TicketManagementApplication.repository.TicketsRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketsRepository TicketsRepository;

	@Override
	public List<Tickets> getAllTickets() {
		return TicketsRepository.findAll();
	}

	@Override
	public void saveTicket(Tickets Tickets) {
		this.TicketsRepository.save(Tickets);
	}

	@Override
	public Tickets getTicketById(long id) {
		Optional<Tickets> optional = TicketsRepository.findById(id);
		Tickets Tickets = null;
		if (optional.isPresent()) {
			Tickets = optional.get();
		} else {
			throw new RuntimeException(" Tickets not found for id :: " + id);
		}
		return Tickets;
	}

	@Override
	public void deleteTicketById(long id) {
		this.TicketsRepository.deleteById(id);
	}

	@Override
	public void viewTicketById(long id) {
		this.TicketsRepository.findById(id);
	}

	public List<Tickets> searchTicketBytitle(String query,String desc) {
		Tickets matchingtitle = new Tickets();
		Tickets matchingdesc = new Tickets();
		matchingtitle.setTitle(query);
		matchingdesc.setShort_desc(query);
		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains().caseSensitive())
				.withMatcher("short_desc", ExampleMatcher.GenericPropertyMatchers.contains().caseSensitive())
				.withIgnorePaths("id", "content", "date");
		List<Tickets> MatchingTicket = new ArrayList<>();
		MatchingTicket.add(matchingtitle);
		MatchingTicket.add(matchingdesc);
		Example<Tickets> example = Example.of(matchingtitle, exampleMatcher);
		Example<Tickets> example1 = Example.of(matchingdesc, exampleMatcher);
		return TicketsRepository.findAll(example);
	}

}

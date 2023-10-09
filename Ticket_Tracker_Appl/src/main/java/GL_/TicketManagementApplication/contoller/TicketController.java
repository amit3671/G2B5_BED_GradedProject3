package GL_.TicketManagementApplication.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import GL_.TicketManagementApplication.model.Tickets;
import GL_.TicketManagementApplication.service.TicketService;

@Controller
@RequestMapping("/admin")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@GetMapping("/tickets")
	public String tickets(Model model) {
		model.addAttribute("listTickets", ticketService.getAllTickets());
		return "index";
	}

	@RequestMapping("/search")
	public String search(Model model, @Param("query") String query, @Param("short_desc") String short_desc) {
		List<Tickets> listickets = ticketService.searchTicketBytitle(query, short_desc);
		model.addAttribute("listickets", listickets);
		return "search";
	}

	@GetMapping("/newtickets")
	public String newtickets(Model model) {

		Tickets ticket = new Tickets();
		model.addAttribute("ticket", ticket);
		return "new_ticket";
	}

	@PostMapping("/saveTicket")
	public String saveTicket(@ModelAttribute("ticket") Tickets ticket) {

		ticketService.saveTicket(ticket);
		return "redirect:/admin/tickets";
	}

	@GetMapping("ticketsedit/{id}")
	public String ticketsedit(@PathVariable(value = "id") long id, Model model) {

		Tickets ticket = ticketService.getTicketById(id);

		model.addAttribute("ticket", ticket);
		return "update_ticket";
	}

	@GetMapping("/deleteTicket/{id}")
	public String deleteTicket(@PathVariable(value = "id") long id) {

		this.ticketService.deleteTicketById(id);
		return "redirect:/admin/tickets";
	}

	@GetMapping("/viewTicket/{id}")
	public String viewTicket(@PathVariable(value = "id") long id, Model model) {

		Tickets ticket = ticketService.getTicketById(id);

		model.addAttribute("ticket", ticket);
		return "view_ticket";
	}

}

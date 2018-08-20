package pl.zar.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.zar.app.dao.CustomerDAO;
import pl.zar.app.entity.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {
		
		//inject the customer to DAO
		@Autowired
		private CustomerDAO customerDao;
	
	@RequestMapping("/list")
	public String listCustomers(Model model) {
		
		//get customers from the DAO
		List<Customer> customers = customerDao.getCustomers();
		
		//add the customers to the model
		model.addAttribute("customers", customers);
		
		return "list-customers";
	}

}

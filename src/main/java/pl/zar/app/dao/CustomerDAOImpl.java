package pl.zar.app.dao;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.zar.app.entity.Customer;

@Repository // find from scan package
public class CustomerDAOImpl implements CustomerDAO {

	//inject session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query
		Query<Customer> query = currentSession.createQuery("from Customer", Customer.class);
		
		//executequery and get list with result 
		List<Customer> customers = query.getResultList();
		
		//return result
		return customers;
	}

}

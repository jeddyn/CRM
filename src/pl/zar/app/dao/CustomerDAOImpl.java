package pl.zar.app.dao;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jdk.nashorn.internal.runtime.regexp.JoniRegExp.Factory;
import pl.zar.app.entity.Customer;

@Repository // find from scan package
public class CustomerDAOImpl implements CustomerDAO {

	//inject session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Customer> getCustomers() {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query .. sort by last name
		Query<Customer> query = currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		//executequery and get list with result 
		List<Customer> customers = query.getResultList();
		
		//return result
		return customers;
	}


	@Override
	public void saveCustomer(Customer theCustomer) {
		//get current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		//save/update the customer
		session.saveOrUpdate(theCustomer);
		
	}


	@Override
	public Customer getCustomer(int theId) {
		
		//get the current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		//retrive/read from database using the primary key
		Customer theCustomer = session.get(Customer.class, theId);
		
		return theCustomer;
	}


	@Override
	public void deleteCustomer(int theId) {
		
		//get the current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		//delete object with primary key
		Query query = 
				session.createQuery("delete from Customer where id=:customerId");
		query.setParameter("customerId", theId);
		
		query.executeUpdate();
	}


	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		  // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        
        Query theQuery = null;
        
        // only search by name if theSearchName is not empty
        if (theSearchName != null && theSearchName.trim().length() > 0) {

            // search for firstName or lastName ... case insensitive
            theQuery = currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer", Customer.class);            
        }
        
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();
                
        // return the results        
        return customers;
	}

}

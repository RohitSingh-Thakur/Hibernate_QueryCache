package root.client;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import root.entity.EEmployee;

public class Test {
	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure().addAnnotatedClass(EEmployee.class);
		SessionFactory sessionFactory = cfg.buildSessionFactory();

		// First Session: Save employee data
		Session session = sessionFactory.openSession();
		Transaction transaction = null;

		try {
			
			transaction = session.beginTransaction();
			
			  // Create and save employees
				EEmployee employee = new EEmployee("RohitSingh", 89.00);
				session.save(employee);
				transaction.commit();

		} catch (Exception e) {
			 if (transaction != null) transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		 // First Query: Caches the result
		session = sessionFactory.openSession();
		Query query1 = session.createQuery("FROM EEmployee WHERE empid= :ID");
		query1.setParameter("ID", 1);
		query1.setCacheable(true); // Enable query caching


		EEmployee emp1 = (EEmployee) query1.uniqueResult();
		System.out.println(emp1);

		session.close();

		// Second Query: Fetches from cache
		session = sessionFactory.openSession();
		Query query2 = session.createQuery("FROM EEmployee WHERE empid= :ID");
		query2.setParameter("ID", 1);
		query2.setCacheable(true); // Enable query caching
		EEmployee emp2 = (EEmployee) query2.uniqueResult();
		System.out.println(emp2);

		session.close();
		
		sessionFactory.close();

	}

}

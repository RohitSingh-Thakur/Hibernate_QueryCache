package root.entity;

import javax.persistence.Cacheable; 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class EEmployee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empId;
	private String empName;
	private double salary;

	public EEmployee() {
		// TODO Auto-generated constructor stub
	}

	public EEmployee(String empName, double salary) {
		this.empName = empName;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "EEmployee [empId=" + empId + ", empName=" + empName + ", salary=" + salary + "]";
	}

}

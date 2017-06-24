package MtsTest.persistence.repo.impl;

import MtsTest.persistence.model.Employee;
import MtsTest.persistence.repo.EmployeeRepo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeRepoImpl extends BaseRepoImpl implements EmployeeRepo {

    @Override
    @Transactional
    public Employee persist(final Employee employee) {
        if(employee.isNew()) {
            em.persist(employee);
            return employee;
        }
        return em.merge(employee);
    }

    @Override
    public List<Employee> findAll() {
        return em.createQuery("from Employee e").getResultList();
    }

    public List<Employee> findAllByQuery(final String query) {
        return em.createNamedQuery(query, Employee.class).getResultList();
    }


    @Override
    public Employee findById(final Integer id) {
//        List<Employee> employees = em.createQuery("from Employee e where e.id = :id").setParameter("id", id).getResultList();
//        return employees.size() > 0 ? employees.get(0) : new Employee();
        return em.find(Employee.class, id);
    }

    @Override
    @Transactional
    public void deleteById(final Integer id) {
        Employee employee = em.find(Employee.class, id);
        if(employee != null) {
            em.remove(employee);
        }
    }

}

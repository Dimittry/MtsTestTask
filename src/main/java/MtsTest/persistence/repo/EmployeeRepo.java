package MtsTest.persistence.repo;

import MtsTest.persistence.model.Employee;

import java.util.List;

public interface EmployeeRepo {

    Employee persist(final Employee employee);

    List<Employee> findAll();

    Employee findById(final Integer id);

    void deleteById(final Integer id);

    List<Employee> findAllByQuery(final String query);
}

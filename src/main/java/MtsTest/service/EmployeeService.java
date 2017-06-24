package MtsTest.service;

import MtsTest.persistence.model.Employee;

import java.util.Comparator;
import java.util.List;

public interface EmployeeService {
    Employee findById(final Integer id);

    List<Employee> findAll();

    Employee persist(final Employee employee);

    List<Employee> findAllWithOrder(final String query);

    List<Employee> findAllWithOrder(final Comparator<Employee> comparator);

    void deleteById(final Integer id);
}

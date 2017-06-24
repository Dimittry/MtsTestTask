package MtsTest.service.impl;

import MtsTest.persistence.model.Employee;
import MtsTest.persistence.repo.EmployeeRepo;
import MtsTest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public Employee persist(final Employee employee) {
        return employeeRepo.persist(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    @Override
    public List<Employee> findAllWithOrder(final String query) {
        return employeeRepo.findAllByQuery(query);
    }

    @Override
    public List<Employee> findAllWithOrder(final Comparator<Employee> comparator) {
        List<Employee> employees = employeeRepo.findAll();
        Collections.sort(employees, comparator);
        return employees;
    }


    @Override
    public Employee findById(final Integer id) {
        return employeeRepo.findById(id);
    }

    public void deleteById(final Integer id) {
        employeeRepo.deleteById(id);
    }

}

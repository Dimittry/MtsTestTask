package MtsTest.persistence.repo.impl;

import MtsTest.persistence.model.Employee;
import MtsTest.persistence.repo.EmployeeRepo;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

//@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
//@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeRepoImplTest {

    @Autowired
    private EmployeeRepo employeeRepo;


//    @Test
    public void testDeleteById() throws Exception {
        employeeRepo.deleteById(6);
    }

//    @Test
    public void testFindAll() {
        List<Employee> employees = employeeRepo.findAll();
        System.out.println(employees);
        assertNotNull(employees);
    }

//    @Test
    public void testFindById() {
        Employee employee = employeeRepo.findById(3);
        System.out.println(employee);
        assertEquals(false, employee.isNew());
    }

}
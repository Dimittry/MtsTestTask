package MtsTest.web.controller.rest;

import MtsTest.persistence.model.Employee;
import MtsTest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public @ResponseBody Employee getById(@PathVariable("id") Integer id) {
        return employeeService.findById(id);
    }
}

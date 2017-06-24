package MtsTest.web.controller;

import MtsTest.persistence.model.Employee;
import MtsTest.service.EmployeeService;
import MtsTest.utils.SortedParamNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("index");
        model.addObject("employees", employeeService.findAll());
        return model;
    }

    @RequestMapping(value = "/", params = "sort")
    public ModelAndView indexSort(@RequestParam("sort") String sort) {
        ModelAndView model = new ModelAndView("index");
        List<Employee> employees = defineEmployees(
                sort,
                Employee.FIND_ALL_NAME1_SORTED_ASC,
                (o1, o2) -> o1.getName2().compareTo(o2.getName2())
        );
        model.addObject("employees", employees);
        return model;
    }

    @RequestMapping(value = "/", params = {"sort", "order"})
    public ModelAndView indexSortDesc(@RequestParam("sort") String sort,
                                      @RequestParam("order") String order) {
        ModelAndView model = new ModelAndView("index");
        List<Employee> employees = defineEmployees(
                sort,
                Employee.FIND_ALL_NAME1_SORTED_DESC,
                (o1, o2) -> o2.getName2().compareTo(o1.getName2())
        );

        model.addObject("employees", employees);
        return model;
    }

    @RequestMapping("/employee")
    public ModelAndView showAddForm() {
        return new ModelAndView("employeeform", "employee", new Employee());
    }

    @RequestMapping("/employee/{id}/update")
    public ModelAndView showUpdateForm(@PathVariable("id") Integer id, ModelMap model) {
        model.addAttribute("employee", employeeService.findById(id));
        return new ModelAndView("employeeform", model);
    }

    @RequestMapping("/employee/{id}/delete")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        employeeService.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/persistEmployee", method = RequestMethod.POST)
    public String persist(@ModelAttribute("employee") @Validated Employee employee,
                                BindingResult result, ModelMap modelMap) {
        System.out.println(employee);
        if(result.hasErrors()) {
            modelMap.addAttribute("error", "Заполните поля");
            return "employeeform";
        }
        employeeService.persist(employee);
        modelMap.addAttribute("employee", employee);
        return "redirect:/";
    }

    private List<Employee> defineEmployees(final String sort,
                                           final String query,
                                           final Comparator<Employee> comparator) {
        List<Employee> employees;
        if(sort.equals(SortedParamNames.NAME1.getSortParam())) {
            employees = employeeService.findAllWithOrder(query);
        } else if(sort.equals(SortedParamNames.NAME2.getSortParam())) {
            employees = employeeService.findAllWithOrder(comparator);
        } else {
            employees = employeeService.findAll();
        }
        return employees;
    }

}

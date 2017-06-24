package MtsTest.persistence.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employees")
@NamedQueries({
    @NamedQuery(name = Employee.FIND_ALL_NAME1_SORTED_ASC,
            query = "from Employee e ORDER BY name1 ASC"),
    @NamedQuery(name = Employee.FIND_ALL_NAME1_SORTED_DESC,
            query = "from Employee e ORDER BY name1 DESC"),
})
public class Employee extends BaseEntity implements Comparable<Employee>{

    public static final String FIND_ALL_NAME1_SORTED_ASC = "Employee.findAllName1Asc";
    public static final String FIND_ALL_NAME1_SORTED_DESC = "Employee.findAllName1Desc";

    @NotNull
    @Length(min = 2)
    private String name1;

    @NotNull
    @Length(min = 2)
    private String name2;

    public Employee() {}

    public Employee(final Integer id, final String name1, final String name2) {
        super(id);
        this.name1 = name1;
        this.name2 = name2;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    @Override
    public String toString() {
        return  String.format("Employee{id='%d', name1='%s', name2='%s'}", id, name1, name2);
    }

    @Override
    public int compareTo(final Employee employee) {
        return employee.getName2().compareTo(employee.getName2());
    }
}

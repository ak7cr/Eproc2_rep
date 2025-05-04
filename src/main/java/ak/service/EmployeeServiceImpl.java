package ak.service;

import ak.entity.Employee;
import ak.repository.EmployeeRepository;
import ak.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Employee updateEmployee(Integer id, Employee employee) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(employee.getName());
                    existing.setEmail(employee.getEmail());
                    existing.setPhone(employee.getPhone());
                    existing.setCity(employee.getCity());
                    existing.setState(employee.getState());
                    existing.setDepartment(employee.getDepartment());
                    existing.setDob(employee.getDob());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found: " + id));
    }

    @Override
    public void deleteEmployee(Integer id) {
        repository.deleteById(id);
    }
}
package ak.controller;

import ak.entity.Employee;
import ak.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {
    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    // ======================
    // Form Handling Endpoints
    // ======================
    @GetMapping("/emp")
    public String showForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee";
    }

    @PostMapping("/emp")
    public String submitForm(@ModelAttribute Employee employee) {
        service.createEmployee(employee);
        return "redirect:/emp";
    }

    // =================
    // REST API Endpoints
    // =================
    @RestController
    @RequestMapping("/api/employees")
    public static class ApiController {
        private final EmployeeService service;

        @Autowired
        public ApiController(EmployeeService service) {
            this.service = service;
        }

        @GetMapping
        public List<Employee> getAll() {
            return service.getAllEmployees();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Employee> getById(@PathVariable Integer id) {
            return service.getEmployeeById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        @PostMapping
        public Employee create(@RequestBody Employee employee) {
            return service.createEmployee(employee);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Employee> update(
                @PathVariable Integer id,
                @RequestBody Employee employee) {
            return ResponseEntity.ok(service.updateEmployee(id, employee));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Integer id) {
            service.deleteEmployee(id);
            return ResponseEntity.noContent().build();
        }
    }
}
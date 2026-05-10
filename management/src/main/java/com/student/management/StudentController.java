package com.student.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository repository;

    // 1. Home Page - Now with Report Logic
    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Student> students = repository.findAll();
        model.addAttribute("listStudents", students);
        
        // --- REPORT GENERATION LOGIC ---
        long count = students.size();
        double average = students.stream()
                                  .mapToDouble(Student::getMarks)
                                  .average()
                                  .orElse(0.0);

        // We send these two extra pieces of data to the HTML
        model.addAttribute("totalStudents", count);
        model.addAttribute("averageMarks", String.format("%.2f", average));
        
        return "index";
    }

    // 2. Show the "Add Student" form
    @GetMapping("/showNewStudentForm")
    public String showNewStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "new_student";
    }

    // 3. Save student to database
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student) {
        repository.save(student);
        return "redirect:/";
    }

    // 4. Delete a student
    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable(value = "id") Long id) {
        this.repository.deleteById(id);
        return "redirect:/";
    }

    // 5. Show the Update form
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model) {
        Student student = repository.findById(id).get();
        model.addAttribute("student", student);
        return "update_student";
    }

    // 6. Search for students
    @GetMapping("/search")
    public String searchStudents(@RequestParam("name") String name, Model model) {
        List<Student> searchResults = repository.findByNameContaining(name);
        model.addAttribute("listStudents", searchResults);
        
        // Keep the stats working even during a search
        model.addAttribute("totalStudents", searchResults.size());
        model.addAttribute("averageMarks", "N/A (Search Mode)");
        
        return "index";
    }
}
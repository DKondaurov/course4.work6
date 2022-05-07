package pro.sky.java.course4.work6.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.java.course4.work6.model.Faculty;
import pro.sky.java.course4.work6.service.FacultyService;

import java.util.Collection;
import java.util.List;

@RequestMapping("/faculty")
@RestController
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> findFaculty(@PathVariable long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> removeFaculty(@PathVariable long id) {
        facultyService.removeFaculty(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> filterFacultyByColor(@RequestParam(required = false) String color, @RequestParam(required = false) String name) {
        Collection<Faculty> colorFaculty = facultyService.filterFacultyByColor(color, name);
        if (colorFaculty.size() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(colorFaculty);
    }

    @GetMapping("/longest-name-faculty")
    public ResponseEntity<List<Faculty>> longestNameFaculty() {
        List<Faculty> faculty = facultyService.longestNameFaculty();
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("/value-of-the-formula")
    public int valueOfTheFormula() {
        return facultyService.valueOfTheFormula();
    }
}

package pro.sky.java.course4.work6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.java.course4.work6.model.Faculty;

import java.util.Collection;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Collection<Faculty> findFacultyByColorIgnoreCaseOrNameIgnoreCase(String color, String name);

}

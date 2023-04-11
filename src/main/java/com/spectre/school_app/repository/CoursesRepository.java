package com.spectre.school_app.repository;

import com.spectre.school_app.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(path = "courses")  // ``only`` path(uri) defined for Rest services will be `basePath/courses`
// @RepositoryRestResource(exported = false) // will disable creating Rest APIs for `Courses Repository`
public interface CoursesRepository extends JpaRepository<Courses, Integer> {

    /*
    Spring Data JPA allows us to apply static sorting by adding the OrderBy keyword
    to the method name along with the property name and sort direction (Asc or Desc).
     */
    List<Courses> findByOrderByNameDesc();
    List<Courses> findByOrderByName();
}

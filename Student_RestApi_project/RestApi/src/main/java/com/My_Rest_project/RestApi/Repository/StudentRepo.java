package com.My_Rest_project.RestApi.Repository;

import com.My_Rest_project.RestApi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

    // 1. Search by name — case-insensitive partial match
    @Query("SELECT s FROM Student s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Student> searchByName(@Param("name") String name);

    // 2. Search by email — case-insensitive partial match
    @Query("SELECT s FROM Student s WHERE LOWER(s.email) LIKE LOWER(CONCAT('%', :email, '%'))")
    List<Student> searchByEmail(@Param("email") String email);

    // 3. Search by minimum marks — marks >= threshold
    @Query("SELECT s FROM Student s WHERE s.marks >= :minMarks")
    List<Student> findByMinMarks(@Param("minMarks") Float minMarks);

    // 4. Search by maximum marks — marks <= threshold
    @Query("SELECT s FROM Student s WHERE s.marks <= :maxMarks")
    List<Student> findByMaxMarks(@Param("maxMarks") Float maxMarks);

    // 5. Search between two marks — BETWEEN min AND max
    @Query("SELECT s FROM Student s WHERE s.marks BETWEEN :min AND :max")
    List<Student> findByMarksBetween(@Param("min") Float min, @Param("max") Float max);

    // 6. Combined filtering — all parameters optional
    @Query("SELECT s FROM Student s WHERE " +
           "(:name IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
           "(:email IS NULL OR LOWER(s.email) LIKE LOWER(CONCAT('%', :email, '%'))) AND " +
           "(:minMarks IS NULL OR s.marks >= :minMarks) AND " +
           "(:maxMarks IS NULL OR s.marks <= :maxMarks)")
    List<Student> combinedFilter(@Param("name") String name,
                                 @Param("email") String email,
                                 @Param("minMarks") Float minMarks,
                                 @Param("maxMarks") Float maxMarks);

    // 7. General search by name OR email
    @Query("SELECT s FROM Student s WHERE " +
           "LOWER(s.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(s.email) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Student> searchStudents(@Param("query") String query);
}

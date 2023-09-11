package com.example.prog4.repository;

import com.example.prog4.model.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "SELECT * FROM Employee WHERE first_name LIKE %:firstName%", nativeQuery = true)
    List<Employee> findByFirstNameContainingIgnoreCase(@Param("firstName") String firstName);

    List<Employee> findByLastNameContainingIgnoreCase(@Param("lastName") String lastName);

    @Query(value = "SELECT * FROM Employee WHERE sex LIKE %:sex%", nativeQuery = true)
    List<Employee> findBySex(@Param("sex") String sex);


    @Query(value = "SELECT * FROM Employee WHERE function LIKE %:function%", nativeQuery = true)
    List<Employee> findByFunctionContainingIgnoreCase(@Param("function") String function);

    @Query(value = "SELECT * FROM Employee WHERE hire_date BETWEEN :hireDateStart AND :hireDateEnd", nativeQuery = true)
    List<Employee> findByHireDateBetween(@Param("hireDateStart") LocalDate hireDateStart, @Param("hireDateEnd") LocalDate hireDateEnd);


    List<Employee> findByRetireDateBetween(@Param("retireDateStart") LocalDate retireDateStart, @Param("retireDateEnd") LocalDate retireDateEnd);


    @Query(value = "SELECT * FROM employee " +
            "WHERE (:firstName IS NULL OR employee.first_name LIKE %:firstName%) " +
            "AND (:lastName IS NULL OR employee.last_name LIKE %:lastName%) " +
            "AND (:sex IS NULL OR employee.sex = :sex) " +
            "AND (:function IS NULL OR employee.function = :function) " +
            "AND (:hireDateStart IS NULL OR employee.hire_date >= :hireDateStart) " +
            "AND (:hireDateEnd IS NULL OR employee.hire_date <= :hireDateEnd) " +
            "AND (:retireDateStart IS NULL OR employee.retire_date >= :retireDateStart) " +
            "AND (:retireDateEnd IS NULL OR employee.retire_date <= :retireDateEnd)",
            nativeQuery = true)
    List<Employee> findByCriteria(@Param("firstName") String firstName,
                                   @Param("lastName") String lastName,
                                   @Param("sex") String sex,
                                   @Param("function") String function,
                                   @Param("hireDateStart") LocalDate hireDateStart,
                                   @Param("hireDateEnd") LocalDate hireDateEnd,
                                   @Param("retireDateStart") LocalDate retireDateStart,
                                   @Param("retireDateEnd") LocalDate retireDateEnd);

}

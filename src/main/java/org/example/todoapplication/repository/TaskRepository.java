package org.example.todoapplication.repository;

import org.example.todoapplication.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByCategoryId (Long categoryId);

    boolean existsByTitle(String title);
}

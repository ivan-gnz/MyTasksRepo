package com.everis.everisdarmytasksms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.everisdarmytasksms.controller.Task;

public interface TasksRepository extends JpaRepository<Task, Integer>, TasksRepositoryCustom {

}
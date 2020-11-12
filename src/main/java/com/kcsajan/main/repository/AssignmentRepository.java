package com.kcsajan.main.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kcsajan.main.model.Assignment;

@Repository
public interface AssignmentRepository extends MongoRepository<Assignment, String> {
}

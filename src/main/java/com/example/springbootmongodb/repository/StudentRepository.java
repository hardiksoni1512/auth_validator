package com.example.springbootmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.springbootmongodb.model.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

} 
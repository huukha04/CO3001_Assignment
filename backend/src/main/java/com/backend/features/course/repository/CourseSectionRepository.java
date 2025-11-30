/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.backend.features.course.repository;

/**
 *
 * @author khahu
 */
import com.backend.features.course.entity.CourseSection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseSectionRepository extends MongoRepository<CourseSection, String> {
    // TODO
}

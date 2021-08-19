package com.maicon.microservice.course.endpoint.service;

import com.maicon.microservice.core.model.Course;
import com.maicon.microservice.core.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public Iterable<Course> list(Pageable pageable) {
        log.info("Listing all courses...");
        return courseRepository.findAll(pageable);
    }
}

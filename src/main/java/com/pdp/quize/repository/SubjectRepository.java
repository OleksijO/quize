package com.pdp.quize.repository;

import com.pdp.quize.domain.Subject;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepository extends CrudRepository<Subject, String> {
}

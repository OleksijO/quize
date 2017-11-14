package com.pdp.quize.repository;

import com.pdp.quize.domain.Subject;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface SubjectRepository extends CrudRepository<Subject, BigInteger> {
}

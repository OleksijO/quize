package com.pdp.quize.repository;

import com.pdp.quize.domain.TestResult;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface TestResultRepository extends CrudRepository<TestResult, BigInteger> {
}

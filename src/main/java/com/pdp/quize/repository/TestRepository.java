package com.pdp.quize.repository;

import com.pdp.quize.domain.Test;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface TestRepository extends CrudRepository<Test, BigInteger> {
}

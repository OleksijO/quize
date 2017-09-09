package com.pdp.quize.repository;

import com.pdp.quize.domain.TestItem;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface TestItemRepository extends CrudRepository<TestItem, BigInteger> {
}

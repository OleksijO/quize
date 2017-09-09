package com.pdp.quize.repository;

import com.pdp.quize.domain.Answer;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface AnswerRepository extends CrudRepository<Answer, BigInteger> {
}

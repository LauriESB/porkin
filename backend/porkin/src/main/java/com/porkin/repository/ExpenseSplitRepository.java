package com.porkin.repository;

import com.porkin.entity.ExpenseSplitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseSplitRepository extends JpaRepository<ExpenseSplitEntity, Long> {
}

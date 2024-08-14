package com.leonel.monsalve.equifaxinterview.data.repository;

import com.leonel.monsalve.equifaxinterview.data.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
}

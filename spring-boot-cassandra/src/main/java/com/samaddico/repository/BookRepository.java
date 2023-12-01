package com.samaddico.repository;

import com.samaddico.model.CreateBook;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface BookRepository extends CassandraRepository<CreateBook, UUID> {
}

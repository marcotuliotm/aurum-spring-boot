package com.aurum.process.repository;

import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

import com.aurum.process.domain.AbstractEntity;

public interface AbstractRepository <E extends AbstractEntity> extends DatastoreRepository<E, Long> {
}

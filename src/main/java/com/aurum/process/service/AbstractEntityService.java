package com.aurum.process.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.aurum.process.domain.AbstractEntity;
import com.aurum.process.exception.CreateNotAllowedException;
import com.aurum.process.exception.UpdateNotAllowedException;
import com.aurum.process.repository.AbstractRepository;

public abstract class AbstractEntityService<S extends AbstractEntity, R extends AbstractRepository<S>> {

	@Autowired
	protected R repository;

	public void beforeCreate(S newInstance) {}
	public void afterCreate(S createdInstance) {}

	public S create(S newInstance) {
		beforeCreate(newInstance);
		checkCreate(newInstance);
		final S createInstance = repository.save(newInstance);
		afterCreate(createInstance);
		return createInstance;
	}

	public Iterable<S> create(Iterable<S> newInstanceIterable) {
		newInstanceIterable.forEach(this::beforeCreate);
		return repository.saveAll(newInstanceIterable);
	}

	private void checkCreate(S toCreate) {
		if (toCreate.getId() != null) {
			throw new CreateNotAllowedException(toCreate.getClass().getName());
		}
	}

	public void beforeUpdate(S toUpdate) {}

	public S update(S toUpdate) {
		beforeUpdate(toUpdate);
		checkUpdate(toUpdate);
		return repository.save(toUpdate);
	}

	public Iterable<S> update(Iterable<S> toUpdateIterable) {
		toUpdateIterable.forEach(toUpdate -> {
			beforeCreate(toUpdate);
			checkUpdate(toUpdate);
		});
		return repository.saveAll(toUpdateIterable);
	}

	private void checkUpdate(S toUpdate) {
		if (toUpdate.getId() == null) {
			throw new UpdateNotAllowedException(toUpdate.getClass().getName());
		}
	}

	public S findById(Long id) {
		return repository.findById(id)
				.orElseThrow();
	}

	public void delete(Long id){
		repository.deleteById(id);
	}
}
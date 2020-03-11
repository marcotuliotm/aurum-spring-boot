package com.aurum.process.domain;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;


@Getter
public abstract class AbstractEntity {
	@Id
	@Setter
	protected Long id;

	protected AbstractEntity() {}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(getClass().isInstance(obj))) {
			return false;
		}
		final AbstractEntity other = (AbstractEntity) obj;
		return other.id != null && this.id != null && this.id.equals(other.id);
	}

	@Override
	public int hashCode() {
		if (id == null) {
			return super.hashCode();
		}
		return getClass().getName().hashCode() + this.id.hashCode();
	}
}

package br.com.ipet.catalog.domain.model;

import br.com.ipet.ordering.domain.model.entity.AggregateRoot;
import br.com.ipet.ordering.domain.model.repository.Repository;

public interface RemoveCapableRepository<T extends AggregateRoot<I>, I> extends Repository<T, I> {
    void remove(T t);
    void remove(I id);
}

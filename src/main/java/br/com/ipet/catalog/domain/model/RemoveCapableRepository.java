package br.com.ipet.catalog.domain.model;

public interface RemoveCapableRepository<T extends AggregateRoot<I>, I> extends Repository<T, I> {
    void remove(T t);
    void remove(I id);
}

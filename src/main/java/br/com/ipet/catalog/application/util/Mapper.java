package br.com.ipet.catalog.application.util;

public interface Mapper {
    <T> T convert(Object object, Class<T> destination);
}

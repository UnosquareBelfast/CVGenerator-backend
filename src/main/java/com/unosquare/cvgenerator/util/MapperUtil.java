package com.unosquare.cvgenerator.util;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MapperUtil {

    private final ModelMapper modelMapper;

    /**
     * Map one type to another type.
     *
     * @param entity          entity to be mapped.
     * @param conversionClass mapped class.
     * @param <T>             source object type.
     * @param <D>             type to map source to.
     * @return Object of conversionClass type.
     */
    public <T, D> D map(T entity, Class<D> conversionClass) {
        return modelMapper.map(entity, conversionClass);
    }

    /**
     * Map a list of one type to a list of another type
     *
     * @param entityList      list of entites to be mapped.
     * @param conversionClass mapped class.
     * @param <T>             source object type.
     * @param <D>             type to map source to.
     * @return ArrayList of conversionClass type.
     */
    public <T, D> List<D> map(Collection<T> entityList, Class<D> conversionClass) {
        return new ArrayList<>(
                entityList.stream()
                          .map(entity -> map(entity, conversionClass))
                          .collect(Collectors.toList())
        );
    }

}

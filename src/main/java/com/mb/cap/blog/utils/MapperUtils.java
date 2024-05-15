package com.mb.cap.blog.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MapperUtils {
    private static ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static String[] getEqualProperties(Object source, Object target) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        final BeanWrapper des = new BeanWrapperImpl(target);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            Object desValue = des.getPropertyValue(pd.getName());
            if (equal(srcValue, desValue)) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static void mapIgnoreNull(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    public static void mapIgnoreEquals(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getEqualProperties(src, target));
    }

    public static <D, T> D map(final T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }

    public static <D, T> List<D> map(final Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> map(entity, outCLass))
                .collect(Collectors.toList());
    }

    public static <D, T> Set<D> mapToSet(final Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> map(entity, outCLass))
                .collect(Collectors.toSet());
    }

    public static <S, D> D map(final S source, D destination) {
        modelMapper.map(source, destination);
        return destination;
    }

    public static <S, D> D copyWithoutAudit(final S source, D destination) {
        return copy(source, destination, "createBy", "createDate", "version", "id");
    }

    public static <S, D> D copy(final S source, D destination) {
        BeanUtils.copyProperties(source, destination);
        return  destination;
    }

    public static <S, D> D copy(final S source, D destination, String... ignore) {
        BeanUtils.copyProperties(source, destination, ignore);
        return destination;
    }

    public static boolean equal(Object source, Object target) {
        return (source == null && target == null) || (source != null && target != null && source.equals(target));
    }

    public static List<Map<String, Object>> underscoreToCamelcase(List<Map<String, Object>> list, String... ignore) {
        List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
        list.stream().forEach(item->{
            newList.add(underscoreToCamelcase(item,ignore));
        });
        return newList;
    }

    public static Map<String, Object> underscoreToCamelcase(Map<String, Object> map,String... ignore) {
        List<String> keyIgnores=Arrays.asList(ignore);
        Map<String, Object> newMap = new HashMap<String, Object>();
        map.forEach((key,value)->{
            if(keyIgnores.contains(key)) {
                newMap.put(key, value);
            }else{
                String newKey = Pattern.compile("_([a-z])").matcher(key.toLowerCase()).replaceAll(m -> m.group(1).toUpperCase());
                newMap.put(newKey, value);
            }
        });
        return newMap;
    }

    public static <T, D> List<D> mapList(List<T> entities, Class<D> dtos) {
        return entities.stream().map(entity -> modelMapper.map(entity, dtos)).collect(Collectors.toList());
    }
}

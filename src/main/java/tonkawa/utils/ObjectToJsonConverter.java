package utils;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ObjectToJsonConverter {

    <T> String convert(T object) throws JsonProcessingException;
}

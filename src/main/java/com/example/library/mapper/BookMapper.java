package com.example.library.mapper;

import com.example.library.dto.BookDTO;
import com.example.library.entities.Book;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {

    private static ModelMapper mapper = new ModelMapper();

    static {
        mapper.createTypeMap(Book.class, BookDTO.class)
                .addMapping(Book::getId,BookDTO::setKey);

        mapper.createTypeMap(BookDTO.class, Book.class)
                .addMapping(BookDTO::getKey,Book::setId);
    }


    public static <O,D> D parseObject(O origin, Class<D> destination){
        return mapper.map(origin,destination);
    }

    public static <O,D> List<D> parseListObjects(List<O> origin, Class<D> destination){
        List<D> destinationObjects = new ArrayList<>();
        for (O o: origin){
            destinationObjects.add(mapper.map(o, destination));
        }
        return destinationObjects;
    }
}

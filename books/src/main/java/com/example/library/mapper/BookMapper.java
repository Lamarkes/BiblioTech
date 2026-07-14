package com.example.library.mapper;

import com.example.library.dto.book.BookUpdateDTO;
import com.example.library.entities.Book;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {

    private static final ModelMapper mapper = new ModelMapper();


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


    public static Book updateBookDtoToEntity(BookUpdateDTO request, Book book){

        book.setTitle(request.getTitle());
        book.setYear(request.getYear());
        book.setValue(request.getValue());
        book.setNumPages(request.getNumPages());
        return book;
    }
}

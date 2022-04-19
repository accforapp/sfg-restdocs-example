package com.example.sfgrestdocsexample.web.mappers;

import com.example.sfgrestdocsexample.domain.Beer;
import com.example.sfgrestdocsexample.web.model.BeerDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

  BeerMapper INSTANCE = Mappers.getMapper(BeerMapper.class);

  BeerDto beerToBeerDto(Beer beer);
  Beer beerDtoToBeer(BeerDto beerDto);
}

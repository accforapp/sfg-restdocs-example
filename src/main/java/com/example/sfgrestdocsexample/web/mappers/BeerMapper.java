package com.example.sfgrestdocsexample.web.mappers;

import com.example.sfgrestdocsexample.domain.Beer;
import com.example.sfgrestdocsexample.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
  BeerDto beerToBeerDto(Beer beer);
  Beer beerDtoToBeer(BeerDto beerDto);
}

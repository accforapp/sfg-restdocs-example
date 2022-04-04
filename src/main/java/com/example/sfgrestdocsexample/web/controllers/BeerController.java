package com.example.sfgrestdocsexample.web.controllers;

import com.example.sfgrestdocsexample.repositories.BeerRepository;
import com.example.sfgrestdocsexample.web.mappers.BeerMapper;
import com.example.sfgrestdocsexample.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("api/v1/beer")
@RestController
public class BeerController {

  private final BeerMapper beerMapper;
  private final BeerRepository beerRepository;

  public BeerController(BeerMapper beerMapper, BeerRepository beerRepository) {
    this.beerMapper = beerMapper;
    this.beerRepository = beerRepository;
  }

  @GetMapping("/{beerId}")
  public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId) {

    return new ResponseEntity<>(beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow()), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity saveNewBeer(@RequestBody @Validated BeerDto beerDto) {

    beerRepository.save(beerMapper.beerDtoToBeer(beerDto));

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping("/{beerId}")
  public ResponseEntity updateBeerById(@PathVariable UUID beerId, @RequestBody @Validated BeerDto beerDto) {

    beerRepository.findById(beerId).ifPresent(beer -> {
      beer.setBeerName(beerDto.getBeerName());
      beer.setBeerStyle(beerDto.getBeerStyle().name());
      beer.setPrice(beerDto.getPrice());
      beer.setUpc(beerDto.getUpc());

      beerRepository.save(beer);
    });

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }
}

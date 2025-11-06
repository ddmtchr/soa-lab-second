package com.ddmtchr.soalabsecond.service;

import com.ddmtchr.soalabsecond.client.PersonClient;
import com.ddmtchr.soalabsecond.dto.person.PersonRequestDto;
import com.ddmtchr.soalabsecond.dto.person.PersonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonClient personClient;

    public PersonResponseDto findById(Long id) {
        return personClient.getById(id);
    }

    public List<PersonResponseDto> findAll() {
        return personClient.findAll().getContent();
    }

    public PersonResponseDto save(PersonRequestDto dto) {
        return personClient.create(dto);
    }

    public PersonResponseDto update(Long id, PersonRequestDto dto) {
        return personClient.update(id, dto);
    }

    public void delete(Long id) {
        personClient.delete(id);
    }

}

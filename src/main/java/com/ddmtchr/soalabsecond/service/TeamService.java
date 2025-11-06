package com.ddmtchr.soalabsecond.service;

import com.ddmtchr.soalabsecond.client.TeamClient;
import com.ddmtchr.soalabsecond.dto.team.TeamRequestDto;
import com.ddmtchr.soalabsecond.dto.team.TeamResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamClient teamClient;

    public TeamResponseDto findById(Long id) {
        return teamClient.getById(id);
    }

    public List<TeamResponseDto> findAll() {
        return teamClient.findAll().getContent();
    }

    public TeamResponseDto save(TeamRequestDto dto) {
        return teamClient.create(dto);
    }

    public TeamResponseDto update(Long id, TeamRequestDto dto) {
        return teamClient.update(id, dto);
    }

    public void delete(Long id) {
        teamClient.delete(id);
    }
}

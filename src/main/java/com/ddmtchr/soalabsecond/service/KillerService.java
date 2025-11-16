package com.ddmtchr.soalabsecond.service;

import com.ddmtchr.soalabsecond.client.CaveClient;
import com.ddmtchr.soalabsecond.client.DragonClient;
import com.ddmtchr.soalabsecond.client.PersonClient;
import com.ddmtchr.soalabsecond.client.TeamClient;
import com.ddmtchr.soalabsecond.dto.cave.CaveResponseDto;
import com.ddmtchr.soalabsecond.dto.dragon.DragonResponseDto;
import com.ddmtchr.soalabsecond.dto.person.PersonResponseDto;
import com.ddmtchr.soalabsecond.dto.team.TeamResponseDto;
import com.ddmtchr.soalabsecond.exception.ConflictException;
import com.ddmtchr.soalabsecond.mapper.DragonMapper;
import com.ddmtchr.soalabsecond.mapper.TeamMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KillerService {

    private final DragonMapper dragonMapper;
    private final TeamMapper teamMapper;

    private final DragonClient dragonClient;
    private final PersonClient personClient;
    private final TeamClient teamClient;
    private final CaveClient caveClient;

    public void setDragonKiller(Long dragonId, Long killerId) {
        DragonResponseDto dragon = dragonClient.getById(dragonId);
        PersonResponseDto killer = personClient.getById(killerId);

        if (dragon.getKiller() != null) {
            throw new ConflictException(List.of("Dragon is already killed"));
        }

        dragon.setKiller(killer);
        dragonClient.update(dragonId, dragonMapper.responseDtoToRequestDto(dragon));
    }

    public void setTeamCave(Long teamId, Long caveId) {
        TeamResponseDto team = teamClient.getById(teamId);
        CaveResponseDto cave = caveClient.getById(caveId);

        if (team.getCave() != null && team.getCave().getId().equals(caveId)) {
            throw new ConflictException(List.of("Team is already in specified cave"));
        }

        team.setCave(cave);
        teamClient.update(teamId, teamMapper.responseDtoToRequestDto(team));
    }
}

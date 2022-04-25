package be.pxl.superhero.service;

import be.pxl.superhero.api.MissionDTO;
import be.pxl.superhero.api.CreateMissionRequest;
import be.pxl.superhero.api.MissionDetailDTO;
import be.pxl.superhero.api.UpdateMissionRequest;

import java.util.List;

public interface MissionService {

	List<MissionDTO> findAllMissions();

	MissionDetailDTO findMissionById(Long missionId);

	MissionDTO createMission(CreateMissionRequest missionRequest);

	MissionDetailDTO updateMission(Long missionId, UpdateMissionRequest missionRequest);

	boolean deleteMission(Long missionId);
}

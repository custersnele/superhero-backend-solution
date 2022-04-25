package be.pxl.superhero.service.impl;

import be.pxl.superhero.api.MissionDTO;
import be.pxl.superhero.api.CreateMissionRequest;
import be.pxl.superhero.api.MissionDetailDTO;
import be.pxl.superhero.api.UpdateMissionRequest;
import be.pxl.superhero.domain.Mission;
import be.pxl.superhero.exception.ResourceNotFoundException;
import be.pxl.superhero.repository.MissionRepository;
import be.pxl.superhero.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MissionServiceImpl implements MissionService {

	private final MissionRepository missionRepository;

	@Autowired
	public MissionServiceImpl(MissionRepository missionRepository) {
		this.missionRepository = missionRepository;
	}

	@Override
	public List<MissionDTO> findAllMissions() {
		return missionRepository.findAll().stream().map(MissionDTO::new).collect(Collectors.toList());
	}

	@Override
	public MissionDetailDTO findMissionById(Long missionId) {
		return missionRepository.findById(missionId).map(MissionDetailDTO::new).orElseThrow(() -> new ResourceNotFoundException("Mission", "ID", missionId));
	}

	@Override
	public MissionDTO createMission(CreateMissionRequest missionRequest) {
		Mission mission = new Mission();
		mission.setName(missionRequest.getMissionName());
		Mission savedMission = missionRepository.save(mission);
		return new MissionDTO(savedMission);
	}

	@Transactional
	public MissionDetailDTO updateMission(Long missionId, UpdateMissionRequest missionRequest) {
		Mission mission = missionRepository.findById(missionId).orElseThrow(() -> new ResourceNotFoundException("Mission", "ID", missionId));
		mission.setCompleted(missionRequest.isCompleted());
		return new MissionDetailDTO(mission);
	}

	@Override
	public boolean deleteMission(Long missionId) {
		return false;
	}
}

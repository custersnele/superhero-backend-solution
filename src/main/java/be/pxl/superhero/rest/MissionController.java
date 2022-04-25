package be.pxl.superhero.rest;

import be.pxl.superhero.api.MissionDTO;
import be.pxl.superhero.api.CreateMissionRequest;
import be.pxl.superhero.api.UpdateMissionRequest;
import be.pxl.superhero.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/missions")
public final class MissionController {

	private final MissionService missionService;

	@Autowired
	public MissionController(MissionService missionService) {
		this.missionService = missionService;
	}

	@GetMapping
	public List<MissionDTO> getMissions() {
		return missionService.findAllMissions();
	}

	@GetMapping("/{missionId}")
	public MissionDTO getSuperheroById(@PathVariable Long missionId) {
		return missionService.findMissionById(missionId);
	}
	
	@PostMapping
	public ResponseEntity<MissionDTO> createMission(@Valid @RequestBody CreateMissionRequest missionRequest) {
		return new ResponseEntity<>(missionService.createMission(missionRequest), HttpStatus.CREATED);
	}
	
	@PutMapping("/{missionId}")
	public MissionDTO updateMission(@PathVariable Long missionId, @Valid @RequestBody UpdateMissionRequest missionRequest) {
		return missionService.updateMission(missionId, missionRequest);
	}
	
	@DeleteMapping("/{missionId}")
	public ResponseEntity<Void> deleteSuperhero(@PathVariable Long superheroId) {
		return ResponseEntity.ok(null);
	}
}

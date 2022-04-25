package be.pxl.superhero.service.impl;

import be.pxl.superhero.api.SuperheroDTO;
import be.pxl.superhero.api.SuperheroDetailDTO;
import be.pxl.superhero.api.SuperheroRequest;
import be.pxl.superhero.domain.Mission;
import be.pxl.superhero.domain.Superhero;
import be.pxl.superhero.exception.ResourceNotFoundException;
import be.pxl.superhero.repository.MissionRepository;
import be.pxl.superhero.repository.SuperheroRepository;
import be.pxl.superhero.service.SuperheroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SuperheroServiceImpl implements SuperheroService {

	private final SuperheroRepository superheroRepository;
	private final MissionRepository missionRepository;

	@Autowired
	public SuperheroServiceImpl(SuperheroRepository superheroRepository, MissionRepository missionRepository) {
		this.superheroRepository = superheroRepository;
		this.missionRepository = missionRepository;
	}

	public List<SuperheroDTO> findAllSuperheroes() {
		return superheroRepository.findAll()
				.stream().map(SuperheroDTO::new).collect(Collectors.toList());
	}

	public SuperheroDetailDTO findSuperheroById(Long superheroId) {
		return superheroRepository.findById(superheroId).map(SuperheroDetailDTO::new)
				.orElseThrow(() -> new ResourceNotFoundException("Superhero", "ID", superheroId));
	}

	public SuperheroDTO createSuperhero(SuperheroRequest superheroRequest) {
		Optional<Superhero> existingSuperhero = superheroRepository.findSuperheroBySuperheroName(superheroRequest.getSuperheroName());
		existingSuperhero.ifPresent(s -> {
			throw new ValidationException("This superhero name is already taken.");
		});
		Superhero superhero = new Superhero();
		superhero.setFirstName(superheroRequest.getFirstName());
		superhero.setLastName(superheroRequest.getLastName());
		superhero.setSuperheroName(superheroRequest.getSuperheroName());
		Superhero newSuperhero = superheroRepository.save(superhero);
		return new SuperheroDTO(newSuperhero);
	}

	public SuperheroDTO updateSuperhero(Long superheroId, SuperheroRequest superheroRequest) {
		return superheroRepository.findById(superheroId).map(superhero -> {
			superhero.setFirstName(superheroRequest.getFirstName());
			superhero.setLastName(superheroRequest.getLastName());
			superhero.setSuperheroName(superheroRequest.getSuperheroName());
			return new SuperheroDTO(superheroRepository.save(superhero));
		}).orElseThrow(() -> new ResourceNotFoundException("Superhero", "id", superheroId));
	}

	public boolean deleteSuperhero(Long superheroId) {
		return superheroRepository.findById(superheroId)
				.map(superhero -> {
					superheroRepository.delete(superhero);
					return true;
				}).orElseThrow(() -> new ResourceNotFoundException("Superhero", "id", superheroId));

	}

	@Transactional
	public void addSuperheroToMission(Long superheroId, Long missionId) {
		Superhero superhero = superheroRepository.findById(superheroId).orElseThrow(() -> new ResourceNotFoundException("Superhero", "ID", superheroId));
		Mission mission = missionRepository.findById(missionId).orElseThrow(() -> new ResourceNotFoundException("Mission", "ID", missionId));
		superhero.addMission(mission);
	}
}

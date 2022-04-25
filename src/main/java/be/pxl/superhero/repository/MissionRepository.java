package be.pxl.superhero.repository;

import be.pxl.superhero.domain.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

	Optional<Mission> findMissionByName(String name);
}

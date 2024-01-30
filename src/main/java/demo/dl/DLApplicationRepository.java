package demo.dl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DLApplicationRepository extends JpaRepository<DLApplicationModel, UUID> {
}
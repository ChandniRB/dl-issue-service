package demo.dl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DLService {
    
    @Autowired
    private DLApplicationRepository dlApplicationRepository;

    @Autowired
    private DLApplicationReferenceRepository dlApplicationReferenceRepository;

    @Autowired
    private DLRepository dlRepository;

    public  DLApplicationModel createDLApplication(DLApplicationModel dl) {
        return dlApplicationRepository.save(dl);
    }

    public Optional<DLApplicationModel> getDLApplicationById(UUID id) {
        return dlApplicationRepository.findById(id);
    }
    public  DLModel createDL(DLModel dl) {
        return dlRepository.save(dl);
    }

    public Optional<DLModel> getDLById(UUID id) {
        return dlRepository.findById(id);
    }

    public  DLApplicationReferenceModel createDLApplicationReference(DLApplicationReferenceModel dl) {
        return dlApplicationReferenceRepository.save(dl);
    }

    public Optional<DLApplicationReferenceModel> getDLApplicationReferenceById(String id) {
        return dlApplicationReferenceRepository.findById(id);
    }
}

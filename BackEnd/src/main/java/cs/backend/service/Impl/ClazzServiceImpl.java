package cs.backend.service.Impl;

import cs.backend.pojo.Clazz;
import cs.backend.reporitoty.ClazzRepository;
import cs.backend.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzRepository clazzRepository;

    @Override
    public void addClazz(Clazz clazz) {
        clazzRepository.save(clazz);
    }

    @Override
    public Clazz getClazz(int id) {
        return null;
    }

    @Override
    public List<Clazz> getAllClazzes() {
        List<Clazz> clazzes=clazzRepository.findAll();
        return clazzes;
    }

    @Override
    public void deleteClazz(int id) {
        clazzRepository.deleteById(id);
    }

    @Override
    public void updateClazz(Clazz clazz) {
        clazzRepository.save(clazz);
    }
}

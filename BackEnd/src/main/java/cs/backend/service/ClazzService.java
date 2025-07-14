package cs.backend.service;


import cs.backend.pojo.Clazz;

import java.util.List;

public interface ClazzService {
    public void addClazz(Clazz clazz);
    public Clazz getClazz(int id);
    public List<Clazz> getAllClazzes();
    public void deleteClazz(int id);
    public void updateClazz(Clazz clazz);

}

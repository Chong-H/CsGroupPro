package cs.backend.service.Impl;

import cs.backend.pojo.Notice;
import cs.backend.reporitoty.NoticeRepository;
import cs.backend.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeRepository noticeRepository;
    @Override
    public List<Notice> findall() {
        try {
            List<Notice> list = noticeRepository.findAll();
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Notice findone(int id) {
        try{
            List<Notice> list = noticeRepository.findAll();
            for (Notice notice : list) {
                if (notice.getId() == id) {
                    return notice;
                }
             }
        return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void save(Notice notice) {
        try{
            notice.setCreate_date(LocalDateTime.now().toString());
            noticeRepository.save(notice);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Integer delete(Long id) {
        try{
            noticeRepository.deleteById((long) id);
            return Math.toIntExact(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;

    }

    @Override
    public Notice update(Notice notice) {
        try{
            noticeRepository.save(notice);
            return notice;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

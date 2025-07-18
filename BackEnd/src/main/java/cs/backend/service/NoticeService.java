package cs.backend.service;

import cs.backend.pojo.Notice;

import java.util.List;

public interface NoticeService {
    List<Notice> findall();
    Notice findone(int id);
    void save(Notice notice);
    Integer delete(Long id);
    Notice update(Notice notice);
}

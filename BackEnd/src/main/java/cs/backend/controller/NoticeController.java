package cs.backend.controller;

import cs.backend.dto.ResponseMessage;
import cs.backend.pojo.Notice;
import cs.backend.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {
    @Autowired
    NoticeService noticeService;
    @GetMapping("/get")
    public ResponseMessage<List<Notice>> getNotices() {
        return ResponseMessage.success(noticeService.findall());
    }
    @PostMapping("/add")
    public ResponseMessage<Notice> addNotice(@RequestBody Notice notice) {
        noticeService.save(notice);
        return ResponseMessage.success(notice);
    }
    @PostMapping("/update")
    public ResponseMessage<Notice> updateNotice(@RequestBody Notice notice) {
        noticeService.save(notice);
        return ResponseMessage.success(notice);
    }
    @DeleteMapping("/del")
    public ResponseMessage<Integer> delNotice(@RequestBody Notice notice){
        Integer i=noticeService.delete(notice.getId());
        if(i==-1){
            ResponseMessage.error(333,"未能删除",null);
        }else{
            return ResponseMessage.success(i);
        }
        return ResponseMessage.success(i);
    }

}

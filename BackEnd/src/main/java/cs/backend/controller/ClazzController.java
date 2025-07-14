package cs.backend.controller;

import cs.backend.dto.ResponseMessage;
import cs.backend.pojo.Clazz;
import cs.backend.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clazz")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /**
     * 获取所有班级列表
     */
    @GetMapping("/all")
    public ResponseMessage<List<Clazz>> getAllClazzes() {
        try {
            List<Clazz> clazzes = clazzService.getAllClazzes();
            if (clazzes.isEmpty()) {
                return ResponseMessage.error(222, "班级列表为空", null);
            }
            return ResponseMessage.success(clazzes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error(500, "获取班级列表失败", null);
        }
    }

    /**
     * 根据ID获取单个班级
     */
    @GetMapping("/{id}")
    public ResponseMessage<Clazz> getClazzById(@PathVariable int id) {
        try {
            Clazz clazz = clazzService.getClazz(id);
            if (clazz == null) {
                return ResponseMessage.error(404, "班级不存在", null);
            }
            return ResponseMessage.success(clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error(500, "获取班级信息失败", null);
        }
    }

    /**
     * 添加新班级
     */
    @PostMapping("/add")
    public ResponseMessage<Clazz> addClazz(@RequestBody Clazz clazz) {
        try {
            clazzService.addClazz(clazz);
            return ResponseMessage.success(clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error(500, "添加班级失败", null);
        }
    }

    /**
     * 更新班级信息
     */
    @PutMapping("/update")
    public ResponseMessage<Clazz> updateClazz(@RequestBody Clazz clazz) {
        try {
            // 检查班级是否存在
            Clazz existingClazz = clazzService.getClazz(clazz.getClazzId());
            if (existingClazz == null) {
                return ResponseMessage.error(404, "班级不存在", null);
            }

            clazzService.updateClazz(clazz);
            return ResponseMessage.success(clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error(500, "更新班级信息失败", null);
        }
    }

    /**
     * 根据ID删除班级
     */
    @DeleteMapping("/delete/{id}")
    public ResponseMessage<Void> deleteClazz(@PathVariable int id) {
        try {
            // 检查班级是否存在
            Clazz existingClazz = clazzService.getClazz(id);
            if (existingClazz == null) {
                return ResponseMessage.error(404, "班级不存在", null);
            }

            clazzService.deleteClazz(id);
            return ResponseMessage.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error(500, "删除班级失败", null);
        }
    }
}
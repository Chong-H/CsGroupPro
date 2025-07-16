package cs.backend.controller;

import cs.backend.dto.ResponseMessage;
import cs.backend.pojo.Assignment;
import cs.backend.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;


    @GetMapping("/num")
    public ResponseMessage<Integer> getAssignmentsnum() {
        try{
            int size=assignmentService.findAssignmentsByCriteria(null,null,null).size();
            return ResponseMessage.success(size);
        } catch (Exception e) {
            return ResponseMessage.error(300, "查询作业数量失败", null);
        }
    }
    @PostMapping("/create")
    public ResponseMessage<Assignment> createAssignment(@RequestBody Assignment assignment) {
        try {
            if (assignment.getTeacherId() == null || assignment.getClazzId() == null || assignment.getCourseId() == null || assignment.getDueTime() == null) {
                return ResponseMessage.error(400, "教师ID、班级ID、课程ID和截止时间不能为空", null);
            }
            Assignment createdAssignment = assignmentService.createAssignment(assignment);
            return ResponseMessage.success(createdAssignment);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error(500, "作业布置失败", null);
        }
    }


    @GetMapping("/query")
    public ResponseMessage<List<Assignment>> getAssignments(
            @RequestParam(required = false) Integer clazzId,
            @RequestParam(required = false) Integer courseId,
            @RequestParam(required = false) Integer teacherId) {
        try {
            List<Assignment> assignments = assignmentService.findAssignmentsByCriteria(clazzId, courseId, teacherId);
            return ResponseMessage.success(assignments);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error(500, "获取作业列表失败", null);
        }
    }


    @PutMapping("/update")
    public ResponseMessage<List<Assignment>> updateAssignments(
            @RequestParam(required = false) Integer clazzId,
            @RequestParam(required = false) Integer courseId,
            @RequestParam(required = false) Integer teacherId,

            @RequestBody Assignment updates) {
        try {
            List<Assignment> updatedAssignments = assignmentService.updateAssignmentsByCriteria(updates, clazzId, courseId, teacherId);
            return ResponseMessage.success(updatedAssignments);
        } catch (IllegalArgumentException e) {
            return ResponseMessage.error(400, e.getMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error(500, "更新作业失败", null);
        }
    }


    @DeleteMapping("/delete")
    public ResponseMessage<String> deleteAssignments(
            @RequestParam(required = false) Integer clazzId,
            @RequestParam(required = false) Integer courseId,
            @RequestParam(required = false) Integer teacherId) {
        try {
            long deletedCount = assignmentService.deleteAssignmentsByCriteria(clazzId, courseId, teacherId);
            if (deletedCount == 0) {
                return ResponseMessage.success("没有找到符合条件的记录进行删除。");
            }
            return ResponseMessage.success("成功删除 " + deletedCount + " 条记录。");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error(500, "删除作业失败", null);
        }
    }
}
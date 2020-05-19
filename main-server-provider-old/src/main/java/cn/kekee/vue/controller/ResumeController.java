package cn.kekee.vue.controller;

import cn.kekee.vue.bean.Send;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.kekee.vue.Service.ResumeService;

import java.util.Map;

/**
 * @author cocoa
 */
@RestController
@CrossOrigin()
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @PostMapping("/resume")
    public int addResume(@RequestBody Map<String, Object> models) {
        boolean res = resumeService.AddResume(models);
        if (res) {
            return 1;
        }
        return 0;
    }

    @PostMapping("/updateResume")
    public int updateResume(@RequestBody Map<String, Object> models) {
        if (resumeService.updateResume(models)) {
            return 1;
        }
        return 0;
    }

    @GetMapping("/resume/{uid}")
    public Map<String, Object> getResumeByUid(@PathVariable("uid") Integer uid) {
        return resumeService.getResume(uid);
    }

    @PostMapping("/send")
    public int send(@RequestBody Send send) {
        boolean res = resumeService.Deliver(send);
        if (res) {
            return 1;
        }
        return 0;
    }

    @PostMapping("/checkPost")
    public int send(@RequestBody Map<String, Object> req) {
        Integer uid, jid;
        uid = (Integer) req.get("uid");
        jid = (Integer) req.get("jid");
        boolean res = resumeService.checkPost(uid, jid);
        System.out.println(res);
        if (res) {
            return 1;
        }
        return 0;
    }
}

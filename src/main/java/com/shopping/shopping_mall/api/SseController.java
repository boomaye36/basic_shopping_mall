package com.shopping.shopping_mall.api;

import com.shopping.shopping_mall.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sse")
public class SseController {
    private final NotificationService notificationService;

    @GetMapping(value = "/notification", produces = "text/event-stream")
    public SseEmitter notifications(@RequestParam Long userId){
        SseEmitter emitter = new SseEmitter();
        notificationService.addEmitter(userId, emitter);
        emitter.onCompletion(() ->
                notificationService.removeEmitter(userId, emitter));
        return emitter;
    }
}

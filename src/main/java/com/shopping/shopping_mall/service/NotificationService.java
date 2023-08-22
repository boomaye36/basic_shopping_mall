package com.shopping.shopping_mall.service;

import com.shopping.shopping_mall.dto.Notification;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NotificationService {
    private final Map<Long, List<SseEmitter>> emitterMap = new ConcurrentHashMap<>();

    public void addEmitter(Long userId, SseEmitter emitter){
        emitterMap.computeIfAbsent(userId, k -> new ArrayList<>()).add(emitter);
    }

    public void removeEmitter(Long userId, SseEmitter emitter){
        List<SseEmitter> emitters = emitterMap.get(userId);
        if (emitters != null){
            emitters.remove(emitter);
            if (emitters.isEmpty()){
                emitterMap.remove(userId);
            }
        }
    }

    public void sendNotification(Long userId, Notification notification){
        List<SseEmitter> emitters = emitterMap.get(userId);
        if (emitters != null){
            emitters.forEach(emitter -> {
                try{
                    emitter.send(notification);
                }catch (IOException e){
                    emitter.complete();
                }
            });
        }
    }
}

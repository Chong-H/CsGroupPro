package cs.backend.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@RestController
public class ChatbotController {

    private static final String API_KEY = "sk-7bd1a14459c74c4bbb35ef206c40a623";
    private static final String API_URL = "https://api.deepseek.com/v1/chat/completions";

    @PostMapping("/api/chatbot")
    public Map<String, String> chat(@RequestBody Map<String, String> body) {
        String question = body.get("question");
        String answer = callDeepSeek(question);
        Map<String, String> result = new HashMap<>();
        result.put("answer", answer);
        return result;
    }

    private String callDeepSeek(String question) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "deepseek-chat");
        List<Map<String, String>> messages = new ArrayList<>();
        // 系统提示词
        messages.add(Map.of("role", "system", "content", "你是一个专业、耐心的在线智能客服，只能解答与本平台相关的问题，禁止回答与平台无关的内容。"));
        // 用户问题
        messages.add(Map.of("role", "user", "content", question));
        requestBody.put("messages", messages);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(API_KEY);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(API_URL, entity, Map.class);
            // 解析返回内容
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                // DeepSeek 返回格式：choices[0].message.content
                List choices = (List) response.getBody().get("choices");
                if (choices != null && !choices.isEmpty()) {
                    Map choice = (Map) choices.get(0);
                    Map message = (Map) choice.get("message");
                    if (message != null) {
                        return (String) message.get("content");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "很抱歉，智能客服暂时无法为您解答。";
    }
}
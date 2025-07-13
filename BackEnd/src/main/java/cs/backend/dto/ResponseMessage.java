package cs.backend.dto;


import org.springframework.http.HttpStatus;

public class ResponseMessage<T> {

    private Integer code;
    private String message;
    private T data;
    private String sessionId;
    private String pkeybob;

    public String getPkeybob() {
        return pkeybob;
    }

    public void setPkeybob(String pkeybob) {
        this.pkeybob = pkeybob;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public ResponseMessage(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // Success
    public static<T> ResponseMessage<T> success(T data) {
        return new ResponseMessage<>(HttpStatus.OK.value(), "Success", data);
    }
}

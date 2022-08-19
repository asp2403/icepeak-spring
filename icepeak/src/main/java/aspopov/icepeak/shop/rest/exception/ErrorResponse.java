package aspopov.icepeak.shop.rest.exception;

import org.springframework.lang.Nullable;

public class ErrorResponse {

    private int errorCode;

    private String errorMessage;

    @Nullable
    private Long id;

    public ErrorResponse(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ErrorResponse(int errorCode, String errorMessage, Long id) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.id = id;
    }

    public ErrorResponse(int errorCode, Long id, RuntimeException ex) {
        this.errorCode = errorCode;
        this.errorMessage = ex.getMessage();
        this.id = id;
    }

    public ErrorResponse(int errorCode, RuntimeException ex) {
        this.errorCode = errorCode;
        this.errorMessage = ex.getMessage();
    }

    public ErrorResponse() {
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

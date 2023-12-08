package fca.suayed.dto;

public class ResponseDto<T> {
    private Boolean success;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseDto(){ }

    public ResponseDto(Boolean success){
        this.success = success;
    }


    public ResponseDto(T data){
        this.data = data;
    }

    public ResponseDto(Boolean success, T data){
        this.success = success;
        this.data = data;
    }

    private T data;
}

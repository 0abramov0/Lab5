package utils.responses;

public class Response<T> {
    private ResponseStatus status;
    private T message;

    public Response(){}

    public Response(ResponseStatus status, T data){
        this.status = status;
        this.message = data;
    }

    public void setStatus(ResponseStatus status){
        this.status = status;
    }

    public void setMessage(T data){
        this.message = data;
    }

    public ResponseStatus getStatus(){
        return status;
    }

    public T getMessage(){
        return message;
    }
}

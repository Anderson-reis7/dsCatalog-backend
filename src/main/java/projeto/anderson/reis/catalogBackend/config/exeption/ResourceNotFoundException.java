package projeto.anderson.reis.catalogBackend.config.exeption;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String msg){
        super(msg);
    }

}

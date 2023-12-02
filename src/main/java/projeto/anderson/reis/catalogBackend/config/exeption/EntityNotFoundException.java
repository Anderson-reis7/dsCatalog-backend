package projeto.anderson.reis.catalogBackend.config.exeption;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String msg){
        super(msg);
    }

}

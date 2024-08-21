package tz.ac.iact.va.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tz.ac.iact.va.dto.ErrorResponse;
import tz.ac.iact.va.dto.MessageDTO;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JwtAuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleJwtAuthenticationException(JwtAuthenticationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<MessageDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        MessageDTO messageDTO =new MessageDTO();
        List<String> errors = exception.getBindingResult().getFieldErrors()
                .stream().map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.toList());
        messageDTO.setMessage(errors);
        messageDTO.setStatus(false);
        return new ResponseEntity<>(messageDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DataNotFoundException.class)
    public ResponseEntity<MessageDTO> handleDataNotFoundException(DataNotFoundException exception){
        MessageDTO messageDTO =new MessageDTO();
        messageDTO.setMessage(exception.getMessage());
        messageDTO.setStatus(false);
        messageDTO.setStatus(false);
        return new ResponseEntity<>(messageDTO, HttpStatus.BAD_REQUEST);
    }

}

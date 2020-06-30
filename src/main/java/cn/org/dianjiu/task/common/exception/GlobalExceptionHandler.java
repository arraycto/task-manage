package cn.org.dianjiu.task.common.exception;

import cn.org.dianjiu.task.common.vo.RespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler( BusinessException.class)
    public RespVO BusinessExceptionHandler(BusinessException e) {
        RespVO<Object> respVO = new RespVO<>();
        respVO.setCode(e.getCode());
        respVO.setMsg(e.getMessage());
        log.error(String.valueOf(respVO));
        return respVO;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler( Exception.class)
    public RespVO ExceptionHandler(Exception e) {
        log.error("Exception happened: ", e);
        RespVO<Object> respVO = new RespVO<>();
        respVO.setCode("500");
        respVO.setMsg("系统异常！");
        return respVO;
    }
}


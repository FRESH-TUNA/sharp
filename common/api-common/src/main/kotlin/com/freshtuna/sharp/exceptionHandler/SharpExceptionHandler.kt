package com.freshtuna.sharp.exceptionHandler

import com.freshtuna.sharp.api.response.DataResponse
import com.freshtuna.sharp.api.response.MessageResponse
import com.freshtuna.sharp.oh.exception.SharpException
import com.freshtuna.sharp.oh.exception.SharpMsgMapException

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.*


@ControllerAdvice
class SharpExceptionHandler {

    @ExceptionHandler(SharpException::class)
    protected fun handleSharpException(e: SharpException): ResponseEntity<*>
        = ResponseEntity.badRequest().body(MessageResponse(e.oh.code, e.oh.explanation))

    @ExceptionHandler(SharpMsgMapException::class)
    protected fun handleSharpMsgMapException(e: SharpMsgMapException): ResponseEntity<*>
        = ResponseEntity.badRequest().body(DataResponse(e.oh.code, e.map))
}

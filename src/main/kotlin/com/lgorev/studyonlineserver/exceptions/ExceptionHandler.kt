package com.lgorev.studyonlineserver.exceptions

import com.lgorev.studyonlineserver.domain.SuccessResultModel
import org.hibernate.exception.ConstraintViolationException
import org.hibernate.exception.GenericJDBCException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.validation.ValidationUtils
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.sql.SQLException

@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {
    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(DataIntegrityViolationException::class)
    @ResponseBody
    fun handleDataIntegrityViolationException(e: DataIntegrityViolationException, request: WebRequest): SuccessResultModel {
        return SuccessResultModel((e.cause as ConstraintViolationException).sqlState.toLong(), "")
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(GenericJDBCException::class)
    @ResponseBody
    fun handleGenericJDBCException(e: GenericJDBCException, request: WebRequest): SuccessResultModel {
        return SuccessResultModel(1, e.sqlException.localizedMessage)
    }
}
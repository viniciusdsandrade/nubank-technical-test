package com.restful.nubank.technical.test.exception

import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(CREATED)
class BusinessRuleException(message: String) : RuntimeException(message)
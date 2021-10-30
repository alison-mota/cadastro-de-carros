package br.com.zup.cadastroDeCarros.compartilhado.handlers

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ErrosDto(
    val field: String?,
    val message: String?
)
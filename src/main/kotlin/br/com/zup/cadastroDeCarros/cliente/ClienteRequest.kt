package br.com.zup.cadastroDeCarros.cliente

import br.com.zup.cadastroDeCarros.compartilhado.validadores.CampoUnico
import org.hibernate.validator.constraints.br.CPF
import java.time.LocalDate
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class ClienteRequest(
    @field:NotBlank val nome: String,
    @field:NotBlank @field:Email @field:CampoUnico(fieldName = "email", entityClass = Cliente::class) val email: String,
    @field:NotBlank @field:CPF @field:CampoUnico(fieldName = "cpf", entityClass = Cliente::class) val cpf: String,
    @field:NotNull val dataNascimento: LocalDate
) {
    fun toModel(): Cliente {
        return Cliente(nome, email, cpf, dataNascimento)
    }
}
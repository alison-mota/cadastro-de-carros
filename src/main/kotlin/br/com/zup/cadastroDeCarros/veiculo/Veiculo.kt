package br.com.zup.cadastroDeCarros.veiculo

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
class Veiculo(
    @field:NotBlank val marca: String,
    @field:NotNull val marcaId: Long,
    @field:NotBlank val modelo: String,
    @field:NotNull val modeloId: Long,
    @field:Size(min = 4, max = 4) @field:NotNull val ano: Long,
    @field:NotNull val valor: BigDecimal
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true)
    val id: Long? = null

    @Column(updatable = false)
    val dataCriacao: LocalDateTime = LocalDateTime.now()
}
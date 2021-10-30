package br.com.zup.cadastroDeCarros.cliente

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/cliente")
class ClienteController(val clienteRepository: ClienteRepository) {

    @PostMapping
    fun novo(
        @Valid @RequestBody clienteRequest: ClienteRequest,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<Any> {

        val cliente: Cliente = clienteRequest.toModel()
        clienteRepository.save(cliente)

        val location: URI = uriBuilder
            .path("/api/v1/cliente/{id}")
            .buildAndExpand(cliente.id)
            .toUri()

        return ResponseEntity.created(location).build()
    }
}
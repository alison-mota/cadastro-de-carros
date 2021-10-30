package br.com.zup.cadastroDeCarros.cliente

import org.springframework.data.jpa.repository.JpaRepository

interface ClienteRepository: JpaRepository<Cliente, Long> {

}

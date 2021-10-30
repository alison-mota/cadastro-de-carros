package br.com.zup.cadastroDeCarros.compartilhado.auxiliar

import br.com.zup.cadastroDeCarros.cliente.Cliente
import java.io.FileNotFoundException
import java.time.LocalDate

fun loadResource(nome: String): String {
    return object {}.javaClass.classLoader?.getResource(nome)?.readText() ?: throw FileNotFoundException()
}

fun instanciaClientePassandoEmailECpf(email: String, cpf: String): Cliente = Cliente("Alison", email, cpf, LocalDate.of(1989, 4, 17,))

fun instanciaCliente201Request(): String = loadResource("payload/cliente/cria_cliente_201_request.json")

fun instanciaCliente400RequestEmailExistente(): String = loadResource(
    "payload/cliente/cria_cliente_400_email_existente_request.json")

fun instanciaCliente400RequestCpfExistente(): String = loadResource(
    "payload/cliente/cria_cliente_400_cpf_existente_request.json")

fun instanciaCliente400RequestNomeEmBranco(): String = loadResource(
    "payload/cliente/cria_cliente_400_nome_em_branco_request.json")

fun instanciaCliente400RequestFaltandoNome(): String = loadResource(
    "payload/cliente/cria_cliente_400_faltando_nome_request.json")
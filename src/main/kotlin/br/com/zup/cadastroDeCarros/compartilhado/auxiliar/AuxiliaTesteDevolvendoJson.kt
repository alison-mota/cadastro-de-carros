package br.com.zup.cadastroDeCarros.compartilhado.auxiliar

fun devolveJsonDeRespostaEmailExistente(): String = loadResource("payload/retornos/dto_erro_400_email_response.json")
fun devolveJsonDeRespostaCpfExistente(): String = loadResource("payload/retornos/dto_erro_400_cpf_response.json")
fun devolveJsonDeRespostaNomeEmBranco(): String = loadResource("payload/retornos/dto_erro_400_nome_em_branco_response.json")
fun devolveJsonDeRespostaFaltaAtributo(): String = loadResource("payload/retornos/dto_erro_400_falta_atributo_response.json")
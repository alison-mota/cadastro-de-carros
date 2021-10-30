package br.com.zup.cadastroDeCarros.cliente

import br.com.zup.cadastroDeCarros.compartilhado.auxiliar.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
internal class ClienteControllerTest(
    @Autowired val mockMvc: MockMvc,
    @Autowired val clienteRepository: ClienteRepository
) {

    private val endpoint: String = "/api/v1/cliente"
    private val urlRedirecionamento201: String = "http://localhost/api/v1/cliente/1"

    @BeforeEach
    fun limpaBanco(){
        clienteRepository.deleteAll()
    }

    @Test
    fun `aa deve cadastrar um cliente quando os dados sao validos`() {
        mockMvc.perform(
            MockMvcRequestBuilders.post(endpoint)
                .accept(MediaType.APPLICATION_JSON)
                .content(instanciaCliente201Request())
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.redirectedUrl(urlRedirecionamento201))
    }

    @Test
    fun `nao deve cadastrar um cliente quando o email ja existe no banco de dados`() {
        clienteRepository.save(instanciaClientePassandoEmailECpf("emailexistente@email.com", "42544295090"))

        mockMvc.perform(
            MockMvcRequestBuilders.post(endpoint)
                .accept(MediaType.APPLICATION_JSON)
                .content(instanciaCliente400RequestEmailExistente())
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.content().json(devolveJsonDeRespostaEmailExistente()))
    }

    @Test
    fun `nao deve cadastrar um cliente quando o cpf ja existe no banco de dados`() {
        clienteRepository.save(instanciaClientePassandoEmailECpf("asdfasdfedd@email.com", "71475315074"))

        mockMvc.perform(
            MockMvcRequestBuilders.post(endpoint)
                .accept(MediaType.APPLICATION_JSON)
                .content(instanciaCliente400RequestCpfExistente())
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.content().json(devolveJsonDeRespostaCpfExistente()))
    }

    @Test
    fun `nao deve cadastrar um cliente quando eh enviado o nome em branco na requisicao`() {
        mockMvc.perform(
            MockMvcRequestBuilders.post(endpoint)
                .accept(MediaType.APPLICATION_JSON)
                .content(instanciaCliente400RequestNomeEmBranco())
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.content().json(devolveJsonDeRespostaNomeEmBranco()))
    }

    @Test
    fun `nao deve cadastrar um cliente quando nao eh enviado o nome na requisicao`() {
        mockMvc.perform(
            MockMvcRequestBuilders.post(endpoint)
                .accept(MediaType.APPLICATION_JSON)
                .content(instanciaCliente400RequestFaltandoNome())
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.content().json(devolveJsonDeRespostaFaltaAtributo()))
    }
}
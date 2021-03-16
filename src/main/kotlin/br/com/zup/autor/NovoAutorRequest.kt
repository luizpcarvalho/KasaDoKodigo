package br.com.zup.autor

import br.com.zup.config.validacao.UniqueValue
import br.com.zup.endereco.Endereco
import br.com.zup.endereco.EnderecoResponse
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class NovoAutorRequest(
    @field:NotBlank val nome: String?,
    @field:NotBlank @field:Email
    @field:UniqueValue(campo = "email", tabela = "Autor") val email: String?,
    @field:NotBlank @field:Size(max = 400) val descricao: String?,
    @field:NotBlank val cep: String?,
    @field:NotBlank val numero: String?
) {
    fun paraAutor(enderecoResponse: EnderecoResponse): Autor {
        val endereco = Endereco(enderecoResponse, numero!!, cep!!)
        return Autor(nome!!, email!!, descricao!!, endereco)
    }
}

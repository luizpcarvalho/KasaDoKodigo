package br.com.zup.endereco

import javax.persistence.Embeddable

@Embeddable
class Endereco(
    enderecoResponse: EnderecoResponse,
    val numero: String,
    val cep: String
) {
    val rua = enderecoResponse.logradouro
    val cidade = enderecoResponse.localidade
    val estado = enderecoResponse.uf
}
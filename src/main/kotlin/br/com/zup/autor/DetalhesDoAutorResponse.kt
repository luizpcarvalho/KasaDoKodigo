package br.com.zup.autor

class DetalhesDoAutorResponse(autor: Autor) {
    val nome: String = autor.nome
    val email: String = autor.email
    val descricao: String = autor.descricao
    val criadoEm: String = autor.criadoEm.toString()
}

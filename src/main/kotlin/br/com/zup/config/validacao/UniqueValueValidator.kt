package br.com.zup.config.validacao

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Singleton
open class UniqueValueValidator(@PersistenceContext val entityManager: EntityManager)
    : ConstraintValidator<UniqueValue, String> {

    @Transactional
    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<UniqueValue>,
        context: ConstraintValidatorContext
    ): Boolean {
        if(value.isNullOrBlank()) {
            return true
        }
        val campo = annotationMetadata.stringValue("campo").get()
        val tabela = annotationMetadata.stringValue("tabela").get()

        val query = entityManager
            .createQuery("SELECT 1 FROM $tabela WHERE $campo = :value")
        query.setParameter("value", value)
        val result = query.resultList

        return result.isEmpty()
    }
}

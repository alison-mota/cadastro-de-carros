package br.com.zup.cadastroDeCarros.compartilhado.validadores

import javax.persistence.EntityManager
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import kotlin.reflect.KClass

class CampoUnicoValidator (private val manager: EntityManager) : ConstraintValidator<CampoUnico, Any> {

    private var fieldName: String? = null
    private var entityClass: KClass<*>? = null

    override fun initialize(constraintAnnotation: CampoUnico?) {
        this.entityClass = constraintAnnotation?.entityClass
        this.fieldName = constraintAnnotation?.fieldName
    }

    override fun isValid(value: Any?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) return true

        val jpql = "SELECT 1 FROM ${entityClass?.simpleName} e WHERE e.$fieldName = :value"
        val entities = manager
            .createQuery(jpql, Integer::class.java)
            .setParameter("value", value)
            .resultList

        return entities.isEmpty()
    }
}
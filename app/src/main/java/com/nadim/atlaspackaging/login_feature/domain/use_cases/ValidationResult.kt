package com.nadim.atlaspackaging.login_feature.domain.use_cases
data class ValidationResult (
    val successful: Boolean = false,
    val errorType: String? = null,
    val errorMessage: String? = null,
)

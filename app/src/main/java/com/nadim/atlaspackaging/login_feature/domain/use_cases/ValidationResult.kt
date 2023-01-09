package com.nadim.atlaspackaging.login_feature.domain.use_cases

import javax.inject.Inject

data class ValidationResult @Inject constructor(
    val successful: Boolean,
    val errorMessage: String? = null,
)

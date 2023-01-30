package com.nadim.atlaspackaging.login_feature.domain.use_cases

import javax.inject.Inject

class ValidatePassword @Inject constructor() {
    fun execute (password: String) : ValidationResult {
        return if (password.isBlank()) {
            ValidationResult(
                successful = false,
                errorMessage = "Password field is blank!"
            )
        }
        else ValidationResult(true)
    }
}
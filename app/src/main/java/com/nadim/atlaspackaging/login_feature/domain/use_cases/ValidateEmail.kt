package com.nadim.atlaspackaging.login_feature.domain.use_cases

import android.util.Patterns
import javax.inject.Inject

class ValidateEmail @Inject constructor() {
    fun execute (email: String) : ValidationResult {
        return if (email.isBlank()) {
            ValidationResult(
                successful = false,
                errorMessage = "Email is blank!"
            )
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            ValidationResult(
                successful = false,
                errorMessage = "Not a valid email format!"
            )
        } else ValidationResult(true)
    }
}

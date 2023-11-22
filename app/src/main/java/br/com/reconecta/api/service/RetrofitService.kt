package br.com.reconecta.api.service

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import br.com.reconecta.api.model.CreateAccountRequest
import br.com.reconecta.api.model.UpdatePasswordRequest
import br.com.reconecta.components.EAccountType
import br.com.reconecta.core.AppConstants
import br.com.reconecta.enums.EScreenNames

object RetrofitService {

    fun handleUpdatePassword(
        applicationContext: Context,
        email: String,
        password: String,
        isLoading: MutableState<Boolean>,
        openAlertDialog: MutableState<Boolean>,
        errorMessage: MutableState<String?>
    ) {
        handleRetrofitApiCall(call = RetrofitFactory().getAuthService(
            applicationContext
        ).updatePassword(
            UpdatePasswordRequest(
                password = password, email = email
            )
        ), isLoading = isLoading, onResponse = {
            if (it.isSuccessful) openAlertDialog.value = true
            else errorMessage.value = "Email ou senha não conferem!"
        })
    }

    fun handleSignUpCall(
        context: Context,
        navController: NavHostController,
        errorMessage: MutableState<String?>,
        loading: MutableState<Boolean>,
        request: CreateAccountRequest,
        accountType: EAccountType
    ) {
        val call =
            if (accountType == EAccountType.ESTABLISHMENT) RetrofitFactory().getEstablishmentService(
                context
            ).create(request) else RetrofitFactory().getOrganizationService(context).create(request)

        handleRetrofitApiCall(
            call = call,
            isLoading = loading,
            onResponse = {
                if (it.isSuccessful) {
                    navController.navigate(EScreenNames.LOGIN.path)
                } else {
                    errorMessage.value =
                        it.message().ifEmpty { "Dados inválidos" }
                }
            }
        ) { _, _ -> errorMessage.value = AppConstants.ERROR_MESSAGE }
    }


}
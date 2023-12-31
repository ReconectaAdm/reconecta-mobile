package br.com.reconecta.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.text.NumberFormat
import java.util.Base64
import java.util.Locale


object StringUtils {

    private const val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"

    fun String.capitalizeText(): String {
        return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

    fun converStringToBrl(string: String): String {
        val nf: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        return nf.format(string.toDouble())
    }

    fun convertBase64StringToBitmap(base64Str: String): Bitmap = try {
        val byteArr: ByteArray = Base64.getDecoder().decode(base64Str)
        BitmapFactory.decodeByteArray(byteArr, 0, byteArr.size)
    } catch (ex: Exception) {
        println(ex)
        throw Exception("Invalid base64 String")
    }

    fun isValidEmail(email: String): Boolean = email.matches(emailRegex.toRegex())


    fun isValidCnpj(cnpj: String): Boolean {
        return validateCNPJLength(cnpj) && validateCNPJRepeatedNumbers(cnpj)
                && validateCNPJVerificationDigit(true, cnpj)
                && validateCNPJVerificationDigit(false, cnpj)
    }

    private fun validateCNPJLength(cnpj: String) = cnpj.length == 14

    private fun validateCNPJRepeatedNumbers(cnpj: String): Boolean {
        return (0..9)
            .map { it.toString().repeat(14) }
            .map { cnpj == it }
            .all { !it }
    }


    private fun validateCNPJVerificationDigit(firstDigit: Boolean, cnpj: String): Boolean {
        val startPos = when (firstDigit) {
            true -> 11
            else -> 12
        }
        val weightOffset = when (firstDigit) {
            true -> 0
            false -> 1
        }
        val sum = (startPos downTo 0).fold(0) { acc, pos ->
            val weight = 2 + ((11 + weightOffset - pos) % 8)
            val num = cnpj[pos].toString().toInt()
            val sum = acc + (num * weight)
            sum
        }
        val result = sum % 11
        val expectedDigit = when (result) {
            0, 1 -> 0
            else -> 11 - result
        }

        val actualDigit = cnpj[startPos + 1].toString().toInt()

        return expectedDigit == actualDigit
    }
}
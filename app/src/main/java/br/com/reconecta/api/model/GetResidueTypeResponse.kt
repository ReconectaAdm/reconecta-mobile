package br.com.reconecta.api.model

data class GetResidueTypeResponse(
    val id: Int, val name: String, val path: String
)

data class TypeResidueItem(
    val id: Int,
    val name: String,
    val qtd: Int
) : Comparable<TypeResidueItem> {

    override fun compareTo(other: TypeResidueItem): Int {
        return qtd.compareTo(other.qtd)
    }
}
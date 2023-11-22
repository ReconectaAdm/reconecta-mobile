package br.com.reconecta.api.model.residue

data class CreateResidueRequest (
   val name : String,
   val unitMeasure : Int,
   val type : Int,
   val amountPaid : Double
)
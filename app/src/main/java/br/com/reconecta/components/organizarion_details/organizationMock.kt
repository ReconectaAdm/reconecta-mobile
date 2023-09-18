package br.com.reconecta.components.organizarion_details

data class Organization(
    val name: String,
    val address: String,
    val description: String,
    val items: List<Residuo>,
    val reviews: List<String>,
    val openTime: String,
    val stars: Float
)

data class Residuo(val name: String, val price: Double, val pointsInfo: String)


val orgMock = Organization(
    name = "PlasRecicla",
    address = "Rua Bela Vista, 1195 Bela Vista",
    description = "A PlastRecicla é uma empresa privada de reciclagem comprometida com a sustentabilidade ambiental e a orização dos resíduos plásticos.",
    items = listOf(
        Residuo(
            "GarrafaPet", 0.30, "0 a 10 unidades: 5 pontos.\n" +
                    "10 a 49 unidades: 20 pontos.\n" +
                    "50 ou mais unidades: 100 pontos."
        ),
        Residuo("Aluminio", 0.50, "description"),
        Residuo("Vidro", 0.60, "DUAHSDASHDU")
    ),
    reviews = listOf("Comentario 1", "Comentario 2"),
    openTime = "Segunda a sexta das 09:00h ás 19:00h",
    stars = 5f
)


package br.com.reconecta.components.scheduling

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.reconecta.R
import br.com.reconecta.components.CreateOrganizationItem2

@Composable
fun SchedulingInfo() {
    Column {
        Text(text = "Empresa", fontWeight = FontWeight.Medium)
        CreateOrganizationItem2(
            painter = painterResource(id = R.drawable.logo_plasrecicla),
            contentDescription = "Descrição da organização 2",
            nome = "PlasRecicla",
            coletaId = 125
        )

        Text(text = "Informações", fontWeight = FontWeight.Medium)
        Column(modifier = Modifier.padding(start = 15.dp)) {
            Text(text = "Resíduo: Garrafa PET")
            Text(text = "Quantidade: 50 unidades")
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "Valor", fontWeight = FontWeight.Medium)
        Column(modifier = Modifier.padding(start = 15.dp)) {
            Text(text = "Valor: R$ 15,00")
        }

        Spacer(modifier = Modifier.height(15.dp))
    }

}

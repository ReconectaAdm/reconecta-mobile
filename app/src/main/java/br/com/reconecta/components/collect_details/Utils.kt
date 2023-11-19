package br.com.reconecta.components.collect_details

import br.com.reconecta.api.model.enums.CollectStatus

fun mapCollecStatus(status: CollectStatus): String {
    return when (status) {
        CollectStatus.IN_PROGRESS -> "Em andamento"
        CollectStatus.PENDING -> "Pendente"
        CollectStatus.CONCLUDED -> "Concluída"
        CollectStatus.SCHEDULED -> "Agendada"
        CollectStatus.CANCELLED -> "Cancelada"
    }
}

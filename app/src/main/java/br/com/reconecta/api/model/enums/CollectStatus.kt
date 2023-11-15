package br.com.reconecta.api.model.enums

enum class CollectStatus(val value: Int) {
    PENDING(1),
    IN_PROGRESS(2),
    CONCLUDED(3),
    CANCELLED(4),
    SCHEDULED(5),
}
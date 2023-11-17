package br.com.reconecta.api.model.enums

import com.google.gson.annotations.SerializedName

enum class CollectStatus(val value: Int) {
    @SerializedName("1")
    PENDING(1),
    @SerializedName("2")
    IN_PROGRESS(2),
    @SerializedName("3")
    CONCLUDED(3),
    @SerializedName("4")
    CANCELLED(4),
    @SerializedName("5")
    SCHEDULED(5),
}
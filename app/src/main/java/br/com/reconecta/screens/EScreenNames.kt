package br.com.reconecta.screens

enum class EScreenNames(val path: String) {
    LOGIN("login"),
    REGISTER("register"),
    HOME("home"),
    SCHEDULING("scheduling"),
    ORGANIZATION_DETAILS("organizationetails"),
    HOME_ESTABLISHMENT("homeEstablishment"),
    ORGANIZATION_LIST("organizationList"),
    RESET_PASSWORD("resetPassword"),
    ORGANIZATION_COLLECT_DETAILS("organizationCollectDetails"),
    ORGANIZATION_COLLECT_IN_PROGRESS("organizationCollectInProgress"),
    ESTABLISHMENT_COLLECT_DETAILS("establishmentCollectDetails"),
    AVAILABILITY("availability"),
    ACCOUNT_INFO("accountInfo"),
    ACCOUNT_INFO_EDIT_PERFIL("accountInfoEditPerfil"),
    ACCOUNT_INFO_EDIT_PASSWORD("accountInfoResetPassword"),
    ACCOUNT_INFO_EDIT_WALLET("accountInfoEditWallet"),
    ACCOUNT_INFO_EDIT_AVAILABILITY("accountInfoEditAvailability"),
    ACCOUNT_INFO_EDIT_RESIDUES("accountInfoEditResidues"),
}
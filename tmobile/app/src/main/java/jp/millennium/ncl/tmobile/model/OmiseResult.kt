package jp.millennium.ncl.tmobile.model

sealed class OmiseResult {
    object Ok: OmiseResult()
    data class Ng(val message: String): OmiseResult()
}
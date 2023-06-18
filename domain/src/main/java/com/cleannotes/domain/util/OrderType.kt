package com.cleannotes.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}

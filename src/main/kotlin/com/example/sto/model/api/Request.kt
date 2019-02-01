package com.example.sto.model.api

import com.example.sto.model.storage.RequestStatus
import com.example.sto.model.storage.RequestType

data class Request(
    val id: Long,
    var type: RequestType,
    var text: String,
    var status: RequestStatus,
    val managerId: Long? = null,
    val clientId: Long,
    var masterId: Long? = null
)
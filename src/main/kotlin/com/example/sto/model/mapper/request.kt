package com.example.sto.model.mapper

import com.example.sto.model.storage.Request
import com.example.sto.repository.ClientRepository
import com.example.sto.repository.ManagerRepository
import com.example.sto.repository.MasterRepository
import com.example.sto.model.api.Request as ApiRequest

fun Request.toApi() = ApiRequest(
    id = id,
    status = status,
    text = text,
    type = type,
    managerId = manager?.id,
    clientId = client.id,
    masterId = master?.id
)

fun ApiRequest.toDomain(masters: MasterRepository, clients: ClientRepository, managers: ManagerRepository) = Request(
    id = id,
    status = status,
    text = text,
    type = type,
    manager = managerId?.let { managers.findById(it).get() },
    client = clientId.let { clients.findById(it).get() },
    master = masterId?.let { masters.findById(it).get() }
)
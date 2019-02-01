package com.example.sto.controller

import com.example.sto.model.mapper.toApi
import com.example.sto.model.mapper.toDomain
import com.example.sto.model.storage.Request
import com.example.sto.repository.ClientRepository
import com.example.sto.repository.ManagerRepository
import com.example.sto.repository.MasterRepository
import com.example.sto.repository.RequestRepository
import com.example.sto.util.extension.ElementAlreadyExists
import com.example.sto.util.extension.ElementNotFound
import org.springframework.web.bind.annotation.*
import com.example.sto.model.api.Request as ApiRequest

@RestController
@RequestMapping("/api/requests")
class RequestRestController(
    private val requests: RequestRepository,
    private val masters: MasterRepository,
    private val clients: ClientRepository,
    private val managers: ManagerRepository
) {

    @GetMapping("/all")
    fun all() = requests.findAll().map { it.toApi() }

    @PostMapping("/create")
    fun create(@RequestBody request: ApiRequest): Any = when {
        requests.existsById(request.id) -> ElementAlreadyExists()
        else -> requests.save(request.toDomain(masters, clients, managers)).toApi()
    }

    @GetMapping("/{id}")
    fun get(@PathVariable(value = "id") id: Long): Any = try {
        requests.findById(id).get().toApi()
    } catch (e: Exception) {
        ElementNotFound()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable(value = "id") id: Long) = try {
        requests.deleteById(id)
    } catch (e: Exception) {
        ElementNotFound()
    }

    @PostMapping("/change")
    fun update(@RequestBody request: ApiRequest): Any = when {
        requests.existsById(request.id) -> {
            val entity = requests.findById(request.id).get()
            entity.master = request.masterId?.let { masters.findById(it).get() }
            entity.status = request.status
            entity.text = request.text
            entity.type = request.type
            requests.save(entity)
        }
        else -> ElementNotFound()
    }
}
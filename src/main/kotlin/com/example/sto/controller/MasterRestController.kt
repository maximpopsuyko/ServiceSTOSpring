package com.example.sto.controller

import com.example.sto.model.api.LoginAndPassword
import com.example.sto.model.storage.Master
import com.example.sto.repository.MasterRepository
import com.example.sto.util.extension.ElementAlreadyExists
import com.example.sto.util.extension.ElementNotFound
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/masters")
class MasterRestController(private val masters: MasterRepository) {

    @GetMapping("/all")
    fun all() = masters.findAll()

    @PostMapping("/create")
    fun create(@RequestBody master: Master): Any = when {
        masters.findAll().any { it.login == master.login } -> ElementAlreadyExists()
        else -> masters.save(master)
    }

    @PostMapping("/sign")
    fun signIn(@RequestBody loginAndPassword: LoginAndPassword): Any {
        val (login, password) = loginAndPassword
        return masters.findAll().find { it.login == login && it.password == password } ?: ElementNotFound()
    }

    @GetMapping("/{id}")
    fun get(@PathVariable(value = "id") id: Long): Any = try {
        masters.findById(id).get()
    } catch (e: Exception) {
        ElementNotFound()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable(value = "id") id: Long) = try {
        masters.deleteById(id)
    } catch (e: Exception) {
        ElementNotFound()
    }
}
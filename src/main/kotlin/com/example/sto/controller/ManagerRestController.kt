package com.example.sto.controller

import com.example.sto.model.api.LoginAndPassword
import com.example.sto.model.storage.Manager
import com.example.sto.repository.ManagerRepository
import com.example.sto.util.extension.ElementAlreadyExists
import com.example.sto.util.extension.ElementNotFound
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/managers")
class ManagerRestController(private val managers: ManagerRepository) {

    @GetMapping("/all")
    fun all() = managers.findAll()

    @PostMapping("/create")
    fun create(@RequestBody manager: Manager): Any = when {
        managers.findAll().any { it.login == manager.login } -> ElementAlreadyExists()
        else -> managers.save(manager)
    }

    @PostMapping("/sign")
    fun signIn(@RequestBody loginAndPassword: LoginAndPassword): Any {
        val (login, password) = loginAndPassword
        return managers.findAll().find { it.login == login && it.password == password } ?: ElementNotFound()
    }

    @GetMapping("/{id}")
    fun get(@PathVariable(value = "id") id: Long): Any = try {
        managers.findById(id).get()
    } catch (e: Exception) {
        ElementNotFound()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable(value = "id") id: Long) = try {
        managers.deleteById(id)
    } catch (e: Exception) {
        ElementNotFound()
    }
}
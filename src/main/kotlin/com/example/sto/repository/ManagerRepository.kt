package com.example.sto.repository

import com.example.sto.model.storage.Manager
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ManagerRepository : CrudRepository<Manager, Long>
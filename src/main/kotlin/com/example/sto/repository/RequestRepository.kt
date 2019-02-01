package com.example.sto.repository

import com.example.sto.model.storage.Request
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RequestRepository : CrudRepository<Request, Long>
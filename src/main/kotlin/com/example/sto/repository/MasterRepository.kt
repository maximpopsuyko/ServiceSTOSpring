package com.example.sto.repository

import com.example.sto.model.storage.Master
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MasterRepository : CrudRepository<Master, Long>
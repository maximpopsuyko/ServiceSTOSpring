package com.example.sto.model.storage

import javax.persistence.*
import com.example.sto.model.storage.Entity as DbEntity

@Entity
data class Request(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Long = 0,
    var type: RequestType,
    var text: String,
    var status: RequestStatus = RequestStatus.WAIT_CONFIRM,

    @ManyToOne @JoinColumn(name = "manager_id")
    val manager: Manager? = null,

    @ManyToOne @JoinColumn(name = "master_id")
    var master: Master? = null,

    @ManyToOne @JoinColumn(name = "client_id")
    var client: Client
) : DbEntity


enum class RequestType {
    MAINTENANCE, GUARANTEE, REPAIRS
}


enum class RequestStatus {
    WAIT_CONFIRM, CONFIRMED, WAIT_REPAIRS, IN_REPAIRS, COMPLETE, CANCELED
}
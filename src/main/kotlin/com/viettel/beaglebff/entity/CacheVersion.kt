package com.viettel.beaglebff.entity

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "CACHE_VERSION")
data class CacheVersion(
    @Id
    @Column(name = "ID")
    val id: Int = 0,

    @Column(name = "NAME")
    val name: String = "",

    @Column(name = "VERSION")
    val version: String = "",

    @Column(name = "API")
    val api: String = ""
) : Serializable
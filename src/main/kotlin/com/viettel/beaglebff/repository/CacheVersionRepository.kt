package com.viettel.beaglebff.repository

import com.viettel.beaglebff.entity.CacheVersion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CacheVersionRepository: JpaRepository<CacheVersion, Int> {
    fun getCacheVersionById(id: Int): CacheVersion
    fun getCacheVersionByName(name: String): CacheVersion
}
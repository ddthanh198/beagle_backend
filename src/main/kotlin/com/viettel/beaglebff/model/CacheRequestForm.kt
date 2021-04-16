package com.viettel.beaglebff.model

import com.viettel.beaglebff.model.version.VersionModel

data class CacheRequestForm(
    val components: List<VersionModel>
)

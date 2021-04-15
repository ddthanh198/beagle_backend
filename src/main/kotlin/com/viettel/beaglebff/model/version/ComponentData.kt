package com.viettel.beaglebff.model.version

import org.json.simple.JSONObject

data class ComponentData(
    val name: String,
    val version: String,
    val json: JSONObject?
)
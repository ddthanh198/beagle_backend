package com.viettel.bealglebff.model

data class UserInfo(
        val username: String,
        val gender: String,
        val age: Int,
        val placeOfBirth: String,
        val avatarUrl: String? = null
)

fun generateUserInfoList() = listOf(
        UserInfo("PhongLD2", "Male", 31, "Hải Phòng"),
        UserInfo("HoangVV11", "Male", 29, "Hải Phòng"),
        UserInfo("BinhPD2", "Male", 29, "Bắc Ninh"),
        UserInfo("HoangNV65", "Male", 23, "Bắc Ninh"),
        UserInfo("ThanhDD21", "Male", 23, "Bắc Ninh"),
        UserInfo("MinhPV21", "Male", 23, "Bắc Ninh"),
        UserInfo("DuongCN", "Male", 23, "Bắc Ninh")
)
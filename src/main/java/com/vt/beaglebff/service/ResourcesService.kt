package com.vt.beaglebff.service

import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.InputStreamResource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ResourcesService {

    fun getImage(imagePath: String, type: String): ResponseEntity<InputStreamResource> {
        val imgFile = ClassPathResource(imagePath)
        if (type == "PNG") {
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(InputStreamResource(imgFile.inputStream))
        }
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(InputStreamResource(imgFile.inputStream))
    }
}
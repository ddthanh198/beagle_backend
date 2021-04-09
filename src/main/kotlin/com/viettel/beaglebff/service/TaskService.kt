package com.viettel.beaglebff.service

import com.viettel.beaglebff.builder.TaskBuilder
import org.springframework.stereotype.Service

@Service
class TaskService {
     fun getTaskScreen() = TaskBuilder()
}






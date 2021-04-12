package com.viettel.beaglebff.model

data class TaskResponse(var data: Array<TaskModel>? = null,
                        var meta: MetaData? = null,
                        var countReport: ReportCounting? = null
)

data class TaskModel(var id: Int? = null,
                     var user_id: Int? = null,
                     var group_assign_id: Int? = null,
                     var process_id: Int? = null,
                     var process_request_id: Int? = null,
                     var element_id: String? = null,
                     var element_type: String? = null,
                     var element_name: String? = null,
                     var status: String? = null,
                     var element_index: Int? = null,
                     var due_at: String? = null,
                     var due_notified: Int? = null,
                     var riskchanges_at: String? = null,
                     var created_at: String? = null,
                     var updated_at: String? = null,
                     var is_first_task: Int? = null,
                     var step: Int? = null,
                     var assignment_type: String? = null,
                     var advanceStatus: String? = null,
                     var process_request: ProcessRequest? = null,
                     var statusName: String? = null,
                     var statusIcon: String? = null) {

}

data class ProcessRequest( var status: String? = null,
                           var data: ProcessRequestData? = null,
                           var name: String? = null)

data class  ProcessRequestData (
    var link: String? = null,
    var address: GovAddress? = null,
    var thumbnail: String? = null,
    var name: String? = null,
    var content: String? = null,
    var created_at: String? = null,
    var media_file: Array<MediaFile>? = null
)

data class GovAddress(var location: String? = null)

data class MediaFile (  var host: String? = null,
                        var size: Int? = null,
                        var fileType: String? = null,
                        var thumbnail: String? = null,
                        var relLocation: String? = null,
                        var originalName: String? = null,
                        var fileExtension: String? = null)
data class MetaData(var filter: String? = null,
                    var sort_by: String? = null,
                    var sort_order: String? = null,
                    var count: Int? = null,
                    var total_pages: Int? = null,
                    var current_page: Int? = null,
                    var from: Int? = null,
                    var last_page: Int? = null,
                    var path: String? = null,
                    var per_page: String? = null,
                    var to: Int? = null,
                    var total: Int? = null)


data class ReportCounting( var in_progress: Int? = null,
                           var in_overdue: Int? = null,
                           var in_completed: Int? = null,
                           var total: Int? = null,
                           var in_today_overdue: Int? = null)



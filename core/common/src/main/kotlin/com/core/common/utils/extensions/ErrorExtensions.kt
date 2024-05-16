package com.core.common.utils.extensions

import com.core.common.model.BasicResponseDTO
import com.google.gson.Gson
import okhttp3.ResponseBody

fun ResponseBody.getErrorMessage(): String {
    return Gson().fromJson(this.string(), BasicResponseDTO::class.java).message
}
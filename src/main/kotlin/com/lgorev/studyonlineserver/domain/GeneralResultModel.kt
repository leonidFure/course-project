package com.lgorev.studyonlineserver.domain

import com.lgorev.studyonlineserver.domain.user.UserModel

data class GeneralResultModel(
        var userModel: UserModel? = null,
        var code: Long = 201,
        var message: String = "OK"
)
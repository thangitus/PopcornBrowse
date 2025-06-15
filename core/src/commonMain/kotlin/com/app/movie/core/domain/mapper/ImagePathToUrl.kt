package com.app.movie.core.domain.mapper

import com.app.movie.core.utils.IMAGE_BASE_URL


fun String?.pathToUrl() = "${IMAGE_BASE_URL}$this"
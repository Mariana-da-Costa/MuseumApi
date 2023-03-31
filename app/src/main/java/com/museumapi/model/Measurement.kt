package com.museumapi.model

data class Measurement(
    val elementDescription: Any,
    val elementMeasurements: ElementMeasurements,
    val elementName: String
)
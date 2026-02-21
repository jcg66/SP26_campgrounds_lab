package com.codepath.campgrounds

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// COMPLETED: Create a data class for the Data Response
@Keep
@Serializable
data class CampgroundResponse(
    @SerialName("data")
    val data: List<Campground>?
)

// COMPLETED: Implement remaining keys of the Campground data class
@Keep
@Serializable
data class Campground(
    @SerialName("name")
    val name: String?,
    @SerialName("description")
    val description: String?,
//    @SerialName("latLong")
//    val latLong: String?,
    @SerialName("latitude")
    val rawLatitude: String? = "",
    @SerialName("longitude")
    val rawLongitude: String? = "",
    @SerialName("images")
    val images: List<CampgroundImage>?,
) : java.io.Serializable {
    // Convenience property to easily get the first image URL if it exists
    val imageUrl: String
        get() = images?.firstOrNull { !it.url.isNullOrEmpty() }?.url ?: ""

    val latitude: Float?
        get() = if (rawLatitude.isNullOrBlank()) null else rawLatitude.toFloatOrNull()
    val longitude: Float?
        get() = if (rawLongitude.isNullOrBlank()) null else rawLongitude.toFloatOrNull()
    val latLong: String
        get() {
            if (latitude == null || longitude == null) {
                return "(no location available)"
            } else {
                // round to 4 decimal places for simplicity
                // ref: https://www.reddit.com/r/LifeProTips/comments/5sf664/lpt_gps_coordinates_to_five_decimal_places_has_a/
                return "%.4f°N %.4f°W".format(latitude,longitude)
            }
        }

}


// COMPLETED: Create a data class for the Image Response
@Keep
@Serializable
data class CampgroundImage(
    @SerialName("url")
    val url: String?,
    @SerialName("title")
    val title: String?
) : java.io.Serializable
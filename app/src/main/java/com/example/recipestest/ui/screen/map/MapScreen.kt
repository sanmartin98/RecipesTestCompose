package com.example.recipestest.ui.screen.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.domain.model.LocationRecipe
import com.example.recipestest.ui.theme.RecipesTestTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(locationRecipe: LocationRecipe) {
    RecipesTestTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MyGoogleMaps(locationRecipe)
        }
    }
}

@Composable
fun MyGoogleMaps(locationRecipe: LocationRecipe) {
    val properties by remember { mutableStateOf(MapProperties()) }
    val uiSettings by remember { mutableStateOf(MapUiSettings()) }
    val marker = LatLng(locationRecipe.lat.toDouble(), locationRecipe.lng.toDouble())
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(marker, 10f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        properties = properties,
        uiSettings = uiSettings,
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = marker),
            title = locationRecipe.locationName,
            snippet = "Marker in ${locationRecipe.locationName}"
        )
    }
}
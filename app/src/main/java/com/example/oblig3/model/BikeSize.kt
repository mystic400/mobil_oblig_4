package com.example.oblig3.model

/**
 *  Verdien til **label** varierer med kategori:
 *  Mountain bikes: S, M, L, XL, XXL
 *  Hybrid bikes: S, M, L, XL, XXL
 *  Gravel bikes: XS/S, S/M, M/L, L/XL, XL/XXL
 *  Road bikes: XXS, XS, S, S/M, M, M/L, L, XL, XXL
 *  Kids bikes: Ikke relevant, størrelsen er basert hjulstørrelse (wheelSize).
 *


 * Bruker data class så det blir enklere å sammenligne størrelser i state og UI.
 */
data class BikeSize(
    val heightFrom: Int = 0,        // cm
    val heightTo: Int = 0,          // cm
    val frameSizeFrom: Float = 0f,  // cm
    val frameSizeTo: Float = 0f,    // cm
    val label: String ="--",        // F.eks. (se over): S, M, L, XL, XXL
    val wheelSize: Int=0,           // Barnesykler: i tommer
    val ageFrom: Int=0,             // Barnesykler: år
    val ageTo: Int=0,               // Barnesykler: år
)
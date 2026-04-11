package com.example.oblig3.data

import com.example.oblig3.R
import com.example.oblig3.model.Bike
import com.example.oblig3.model.BikeCategory
import com.example.oblig3.model.BikeSize
import com.example.oblig3.model.Brand

/*
 * Typeinndeling og størrelser er basert på data fra:
 *   https://bike-size.com/guides/bike-size-chart-by-height
 * Beskrivelsene av de ulike kategoriene er laget vha. AI.
 * Hybrid bike (hybridsykkel):
 *   Hybrid bikes are designed for comfort and versatility, with a more upright riding position and
 *   wider tires than road bikes. They often have flat handlebars and a more relaxed geometry
 *   than road bikes, making them suitable for a variety of terrains and riding styles.
 * Gravel bike (gravelsykkel):
 *   Gravel bikes are designed for off-road riding on unpaved roads and trails, with
 *   wider tires and a more relaxed geometry than road bikes. They often have drop handlebars and a
 *   more aggressive riding position than hybrid bikes, making them suitable for long-distance rides
 *   and rough terrain.
 * Road bike (landeveissykkel):
 *   The best road bikes are designed for speed and efficiency on paved roads (landeveissykkel).
 *   These bikes typically have lightweight frames, narrow tires, and drop handlebars that allow for
 *   an aerodynamic riding position. They are ideal for long-distance rides and racing.
 * Mountain bike (terrengsykkel):
 *   The best mountain bikes are designed for rough terrain and durability (terrengsykkel).
 * Kids bike (barnesykkel):
 *   The best kids bikes are designed for safety and ease of use (barnesykkel).
 *
 * BikeSizes-listene er basert på tabellene fra:
 *   https://bike-size.com/guides/bike-size-chart-by-height
 * Merk: Listene er ikke komplettte. Det finnes flere størrelser og varianter enn det som er inkludert
 * her. Innholdet i Bikes-listene er AI-generert og ikke nødvendigvis realistiske eller korrekte,
 * men det fungerer fint som testdata.
 *
 * Legg merke til at Bikes og BikeSizes er uavhengig av hverandre. Vi antar derfor at utleier
 * har alle størrelser av de ulike sykkeltypene.
 */
class DataSource {
    val brands = listOf<Brand>(
        Brand(
            name = "Trek",
            country = "USA"
        ),
        Brand(
            name = "Specialized",
            country = "USA"
        ),
        Brand(
            name = "Giant",
            country = "Taiwan"
        ),
        Brand(
            name = "Cannondale",
            country = "USA"
        ),
        Brand(
            name = "Scott",
            country = "Switzerland"
        ),
        Brand(
            name = "Santa Cruz",
            country = "USA"
        ),
        Brand(
            name = "Bianchi",
            country = "Italy"
        ),
        Brand(
            name = "Rock Machine",
            country = "USA"
        ),
    )
    // BIKESIZES:
    val roadBikeSizes: List<BikeSize> = listOf(
        BikeSize(
            heightFrom = 147,
            heightTo = 155,
            frameSizeFrom = 47f,
            frameSizeTo = 49f,
            label = "XXS"
        ),
        BikeSize(
            heightFrom = 155,
            heightTo = 160,
            frameSizeFrom = 49f,
            frameSizeTo = 51f,
            label = "XS"
        ),
        BikeSize(
            heightFrom = 160,
            heightTo = 165,
            frameSizeFrom = 51f,
            frameSizeTo = 53f,
            label = "S"
        ),
        BikeSize(
            heightFrom = 165,
            heightTo = 170,
            frameSizeFrom = 53f,
            frameSizeTo = 54f,
            label = "S/M"
        ),
        BikeSize(
            heightFrom = 170,
            heightTo = 175,
            frameSizeFrom = 54f,
            frameSizeTo = 55f,
            label = "M"
        ),
        BikeSize(
            heightFrom = 175,
            heightTo = 180,
            frameSizeFrom = 55f,
            frameSizeTo = 57f,
            label = "M/L"
        ),
        BikeSize(
            heightFrom = 180,
            heightTo = 185,
            frameSizeFrom = 57f,
            frameSizeTo = 58f,
            label = "L"
        ),
        BikeSize(
            heightFrom = 185,
            heightTo = 190,
            frameSizeFrom = 58f,
            frameSizeTo = 60f,
            label = "XL"
        ),
        BikeSize(
            heightFrom = 190,
            heightTo = 196,
            frameSizeFrom = 60f,
            frameSizeTo = 62f,
            label = "XXL"
        )
    )
    val mountainBikeSizes: List<BikeSize> = listOf(
        BikeSize(
            heightFrom = 147,
            heightTo = 157,
            frameSizeFrom = 33f,    //13 tommer
            frameSizeTo = 35.5f,    //14 tommer
            label = "XS"
        ),
        BikeSize(
            heightFrom = 157,
            heightTo = 168,
            frameSizeFrom = 38f,    //15 tommer
            frameSizeTo = 40.6f,    //16 tommer
            label = "S"
        ),
        BikeSize(
            heightFrom = 168,
            heightTo = 178,
            frameSizeFrom = 43f,    //17 tommer
            frameSizeTo = 45.7f,    //18 tommer
            label = "M"
        ),
        BikeSize(
            heightFrom = 178,
            heightTo = 185,
            frameSizeFrom = 48.2f,    //19 tommer
            frameSizeTo = 50.8f,    //20 tommer
            label = "L"
        )
    )
    val hybridBikeSizes: List<BikeSize> = listOf(
        BikeSize(
            heightFrom = 150,
            heightTo = 160,
            frameSizeFrom = 35.5f,   //14 tommer
            frameSizeTo = 38f,       //15 tommer
            label = "S"
        ),
        BikeSize(
            heightFrom = 160,
            heightTo = 170,
            frameSizeFrom = 38f,     //15 tommer
            frameSizeTo = 40.6f,     //16 tommer
            label = "S/M"
        ),
        BikeSize(
            heightFrom = 170,
            heightTo = 180,
            frameSizeFrom = 43f,     //17 tommer
            frameSizeTo = 45.7f,     //18 tommer
            label = "M"
        ),
        BikeSize(
            heightFrom = 180,
            heightTo = 188,
            frameSizeFrom = 48.2f,     //19 tommer
            frameSizeTo = 50.8f,       //20 tommer
            label = "L"
        ),
        BikeSize(
            heightFrom = 188,
            heightTo = 196,
            frameSizeFrom = 53.3f,     //21 tommer
            frameSizeTo = 55.9f,       //22 tommer
            label = "XL"
        )
        
    )
    val gravelBikeSizes: List<BikeSize> = listOf(
        BikeSize(
            heightFrom = 155,
            heightTo = 163,
            frameSizeFrom = 49f,
            frameSizeTo = 51f,
            label = "XS/S"
        ),
        BikeSize(
            heightFrom = 163,
            heightTo = 170,
            frameSizeFrom = 51f,
            frameSizeTo = 54f,
            label = "S/M"
        )
    )
    val kidsBikeSizes: List<BikeSize> = listOf(
        BikeSize(
            heightFrom = 86,
            heightTo = 100,
            wheelSize = 12,
            ageFrom = 2,
            ageTo = 3
        ),
        BikeSize(
            heightFrom = 100,
            heightTo = 115,
            wheelSize = 14,
            ageFrom = 3,
            ageTo = 5
        ),
        BikeSize(
            heightFrom = 115,
            heightTo = 130,
            wheelSize = 16,
            ageFrom = 4,
            ageTo = 6
        ),
        BikeSize(
            heightFrom = 122,
            heightTo = 135,
            wheelSize = 18,
            ageFrom = 5,
            ageTo = 7
        ),
        BikeSize(
            heightFrom = 130,
            heightTo = 145,
            wheelSize = 20,
            ageFrom = 6,
            ageTo = 9
        ),
        BikeSize(
            heightFrom = 145,
            heightTo = 160,
            wheelSize = 24,
            ageFrom = 8,
            ageTo = 12
        ),
        BikeSize(
            heightFrom = 152,
            heightTo = 165,
            wheelSize = 26,
            ageFrom = 10,
            ageTo = 13
        ),
    )
    // BIKES:
    val roadBikes = listOf<Bike>(
        Bike(
            bikeCategory = BikeCategory.Road,
            electric = false,
            brand = brands[0],
            brandModel = "Domane SL 6",
            modelYear = 2023,
            pricePerDay = 299.99,
            imageResource = R.drawable.road_bike_24
        ),
        Bike(
            bikeCategory = BikeCategory.Road,
            electric = false,
            brand = brands[1],
            brandModel = "Tarmac SL 7",
            modelYear = 2022,
            pricePerDay = 349.99,
            imageResource = R.drawable.road_bike_24
        ),
        Bike(
            bikeCategory = BikeCategory.Road,
            electric = true,
            brand = brands[2],
            brandModel = "Defy Advanced Pro 1",
            modelYear = 2023,
            pricePerDay = 279.99,
            imageResource = R.drawable.road_bike_24
        )
    )
    val mountainBikes = listOf<Bike>(
        Bike(
            bikeCategory = BikeCategory.Mountain,
            electric = false,
            brand = brands[1],
            brandModel = "Marlin 5",
            modelYear = 2023,
            pricePerDay = 49.99,
            imageResource = R.drawable.mountain_bike_24
        ),
        Bike(
            bikeCategory = BikeCategory.Mountain,
            electric = false,
            brand = brands[2],
            brandModel = "Talon 3",
            modelYear = 2022,
            pricePerDay = 59.99,
            imageResource = R.drawable.mountain_bike_24
        ),
        Bike(
            bikeCategory = BikeCategory.Mountain,
            electric = true,
            brand = brands[3],
            brandModel = "Powerfly FS 4",
            modelYear = 2023,
            pricePerDay = 89.99,
            imageResource = R.drawable.mountain_bike_24
        ),
        Bike(
            bikeCategory = BikeCategory.Mountain,
            electric = false,
            brand = brands[7],
            brandModel = "Vyöry 30 fatbike",
            modelYear = 2026,
            pricePerDay = 398.0,
            imageResource = R.drawable.gravel_bike_24
        )
    )
    val hybridBikes = listOf<Bike>(
        Bike(
            bikeCategory = BikeCategory.Hybrid,
            electric = false,
            brand = brands[4],
            brandModel = "CrossRip 2",
            modelYear = 2023,
            pricePerDay = 69.99,
            imageResource = R.drawable.hybrid_bike_24
        ),
        Bike(
            bikeCategory = BikeCategory.Hybrid,
            electric = false,
            brand = brands[5],
            brandModel = "CrossRip 3",
            modelYear = 2022,
            pricePerDay = 79.99,
            imageResource = R.drawable.hybrid_bike_24
        ),
        Bike(
            bikeCategory = BikeCategory.Hybrid,
            electric = true,
            brand = brands[6],
            brandModel = "CrossRip 4",
            modelYear = 2023,
            pricePerDay = 99.99,
            imageResource = R.drawable.hybrid_bike_24
        ),
        Bike(
            bikeCategory = BikeCategory.Hybrid,
            electric = false,
            brand = brands[7],
            brandModel = "CrossRip 5",
            modelYear = 2022,
            pricePerDay = 89.99,
            imageResource = R.drawable.hybrid_bike_24
        )
    )
    val gravelBikes = listOf<Bike>(
        Bike(
            bikeCategory = BikeCategory.Gravel,
            electric = false,
            brand = brands[6],
            brandModel = "Zerouno",
            modelYear = 2023,
            pricePerDay = 99.99,
            imageResource = R.drawable.gravel_bike_24
        ),
        Bike(
            bikeCategory = BikeCategory.Gravel,
            electric = true,
            brand = brands[0],
            brandModel = "Checkpoint SL 7",
            modelYear = 2022,
            pricePerDay = 119.99,
            imageResource = R.drawable.gravel_bike_24
        ),
        Bike(
            bikeCategory = BikeCategory.Gravel,
            electric = false,
            brand = brands[7],
            brandModel = "Lukk 40 Gloss Granite Green",
            modelYear = 2026,
            pricePerDay = 338.0,
            imageResource = R.drawable.gravel_bike_24
        )
    )
    val kidsBikes = listOf<Bike> (
        Bike(
            bikeCategory = BikeCategory.KidsBike,
            electric = false,
            brand = brands[3],
            brandModel = "Precaliber 12",
            modelYear = 2023,
            pricePerDay = 19.99,
            imageResource = R.drawable.kids_bike_24
        ),
        Bike(
            bikeCategory = BikeCategory.KidsBike,
            electric = false,
            brand = brands[4],
            brandModel = "Precaliber 14",
            modelYear = 2022,
            pricePerDay = 24.99,
            imageResource = R.drawable.kids_bike_24
        ),
        Bike(
            bikeCategory = BikeCategory.KidsBike,
            electric = false,
            brand = brands[5],
            brandModel = "Precaliber 16",
            modelYear = 2023,
            pricePerDay = 29.99,
            imageResource = R.drawable.kids_bike_24
        ),
        Bike(
            bikeCategory = BikeCategory.KidsBike,
            electric = false,
            brand = brands[6],
            brandModel = "Precaliber 18",
            modelYear = 2022,
            pricePerDay = 34.99,
            imageResource = R.drawable.kids_bike_24
        ),
        Bike(
            bikeCategory = BikeCategory.KidsBike,
            electric = true,
            brand = brands[0],
            brandModel = "Precaliber 20",
            modelYear = 2023,
            pricePerDay = 39.99,
            imageResource = R.drawable.kids_bike_24
        )
    )
}
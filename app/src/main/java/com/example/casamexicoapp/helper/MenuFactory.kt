package com.example.casamexicoapp.helper

import com.example.casamexicoapp.model.Category
import com.example.casamexicoapp.model.Product
import java.util.UUID

enum class CategoryType(
    val id: Long,
    val categoryName: String,
    val categoryOrdinal: Int
) {
    APPETIZERS(id = 10000, categoryName = "Appetizers", categoryOrdinal = 0),
    PLATOS(id = 10001, categoryName = "Platos", categoryOrdinal = 1),
    TACOS(10002, "Tacos", 2),
    DESSERT(10003, "Dessert", 3),
    DRINKS(10004, "Drinks", 4);


    fun getProduct(
        productName: String,
        description: String,
        price: Double,
        imageUrl: String
    ): Product {
        return Product(
            id = UUID.randomUUID().toString(),
            name = productName,
            description = description,
            price = price,
            imageUrl = imageUrl,
            categoryId = this.id
        )
    }
}

class MenuFactory {
    companion object {
        fun getCategories(): MutableList<Category> {
            val list = mutableListOf<Category>()
            CategoryType.values().forEach {
                list.add(Category(id = it.id, name = it.categoryName, ordinal = it.categoryOrdinal))
            }
            return list
        }

        fun getProducts(): MutableList<Product> {
            val list = mutableListOf<Product>()
            CategoryType.values().forEach {
                list.addAll(getProductsByCategory(it))
            }
            return list
        }

        private fun getProductsByCategory(type: CategoryType): MutableList<Product> {
            return when (type) {
                CategoryType.APPETIZERS -> getAppetizers(type)

                CategoryType.PLATOS -> getPlatos(type)

                CategoryType.TACOS -> getTacos(type)

                CategoryType.DESSERT -> getDesserts(type)

                CategoryType.DRINKS -> getDrinks(type)

                else -> mutableListOf()
            }
        }

        private fun getAppetizers(type: CategoryType) = mutableListOf<Product>().apply {
            add(
                type.getProduct(
                    "Queso Fundido",
                    "Melted jack cheese and salsa, served with corn tortillas",
                    6.25,
                    ""
                )
            )

            add(
                type.getProduct(
                    "CHILE CON QUESO",
                    "Queso fundido, chorizo, served with com tortillas",
                    6.75,
                    ""
                )
            )

            add(
                type.getProduct(
                    "NACHOS SUPREME",
                    "Pinto beans, cheese, guacamole, sour cream onions and jalapeños",
                    6.95,
                    ""
                )
            )

            add(
                type.getProduct(
                    "TAQUITOS",
                    "Six pork taquitos, served with queso fundido",
                    7.95,
                    ""
                )
            )

            add(
                type.getProduct(
                    "CHILE RELLENO",
                    "Roasted poblano stuffed with jack cheese in, battered and neo",
                    6.95,
                    ""
                )
            )

            add(
                type.getProduct(
                    "SOPE",
                    "Com cake topped with chicken or steak, beans, cheese and guacamole",
                    7.95,
                    ""
                )
            )

            add(
                type.getProduct(
                    "",
                    "",
                    6.25,
                    ""
                )
            )
        }

        private fun getPlatos(type: CategoryType) = mutableListOf<Product>().apply {
            add(
                type.getProduct(
                    "FAJITAS",
                    "Grilled steak or chicken with sauteed peppers and onions. served with sour cream and guacamole",
                    12.65,
                    ""
                )
            )

            add(
                type.getProduct(
                    "FAJITAS DE CAMARON",
                    "Grilled shrimp with sautéed peppers and onions, served with sour cream and guacamole.",
                    13.25,
                    ""
                )
            )

            add(
                type.getProduct(
                    "TAMALES",
                    "Homemade masa dough and pork, wrapped in a corn nusk and steamed, with your choice of roja or verde sauce.",
                    12.25,
                    ""
                )
            )

            add(
                type.getProduct(
                    "POLLO EN CHIPOTLE",
                    "Half chicken rubbed with chipotle and griled",
                    13.75,
                    ""
                )
            )

            add(
                type.getProduct(
                    "CARNE ASADA",
                    "8 oz. New York strip steak rubbed with chipotle and grilled to your liking",
                    6.25,
                    ""
                )
            )

            add(
                type.getProduct(
                    "CHILAQUILES",
                    "Casserole with layered tortilla chips, chicken or steak, roja sauce and jack cheese",
                    12.25,
                    ""
                )
            )
        }

        private fun getTacos(type: CategoryType) = mutableListOf<Product>().apply {
            add(
                type.getProduct(
                    "CHICKEN",
                    "Three tacos filled with grilled chicken",
                    9.25,
                    ""
                )
            )

            add(
                type.getProduct(
                    "SHRIMP",
                    "Three tacos filled with marinated shrimp, onion, and cilantro",
                    9.25,
                    ""
                )
            )

            add(
                type.getProduct(
                    "CARNITAS",
                    "Three tacos filled with pork shoulder with salsa verde, jalapeno, lime, and onion",
                    9.25,
                    ""
                )
            )

            add(
                type.getProduct(
                    "AL PASTOR",
                    "Three tacos filled with spicy, marinated pork",
                    6.25,
                    ""
                )
            )

            add(
                type.getProduct(
                    "CHILE VERDE",
                    "Three tacos filled with pork and a roasted green chili sauce ana tomatillos",
                    8.25,
                    ""
                )
            )

            add(
                type.getProduct(
                    "TILAPIA",
                    "Three tacos filled with fried fish and cabbage",
                    9.75,
                    ""
                )
            )
        }

        private fun getDesserts(type: CategoryType) = mutableListOf<Product>().apply {
            add(
                type.getProduct(
                    "CHURROS",
                    "Deep-fried fritter rolled in cinnamon sugar, with a scoop of vanilla ice cream",
                    5.75,
                    ""
                )
            )

            add(
                type.getProduct(
                    "DIPPING CHURROS",
                    "With dark chocolate & caramel dipping sauces",
                    6.50,
                    ""
                )
            )

            add(
                type.getProduct(
                    "FLAN",
                    "Baked vanilla custard with caramel sauce",
                    4.25,
                    ""
                )
            )

            add(
                type.getProduct(
                    "FRESH BERRY FLAUTAS",
                    "Served with cinnamon ice cream",
                    6.50,
                    ""
                )
            )

            add(
                type.getProduct(
                    "SOPAPILLA",
                    "A puffed pastry topped with honey, with a scoop of vanilla ice cream",
                    5.50,
                    ""
                )
            )
        }

        private fun getDrinks(type: CategoryType) = mutableListOf<Product>().apply {
            add(
                type.getProduct(
                    "Coca-cola",
                    "Soft Drinks",
                    1.75,
                    ""
                )
            )

            add(
                type.getProduct(
                    "Pepsi",
                    "Soft Drinks",
                    1.75,
                    ""
                )
            )


            add(
                type.getProduct(
                    "Sprite",
                    "Soft Drinks",
                    1.75,
                    ""
                )
            )

            add(
                type.getProduct(
                    "HORCHATA",
                    "Cinnamon rice milk - it's dairy free & vegan friendly!",
                    3.50,
                    ""
                )
            )
        }
    }
}
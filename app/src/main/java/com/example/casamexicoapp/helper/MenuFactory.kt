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
                    "https://mamaneedscake.com/wp-content/uploads/2023/05/Queso-Fundido-recipe-card.jpg"
                )
            )

            add(
                type.getProduct(
                    "Chile con Queso",
                    "Queso fundido, chorizo, served with com tortillas",
                    6.75,
                    "https://www.recipeworkbook.com/wp-content/uploads/2021/11/chile-con-queso-1024x683.jpg"
                )
            )

            add(
                type.getProduct(
                    "Nachos Supreme",
                    "Pinto beans, cheese, guacamole, sour cream onions and jalapeños",
                    6.95,
                    "https://www.photosandfood.ca/wp-content/uploads/2021/11/DSC09350.jpg"
                )
            )

            add(
                type.getProduct(
                    "Taquitos",
                    "Six pork taquitos, served with queso fundido",
                    7.95,
                    "https://supermancooks.com/wp-content/uploads/2021/10/chipotle-chicken-taquitos-eaten.jpg"
                )
            )

            add(
                type.getProduct(
                    "Chile Relleno",
                    "Roasted poblano stuffed with jack cheese in, battered and neo",
                    6.95,
                    "https://frugalhausfrau.com/wp-content/uploads/2012/02/Chile-Rellenos-Old-Fashioned.jpg"
                )
            )

            add(
                type.getProduct(
                    "Sope",
                    "Corn cake topped with chicken or steak, beans, cheese and guacamole",
                    7.95,
                    "https://fromfieldtoplate.com/wp-content/uploads/2021/08/DSC4926-1-scaled.jpg"
                )
            )


        }

        private fun getPlatos(type: CategoryType) = mutableListOf<Product>().apply {
            add(
                type.getProduct(
                    "Fajitas",
                    "Grilled steak or chicken with sauteed peppers and onions. served with sour cream and guacamole",
                    12.65,
                    "https://foxeslovelemons.com/wp-content/uploads/2022/04/Fajita-Bar-4.jpg"
                )
            )

            add(
                type.getProduct(
                    "Fajitas de camarón",
                    "Grilled shrimp with sautéed peppers and onions, served with sour cream and guacamole.",
                    13.25,
                    "https://keviniscooking.com/wp-content/uploads/2019/04/Grilled-Shrimp-Fajitas-square-500x500.jpg"
                )
            )

            add(
                type.getProduct(
                    "Tamales",
                    "Homemade masa dough and pork, wrapped in a corn nusk and steamed, with your choice of roja or verde sauce.",
                    12.25,
                    "https://insanelygoodrecipes.com/wp-content/uploads/2020/07/Homemade-Tamales.png"
                )
            )

            add(
                type.getProduct(
                    "Pollo con Chipotle",
                    "Half chicken rubbed with chipotle and griled",
                    13.75,
                    "https://www.spoonforkbacon.com/wp-content/uploads/2016/05/chicken_burrito_bowls.jpg"
                )
            )

            add(
                type.getProduct(
                    "Carne Asada",
                    "8 oz. New York strip steak rubbed with chipotle and grilled to your liking",
                    6.25,
                    "https://inkristaskitchen.com/wp-content/uploads/2022/05/chipotle-carne-asada-comp-5-683x1024.jpg"
                )
            )

            add(
                type.getProduct(
                    "Chilaquiles",
                    "Casserole with layered tortilla chips, chicken or steak, roja sauce and jack cheese",
                    12.25,
                    "https://static01.nyt.com/images/2015/12/30/dining/1230MARTHA/1230MARTHA-superJumbo.jpg"
                )
            )
        }

        private fun getTacos(type: CategoryType) = mutableListOf<Product>().apply {
            add(
                type.getProduct(
                    "Chicken",
                    "Three tacos filled with grilled chicken",
                    9.25,
                    "https://tastesbetterfromscratch.com/wp-content/uploads/2023/05/Grilled-Chicken-Street-Tacos-1-500x500.jpg"
                )
            )

            add(
                type.getProduct(
                    "Shrimp",
                    "Three tacos filled with marinated shrimp, onion, and cilantro",
                    9.25,
                    "https://www.onceuponachef.com/images/2012/05/Grilled-Shrimp-Tacos-1-1200x1729.jpg"
                )
            )

            add(
                type.getProduct(
                    "Carnitas",
                    "Three tacos filled with pork shoulder with salsa verde, jalapeno, lime, and onion",
                    9.25,
                    "https://iamafoodblog.b-cdn.net/wp-content/uploads/2020/05/ultimate-stovetop-carnitas-recipe-3361.jpg"
                )
            )

            add(
                type.getProduct(
                    "Al Pastor",
                    "Three tacos filled with spicy, marinated pork",
                    6.25,
                    "https://www.culinaryhill.com/wp-content/uploads/2022/12/Tacos-al-Pastor-Culinary-Hill-1200x800-1.jpg"
                )
            )

            add(
                type.getProduct(
                    "Chile Verde",
                    "Three tacos filled with pork and a roasted green chili sauce ana tomatillos",
                    8.25,
                    "https://ohsweetbasil.com/wp-content/uploads/authentic-chicken-chile-verde-recipe-10-scaled.jpg"
                )
            )

            add(
                type.getProduct(
                    "Tilapia",
                    "Three tacos filled with fried fish and cabbage",
                    9.75,
                    "https://sabrinacurrie.com/wp-content/uploads/2022/09/Z-Tilapia-Tacos-3-1200x1200.jpg"
                )
            )
        }

        private fun getDesserts(type: CategoryType) = mutableListOf<Product>().apply {
            add(
                type.getProduct(
                    "Churros",
                    "Deep-fried fritter rolled in cinnamon sugar, with a scoop of vanilla ice cream",
                    5.75,
                    "https://popmenucloud.com/cdn-cgi/image/width%3D1200%2Cheight%3D1200%2Cfit%3Dscale-down%2Cformat%3Dauto%2Cquality%3D60/unzyjsga/c6e9572b-b1bd-4e5c-a4c7-b0812fee902d.jpg"
                )
            )

            add(
                type.getProduct(
                    "Dipping Churros",
                    "With dark chocolate & caramel dipping sauces",
                    6.50,
                    "https://www.recipetineats.com/wp-content/uploads/2016/08/Churros_9.jpg"
                )
            )

            add(
                type.getProduct(
                    "Flan",
                    "Baked vanilla custard with caramel sauce",
                    4.25,
                    "https://www.bakespace.com/images/large/53e6c34eefee18dc3a578808a229e8a5.jpeg"
                )
            )

            add(
                type.getProduct(
                    "Fresh Berry Flautas",
                    "Served with cinnamon ice cream",
                    6.50,
                    "https://growingupbilingual.com/wp-content/uploads/2015/03/IMG_1172.jpg"
                )
            )

            add(
                type.getProduct(
                    "Sopilla",
                    "A puffed pastry topped with honey, with a scoop of vanilla ice cream",
                    5.50,
                    "https://tastesbetterfromscratch.com/wp-content/uploads/2020/02/Sopaipillas-6.jpg"
                )
            )
        }

        private fun getDrinks(type: CategoryType) = mutableListOf<Product>().apply {
            add(
                type.getProduct(
                    "Coca-cola",
                    "Soft Drinks",
                    1.75,
                    "https://images.pexels.com/photos/7429792/pexels-photo-7429792.jpeg?cs=srgb&dl=pexels-hadis-7429792.jpg&fm=jpg"
                )
            )

            add(
                type.getProduct(
                    "Pepsi",
                    "Soft Drinks",
                    1.75,
                    "https://t3.ftcdn.net/jpg/02/94/41/16/360_F_294411665_vDKbFZWk8gfcETkjB4fBVSpLGBRWl26X.jpg"
                )
            )


            add(
                type.getProduct(
                    "Sprite",
                    "Soft Drinks",
                    1.75,
                    "https://i.pinimg.com/736x/da/29/54/da29549b7fd367669b7d8502d9d0028c.jpg"
                )
            )

            add(
                type.getProduct(
                    "Horchata",
                    "Cinnamon rice milk - it's dairy free & vegan friendly!",
                    3.50,
                    "https://www.halfbakedharvest.com/wp-content/uploads/2020/03/Dirty-Horchata-1.jpg"
                )
            )
        }
    }
}
package com.example.desiredeal.utils;

import com.example.desiredeal.R;
import com.example.desiredeal.model.Category;
import com.example.desiredeal.model.DetailedpageModel;
import com.example.desiredeal.model.MyAccountRecyclerModel;
import com.example.desiredeal.model.ProductForuModel;
import com.example.desiredeal.model.RelatedProductModel;

import java.util.ArrayList;
import java.util.Arrays;



public class DataModel {


    public static ArrayList<Category> getAllCategories() {
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category(R.drawable.category_tshirt, "All Categories"));
        categories.add(new Category(R.drawable.category_tshirt, "Fashion"));
        categories.add(new Category(R.drawable.category_accesories, "Accessories"));
        categories.add(new Category(R.drawable.category_accesories, "Beauty"));
        categories.add(new Category(R.drawable.category_accesories, "Electronics"));
        categories.add(new Category(R.drawable.category_shoes, "Shoes"));
        return categories;
    }


    static String Description = "Care Instructions: Machine Wash\n" + "Fit Type: Regular\n" + "Kurta Color- Blue, Pant Color- White, Dupatta Color- White\n" + "Kurta Fabric- Cotton Blend, Pant Fabric- Cotton Blend, Dupatta Fabric- Cotton Blend";

    public static ArrayList<ProductForuModel> Specialcategory() {
        ArrayList<ProductForuModel> productForuModels = new ArrayList<>();
        productForuModels.add(new ProductForuModel(R.drawable.chinese, "Korean Style", new ArrayList<Integer>(Arrays.asList(R.drawable.chinese,R.drawable.chinese1,R.drawable.chinese2)),"400","Care instructions : Machine wash\n" +
                "Fit Type: Regular\n" +
                "Kurta Colour:Light Pink\n" +
                "Kurta Fabric: Cotton Blend\n" +
                "Sizes : \n" +
                "S (Bust Size : 34 in, Length Size: 47 in)\n" +
                "M (Bust Size : 36 in, Length Size: 47 in)\n" +
                "L (Bust Size : 38 in, Length Size: 47 in)\n" +
                "XL (Bust Size : 40 in, Length Size: 47 in)\n" +
                "Country of Origin : India"));

        productForuModels.add(new ProductForuModel(R.drawable.chinese, "My Style", new ArrayList<Integer>(Arrays.asList(R.drawable.chinese,R.drawable.chinese1,R.drawable.chinese2)),"400","Care instructions : Machine wash\n" +
                "Fit Type: Regular\n" +
                "Kurta Colour:Light Pink\n" +
                "Kurta Fabric: Cotton Blend\n" +
                "Sizes : \n" +
                "S (Bust Size : 34 in, Length Size: 47 in)\n" +
                "M (Bust Size : 36 in, Length Size: 47 in)\n" +
                "L (Bust Size : 38 in, Length Size: 47 in)\n" +
                "XL (Bust Size : 40 in, Length Size: 47 in)\n" +
                "Country of Origin : India"));

        productForuModels.add(new ProductForuModel(R.drawable.bollywooed, "Bollywood Style", new ArrayList<Integer>(Arrays.asList(R.drawable.bollywooed,R.drawable.bollywooed1,R.drawable.bollywooed2)),"500","Care instructions : Machine wash\n" +
                "Fit Type: Regular\n" +
                "Kurta Colour:White\n" +
                "Sleeve length: Long Sleeves\n" +
                "Kurta Fabric: Rayon\n" +
                "Sizes : \n" +
                "S (Bust Size : 34 in, Length Size: 47 in)\n" +
                "M (Bust Size : 36 in, Length Size: 47 in)\n" +
                "L (Bust Size : 38 in, Length Size: 47 in)\n" +
                "XL (Bust Size : 40 in, Length Size: 47 in)\n" +
                "Country of Origin : India"));
        productForuModels.add(new ProductForuModel(R.drawable.wtrad, "Western Trend", new ArrayList<Integer>(Arrays.asList(R.drawable.wtrad,R.drawable.wtrad1,R.drawable.wtrad2)),"400","Care instructions : Machine wash\n" +
                "Fit Type: Regular\n" +
                "Kurta Colour:Red \n" +
                "Plaza Colour: White\n" +
                "Kurta Fabric: Cotton Blend\n" +
                "Sizes : \n" +
                "S (Bust Size : 34 in, Length Size: 47 in)\n" +
                "M (Bust Size : 36 in, Length Size: 47 in)\n" +
                "L (Bust Size : 38 in, Length Size: 47 in)\n" +
                "XL (Bust Size : 40 in, Length Size: 47 in)\n" +
                "Country of Origin : India"));

        productForuModels.add(new ProductForuModel(R.drawable.traditional, "Traditional", new ArrayList<Integer>(Arrays.asList(R.drawable.traditional,R.drawable.traditional1)),"400",Description));
        return productForuModels;
    }

    public static ArrayList<ProductForuModel> featuredProductModels() {
        ArrayList<ProductForuModel> productForuModels = new ArrayList<>();
        productForuModels.add(new ProductForuModel(R.drawable.twins, "Twins Cloth", new ArrayList<Integer>(Arrays.asList(R.drawable.twins,R.drawable.twins1,R.drawable.twins2)),"400","Care instructions : Machine wash\n" +
                "Fit Type: Regular\n" +
                "Kurta Colour:White\n" +
                "Sleeve length: Long Sleeves\n" +
                "Kurta Fabric: Rayon\n" +
                "Sizes : \n" +
                "S (Bust Size : 34 in, Length Size: 47 in)\n" +
                "M (Bust Size : 36 in, Length Size: 47 in)\n" +
                "L (Bust Size : 38 in, Length Size: 47 in)\n" +
                "XL (Bust Size : 40 in, Length Size: 47 in)\n" +
                "Country of Origin : India"));
        productForuModels.add(new ProductForuModel(R.drawable.couple, "Couple Cloth", new ArrayList<Integer>(Arrays.asList(R.drawable.couple,R.drawable.couple1,R.drawable.couple2)),"400",Description));
        productForuModels.add(new ProductForuModel(R.drawable.summer, "Summer Cloth", new ArrayList<Integer>(Arrays.asList(R.drawable.summer,R.drawable.summer1,R.drawable.summer2)),"400",Description));
        productForuModels.add(new ProductForuModel(R.drawable.family1, "Family Cloth", new ArrayList<Integer>(Arrays.asList(R.drawable.family1,R.drawable.family2,R.drawable.family)),"400",Description));
        return productForuModels;
    }


    public static ArrayList<ProductForuModel> productForuUser() {
        ArrayList<ProductForuModel> productForuModels = new ArrayList<>();
        productForuModels.add(new ProductForuModel(R.drawable.foru1, "Chinese Cloth", new ArrayList<Integer>(Arrays.asList(R.drawable.foru1,R.drawable.foru1_1,R.drawable.foru1_2)),"400","Care instructions : Machine wash\n" +
                "Fit Type: Regular\n" +
                "Kurta Colour:Red \n" +
                "Plaza Colour: White\n" +
                "Kurta Fabric: Cotton Blend\n" +
                "Sizes : \n" +
                "S (Bust Size : 34 in, Length Size: 47 in)\n" +
                "M (Bust Size : 36 in, Length Size: 47 in)\n" +
                "L (Bust Size : 38 in, Length Size: 47 in)\n" +
                "XL (Bust Size : 40 in, Length Size: 47 in)\n" +
                "Country of Origin : India"));
        productForuModels.add(new ProductForuModel(R.drawable.foru2, "DarkRed Short", new ArrayList<Integer>(Arrays.asList(R.drawable.foru2,R.drawable.foru2_1,R.drawable.foru2_2)),"400","Care instructions : Machine wash\n" +
                "Fit Type: Regular\n" +
                "Kurta Colour:Dark Red\n" +
                "Kurta Fabric: Polyester \n" +
                "Sizes : \n" +
                "S (Bust Size : 34 in, Length Size: 47 in)\n" +
                "M (Bust Size : 36 in, Length Size: 47 in)\n" +
                "L (Bust Size : 38 in, Length Size: 47 in)\n" +
                "XL (Bust Size : 40 in, Length Size: 47 in)\n" +
                "Country of Origin : India"));
        productForuModels.add(new ProductForuModel(R.drawable.foru3, "Long Shirt", new ArrayList<Integer>(Arrays.asList(R.drawable.foru3,R.drawable.foru3_1)),"400","Care instructions : Machine wash\n" +
                "Fit Type: Regular\n" +
                "Kurta Colour: Light Pink\n" +
                "Kurta Fabric: Crepe\n" +
                "Sizes : \n" +
                "S (Bust Size : 34 in, Length Size: 47 in)\n" +
                "M (Bust Size : 36 in, Length Size: 47 in)\n" +
                "L (Bust Size : 38 in, Length Size: 47 in)\n" +
                "XL (Bust Size : 40 in, Length Size: 47 in)\n" +
                "Country of Origin : India"));
        productForuModels.add(new ProductForuModel(R.drawable.foru4, "BLack Blazer", new ArrayList<Integer>(Arrays.asList(R.drawable.foru4,R.drawable.foru4_1,R.drawable.foru4_2)),"400","Stylish Retro Men Blazers\n" +
                "Name: Stylish Retro Women Blazers\n" +
                "Fabric: Polycotton\n" +
                "Sleeve Length: Long Sleeves\n" +
                "Net Quantity (N): 1\n" +
                "Sizes:\n" +
                "S (Length Size: 28 in) \n" +
                "XL (Length Size: 29 in) \n" +
                "XXL (Length Size: 29 in) \n" +
                "XXXL (Length Size: 30 in) \n" +
                "4XL (Length Size: 31 in) \n" +
                "5XL (Length Size: 31 in) \n" +
                "6XL (Length Size: 32 in) \n" +
                "7XL (Length Size: 32 in) \n" +
                "\n" +
                "Please check the size correctly\n" +
                "Country of Origin: India"));
        productForuModels.add(new ProductForuModel(R.drawable.foru5, "V-Cut Top", new ArrayList<Integer>(Arrays.asList(R.drawable.foru5,R.drawable.foru5_1)),"400","Care instructions: Machine Wash\n" +
                "Care instructions: Machine Wash\n" +
                "Fabric: Cotton Blend\n" +
                "Sleeve Length: Sleeveless\n" +
                "Pattern: Solid\n" +
                "Net Quantity (N): 1\n" +
                "Sizes:\n" +
                "XS (Bust Size: 34 in, Length Size: 15 in) \n" +
                "S (Bust Size: 36 in, Length Size: 15 in) \n" +
                "M (Bust Size: 38 in, Length Size: 15 in ) \n" +
                "Country of Origin: India"));
        productForuModels.add(new ProductForuModel(R.drawable.foru6, "Family Cloth", new ArrayList<Integer>(Arrays.asList(R.drawable.foru6,R.drawable.foru6_1,R.drawable.foru6_2)),"400","Description"));
        return productForuModels;
    }

    public static ArrayList<RelatedProductModel> relatedProductModels() {
        ArrayList<RelatedProductModel> relatedcategory = new ArrayList<>();
        relatedcategory.add(new RelatedProductModel(R.drawable.related, "Yellow Gown"));
        relatedcategory.add(new RelatedProductModel(R.drawable.related1, "Multi Red Shoal"));
        relatedcategory.add(new RelatedProductModel(R.drawable.related2, "China Trend"));
        relatedcategory.add(new RelatedProductModel(R.drawable.related3, "White Onepiss"));
        relatedcategory.add(new RelatedProductModel(R.drawable.related4, "Long Jacket"));
        return relatedcategory;
    }
    public static ArrayList<ProductForuModel> getSectioncategories(String categoryName) {
        ArrayList<ProductForuModel> productForuModels = new ArrayList<>();

        if (categoryName.equals("Women")) {

            productForuModels.add(new ProductForuModel(R.drawable.wcloth1_1, "Black Onepiss", new ArrayList<Integer>(Arrays.asList(R.drawable.wcloth1_1,R.drawable.wcloth1,R.drawable.wcloth1_2)),"400","Care instructions: Machine Wash\n" +
                    "Fabric: Cotton Blend\n" +
                    "Colour : Black\n" +
                    "Sleeve Length: Sleeveless\n" +
                    "Net Quantity (N): 1\n" +
                    "Sizes:\n" +
                    "XS (Bust Size: 34 in, Length Size: 15 in) \n" +
                    "S (Bust Size: 36 in, Length Size: 15 in) \n" +
                    "M (Bust Size: 38 in, Length Size: 15 in ) \n" +
                    "Country of Origin: India"));
            productForuModels.add(new ProductForuModel(R.drawable.wcloth2, "Designer Blazer",new  ArrayList<Integer>(Arrays.asList(R.drawable.wcloth2,R.drawable.wcloth2_1,R.drawable.wcloth2)),"400","Name: Stylish Retro Women Blazers\n" +
                    "Fabric: Polycotton\n" +
                    "Colour: Orange\n" +
                    "Sleeve Length: Long Sleeves\n" +
                    "Net Quantity (N): 1\n" +
                    "Sizes:\n" +
                    "S (Length Size: 28 in) \n" +
                    "XL (Length Size: 29 in) \n" +
                    "XXL (Length Size: 29 in) \n" +
                    "XXXL (Length Size: 30 in) \n" +
                    "4XL (Length Size: 31 in) \n" +
                    "5XL (Length Size: 31 in) \n" +
                    "6XL (Length Size: 32 in) \n" +
                    "7XL (Length Size: 32 in) \n" +
                    "\n" +
                    "Please check the size correctly\n" +
                    "Country of Origin: India" ));
            productForuModels.add(new ProductForuModel(R.drawable.wcloth4, "Short Shirt", new ArrayList<Integer>(Arrays.asList(R.drawable.wcloth4,R.drawable.wcloth4_1,R.drawable.wcloth4_2)),"400","Care instructions : Machine wash\n" +
                    "Fit Type: Regular\n" +
                    "Kurta Colour: Light Blue\n" +
                    "Kurta Fabric: Crepe\n" +
                    "Sizes : \n" +
                    "S (Bust Size : 34 in, Length Size: 47 in)\n" +
                    "M (Bust Size : 36 in, Length Size: 47 in)\n" +
                    "L (Bust Size : 38 in, Length Size: 47 in)\n" +
                    "XL (Bust Size : 40 in, Length Size: 47 in)\n" +
                    "Country of Origin : India"));
            productForuModels.add(new ProductForuModel(R.drawable.wcloth6, "Black Trouser", new ArrayList<Integer>(Arrays.asList(R.drawable.wcloth6,R.drawable.wcloth6_1,R.drawable.wcloth6_2)),"400","Care instructions : Machine wash\n" +
                    "Fit Type: Regular\n" +
                    "Kurta Colour: Black\n" +
                    "Kurta Fabric: Crepe\n" +
                    "Sizes : \n" +
                    "S (Bust Size : 34 in, Length Size: 47 in)\n" +
                    "M (Bust Size : 36 in, Length Size: 47 in)\n" +
                    "L (Bust Size : 38 in, Length Size: 47 in)\n" +
                    "XL (Bust Size : 40 in, Length Size: 47 in)\n" +
                    "Country of Origin : India"));
            productForuModels.add(new ProductForuModel(R.drawable.wcloth7, "Pink Blazer", new ArrayList<Integer>(Arrays.asList(R.drawable.wcloth7,R.drawable.wcloth7_1,R.drawable.wcloth7_2)),"400","Description"));
            productForuModels.add(new ProductForuModel(R.drawable.wcloth8, "Winter Coat", new ArrayList<Integer>(Arrays.asList(R.drawable.wcloth8,R.drawable.wcloth8_1,R.drawable.wcloth8_2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.wcloth9, "White NeckCut", new ArrayList<Integer>(Arrays.asList(R.drawable.wcloth9,R.drawable.wcloth9_1,R.drawable.wcloth9_2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.wcloth10, "Designer Gown",new ArrayList<Integer>(Arrays.asList(R.drawable.wcloth10,R.drawable.wcloth10_1,R.drawable.wcloth10_2)),"400",Description));


        } else if (categoryName.equals("Men")) {
            productForuModels.add(new ProductForuModel(R.drawable.menc1, "Designer Blazer",new ArrayList<Integer>(Arrays.asList(R.drawable.menc1,R.drawable.menc1_1,R.drawable.menc1_2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.menc2, "Black Blazer",new ArrayList<Integer>(Arrays.asList(R.drawable.menc2,R.drawable.menc2_1,R.drawable.menc2_2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.menc3, "Black Denim Jacket",new ArrayList<Integer>(Arrays.asList(R.drawable.menc3,R.drawable.menc3_1,R.drawable.menc3_2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.menc4, "Blue Shirt",new ArrayList<Integer>(Arrays.asList(R.drawable.menc4,R.drawable.menc4_1,R.drawable.menc4_2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.menc5, "Grey Jacket",new ArrayList<Integer>(Arrays.asList(R.drawable.menc5,R.drawable.menc5_2,R.drawable.menc5_1)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.menc6, "Black Shirt",new ArrayList<Integer>(Arrays.asList(R.drawable.menc6,R.drawable.menc6_1,R.drawable.menc6_2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.menc7, "Black Hoodie",new ArrayList<Integer>(Arrays.asList(R.drawable.menc7,R.drawable.menc7_2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.menc8, "Blue Designer Shirt",new ArrayList<Integer>(Arrays.asList(R.drawable.menc8,R.drawable.menc8_1)),"400",Description));


        } else if (categoryName.equals("Kids")) {
            productForuModels.add(new ProductForuModel(R.drawable.kidc1, "Blue Jacket",new ArrayList<Integer>(Arrays.asList(R.drawable.kidc1,R.drawable.kidc1_1,R.drawable.kidc1_2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.kidc2_2, "Cream Jacket",new ArrayList<Integer>(Arrays.asList(R.drawable.kidc2_2,R.drawable.kidc2,R.drawable.kidc2_1)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.kidc3, "White Frock",new ArrayList<Integer>(Arrays.asList(R.drawable.kidc3,R.drawable.kidc3_1,R.drawable.kidc3_2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.kidc4, "Grey Sweatshirt",new ArrayList<Integer>(Arrays.asList(R.drawable.kidc4,R.drawable.kidc4_1,R.drawable.kidc4_2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.kidc5_1, "Blue Dungree",new ArrayList<Integer>(Arrays.asList(R.drawable.kidc5_1,R.drawable.kidc5,R.drawable.kidc5_2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.kidc6, "Pink T-shirt",new ArrayList<Integer>(Arrays.asList(R.drawable.kidc6,R.drawable.kidc6_1,R.drawable.kidc6_2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.kidc7, "Grey Winter T-shirt",new ArrayList<Integer>(Arrays.asList(R.drawable.kidc7,R.drawable.kidc7_1,R.drawable.kidc7_2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.kidc8, "Yellow T-shirt",new ArrayList<Integer>(Arrays.asList(R.drawable.kidc8,R.drawable.kidc8_1)),"400",Description));


        } else if (categoryName.equals("Electronic")) {
            productForuModels.add(new ProductForuModel(R.drawable.watche, "Smart Watch",new ArrayList<Integer>(Arrays.asList(R.drawable.watche,R.drawable.watche_1,R.drawable.watche_2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.virtualreality, "Eye Mask",new ArrayList<Integer>(Arrays.asList(R.drawable.virtualreality,R.drawable.virtual1,R.drawable.virtual2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.phonee, "Iphone",new ArrayList<Integer>(Arrays.asList(R.drawable.phonee,R.drawable.phonee_1,R.drawable.phonee)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.speakere, "Bluethooth Speaker",new ArrayList<Integer>(Arrays.asList(R.drawable.speakere,R.drawable.speaker1_1,R.drawable.speaker1_2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.headphonee, "Headphone",new ArrayList<Integer>(Arrays.asList(R.drawable.headphonee,R.drawable.headphonee_1)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.refe, "MicroWave",new ArrayList<Integer>(Arrays.asList(R.drawable.refe,R.drawable.refe_1)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.camerae, "Camera",new ArrayList<Integer>(Arrays.asList(R.drawable.camerae,R.drawable.camerae_1,R.drawable.camerae1_2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.laptope, "Laptop",new ArrayList<Integer>(Arrays.asList(R.drawable.laptope,R.drawable.laptope_1,R.drawable.laptope_2)),"400",Description));

        } else if (categoryName.equals("Accessories")) {
            productForuModels.add(new ProductForuModel(R.drawable.perfue, "Perfume",new ArrayList<Integer>(Arrays.asList(R.drawable.perfue,R.drawable.perfue1,R.drawable.perfue2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.perfume, "Perfume",new ArrayList<Integer>(Arrays.asList(R.drawable.perfume,R.drawable.perfume,R.drawable.perfume2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.combo, "women Accessories",new ArrayList<Integer>(Arrays.asList(R.drawable.combo,R.drawable.combo1,R.drawable.combo2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.purse, "Designer Purse",new ArrayList<Integer>(Arrays.asList(R.drawable.purse,R.drawable.purse1,R.drawable.purse2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.gogles, "Goggles",new ArrayList<Integer>(Arrays.asList(R.drawable.gogles,R.drawable.gogles1)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.earing, "Women Accessories",new ArrayList<Integer>(Arrays.asList(R.drawable.earing,R.drawable.earing1,R.drawable.earing2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.braclet, "Bracelet",new ArrayList<Integer>(Arrays.asList(R.drawable.braclet,R.drawable.braclet1,R.drawable.braclet2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.boyneck, "Chain",new ArrayList<Integer>(Arrays.asList(R.drawable.boyneck,R.drawable.boyneck1)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.ring, "Ring",new ArrayList<Integer>(Arrays.asList(R.drawable.ring,R.drawable.ring1,R.drawable.ring2)),"400",Description));

        } else if (categoryName.equals("Beauty")) {
            productForuModels.add(new ProductForuModel(R.drawable.muscra, "Macara",new ArrayList<Integer>(Arrays.asList(R.drawable.muscra,R.drawable.muscra1,R.drawable.muscra2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.eyeshadow, "Eyeshadow",new ArrayList<Integer>(Arrays.asList(R.drawable.eyeshadow,R.drawable.eyeshadow1,R.drawable.eyeshadow2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.eyeshadoww, "Eyeshadow Plate",new ArrayList<Integer>(Arrays.asList(R.drawable.eyeshadoww,R.drawable.eyeshadoww1)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.nailpaint, "Nail Polish",new ArrayList<Integer>(Arrays.asList(R.drawable.nailpaint,R.drawable.nailpaint1,R.drawable.nailpaint2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.tonic, "Face Toner",new ArrayList<Integer>(Arrays.asList(R.drawable.tonic,R.drawable.tonic1,R.drawable.tonic2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.lipbalm, "Lip Glow",new ArrayList<Integer>(Arrays.asList(R.drawable.lipbalm,R.drawable.lipbalm1,R.drawable.lipbalm2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.lipstic, "Lipstick",new ArrayList<Integer>(Arrays.asList(R.drawable.lipstic,R.drawable.lipstic2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.cleaner, "Facial Cleanser",new ArrayList<Integer>(Arrays.asList(R.drawable.cleaner,R.drawable.cleaner1,R.drawable.cleaner2)),"400",Description));

        } else if (categoryName.equals("Shoes")) {
            productForuModels.add(new ProductForuModel(R.drawable.girlshoes1, "Heels",new ArrayList<Integer>(Arrays.asList(R.drawable.girlshoes1,R.drawable.girlshoes1_1,R.drawable.girlshoes1_2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.girlshoess2, "Heels",new ArrayList<Integer>(Arrays.asList(R.drawable.girlshoess2,R.drawable.girlshoess2_1)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.girlshoes3, "Black Heels",new ArrayList<Integer>(Arrays.asList(R.drawable.girlshoes3,R.drawable.girlshoes3_1)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.menshoes2_1, "Shoes",new ArrayList<Integer>(Arrays.asList(R.drawable.menshoes2_1,R.drawable.menshoes2,R.drawable.menshoes2_2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.girlshoes4, "Red Heels",new ArrayList<Integer>(Arrays.asList(R.drawable.girlshoes4,R.drawable.girlshoes4_1,R.drawable.girlshoes4_2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.kisshoes, "White Shoes",new ArrayList<Integer>(Arrays.asList(R.drawable.kidshoes,R.drawable.kidshoes,R.drawable.kidshoes2)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.girlshoes5, "Black Wall Shoes",new ArrayList<Integer>(Arrays.asList(R.drawable.girlshoes5,R.drawable.girlshoes5_1)),"400",Description));
            productForuModels.add(new ProductForuModel(R.drawable.menshoes1, "",new ArrayList<Integer>(Arrays.asList(R.drawable.menshoes1,R.drawable.menshoes1_1)),"400",Description));
        }

        return productForuModels;
    }

    ///////////////////////////////////
    public static ArrayList<DetailedpageModel> Detailedimage() {
        ArrayList<DetailedpageModel> DetailedImages = new ArrayList<>();
        DetailedImages.add(new DetailedpageModel(R.drawable.base_3));
        DetailedImages.add(new DetailedpageModel(R.drawable.base_4));
        DetailedImages.add(new DetailedpageModel(R.drawable.base_3));
        DetailedImages.add(new DetailedpageModel(R.drawable.base_4));

        return DetailedImages;
    }



    public static ArrayList<MyAccountRecyclerModel> addtoaddress() {

        ArrayList<MyAccountRecyclerModel> myAccountRecyclerModels = new ArrayList<>();
        myAccountRecyclerModels.add(new MyAccountRecyclerModel("Twins Cloth", "E-42,ila society", "india colony,bapunagar", "ahmedabad", "8128362388", "222"));
        myAccountRecyclerModels.add(new MyAccountRecyclerModel("Couple Cloth", "E-42,ila society", "india colony,bapunagar", "ahmedabad", "812836238", "222"));
        myAccountRecyclerModels.add(new MyAccountRecyclerModel("Summer Cloth", "E-42,ila society", "india colony,bapunagar", "ahmedabad", "812836238", "222"));
        myAccountRecyclerModels.add(new MyAccountRecyclerModel("Family Cloth", "E-42,ila society", "india colony,bapunagar", "ahmedabad", "812836238", "222"));

        return myAccountRecyclerModels;
    }

}










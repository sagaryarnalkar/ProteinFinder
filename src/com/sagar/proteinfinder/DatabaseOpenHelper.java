package com.sagar.proteinfinder;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static DatabaseOpenHelper mInstance = null;
    
    private static final String proteinDBCreator = "CREATE TABLE IF NOT EXISTS "
    	    + "proteins_tbl ("
    	    + "_id integer primary key autoincrement,"
    	    + "Name TEXT, category TEXT, "
    	    + "Img TEXT,shortDesc TEXT,Rating DECIMAL,Factor Integer,url1 TEXT,url2 TEXT);";
    
    private static final String foodsDBCreator = "CREATE TABLE IF NOT EXISTS "
    	    + "foods_tbl ("
    	    + "_id integer primary key autoincrement,"
    	    + "Name TEXT, qserving TEXT,protein TEXT,cals TEXT,pperc TEXT, "
    	    + "Img TEXT,Type Integer);";
    
    
    private static final String insertHere="proteins_tbl(Name,category,Img,shortDesc,Rating,Factor,url1,url2)";
    private static final String insertHereFood="foods_tbl(Name,qserving,protein,cals,pperc,Img,Type)";
    
    private static final String[] foodInserts = {
    	"INSERT INTO "+insertHereFood+" VALUES('Egg'  , '1 Egg' ,   '6g'  , '77cal'  , '7.7','egg',1);",
    	"INSERT INTO "+insertHereFood+" VALUES('Cottage Cheese'  , '1 cup' ,   '26g'  , '163cal'  , '14.2','cheese',1);",
    	"INSERT INTO "+insertHereFood+" VALUES('Quinoa'  , '1 cup' ,   '8g'  , '216cal'  , '3.7','quinoa',1);",
    	"INSERT INTO "+insertHereFood+" VALUES('Seitan'  , '100g' ,   '23g'  , '131cal'  , '17.5','seitan',1);",
    	"INSERT INTO "+insertHereFood+" VALUES('Pumpkin Seeds'  , '30g or 1oz' ,   '7g'  , '126cal'  , '5.5','pumpkin',1);",
    	"INSERT INTO "+insertHereFood+" VALUES('Baked Beans'  , '200g or 7oz' ,   '12g'  , '179cal'  , '6.7','beans',1);",
    	"INSERT INTO "+insertHereFood+" VALUES('Soy Milk'  , '1 cup' ,   '8g'  , '105cal'  , '7','soymilk',1);",
    	"INSERT INTO "+insertHereFood+" VALUES('Yogurt'  , '150g or 5oz' ,   '12g'  , '137cal'  , '8.7','yogurt',1);",
    	"INSERT INTO "+insertHereFood+" VALUES('Peanut Butter'  , '2 tablespoon or 30g' ,   '8g'  , '190cal'  , '4.1','peanutbutter',1);",
    	"INSERT INTO "+insertHereFood+" VALUES('Oats'  , '30g or 1oz' ,   '5g'  , '109cal'  , '4.5','oats',1);",
    	"INSERT INTO "+insertHereFood+" VALUES('Milk'  , '1 standard cup' ,   '10g'  , '137cal'  , '7.2','milk',1);",
    	"INSERT INTO "+insertHereFood+" VALUES('Almonds'  , '30g or 22 almonds' ,   '6g'  , '171cal'  , '4.7','almonds',1);",
    	"INSERT INTO "+insertHereFood+" VALUES('Cashew'  , '30g or 18 kernel or 1oz' ,   '4g'  , '164cal'  , '2.7','cashew',1);",
    	"INSERT INTO "+insertHereFood+" VALUES('Pistachio'  , '30g or 49 count or 1oz' ,   '6g'  , '161cal'  , '2.7','pistachio',1);",
    	"INSERT INTO "+insertHereFood+" VALUES('White Rice'  , '1 cup' ,   '4g'  , '194cal'  , '2.1','whiterice',1);",
    	"INSERT INTO "+insertHereFood+" VALUES('Pork Loin (Chops)'  , '100g or 3.5 oz' ,   '27.5g'  , '199cal'  , '19.3','porkloins',2);",
    	"INSERT INTO "+insertHereFood+" VALUES('Turkey and Chicken breast'  , '100g or 3.5oz' ,   '28g'  , '189cal'  , '22.2','chickenbreast',2);",
    	"INSERT INTO "+insertHereFood+" VALUES('Lean Beef'  , '100g or 3.5oz' ,   '26g'  , '200cal'  , '19.1','leanbeaf',2);",
    	"INSERT INTO "+insertHereFood+" VALUES('Fish(Tuna,salmon,Halibut)'  , '100g or 3.5oz' ,   '26g'  , '120cal'  , '21.9','fish',2);",
    	"INSERT INTO "+insertHereFood+" VALUES('Duck Meat'  , '100g' ,   '20g'  , '337cal'  , '6.1','duckmeat',2);",
    	"INSERT INTO "+insertHereFood+" VALUES('Fish Cod'  , '100g' ,   '22g'  , '105cal'  , '21','fishcod',2);",
    	"INSERT INTO "+insertHereFood+" VALUES('Lamb'  , '100g' ,   '22g'  , '276cal'  , '7.9','lambmeat',2);",
    	"INSERT INTO "+insertHereFood+" VALUES('Rabbit Meat'  , '100g' ,   '27g'  , '187cal'  , '14.4','rabbitmeat',2);",
    	"INSERT INTO "+insertHereFood+" VALUES('Bacon'  , '100g' ,   '33g'  , '173cal'  , '19.1','bacon',2);",
    	"INSERT INTO "+insertHereFood+" VALUES('Pork Sausage'  , '100g' ,   '12g'  , '290cal'  , '4.1','porksausage',2);",
    	"INSERT INTO "+insertHereFood+" VALUES('Beef Sausage'  , '100g' ,   '18.2g'  , '332cal'  , '5.5','beefsausage',2);",
    	"INSERT INTO "+insertHereFood+" VALUES('Hot Dog (Beef)'  , '100g' ,   '11g'  , '302cal'  , '3.7','hotdogbeef',2);"
    };
    
    private static final String[] inserts = {
	    "INSERT INTO "+insertHere+" VALUES('Optimum Gold standard 100% whey'  , '1' ,   'on_w'  , 'Packed With Whey Protein Isolates For Ultra-Fast Absorption!'  , 9.2,2,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/optimum_details_zps84f9e56f.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/oprimum_ingredients_zpsf79b375c.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Optimum Platinum Hydrowhey'  , '1'  ,   'on_platinum_w'  , 'The Fastest, Purest, Most Advanced Whey Protein Optimum Has Ever Developed!'  , 9.2,6,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/hydrowhey_details_zpsfdf355c9.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/hydrowhey_ingredients_zpse45b8592.png');",
	    "INSERT INTO "+insertHere+" VALUES('Twinlab Whey Fuel'  , '1'  ,   'twinlab_w'  , 'The Perfect Protein Blend When Your Goal Is To Gain Lean Muscle Mass Or Bulk Up!'  , 8.8,2,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/twinlabs_details_zps3fc8572d.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/twinlab_ingredients_zps95beb143.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Twinlab Whey Fuel'  , '11'  ,   'twinlab_w'  , 'The Perfect Protein Blend When Your Goal Is To Gain Lean Muscle Mass Or Bulk Up!'  , 8.8,8,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/twinlabs_details_zps3fc8572d.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/twinlab_ingredients_zps95beb143.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Bodybuilding.com Foundation Series 100% Whey Power'  , '1'  ,   'bbcom_w'  , 'Now With 25 grams Of Protein, Improved Taste And Mixability!'  , 8.6,2,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/bodybuildingcom_details_zpsf079bf67.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/bodybuildingcom_ingredients_zps76bbbdde.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Gaspari Nutrition MyoFusion Elite Protein Series'  , '1'  ,   'gaspari_w'  , 'Features A 4-Stage Time-Released Protein Blend To Promote Strength, Recovery and Lean Muscle'  , 8.1,4,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/gaspari_details_zps882b17ae.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/gaspari_ingredients_zps0d0be1d8.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Universal Nutrition Real Gains'  , '2'  ,   'universal_nutrition_w'  , 'Powerful Mass-Cultivating Formula!'  , 8.8,8,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/universal_details_zpse0608300.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/universal_ingredients_zpsc1774f24.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Cellucor COR-Performance Whey'  , '1'  ,   'cellucor_w'  , '25G Of Protein To Satisfy Protein Needs Any Time Of The Day!'  , 9.1,4,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/cellucor_details_zps458a5a96.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/cellucor_ingredients_zpscf40b069.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('BSN Syntha-6'  , '1'  ,   'bsn_w'  , 'An Ultra-Premium Sustained Release Protein Powder!'  , 9.2,4,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/bsn_details_zps02a1999e.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/bsn_ingredients_zps57e357e9.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Dymatize ISO-100'  , '1'  ,   'dymatize_w'  , 'High Quality & Amazing Taste!'  , 9.4,4,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/dymatize_details_zps6055f1a4.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/dymatize_ingredients_zpsfb16607b.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('MusclePharm Combat Powder'  , '1'  ,   'musclepharm_w'  , 'Advanced Time Release Protein - Feed Your Muscles Up To 8 Hours!'  , 9.2,2,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/musclepharm_details_zps20d0fce5.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/musclepharm_ingredients_zps93d45f9f.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('MuscleTech Phase8'  , '1'  ,   'muscletech_w'  , 'Help Feed Your Muscles For Hours!', 9.4,6,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/muscletech_details_zps816f5014.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/muscletech_ingredients_zps51c84bc5.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Optimum 100% Soy Protein'  , '3'  ,   'on_soy'  , 'A Vegetarian Low Fat Protein Suitable For Low Carb Diets!'  , 8.2,2,'','http://i1346.photobucket.com/albums/p685/sagary_Dev89/optimum_ingredients_zps1a5c14e8.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('MHP Probolic'  , '1'  ,   'probolic_soy'  , 'Protein Technology that provides 12 Hour Sustained Release!'  , 8.6,8,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/mhp_details_zpsda167d8c.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/mhp_ingredients_zpsc0d90997.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('MHP Probolic'  , '11'  ,   'probolic_soy'  , 'Protein Technology that provides 12 Hour Sustained Release!'  , 8.6,3,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/mhp_details_zpsda167d8c.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/mhp_ingredients_zpsc0d90997.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Twinlab Vege Fuel'  , '3'  ,   'twinlab_soy'  , 'Fat Free & Cholesterol Free!'  , 9,2,'','http://i1346.photobucket.com/albums/p685/sagary_Dev89/twinlabs_ingredients_zps4a3459b3.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('NOW Soy Protein Isolate'  , '3'  ,   'now_soy'  , 'Non-Genetically Engineered!'  , 9,4,'','http://i1346.photobucket.com/albums/p685/sagary_Dev89/now_ingredients_zpsc86b0a8e.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Naturade 100% Soy'  , '3'  ,   'naturade_soy'  , 'A Balanced Profile Of All The Amino Acids Your Body Needs!'  , 8.7,4,'','http://i1346.photobucket.com/albums/p685/sagary_Dev89/naturade_ingredients_zps70832fd8.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Naturade 100% Soy'  , '31'  ,   'naturade_soy'  , 'A Balanced Profile Of All The Amino Acids Your Body Needs!'  , 8.7,12,'','http://i1346.photobucket.com/albums/p685/sagary_Dev89/naturade_ingredients_zps70832fd8.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Universal Nutrition Advanced Soy Protein'  , '3'  ,   'universal_nutrition_soy'  , 'Lactose, & Cholesterol Free Whole Protein Source!'  , 7.5,16,'','http://i1346.photobucket.com/albums/p685/sagary_Dev89/universal_nutrition_ingredients_zps2ad6e85c.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('EAS Myoplex Lite RTD, 24 - 11 Fl. Oz. Cartons'  , '3'  ,   'eas'  , '170 Calories And Revigor HMB To Help Maintain Lean Muscle!'  , 9.2,16,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/eas_details_zps4329c09a.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/eas_ingredients_zps66d5bbdd.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('MuscleTech CreaCore'  , '4'  ,   'muscletech'  , 'Double-Strength Concentrated Creatine!'  , 8.9,11,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/muscletech_Details_zpsdda0263c.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/muscletech_ingredients_zpscb1cee9d.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Cellucor C4 Extreme'  , '7'  ,   'cellucor'  , 'Cellucor C4 Extreme Workouts...'  , 8.7,14,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/Cellucor_details_zps06ae2199.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/cellucor_ingredients_zps81761b9b.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Optimum Micronized Creatine Powder'  , '4'  ,   'on'  , 'Muscle Energizer And Strength Builder!'  , 8.9,11,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/ON_details_zps5526ec49.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/ON_ingredients_zps6703b258.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Driven Sports CRAZE'  , '4'  ,   'driven_sports'  , 'The Ultimate In Pre-Workout Power!'  , 8.8,13,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/driven_sports_details_zps66f787ce.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/driven_sports_ingredients_zpsd6962963.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Universal Nutrition Creatine'  , '4'  ,   'universal_nutrition_c'  , 'Helps Increase your Strength and Performance!'  , 9,13,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/universal_details_zps6ec71d02.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/universal_ingredients_zps139524d5.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Bodybuilding.com Foundation Series Micronized Creatine'  , '4'  ,   'bb_crt'  , 'Support Muscular Energy, Delay Fatigue and Promote Growth'  , 9,13,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/bodybuilding_details_zpsd5a88210.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/bodybuildingcom_ingredients_zps546d797c.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Cobra Labs The Curse'  , '7'  ,   'cobra_labs_c'  , 'Ultimate Pre-Workout!'  , 8.6,11,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/cobra_Detail_zps715f1a13.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/cobra_ingredients_zps0fade597.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('ProMera Sports CON-CRET'  , '4'  ,   'promera_sports'  , 'Pure Concentrated Creatine Hydrochloride With Unique Micro-Dosing!'  , 9.5,11,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/promera_details_zps0f8801ea.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/promera_ingredients_zpsad56c7a0.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('BSN N.O. Xplode 2.0'  , '7'  ,   'bsn'  , 'Supports Muscular Endurance, Strength and Resistance to Muscular Fatigue!'  , 8.5, 14,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/bsn_details_zps168af8ce.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/bsn_ingredients_zpsf21d4d3a.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('MusclePharm Assault'  , '4'  ,   'musclepharm'  , 'Supercharged Endurance And Training Intensity!'  , 8.6,11,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/musclepharm_details_zps757526a8.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/musclepharm_ingredients_zpsfd9d7ba9.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('ProMera Sports GLUTA-TREN'  , '5'  ,   'promera_glut'  , 'Promotes Cell Volume & Protein Synthesis!'  , 9.5,7,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/promera_details_zps1acab52e.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/promera_ingredients_zps1d23e520.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('ProMera Sports GLUTA-TREN'  , '51'  ,   'promera_glut'  , 'Promotes Cell Volume & Protein Synthesis!'  , 9.5,14,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/promera_details_zps1acab52e.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/promera_ingredients_zps1d23e520.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Optimum Essesntial AmiNO Energy'  , '5'  ,   'on_platinum'  , 'Supports Nitric Oxide Synthesis With Natural Energizer'  , 8.7,14,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/on_details_zps872d6b67.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/on_ingredients_zpsd453a2e4.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('SciVation Xtend'  , '5'  ,   'scivation_glut'  , 'Scientifically Advanced Endurance and Recovery Support!'  , 9,14,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/scivation_details_zps3ce8b775.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/scivation_ingredients_zpsec91eb16.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('SciVation Xtend'  , '51'  ,   'scivation_glut'  , 'Scientifically Advanced Endurance and Recovery Support!'  , 9,7,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/scivation_details_zps3ce8b775.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/scivation_ingredients_zpsec91eb16.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('BSN CellMass 2.0'  , '5'  ,   'bsn_glut'  , 'Concentrated Post-Workout Recovery!'  , 9.2,7,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/bsn_details_zps7b050dc3.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/bsn_ingredients_zps9030cce3.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('BSN CellMass 2.0'  , '51'  ,   'bsn_glut'  , 'Concentrated Post-Workout Recovery!'  , 9.2,14,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/bsn_details_zps7b050dc3.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/bsn_ingredients_zps9030cce3.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Optimum Glutamine powder'  , '5'  ,   'on_glutamine'  , 'Enhance Muscle Growth And Recovery!'  , 8.8,14,'','http://i1346.photobucket.com/albums/p685/sagary_Dev89/on_powder_ingredients_zpscbbf898d.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Dymatize Elite Recoup'  , '5'  ,   'dymatize_glut'  , '7.2 Grams Of BCAAs And 2.5 Grams Of Glutamine Per Serving!'  , 8.9,14,'','http://i1346.photobucket.com/albums/p685/sagary_Dev89/dymatize_ingredients_zps43c48c96.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('AllMax Nutrition Micronized Glutamine'  , '5'  ,   'allmax_glut'  , 'A Very Important Nutrient For Optimum Workout Recovery!'  , 9.2,14,'','http://i1346.photobucket.com/albums/p685/sagary_Dev89/allmax_ingredients_zps4e2c05f9.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('AllMax Nutrition Micronized Glutamine'  , '51'  ,   'allmax_glut'  , 'A Very Important Nutrient For Optimum Workout Recovery!'  , 9.2,6,'','http://i1346.photobucket.com/albums/p685/sagary_Dev89/allmax_ingredients_zps4e2c05f9.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('MusclePharm Glutamine'  , '5'  ,   'musclepharm_glut'  , 'Supports Rebuilding and Recovery From The Toughest Workouts!'  , 8.9,7,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/musclepharm_details_zpsd8165c81.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/musclepharm_ingredients_zps5675207c.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Bodybuilding.com Platinum Series AMINO RECOVERY'  , '5'  ,   'bbglutimg'  , 'Mega-Dosed For Mega-Results!'  , 9.4,6,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/bodybuildingcom_details_zpsfe62922d.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/bodybuildingcom_ingredients_zps6336354a.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Optimum PerforMAXX'  , '6'  ,   'on_perfomaxx'  , 'Performance Sports Multiple With More Than Just Vitamins!'  , 9,4,'','http://i1346.photobucket.com/albums/p685/sagary_Dev89/on_perfomaxx_ingrediants_zpsc60b2b9b.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Gaspari Nutrition Anavite'  , '6'  ,   'anaviteimg'  , 'Build Confidence, build strength, build yourself'  , 9.1,4,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/gaspari_details_zps29d3ee18.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/gaspari_ingrediants_zpsa2e8408f.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Optimum Opti-Men'  , '6'  ,   'on_men'  , 'Multi-Vitamin and Mineral for Men!'  , 9.1,21,'','http://i1346.photobucket.com/albums/p685/sagary_Dev89/opt_men_ingredients_zps8d409e25.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Optimum Opti-Women'  , '6'  ,   'on_women'  , 'A Womens Formula For Optimum Health!'  , 9.4,22,'','http://i1346.photobucket.com/albums/p685/sagary_Dev89/opt_women_ingredients1_zpsf2f6a6e2.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('MusclePharm Armor-V'  , '6'  ,   'musclepharm_mv'  , 'Complete Source of Vitamins and Minerals!'  , 9.2,4,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/musclepharm_details_zpsd846f0e3.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/musclepharm_ingredients1_zps48d180ad.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('MusclePharm Armor-V'  , '61'  ,   'musclepharm_mv'  , 'Complete Source of Vitamins and Minerals!'  , 9.2,11,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/musclepharm_details_zpsd846f0e3.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/musclepharm_ingredients1_zps48d180ad.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Universal Nutrition Animal Pak'  , '6'  ,   'universal_nutrition_mv'  , 'A Multi To Support Rigorous Training Since 1983'  , 9.1,4,'','http://i1346.photobucket.com/albums/p685/sagary_Dev89/universal_ingredient1_zps10aa1ca2.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Universal Nutrition Animal Pak'  , '61'  ,   'universal_nutrition_mv'  , 'A Multi To Support Rigorous Training Since 1983'  , 9.1,11,'','http://i1346.photobucket.com/albums/p685/sagary_Dev89/universal_ingredient1_zps10aa1ca2.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('MuscleTech NeuroCore'  , '7'  ,   'muscletech_nox'  , 'Energy - Focus - Muscle - Pump – Strength'  , 8.9,14,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/muscletech_neurocore_details_zps357c8ed5.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/muscltetech_neurocore_ingredients_zps5e92f267.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('MuscleTech Nano Vapour'  , '7'  ,   'muscletech_nano_nox'  , 'Hardcore Pre-Workout Formula!'  ,9,14,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/muscletech_nano_details_zps959a846b.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/muscletech_nano_ingredients_zps8d527038.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Gaspari Nutrition SuperPump'  , '7'  ,   'gaspari_nox'  , 'Supports Muscle Pumps and Explosive Workouts!'  , 8.2,14,'','http://i1346.photobucket.com/albums/p685/sagary_Dev89/gaspari_ingredients_zps5821054a.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Optimum Platinum PRE'  , '7'  ,   'on_platinum_nox'  , 'A Pre-workout Built On Researched Ingredients And Superior Quality!'  , 7.8,14,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/on_platinum_details_zps75edc185.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/on_platinum_ingredients_zpsb35f44de.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('BSN True-Mass' , '2' , 'truemass_w' , 'An Ultra Premium Lean Muscle And Lean Mass Gainer!' , 8.7,8,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/truemass_details_zpsdd162e91.png','http://i1346.photobucket.com/albums/p685/sagary_Dev89/truemass_ingredients_zps62405bbf.png');",
	    "INSERT INTO "+insertHere+" VALUES('Optimum Serious Mass' , '2' , 'seriousmss_w' , 'An Ultra Premium Mass Gainer!' , 8.5,8,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/ON_details_zps52f1bc84.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/ON_ingredients_zpsf0a83566.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('Optimum Platinum Hydrobuilder' , '2' , 'hydrobuilderimg' , 'Complete Protein Muscle Constructor!' , 9.2,8,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/hydro_details_zpsf5d37a21.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/hydro_ingredients_zpsd1768428.jpg');",
	    "INSERT INTO "+insertHere+" VALUES('MuscleTech Nano Vapour'  , '71'  ,   'muscletech_nano_nox'  , 'Hardcore Pre-Workout Formula!'  ,9,14,'http://i1346.photobucket.com/albums/p685/sagary_Dev89/muscletech_nano_details_zps959a846b.jpg','http://i1346.photobucket.com/albums/p685/sagary_Dev89/muscletech_nano_ingredients_zps8d527038.jpg');"};
    
    private DatabaseOpenHelper(Context ctx) throws NameNotFoundException {
	super(ctx, "ProteinDB", null, ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0 ).versionCode);
    }

    private DatabaseOpenHelper(Context context, String name,
	    CursorFactory factory, int version) {
	super(context, name, factory, version);
    }

    public static DatabaseOpenHelper getInstance(Context ctx) {

	if (mInstance == null) {
	    try {
			mInstance = new DatabaseOpenHelper(ctx.getApplicationContext());
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			mInstance = new DatabaseOpenHelper(ctx.getApplicationContext(),"ProteinDB", null,1);
		}
	}
	return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

	db.execSQL(proteinDBCreator);
	db.execSQL(foodsDBCreator);
	for(String insert:inserts)
	{
		db.execSQL(insert);
	}
	for(String insert:foodInserts)
	{
		db.execSQL(insert);
	}

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	db.execSQL("DROP TABLE IF EXISTS proteins_tbl");
	db.execSQL("DROP TABLE IF EXISTS foods_tbl");
	// Create tables again
	onCreate(db);
    }
}

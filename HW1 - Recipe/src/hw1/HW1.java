/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1;

/**
 *
 * @author Kristen
 */
public class HW1 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Create a recipe
        Recipe rec = new Recipe();
        
        //Add values
        rec.arrIng[0] = new Ingredient(1, "cup salted butter (softened)");
        rec.arrIng[1]= new Ingredient(1, "cup white sugar");
        rec.arrIng[2] = new Ingredient(1, "cup light brown sugar");
        rec.arrIng[3] = new Ingredient(2, "tsp pure vanilla extract");
        rec.arrIng[4] = new Ingredient(2, "large eggs");
        rec.arrIng[5] = new Ingredient(3, "cup all-purpose flour");
        rec.arrIng[6] = new Ingredient(1, "tsp baking soda");
        rec.arrIng[7] = new Ingredient(0.5, "tsp baking powder");
        rec.arrIng[8] = new Ingredient(1, "tsp sea salt");
        rec.arrIng[9] = new Ingredient(2, "cup chocolate chips");
        rec.arrSteps[0] = "Preheat oven to 375 degrees F. Line a baking pan with parchment paper and set aside.";
        rec.arrSteps[1] = "In a separate bowl mix flour, baking soda, salt, baking powder. Set aside.";
        rec.arrSteps[2] = "Cream together butter and sugars until combined.";
        rec.arrSteps[3] = "Beat in eggs and vanilla until fluffy.";
        rec.arrSteps[4] = "Mix in the dry ingredients until combined.";
        rec.arrSteps[5] = "Add 12 oz package of chocolate chips and mix well.";
        rec.arrSteps[6] = "Roll 2-3 TBS of dough at a time into balls and place them evenly spaced on your prepared cookie sheets.";
        rec.arrSteps[7] = "Bake in preheated oven for approximately 8-10 minutes. Take them out when they are just BARELY starting to turn brown.";
        rec.arrSteps[8] = "Let them sit on the baking pan for 2 minutes before removing to cooling rack.";
        
        //Print to screen
        System.out.print(rec.toString());
    }
}

class Ingredient{
    public double measurement;
    public String item;
    
    Ingredient(double m, String i){
        measurement = m;
        item = i;
    }
}

class Recipe{
    public Ingredient[] arrIng = new Ingredient[10];
    public String[] arrSteps = new String[9];
    
    public String toString(){
        String result = "";
        for (Ingredient ing : arrIng) {
            result += (ing.measurement + " " + ing.item + "\n");
        }
        result += "\r\n\r\n";
        for(int i=0; i<arrSteps.length; i++){
            result += ((i+1) + ". " + arrSteps[i] + "\n");
        }
        return result;
    }
}
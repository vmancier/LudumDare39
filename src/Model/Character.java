package Model;

public class Character {
    private String name;
    private String description;
    private int[] position;
    private Tool[] inventaire;


    public void Character(String name){
        this.name=name;
    }
    public void Character(String name,String description){
        this.name=name;
        this.description=description;
    }

    public void moveTo(int[] new_position){
    }

    public void moveTo(String mot){

    }





    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public Tool[] getInventaire() {
        return inventaire;
    }

    public void setInventaire(Tool[] inventaire) {
        this.inventaire = inventaire;
    }
}

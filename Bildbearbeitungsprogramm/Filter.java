public abstract class Filter
{
    private String name;

    public Filter(String name){
        this.name = name;
    }

    public String gibName(){
        return name;   
    }

    public abstract void anwenden(Farbbild bild);
}

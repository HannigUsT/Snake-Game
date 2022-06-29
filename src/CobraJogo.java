public class CobraJogo{
    protected int x;
    protected int y;
    protected int partescorpo = 4;

    public CobraJogo(){
    }

    public CobraJogo(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void setX(int x){
        this.x = x;
    }
    public int getX(){
        return this.x;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getY(){
        return this.y;
    }
    public void setPartesCorpo(int partescorpo){
        this.partescorpo = partescorpo;
    }
    public int getPartesCorpo(){
        return this.partescorpo;
    }

}
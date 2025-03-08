public class ScreenAdapter{
    private Screen scr;

    ScreenAdapter(){
        scr = new Screen();
    }
    public void writeInt(int i){
        scr.writeInt(new Integer(i));
    }
}

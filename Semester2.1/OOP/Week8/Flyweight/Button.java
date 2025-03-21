import java.awt.*;

public class Button {
    private boolean selected;
    private final int W = 50, H = 30;
    
    //Constructor to create the selected state of the button
    public Button(boolean selected) {
        this.selected = selected;
    }
    
    //Setter method to get the selected state of the button
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    //Draws the button with the given name 
    public void draw(Graphics g, int tx, int ty, String name) {
        // Choose colors based on state
        Color borderColor = Color.BLACK;
        Color fillColor = selected ? Color.PINK : Color.GREEN;
        Color textColor = Color.BLACK;
        
        // Draw border and fill
        g.setColor(borderColor);
        g.drawRect(tx, ty, W, H);
        g.setColor(fillColor);
        g.fillRect(tx + 1, ty + 1, W - 2, H - 2);
        
        // Draw label text centered in the button
        g.setColor(textColor);
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(name);
        int textHeight = fm.getAscent();
        int xText = tx + (W - textWidth) / 2;
        int yText = ty + (H + textHeight) / 2 - 2;
        g.drawString(name, xText, yText);
    }
}

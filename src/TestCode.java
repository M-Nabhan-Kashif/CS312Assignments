import java.awt.*;

public class TestCode {
    public static void main(String[] args) {
        final int WIDTH = 800;
        final int HEIGHT = 800;
        DrawingPanel dp = new DrawingPanel(WIDTH, HEIGHT);
        //Drawing panel created
        Graphics g = dp.getGraphics();
        dp.setBackground(Color.WHITE);

        drawFigure(g, 10, 200, 210, 6); // top left
        drawFigure(g, 300, 100, 300, 3); // top right
        drawFigure(g, 10, 500, 250, 1); // lower left
        drawFigure(g, 300, 500, 400, 16); // lower right

    }
    public static void drawFigure(Graphics g, int x, int y, int size,int numCircles){
        g.setColor(Color.YELLOW);
        g.fillRect(x,y,size,size);

        int changingX = x;
        int changingY = y;
        for (int i = 0; i <numCircles; i++){
            if (i % 2 == 0){
                g.setColor(Color.BLACK);
            }
            else{
                g.setColor(Color.WHITE);
            }
            int circleDia = size - i * (size / numCircles);
            changingX += (i * (size / numCircles));
            changingY += (i * (size / numCircles));
            g.fillOval( changingX, changingY, circleDia, circleDia);
        }
    }

}

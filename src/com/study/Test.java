package com.study;

/**
 * Created by Administrator on 2016/3/5.
 */
public class Test {
    public static void main(String[] args) {
        // 画一个半径为10，旋转为0，空白为全身空格，填充为★的五角星
        Pentagrama pen = new Pentagrama(10, 0, ' ', '★');
        // 在控制台上输出这个五角星
        Draw.printCanvas(pen.getPentagram());
    }
}
class Pentagrama {
    private final char FILL_CHAR;   // 填充字符
    private final char SPACE_CHAR;  // 空档字符
    private final int R;            // 五角星的外接圆半径
    private final float ROTATION;   // 五角星逆时针旋转角度
    private final int X;            // 用于生成画图数组
    private final int Y;            // 用于生成画图数组

    /**
     * 构造一个Pentagram对象
     * @param radius 五角星的半径
     * @param rotation 五角星的逆时针旋转度数
     * @param spaceChar 画布上空白处填充字符
     * @param fillChar 画布上线条部分填充字符
     */
    public Pentagrama(int radius, float rotation, char spaceChar, char fillChar) {
        this.R = radius;
        this.ROTATION = rotation;
        this.FILL_CHAR = fillChar;
        this.SPACE_CHAR = spaceChar;
        this.X = 2 * R + 1;
        this.Y = 2 * R + 1;
    }

    public char[][] getPentagram() {
        char[][] canvas = initCanvas();
        Draw draw = new Draw(FILL_CHAR);
        // 设五角星的最右边的一个点为 A，逆时针选取点 B~E
        // 通过圆的极坐标公式可以得出：
        // 得出以下各点的坐标
        // A 点坐标(0.951R, 0.309R)
        // B 点坐标(0, R)
        // C 点坐标(-0.951R, 0.309R)
        // D 点坐标(-0.588R, -0.809R)
        // E 点坐标(0.588R, -0.809R)

        // 画线段CA
        draw.drawLine(mcos(162) * R, msin(162) * R, mcos(18) * R, msin(18) * R, canvas);
        // 画线段DA
        draw.drawLine(mcos(234) * R, msin(234) * R, mcos(18) * R, msin(18) * R, canvas);
        // 画线段CE
        draw.drawLine(mcos(162) * R, msin(162) * R, mcos(306) * R, msin(306) * R, canvas);
        // 画线段DB
        draw.drawLine(mcos(234) * R, msin(234) * R, mcos(90) * R, msin(90) * R, canvas);
        // 画线段BE
        draw.drawLine(mcos(90) * R, msin(90) * R, mcos(306) * R, msin(306) * R, canvas);
        return canvas;
    }

    // 在方形的字符数组中指定两点画线条
    // 对图形数组进行初始化，填充空格
    private char[][] initCanvas() {
        char[][] canvas = new char[Y][X];
        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                canvas[i][j] = SPACE_CHAR;
            }
        }
        return canvas;
    }

    // 根据角度求正弦值，保留两位小数
    private double msin(float a) {
        return ((int) (Math.sin(Math.toRadians(a + ROTATION)) * 100)) / 100.0;
    }

    // 根据角度求余弦值，保留两位小数
    private double mcos(float a) {
        return ((int) (Math.cos(Math.toRadians(a + ROTATION)) * 100)) / 100.0;
    }
}
class Draw {

    private char fillChar;

    public Draw(char fillChar) {
        this.fillChar = fillChar;
    }

    /**
     * 根据两个点画线在二维字符数组上画线
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param canvas
     */
    public void drawLine(double x1, double y1, double x2, double y2, char[][] canvas) {
        int radius = (canvas.length - 1) / 2;
        // 从 x 方向进行填充
        if (x1 > x2) {
            double t = x1;
            x1 = x2;
            x2 = t;
            t = y1;
            y1 = y2;
            y2 = t;
        }

        // 获得直线方程的两个系数
        double a = (y1 - y2) / (x1 - x2);
        double b = y1 - a * x1;

        // 根据 x 方向的值求出 y 值，并填充图形
        for (int i = (int) Math.round(x1); i <= (int) Math.round(x2); i++) {
            // 根据直线方程 y = ax + b，求 y
            int y = (int) Math.round(a * i + b);

            // 因为 y 和 i 算出来的结果有可能是负数，
            // 为了采用数组来表示坐标，做了以下变换
            // c[R][R] 即为坐标原点
            // c[R][0..R] 为 x 方向的负半轴
            // c[R][R+1..2*R] 为 x 方向的正半轴
            // c[0..R][R] 为 y 方向的正半轴
            // c[R+1..2*R][R] 为 y 方向的负半轴
            int yy = radius - y;
            int xx = radius + i;

            yy = yy < 0 ? 0 : yy;
            yy = yy > 2 * radius ? 2 * radius : yy;
            xx = xx < 0 ? 0 : xx;
            xx = xx > 2 * radius ? 2 * radius : xx;

            canvas[yy][xx] = fillChar;
        }

        // 从 y 方向进行填充，便于减少间距问题产生的字符空档
        if (y1 > y2) {
            double t = x1;
            x1 = x2;
            x2 = t;
            t = y1;
            y1 = y2;
            y2 = t;
        }

        // 根据 y 方向的值求出 x 值，并填充图形
        for (int i = (int) Math.round(y1); i <= (int) Math.round(y2); i++) {
            // 根据 x = (y - b) / a，求 x
            int y = (int) Math.round((i - b) / a);

            int yy = radius - i;
            int xx = radius + y;

            yy = yy < 0 ? 0 : yy;
            yy = yy > 2 * radius ? 2 * radius : yy;
            xx = xx < 0 ? 0 : xx;
            xx = xx > 2 * radius ? 2 * radius : xx;

            canvas[yy][xx] = fillChar;
        }
    }

    /**
     * 将画完图之后的画布输出到控制台上
     * @param canvas
     */
    public static void printCanvas(char[][] canvas) {
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[i].length; j++) {
                System.out.print(canvas[i][j]);
            }
            System.out.println();
        }
    }
}
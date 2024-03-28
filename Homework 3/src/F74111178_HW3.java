import java.util.Scanner;

public class F74111178_HW3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int main_x = scanner.nextInt();
        int main_y = scanner.nextInt();

        int other_x = scanner.nextInt();
        int other_y = scanner.nextInt();

        int move_x = scanner.nextInt();
        int move_y = scanner.nextInt();

        Point main_point = new Point();

        main_point.Set(main_x,main_y);
        System.out.println(main_point.RetrieveVertical() + " " + main_point.RetrieveHorizontal());

        main_point.Move(move_x,move_y);
        System.out.println(main_point.RetrieveVertical() + " " + main_point.RetrieveHorizontal());

        for(int i=0;i<4;i++){
            main_point.Rotate();
            System.out.println(main_point.RetrieveVertical() + " " + main_point.RetrieveHorizontal());
        }

        Point other_point = new Point();
        other_point.Set(other_x,other_y);
        System.out.println(main_point.calculateManhattanDistance(other_point));
        System.out.println(main_point.ChebyshevDistance(other_point));

    }


}
class Point {
    private int vertical = 0;
    private int horizontal = 0;
    public void Set(int vertical, int horizontal) {
        this.vertical = vertical;
        this.horizontal = horizontal;
    }
    public void Move(int x, int y) {
        this.vertical += x;
        this.horizontal += y;
    }
    public void Rotate() {
        int temp = this.vertical;
        this.vertical = this.horizontal;
        this.horizontal = -temp;
    }
    public int RetrieveVertical() {
        return this.vertical;
    }
    public int RetrieveHorizontal() {
        return this.horizontal;
    }
    public int calculateManhattanDistance(Point other) {
        return Math.abs(this.vertical - other.vertical) + Math.abs(this.horizontal - other.horizontal);
    }
    public double ChebyshevDistance(Point other) {
        return Math.max(Math.abs(this.vertical - other.vertical), Math.abs(this.horizontal - other.horizontal));
    }
}
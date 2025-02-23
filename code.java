import java.util.*;
import javax.swing.*;
import java.awt.*;

public class NQueenVisualizer extends JPanel {
    private static final int TILE_SIZE = 50;
    private int n;
    private int[] queens;

    public NQueenVisualizer(int n) {
        this.n = n;
        this.queens = new int[n];
        solveNQueen(0);
    }

    private boolean solveNQueen(int row) {
        if (row == n) {
            repaint();
            return true;
        }
        for (int col = 0; col < n; col++) {
            if (isValid(row, col)) {
                queens[row] = col;
                if (solveNQueen(row + 1)) return true;
            }
        }
        return false;
    }

    private boolean isValid(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (queens[i] == col || Math.abs(queens[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g.setColor((i + j) % 2 == 0 ? Color.LIGHT_GRAY : Color.DARK_GRAY);
                g.fillRect(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
        g.setColor(Color.RED);
        for (int i = 0; i < n; i++) {
            g.fillOval(queens[i] * TILE_SIZE + TILE_SIZE / 4, i * TILE_SIZE + TILE_SIZE / 4, TILE_SIZE / 2, TILE_SIZE / 2);
        }
    }

    public static void main(String[] args) {
        int n = 8;
        JFrame frame = new JFrame("N-Queen Visualizer");
        NQueenVisualizer panel = new NQueenVisualizer(n);
        frame.add(panel);
        frame.setSize(n * TILE_SIZE, n * TILE_SIZE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

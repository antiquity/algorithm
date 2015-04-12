import java.awt.image.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.security.*;
import java.math.*;
import javax.swing.*;

public class ColorLinkerVis {
    //Constraints
    static final int MIN_COLORS_NO = 2;
    static final int MAX_COLORS_NO = 5;
    static final int MIN_GRID_SIZE = 20;
    static final int MAX_GRID_SIZE = 60;
    static final double MIN_COLORED_CELLS_RATIO = 0.5;
    static final double MAX_COLORED_CELLS_RATIO = 4.0;
    static final double MIN_CROSS_PENALTY = 1.0;
    static final double MAX_CROSS_PENALTY = 8.0;

    static final double TIME_LIMIT = 10.0;

    static final int RECT_SIZE = 4;
    static final int DEFAULT_SIDE = 15;

    //testcase data
    boolean[][][] coloredGrid = null;
    int gridSize;
    int[][] grid;
    int crossPenalty;
    int colorsSize;
    double[] colorWeights;

    static int side = DEFAULT_SIDE;
    static String exec = null;
    static boolean vis = true;
    static Process proc = null;

    InputStream is;
    OutputStream os;
    BufferedReader br;
    JFrame jf;
    Vis v;

    public ColorLinkerVis(String seed) {
        if (vis) {
            jf = new JFrame();
            v = new Vis();
            jf.getContentPane().add(v);
        }

        if (exec != null) {
            try {
                Runtime rt = Runtime.getRuntime();
                proc = rt.exec(exec);
                os = proc.getOutputStream();
                is = proc.getInputStream();
                br = new BufferedReader(new InputStreamReader(is));
                new ErrorReader(proc.getErrorStream()).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Score = " + runTest(seed));
        if (proc != null) {
            try {
                proc.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void generate(String seed) {
        SecureRandom rnd = null;
        try {
            rnd = SecureRandom.getInstance("SHA1PRNG");
            rnd.setSeed(Long.parseLong(seed));
        } catch (Exception e) {
            e.printStackTrace();
        }

        gridSize = MIN_GRID_SIZE + rnd.nextInt(MAX_GRID_SIZE - MIN_GRID_SIZE + 1);
        colorsSize = MIN_COLORS_NO + rnd.nextInt(MAX_COLORS_NO - MIN_COLORS_NO + 1);
        grid = new int[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++)
            for (int j = 0; j < gridSize; j++)
                grid[i][j] = -1;

        colorWeights = new double[colorsSize];
        for (int i = 0; i < colorsSize; i++)
            colorWeights[i] = rnd.nextDouble() * rnd.nextDouble();
        double totalWeight = 0;
        for (double d : colorWeights)
            totalWeight += d;
        for (int i = 0; i < colorsSize; i++)
            colorWeights[i] /= totalWeight;


        int minColoredCellsSize = (int) (gridSize * MIN_COLORED_CELLS_RATIO);
        int maxColoredCellsSize = (int) (gridSize * MAX_COLORED_CELLS_RATIO);
        int coloredCellsSize = minColoredCellsSize + rnd.nextInt(maxColoredCellsSize - minColoredCellsSize + 1);

        int minCrossPenalty = (int) (gridSize * MIN_CROSS_PENALTY);
        int maxCrossPenalty = (int) (gridSize * MAX_CROSS_PENALTY);
        crossPenalty = minCrossPenalty + rnd.nextInt(maxCrossPenalty - minCrossPenalty + 1);

        while (coloredCellsSize > 0) {
            int i = rnd.nextInt(gridSize);
            int j = rnd.nextInt(gridSize);
            if (grid[i][j] != -1) continue;

            double p = rnd.nextDouble();
            int color = 0;
            while (p >= colorWeights[color] && color + 1 < colorsSize) {
                p -= colorWeights[color];
                color++;
            }

            grid[i][j] = color;
            coloredCellsSize--;
        }
    }

    private int[] callSolution() throws IOException, NumberFormatException {
        if (exec == null) return null;

        StringBuilder sb = new StringBuilder();
        sb.append(gridSize).append('\n');
        sb.append(crossPenalty).append('\n');
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++)
                sb.append(grid[i][j] == -1 ? '-' : (char)('A' + grid[i][j]));
            sb.append("\n");
        }
        os.write(sb.toString().getBytes());
        os.flush();

        int[] rv = new int[Integer.parseInt(br.readLine())];
        for (int i = 0; i < rv.length; i++)
            rv[i] = Integer.parseInt(br.readLine());
        return rv;
    }

    public double runTest(String seed) {
        try {
            generate(seed);

            if (exec == null) {
                System.out.println("Nothing to run. Please specify your solution with -exec option.");
                return 0.0;
            }

            int[] solution;
            try {
                solution = callSolution();
            } catch (Exception e) {
                addFatalError("Error in parsing your output. You should print only numbers and the first line should contain total number of the integers.");
                return 0.0;
            }

            if (solution == null) {
                addFatalError("Unable to get any return value from your solution.");
                return 0.0;
            }

            if (solution.length % 3 != 0) {
                addFatalError("The number of integers in your return value should be divisible by 3");
                return 0.0;
            }

            int n = solution.length / 3;
            int[] row = new int[n];
            int[] col = new int[n];
            int[] color = new int[n];
            try {
                for (int i = 0; i < n; i++) {
                    row[i] = solution[i * 3 + 0];
                    col[i] = solution[i * 3 + 1];
                    color[i] = solution[i * 3 + 2];
                }
            } catch (Exception e) {
                addFatalError("You should only print integers.");
                return 0.0;
            }

            for (int i = 0; i < n; i++) {
                if (row[i] < 0 || row[i] >= gridSize || col[i] < 0 || col[i] >= gridSize) {
                    addFatalError("Your coordinates have to be inside the grid.");
                    return 0.0;
                }
                if (color[i] < 0 || color[i] >= MAX_COLORS_NO) {
                    addFatalError("You can only use up to " + MAX_COLORS_NO + " colors.");
                    return 0.0;
                }
            }

            coloredGrid = new boolean[MAX_COLORS_NO][gridSize][gridSize];
            for (int i = 0; i < n; i++)
                coloredGrid[color[i]][row[i]][col[i]] = true;

            if (vis) {
                jf.setSize(gridSize * side + Vis.HMARGINS, gridSize * side + Vis.VMARGINS);
                jf.setVisible(true);
                draw();
            }


            for (int r = 0; r < gridSize; r++)
                for (int c = 0; c < gridSize; c++)
                    if (grid[r][c] != -1 && !coloredGrid[grid[r][c]][r][c]) {
                        addFatalError("You should color every cell that is
                                originally colored");
                        return 0.0;
                    }

            for (int curColor = 0; curColor < MAX_COLORS_NO; curColor++) {
                final int[] dr = {-1, 1, 0, 0};
                final int[] dc = {0, 0, -1, 1};
                int sr = -1, sc = -1;
                for (int r = 0; r < gridSize; r++)
                    for (int c = 0; c < gridSize; c++)
                        if (coloredGrid[curColor][r][c]) {
                            sr = r;
                            sc = c;
                        }

                boolean[][] vis = new boolean[gridSize][gridSize];
                Queue<Integer> q = new LinkedList<Integer>();
                q.add(sr);
                q.add(sc);

                while (q.peek() != null) {
                    int r = q.poll();
                    int c = q.poll();
                    if (r < 0 || r >= gridSize || c < 0 || c >= gridSize || !coloredGrid[curColor][r][c] || vis[r][c]) continue;
                    vis[r][c] = true;
                    for (int d = 0; d < 4; d++) {
                        q.add(r + dr[d]);
                        q.add(c + dc[d]);
                    }
                }

                for (int r = 0; r < gridSize; r++)
                    for (int c = 0; c < gridSize; c++)
                        if (!vis[r][c] && coloredGrid[curColor][r][c]) {
                            addFatalError("Color #" + curColor + " is not 4-connected");
                            return 0.0;
                        }
            }

            int score = n;
            for (int r = 0; r < gridSize; r++)
                for (int c = 0; c < gridSize; c++) {
                    int x = 0;
                    for (int i = 0; i < MAX_COLORS_NO; i++)
                        x += coloredGrid[i][r][c] ? 1 : 0;
                    score += x * (x - 1) * crossPenalty;
                }

            return score;
        } catch (Exception e) {
            System.err.println("An exception occurred while trying to get your program's results: ");
            e.printStackTrace();
            return 0.0;
        }
    }

    public static void main(String[] args) {
        String seed = "1";
        for (int i = 0; i<args.length; i++) {
            if (args[i].equals("-seed")) {
                seed = args[++i];
            } else if (args[i].equals("-exec")) {
                exec = args[++i];
            } else if (args[i].equals("-novis")) {
                vis = false;
            } else if (args[i].equals("-side")) {
                side = Integer.parseInt(args[++i]);
            }
        }

        new ColorLinkerVis(seed);
    }

    void addFatalError(String message) {
        System.out.println(message);
    }

    // Server stuff
    public String checkData(String test) {
        return "";
    }

    public String displayTestCase(String test) {
        StringBuilder sb = new StringBuilder();
        generate(test);
        sb.append("Seed = " + test + "\n");
        sb.append("Grid size = " + gridSize + "\n");
        sb.append("Colors = " + colorsSize + "\n");
        sb.append("Grid =\n");
        for (int i = 0; i < gridSize; ++i) {
            for (int j = 0; j < gridSize; j++)
                sb.append(grid[i][j] == -1 ? '-' : 'A' + grid[i][j]);
            sb.append('\n');
        }
        return sb.toString();
    }

    public double[] score(double[][] sc) {
        double[] res = new double[sc.length];
        int tests = sc[0].length;
        if (tests == 0) return res;

        for (int i = 0; i < tests; i++) {
            double best = 0;
            for (int j = 0; j < sc.length; j++)
                if (best == 0 || sc[j][i] > 0 && sc[j][i] < best)
                    best = sc[j][i];
            for (int j = 0; j < sc.length; j++)
                if (sc[j][i] > 0)
                    res[j] += best / sc[j][i];
        }

        for (int i = 0; i < sc.length; i++)
            res[i] = res[i] / tests * 1000000.0;
        return res;
    }

    // Visualization stuff
    void draw() {
        if (v == null) return;
        v.repaint();
        try {
            //Thread.sleep(del);
        } catch (Exception e) { };
    }

    class Vis extends JPanel implements WindowListener {
        static final int MARGIN = 20;
        static final int HMARGINS = 2 * MARGIN + 15;
        static final int VMARGINS = 2 * MARGIN + 35;
        final Color COLORS[] = new Color[] {new Color(0xAF0000), new Color(0x00AF00), new Color(0x0000AF), new Color(0xAFAF00), new Color(0x00AFAF)};
        final Color ORIG_COLORS[] = new Color[] {new Color(0xFF0000), new Color(0x00FF00), new Color(0x0000FF), new Color(0xFFFF00), new Color(0x00FFFF)};
        final Color BLACK = new Color(0x000000);
        final Color WHITE = new Color(0xFFFFFF);

        public void paint(Graphics gr) {
            BufferedImage bi = new BufferedImage(gridSize * side + HMARGINS, gridSize * side + VMARGINS, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = (Graphics2D)bi.getGraphics();

            // background
            g2.setColor(WHITE);
            g2.fillRect(0, 0, gridSize * side + HMARGINS, gridSize * side + VMARGINS);

            if (grid != null) {
                for (int r = 0; r < gridSize; r++)
                    for (int c = 0; c < gridSize; c++) {
                        ArrayList<Color> colors = new ArrayList<Color>();
                        for (int i = 0; i < colorsSize; i++)
                            if (coloredGrid[i][r][c]) {
                                if (grid[r][c] == i) {
                                    colors.add(ORIG_COLORS[i]);
                                } else {
                                    colors.add(COLORS[i]);
                                }
                            }

                        if (colors.size() == 0)
                            colors.add(WHITE);

                        for (int i = 0; i < colors.size(); i++) {
                            g2.setColor(colors.get(i));
                            for (int y = 0; y < (side + RECT_SIZE - 1) / RECT_SIZE; y++)
                                for (int x = (i + y) % colors.size(); x < (side + RECT_SIZE - 1) / RECT_SIZE; x += colors.size())
                                    g2.fillRect(MARGIN + c * side + x * RECT_SIZE, MARGIN + r * side + y * RECT_SIZE, Math.min(RECT_SIZE, side - x * RECT_SIZE), Math.min(RECT_SIZE, side - y * RECT_SIZE));
                        }

                    }

                // grid
                g2.setColor(BLACK);
                g2.setStroke(new BasicStroke(1));
                for (int i = 0; i <= gridSize; i++) {
                    g2.drawLine(MARGIN, MARGIN + i * side, MARGIN + gridSize * side, MARGIN + i * side);
                    g2.drawLine(MARGIN + i * side, MARGIN, MARGIN + i * side, MARGIN + gridSize * side);
                }

            }

            gr.drawImage(bi, 0, 0, gridSize * side + HMARGINS, gridSize * side + VMARGINS, null);
        }


        public Vis() {
            jf.addWindowListener(this);
        }

        public void windowClosing(WindowEvent e){
            if(proc != null)
                try { proc.destroy(); }
                catch (Exception ex) { ex.printStackTrace(); }
            System.exit(0);
        }
        public void windowActivated(WindowEvent e) { }
        public void windowDeactivated(WindowEvent e) { }
        public void windowOpened(WindowEvent e) { }
        public void windowClosed(WindowEvent e) { }
        public void windowIconified(WindowEvent e) { }
        public void windowDeiconified(WindowEvent e) { }
    }


}


class ErrorReader extends Thread {
    InputStream error;

    public ErrorReader(InputStream is) {
        error = is;
    }

    public void run() {
        try {
            byte[] ch = new byte[50000];
            int read;
            while ((read = error.read(ch)) > 0) {
                String s = new String(ch, 0, read);
                System.out.print(s);
                System.out.flush();
            }
        } catch(Exception e) { }
    }
}

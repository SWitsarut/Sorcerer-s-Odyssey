package util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class LoadSave {

    public static String PLAYER_ATLAS = "rogue.png";
    public static String LEVEL_ATLAS = "magecity.png";

    public static BufferedImage GetSpriteAtlas(String file) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/img/entity/" + file);

        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }

    public static BufferedImage GetLevelAtlas(String file) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/img/tile set/" + file);

        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }

    public static int[][] getLevelDate(String file) {
        int[][] mapData = null;

        try {
            ArrayList<ArrayList<Integer>> tempArr = new ArrayList<>();
            File name = new File(file);
            FileReader fr = new FileReader(name);
            BufferedReader br = new BufferedReader(fr);
            String line = "";

            int colCount = 0;
            int rowCount = 0;
            while ((line = br.readLine()) != null) {
                String[] tile_id = line.split(",");
                if (colCount == 0) {
                    colCount = tile_id.length;
                } else if (colCount != tile_id.length) {
                    break;
                } else {
                    for (int i = 0; i < tile_id.length; i++) {
                        tempArr.get(rowCount).add(Integer.parseInt(tile_id[i]));
                    }
                }
                rowCount++;
            }

            mapData = new int[tempArr.size()][tempArr.get(0).size()];
            for (int i = 0; i < mapData.length; i++) {
                for (int j = 0; j < mapData[i].length; j++) {
                    mapData[i][j] = tempArr.get(i).get(j);
                }
            }
            br.close();
            return mapData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapData;
    }
}

// class CSVReaderTest {
// public static final String delimiter = ",";

// public static void read(String csvFile) {
// try {
// File file = new File(csvFile);
// FileReader fr = new FileReader(file);
// BufferedReader br = new BufferedReader(fr);
// String line = "";
// String[] tempArr;
// while((line = br.readLine()) != null) {
// tempArr = line.split(delimiter);
// for(String tempStr : tempArr) {
// System.out.print(tempStr + " ");
// }
// System.out.println();
// }
// br.close();
// } catch(IOException ioe) {
// ioe.printStackTrace();
// }
// }

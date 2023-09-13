package util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    public static int[][] getLevelData(String file) {
        int[][] mapData = null;

        try {
            ArrayList<ArrayList<Integer>> tempArr = new ArrayList<>();
            InputStream is = LoadSave.class.getResourceAsStream("/img/map/" + file);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = "";

            int colCount = 0;
            while ((line = br.readLine()) != null) {
                String[] tile_id = line.split(",");
                if (colCount == 0) {
                    colCount = tile_id.length;
                } else if (colCount != tile_id.length) {
                    break;
                }
                ArrayList<Integer> rowValues = new ArrayList<>();
                for (int i = 0; i < tile_id.length; i++) {
                    rowValues.add(Integer.parseInt(tile_id[i]));
                }

                tempArr.add(rowValues);
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

package util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import Levels.Level;

public class LoadSave {

    public static String PLAYER_ATLAS = "rogue.png";
    public static String LEVEL_ATLAS = "magecity.png";

    public static BufferedImage GetSpriteAtlas(String file) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/res/entity/" + file);

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
        InputStream is = LoadSave.class.getResourceAsStream("/res/tile set/" + file);

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
            InputStream is = LoadSave.class.getResourceAsStream("/res/map/" + file);
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

    public static String[] getFileList(String path) {
        // Specify the folder path (relative to the "src" directory)
        String folderPath = path; // Change this if the folder is in a different location

        // Create a Path object for the folder
        Path folder = Paths.get("src/res/map", folderPath);

        try {

            // Create a DirectoryStream to list files in the folder
            try (java.nio.file.DirectoryStream<Path> stream = Files.newDirectoryStream(folder)) {
                List<String> fileList = new ArrayList<>();
                for (Path file : stream) {
                    fileList.add(file.getFileName().toString());

                }

                return fileList.toArray(new String[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Level[] getLevelLeyerData(String mapName) {
        String[] fileArr = getFileList(mapName);
        for (String string : fileArr) {
            System.out.println("layer " + string + " loaded");
        }
        Level[] lvlLayer = new Level[fileArr.length];
        String[] layer_order_string = { "ground", "wall", "front", "collision" };
        for (int i = 0; i < 4; i++) {
            lvlLayer[i] = new Level(getLevelData(mapName + "/" + mapName + "_" + layer_order_string[i] + ".csv"));
        }
        return lvlLayer;
    }

    public static Font GetFont(int size) {
        Font font = new Font("Arial", Font.TRUETYPE_FONT, size);
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/asset/Oleaguid.ttf"));
            font = font.deriveFont(Font.PLAIN, size);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        return font;
    }

    public static BufferedImage GetImage(String file) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/res/" + file);

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

    public static BufferedImage[] LinearAnimationLoader(String name, int sizePerFrame) {
        BufferedImage img = GetImage(name);
        int imgHeight = img.getHeight() / sizePerFrame;
        int imgWidth = img.getWidth() / sizePerFrame;
        BufferedImage[] animation = new BufferedImage[imgHeight * imgWidth];

        for (int i = 0; i < imgHeight; i++) {
            for (int j = 0; j < imgWidth; j++) {
                int index = (i * imgWidth) + j;
                animation[index] = img.getSubimage(sizePerFrame * j, sizePerFrame * i, sizePerFrame, sizePerFrame);
            }
        }

        return animation;
    }
}

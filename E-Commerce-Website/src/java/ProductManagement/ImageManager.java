package ProductManagement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;

/**
 *
 * @author HP
 */
public class ImageManager {

    private final String PRODUCT_IMAGES_PATH = "C:/Users/HP/Desktop/Git/Personal/DEA-Project/E-Commerce-Website/web/images/product_images/";

    public boolean storeImage(int newId, Part imagePart) {
        // Save the image to the server
        String imagePath = PRODUCT_IMAGES_PATH + newId + ".png";
        boolean status = false;
        try {
            FileOutputStream fos = new FileOutputStream(imagePath);
            InputStream is = imagePart.getInputStream();
            byte[] data = new byte[is.available()];
            is.read(data);
            fos.write(data);
            is.close();
            fos.close();
            status = true;
        } catch (IOException e) {
            Logger.getLogger(CreateProductServlet.class.getName()).log(Level.SEVERE, "Error writing image to disk", e);
        }
        return status;
    }

    public boolean updateImage(int pId, Part imagePart) {

        String imageName = pId + ".png";
        boolean fileUpdateStatus = false;
        // Delete the image file from the filesystem
        boolean fileDeleteStatus = false;
        File imageFile = new File(PRODUCT_IMAGES_PATH, imageName);
        if (imageFile.exists()) {
            try {
                fileDeleteStatus = Files.deleteIfExists(imageFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (fileDeleteStatus) {
            // Update the image to the server
            String imagePath = PRODUCT_IMAGES_PATH + pId + ".png";
            try {
                FileOutputStream fos = new FileOutputStream(imagePath);
                InputStream is = imagePart.getInputStream();
                byte[] data = new byte[is.available()];
                is.read(data);
                fos.write(data);
                is.close();
                fos.close();
                fileUpdateStatus = true;
            } catch (IOException e) {
                // Handle IO exception
                Logger.getLogger(CreateProductServlet.class.getName()).log(Level.SEVERE, "Error writing image to disk", e);
            }
        }
        return fileUpdateStatus;
    }

    public boolean deleteImage(int pId) {
        // Delete the image file from the filesystem
        String imageName = pId + ".png";
        boolean fileDeleteStatus = false;
        File imageFile = new File(PRODUCT_IMAGES_PATH, imageName);
        if (imageFile.exists()) {
            try {
                fileDeleteStatus = Files.deleteIfExists(imageFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileDeleteStatus;
    }
}

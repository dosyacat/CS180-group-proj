import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class Picture {
    public static byte[] uploadPicture() {
        byte[][] fileBytes = new byte[1][];
        try {
            EventQueue.invokeAndWait(() -> {
                while (true) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                    FileNameExtensionFilter filter = new FileNameExtensionFilter(
                            "Image files", "jpg", "jpeg", "png", "bmp");
                    fileChooser.setFileFilter(filter);

                    int result = fileChooser.showOpenDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        try {
                            byte[] bytes = Files.readAllBytes(selectedFile.toPath());
                            BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes));
                            if (image != null) {
                                fileBytes[0] = bytes;
                                break;
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "The selected file is not a valid image. Please select an image file.");
                            }
                        } catch (IOException e) {
                            System.err.println("Error reading file: " + selectedFile.getAbsolutePath());
                            System.out.println("The selected file is not a valid image. Please select an image file.");
                        }
                    } else {
                        System.out.println("No file selected.");
                        break;
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileBytes[0];
    }

    public static void displayPicture(byte[] imageData) {
        SwingUtilities.invokeLater(() -> {
            BufferedImage originalImage = null;
            try {
                originalImage = ImageIO.read(new ByteArrayInputStream(imageData));
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Can't load the image");
                return;
            }

            int targetWidth = 1920;
            int targetHeight = 1080;
            BufferedImage scaledImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = scaledImage.createGraphics();

            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
            g2d.dispose();

            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            JFrame frame = new JFrame("Image Display");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(imageLabel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

}

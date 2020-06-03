import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PDFtoJPEG {
    private final static Logger LOGGER = Logger.getLogger(PDFtoJPEG.class.getName());

    void pdfToImage(String pdfPath) {
        try {
            LOGGER.info("Started printing " + pdfPath);
            PDDocument doc = PDDocument.load(new File(pdfPath));
            PDFRenderer pdfRenderer = new PDFRenderer(doc);
            for (int page = 0; page < doc.getNumberOfPages(); ++page){
                LOGGER.info("Started page: " + page);
                BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
                String fileName = pdfPath.substring(0, pdfPath.length()-3)+"png";
                ImageIOUtil.writeImage(bim, fileName, 300);
            }
            doc.close();
            LOGGER.info("Printing ended");
        } catch (IOException e) {
            LOGGER.warning("Exception here: " + e.getMessage());
        }
    }

    void pdfToImageAllFilesInFolder(String folderPath) throws IOException{
        Path path = Paths.get(folderPath);
        Stream<Path> subPath = Files.walk(path, 1);
        List<Path> list = subPath.collect(Collectors.toList());
        for (Path p: list) {
            pdfToImage(p.toString());
        }
    }
}

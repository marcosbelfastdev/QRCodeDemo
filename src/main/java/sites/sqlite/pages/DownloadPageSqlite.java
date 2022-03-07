package sites.sqlite.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sites.sqlite.attributes.HomePageSqliteAttributes;

import java.io.File;
import java.util.*;

import static java.util.Objects.isNull;

public class DownloadPageSqlite extends HomePageSqliteAttributes {

    public DownloadPageSqlite(WebDriver driver) {
        super(driver);
    }

    public void navegar(String url) {
        $driver.get(url);
    }

    public void downloadFile() {
        List<File> downloadedFiles =
        download(
                elementFactory.getInteractableWebElement(By.linkText("2021.3.2 - Linux (tar.gz)")),
                "tar.gz"
        );
    }

    public List<File> download(WebElement link, String partialFileName) {
        return download(link, partialFileName, 0);
    }

    public List<File> download(WebElement link, String partialFileName, int additionalTimeout) {

        File downloadFolder = new File("/Users/mark/Downloads/");
        additionalTimeout = Math.max(0, additionalTimeout);

        List<File> filesBefore = getFilesInDirectory(downloadFolder, partialFileName);

        link.click();

        // Aguardamos o download finalizar ou até expirar o tempo
        List<File> filesAfter = new ArrayList<>();
        Tempo tempo = new Tempo((30 + additionalTimeout)*1000L);
        while(tempo.resta()) {
            filesAfter.clear();
            filesAfter.addAll(getFilesInDirectory(downloadFolder, partialFileName));
            if (filesAfter.size() > filesBefore.size()) {
                break;
            }
            tempo.dormir(1000);
        }

        filesAfter.removeAll(filesBefore);
        return filesAfter;
    }

    public List<File> getFilesInDirectory(File directory, String partialFileName) {

        if (!directory.isDirectory())
            return new ArrayList<>();

        List<File> matchingFiles = new ArrayList<>();
        List<File> files = new ArrayList<>(Arrays.asList(Objects.requireNonNull(directory.listFiles())));

        for (File file : files) {
            String fileName = file.getName().toLowerCase(Locale.ROOT);
            if (fileName.contains(partialFileName.toLowerCase(Locale.ROOT))) {
                if (!fileName.contains(".crdownload"))
                    matchingFiles.add(file);            }

        }

        return matchingFiles;
    }


    public List<File> deleteFiles(List<File> files, boolean ignoreErrors) {
        for (File file : files) {
            try {
                file.delete();
            } catch (Exception e) {
                if (!ignoreErrors)
                    System.out.println(e.getMessage());
            }
        }
        return files;
    }

    public class Tempo {
        Long tempo;
        Long horaInicio;
        public Tempo(long tempo) {
            this.tempo = tempo;
        }

        public Boolean resta() {
            if (isNull(horaInicio)) {
                horaInicio = System.currentTimeMillis();
                return true;
            }

            if (getTempoDecorrido() >= tempo) {
                return false;
            }

            return true;
        }

        public Long getTempoDecorrido() {
            return System.currentTimeMillis() - horaInicio;
        }

        public void dormir(long dormir) {
            try {
                Thread.sleep(dormir);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}

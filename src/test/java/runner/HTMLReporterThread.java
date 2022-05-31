package runner;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import utils.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HTMLReporterThread extends Thread{

    private static final Logger LOG = Logger.getLogger(HTMLReporterThread.class);

    @Override
    public void run(){

    }

    private void createHTMLReportInZFolder(){
        File zfolder = new File(FileUtil.getCucumberReportsDirectory() + "\\zRun");
        List<String> jsonFiles = new ArrayList<String>();
        jsonFiles.add(zfolder.getAbsolutePath() + File.separator + "cucumber.json");

        String buildNumber = "948";
        String projectName = "Trade Automation";
        boolean runWithJenkins = false;

        Configuration configuration = new Configuration(zfolder, projectName);
        configuration.setRunWithJenkins(runWithJenkins);
        configuration.setBuildNumber(buildNumber);

        configuration.addClassifications("Platform", "Windows");
        configuration.addClassifications("Release/Iteration", "Release 14.2/Itertion 128");

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }

    private void backUpReports(){
        File reportsDir = new File(FileUtil.getCucumberReportsDirectory());
        File zfolder = new File(FileUtil.getCucumberReportsDirectory() + "\\zRun");
        try{
            FileUtils.copyDirectory(zfolder, createNewRunFolder(reportsDir));
        } catch (IOException e){

        }
    }

    private File createNewRunFolder(File reportsDir){
        int size = reportsDir.listFiles().length;

        File newFolder = new File(reportsDir + "\\Run_" + (size-1));
        newFolder.mkdir();
        return newFolder;
    }
}

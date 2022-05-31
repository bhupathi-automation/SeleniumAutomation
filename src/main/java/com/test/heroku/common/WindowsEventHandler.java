package com.test.heroku.common;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

public class WindowsEventHandler {

    public static void copyIntoClipboard(String filePath){
        StringSelection fileName = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(fileName, null);
    }

    public static void pasteFromClipboard(Robot robot) throws InterruptedException {
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(2000);
    }

    public static void clickEnterOnFileUploadPopUp(Robot robot) throws InterruptedException {
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
    }

    private static Robot getRobot(){
        Robot robot = null;

        try{
            robot = new Robot();
            robot.setAutoDelay(200);
            return robot;
        } catch (AWTException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
            return null;
        }
    }

    public static void enterPathInChooseFileToUploadPopUp(String filePath) throws InterruptedException {
        copyIntoClipboard(filePath);

        File file = new File(filePath);
        String folder = file.getParent();
        String fileName = file.getName();

        Robot robot = getRobot();

        pasteFromClipboard(robot);
        clickEnterOnFileUploadPopUp(robot);
    }

    private static void closeInternetExplorerWindow(Robot robot) throws InterruptedException {
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_F4);
        robot.keyRelease(KeyEvent.VK_F4);
        robot.keyRelease(KeyEvent.VK_ALT);
    }

}

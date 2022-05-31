package com.test.heroku.ObjectRepo;

import org.openqa.selenium.By;

public class FileUploaderUIObject {

    public static By CHOOSE_FILE_BUTTON = By.id("file-upload");
    public static By UPLOAD_BUTTON = By.id("file-submit");
    public static By UPLOAD_SUCESS_TEXT = By.xpath("//*[text()='File Uploaded!']");
    public static By UPLOADED_FILE_NAME = By.id("uploaded-files");
}

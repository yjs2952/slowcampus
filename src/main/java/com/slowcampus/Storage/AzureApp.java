// MIT License
// Copyright (c) Microsoft Corporation. All rights reserved.
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE

package com.slowcampus.Storage;


import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.*;
import lombok.extern.java.Log;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Scanner;

/* *************************************************************************************************************************
 * Summary: This application demonstrates how to use the Blob Storage service.
 * It does so by creating a container, creating a file, then uploading that file, listing all files in a container,
 * and downloading the file. Then it deletes all the resources it created
 *
 * Documentation References:
 * Associated Article - https://docs.microsoft.com/en-us/azure/storage/blobs/storage-quickstart-blobs-java
 * What is a Storage Account - http://azure.microsoft.com/en-us/documentation/articles/storage-whatis-account/
 * Getting Started with Blobs - http://azure.microsoft.com/en-us/documentation/articles/storage-dotnet-how-to-use-blobs/
 * Blob Service Concepts - http://msdn.microsoft.com/en-us/library/dd179376.aspx
 * Blob Service REST API - http://msdn.microsoft.com/en-us/library/dd135733.aspx
 * *************************************************************************************************************************
 */
@Log
public class AzureApp
{
    /* *************************************************************************************************************************
     * Instructions: Update the storageConnectionString variable with your AccountName and Key and then run the sample.
     * *************************************************************************************************************************
     */
    public static final String storageConnectionString =
            "DefaultEndpointsProtocol=https;" +
                    "AccountName=slowcampus;" +
                    "AccountKey=5n66oS54rvE8IG8A+WT8xtSUgMCfrnEu5mDZ1frJxAIyF7MkFSkhalgLwIZEEoV+Bfw381DpvDWasA79gM6xQA==;EndpointSuffix=core.windows.net";


    public void execAzure(String originalName, byte[] fileData)
    {
        File sourceFile = null, downloadedFile = null;
        System.out.println("Azure Blob storage quick start sample");

        CloudStorageAccount storageAccount;
        CloudBlobClient blobClient = null;
        CloudBlobContainer container=null;

        try {
            // Parse the connection string and create a blob client to interact with Blob storage
            storageAccount = CloudStorageAccount.parse(storageConnectionString);
            blobClient = storageAccount.createCloudBlobClient();
            container = blobClient.getContainerReference("quickstartcontainer");

            // Create the container if it does not exist with public access.
            System.out.println("Creating container: " + container.getName());
            container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(), new OperationContext());
            System.out.println("Creating container!!!!");
            //Creating a sample file
            //sourceFile = File.createTempFile("sampleFile", ".txt");
            //sourceFile = File.createTempFile("sampleFile",Long.toString(System.nanoTime()));
            //File sourceFolder = new File("folder1");
            //sourceFile = new File(sourceFolder,"twice.txt");

            // /Users/choijaeyong/Downloads
            // File examFile = new File("/Users/choijaeyong/Downloads/twicesana.jpg");

            String savedName =  originalName;

            File dir = new File("/Users/choijaeyong/fastcampus/slowcampus");
            String path = dir.toString();


            Calendar cal2 = Calendar.getInstance();
            String yearPath2 = String.valueOf(cal2.get(Calendar.YEAR));
            String monthPath2 = yearPath2 + "_" +
                    new DecimalFormat("00").format(cal2.get(Calendar.MONTH)+1);
            String datePath2 = monthPath2 + "_" +
                    new DecimalFormat("00").format(cal2.get(Calendar.DATE));

            String urlPath = datePath2;

            System.out.println("path=path+calc() 전에 : " + path);
            path = path + calcPath(path);
            System.out.println("path=path+calc() 후에 : " + path);
            sourceFile = new File(path , savedName);

            FileCopyUtils.copy(fileData,sourceFile);

            System.out.println(sourceFile.length());

            System.out.println("urlPath ======== " + urlPath);
            System.out.println("path    ======== " + path);



            //sourceFile = new File("jaeyong");
            //File sourceFile2 = new File(sourceFile,"sampletxt.txt" );

            System.out.println("Creating a sample file at: " + sourceFile.toString());

//            Writer output = new BufferedWriter(new FileWriter(sourceFile2));
//            output.write("Hello Azure!");
//            output.close();

            System.out.println("target.getName() : "+ sourceFile.getName());
            System.out.println("target.toString() : " + sourceFile.toString());

            //Getting a blob reference
            // url 경로에 나타날 부분?
            // File() 로 만들면 폴더 뒤에 / 가 붙는데. 그냥 더하니까 안붙길래
            // '/' 하나 추가해준다
            String url = urlPath + File.separator +sourceFile.getName();
            System.out.println("url :   =========  " + url);

            CloudBlockBlob blob = container.getBlockBlobReference(url);

            //Creating blob and uploading file to it
            System.out.println("Uploading the sample file ");

            System.out.println("======target.getAbsolutePath() : " + sourceFile.getAbsolutePath());
            // temp 파일 경로를 지정해줘야한다.??
            blob.uploadFromFile(sourceFile.getAbsolutePath());






            //Listing contents of container
            for (ListBlobItem blobItem : container.listBlobs()) {
                System.out.println("URI of blob is: " + blobItem.getUri());
            }

        }
        catch (StorageException ex)
        {
            System.out.println(String.format("Error returned from the service. Http code: %d and error code: %s", ex.getHttpStatusCode(), ex.getErrorCode()));
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {


            if(downloadedFile != null)
                downloadedFile.deleteOnExit();

            if(sourceFile != null)
                sourceFile.deleteOnExit();
        }
    }


    private String calcPath(String uploadPath) {
        Calendar cal = Calendar.getInstance();
        String yearPath = File.separator+cal.get(Calendar.YEAR);
        String monthPath = yearPath + "_" +
                new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
        String datePath = monthPath + "_" +
                new DecimalFormat("00").format(cal.get(Calendar.DATE));

        //makeDir(uploadPath,yearPath,monthPath,datePath);
        File dirPath = new File(uploadPath + datePath);
        if(!dirPath.exists()){
            dirPath.mkdir();
        }
        //log.info(datePath);


        return datePath;
    }


    private void makeDir(String uploadPath , String... paths) {
        if(new File(uploadPath + paths[paths.length -1]).exists()) {
            return;
        }

        for(String path : paths) {
            File dirPath = new File(uploadPath + path);

            if(! dirPath.exists()) {
                dirPath.mkdir();
            }

        }

    }
}

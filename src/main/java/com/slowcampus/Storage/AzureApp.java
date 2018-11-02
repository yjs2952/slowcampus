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

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;

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


    public void execAzure(String url,String sourceFilePath)
    {
        File sourceFile = null, downloadedFile = null;
        String path = null;
        File deleteDir=null;
        System.out.println("Azure Blob Storage quick start sample");

        CloudStorageAccount storageAccount;
        CloudBlobClient blobClient = null;
        CloudBlobContainer container=null;

        try {
            // Parse the connection string and create a blob client to interact with Blob Storage
            storageAccount = CloudStorageAccount.parse(storageConnectionString);
            blobClient = storageAccount.createCloudBlobClient();
            container = blobClient.getContainerReference("quickstartcontainer");

            // Create the container if it does not exist with public access.
            System.out.println("Creating container: " + container.getName());
            container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(), new OperationContext());
            //Creating a sample file


            // 파일이름 난수 생성부분
            /*
            UUID uid = UUID.randomUUID();
            String savedName = uid.toString() + "_" + originalName;
            File target = new File(uploadPath,savedName);
            FileCopyUtils.copy(fildData,target);
             */




            // 임시 저장할 폴더를 생성.(여기에선 프로젝트 내 경로. 경로 마지막에 / 빼기.)
            //File dir = new File(".");
            //File dir = new File("/Users/choijaeyong/fastcampus/slowcampus");

            // 옮기기 작업 ========================================
//            File dir = new File("/tmp");
//            System.out.println("최초 File('.') 했을때! : " + dir.getAbsolutePath());
//            path = dir.getAbsolutePath();

            /*
            <img src="https://slowcampus.blob.core.windows.net/quickstartcontainer/2018_10_30/dahyun.jpg"/>
             */

//            Calendar cal2 = Calendar.getInstance();
//            String yearPath2 = String.valueOf(cal2.get(Calendar.YEAR));
//            String monthPath2 = yearPath2 + "_" +
//                    new DecimalFormat("00").format(cal2.get(Calendar.MONTH)+1);
//            String datePath2 = monthPath2 + "_" +
//                    new DecimalFormat("00").format(cal2.get(Calendar.DATE));

            // datePath=/2018_10_30
            // 옮기기 작업 ========================================
            //String datePath = calcPath(path);


            // datePath : /2018_10_30
            // path : /Users/choijaeyong/fastcampus/slowcampus
            // path 는 임시로 파일이 생성되어야 할 장소.
            // 옮기기 작업 ========================================
            //path = path + datePath;
            // path : /Users/choijaeyong/fastcampus/slowcampus/2018_10_30

//           File.createTempFile() 써보려 했는데 안되네!.
//            String[] strArr = originalName.split(".");
//            String fileName = strArr[0];
//            String fileExt = strArr[1];

            // 옮기기 작업 ========================================
//            UUID uid = UUID.randomUUID();
//            String savedName = uid.toString() + "_" + originalName;

//           File.createTempFile() 써보려 했는데 안되네!.
//            File dirPath = new File(path);
//            fileName = uid+"_"+fileName;
//            fileExt = "."+fileExt;
//            System.out.println(fileName);
//            System.out.println(fileExt);
//            System.out.println(dirPath.getAbsolutePath());

            // 파일 생성!.
            //sourceFile = new File(path , savedName);
//            sourceFile = File.createTempFile(uid+"_"+fileName , fileExt, dirPath);
//            System.out.println("createTempFile : " + sourceFile.getName()  + "    path : " + sourceFile.getAbsolutePath());

            // 올리려는 이미지를 새 파일에 복사.
            //FileCopyUtils.copy(fileData,sourceFile);

            //System.out.println("path ===============    " + path);

            //System.out.println("Creating a sample file at: " + sourceFile.toString());

            //Getting a blob reference
            // url 경로에 나타날 부분?
            // File() 로 만들면 폴더 뒤에 / 가 붙는데. 그냥 더하니까 안붙길래
            // '/' 하나 추가해준다
            // url : 2018_10_30/chaeyoung.jpg
            //String url = datePath.substring(1) + File.separator +sourceFile.getName();

            // http://~~~~~~/2018_10_30/dddd.jpg 이런식으로 붙는다.
            // 파라미터로 넣어줘야 하는 값에서 맨 앞에 '/'가 없어야 한다.
            // 있으면 폴더안에 값이 안들어가고 폴더랑 파일이 따로 만들어지라구~
            CloudBlockBlob blob = container.getBlockBlobReference(url);

            //Creating blob and uploading file to it
            System.out.println("Uploading the sample file ");

            // temp 파일 경로를 지정해줘야한다.??
            // sourceFile.getAbsolutePath() : /Users/choijaeyong/fastcampus/slowcampus/2018_10_30/chaeyoung.jpg
            blob.uploadFromFile(sourceFilePath);


            // 폴더 지우기용 코드.
//            path = path + File.separator;
//            System.out.println("path : " + path);
//            deleteDir = new File(path);
//            System.out.println("deleteDir.getAbsolutePath() : "+deleteDir.getAbsolutePath());





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

//            if(sourceFile != null){
//                // 이게 없으면 파일이 안지워진다.
//                System.out.println("파일 삭제전");
//                System.out.println("파일삭제 if 문 안에 있는 코드 : "+sourceFile.getAbsolutePath());
//                sourceFile.deleteOnExit();
//                sourceFile.deleteOnExit();
//                System.out.println("파일 삭제후");
//            }


            // 임시로 만들어진 폴더 지우기용
            // 여기서 이 path는 빈폴더야!. 안에 있는 jpg 파일. 위에 if문에서
            // 지웠어!. 근데 왜 안돼!?
            // 위에 파일 삭제 하는 코드 보다
            // 아래 폴더 삭제 명령어가 먼저 읽힌다. (deleteDir.delete())로 했을 시에.
            // 삭제 명령어는 폴더 안에 파일이 있을시엔 삭제가 안된다.
            // 근데 파일 삭제는 톰캣을 stop 했을 시에!  진행이 된다... 아... ?
            if(path != null){
//                System.out.println("폴더 삭제 될까??");
//                System.out.println(deleteDir.delete());   // false 나옴... ㅡㅡ;
//                deleteDir.deleteOnExit();
//                System.out.println("폴더 삭제 코드 후");
            }



        }
    }


    public static String calcPath(String uploadPath) {
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
        //폴더만 만들고 리턴값은 /년도/월/일 이렇게 리턴한다.
        return datePath;
    }



}

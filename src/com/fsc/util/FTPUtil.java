package com.fsc.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Date;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:FTP工具类</p>
 * <p>创建日期:Aug 3, 2011</p>
 * @author tbw
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class FTPUtil {
    FTPClient ftpClient;

    /**
     * 登录FTP服务器
     * @param server
     * @param port
     * @param user
     * @param password
     * @throws Exception
     */
    public void connect(String server, Integer port, String user,
        String password) throws Exception {
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("gbk");
        ftpClient.setConnectTimeout(5000);
        ftpClient.setDataTimeout(100000);
        ftpClient.connect(server, port);
        ftpClient.enterLocalPassiveMode();

        boolean flag = ftpClient.login(user, password);

        if (flag) {
            System.out.println("ftp -- login success !!! ");
        } else {
            throw new Exception("ftp -- login failed! user or pwd wrong!");
        }
    }

    /**
     * 关闭FTP连接
     */
    public void close() {
        try {
            ftpClient.disconnect();
            System.out.println("ftp disconnect success !!! ");
        } catch (IOException e) {
            System.out.println("ftp not disconnect !!! ");
            e.printStackTrace();
        }
    }

    /**
     * 上传文件或文件夹
     * @param uploadFilePath
     * @param destPath
     * @throws Exception
     */
    public void upload(String localPath, String ftpPath)
        throws Exception {
        boolean flag;
        FileInputStream fis = null;

        try {
            changeWorkDir(ftpPath);

            File uploadFile = new File(localPath);
            File[] fileList;

            if (uploadFile.isDirectory()) {
                fileList = uploadFile.listFiles();
            } else {
                fileList = new File[1];
                fileList[0] = uploadFile;
            }

            if (fileList == null) {
                return;
            }

            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) {
                    String destFolerPath = fixPath(ftpPath + "/" +
                            fileList[i].getName());
                    ftpClient.makeDirectory(destFolerPath);
                    upload(fileList[i].getAbsolutePath(), destFolerPath);
                } else {
                    fis = new FileInputStream(fileList[i]);

                    String destinationFileName = fileList[i].getName();

                    String tempFileName = " temp_" + destinationFileName;
                    //String tempFileName = destinationFileName;
                    flag = ftpClient.storeFile(tempFileName, fis);

                    if (flag) {
                        ftpClient.rename(tempFileName, destinationFileName);
                        System.out.println("ftp -- file '" +
                            destinationFileName + "' upload success !!! ");
                    } else {
                        System.out.println("ftp -- file '" +
                            destinationFileName + "' upload failed !!! ");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("ftp -- file not upload !!! ");
            e.printStackTrace();
        } finally {
            fis.close();
        }
    }

    /**
     * 改变工作目录
     * @param destPath
     * @return
     * @throws Exception
     * @throws IOException
     */
    public boolean changeWorkDir(String destPath) throws Exception, IOException {
        if ((destPath == null) || (destPath.length() == 0)) {
            throw new Exception("ftp -- destPath is null");
        }

        boolean flag = ftpClient.changeWorkingDirectory(destPath);

        if (flag) {
            System.out.println("ftp -- set working directory to " + destPath);
        } else {
            throw new Exception("ftp -- set working directory to " + destPath +
                " failed!");
        }

        return flag;
    }

    /**
     * 下载文件或文件夹
     * @param localPath
     * @param ftpPath
     */
    public void download(String localPath, String ftpPath) {
        String name = null;

        try {
            int pos = ftpPath.lastIndexOf("/");
            String fileName = ftpPath.substring(pos + 1);
            FTPFile[] fileList;
            String destPath = ftpPath;
            int pos1 = fileName.indexOf(".");

            if (pos1 != -1) {
                destPath = ftpPath.substring(0, pos);
            }

            changeWorkDir(destPath);
            fileList = ftpClient.listFiles();

            if (pos1 != -1) {
                FTPFile singleFile = null;

                for (int i = 0; i < fileList.length; i++) {
                    if (fileName.equals(fileList[i].getName())) {
                        singleFile = fileList[i];

                        break;
                    }
                }

                fileList = new FTPFile[1];
                fileList[0] = singleFile;
            }

            for (int i = 0; i < fileList.length; i++) {
                FTPFile file = fileList[i];
                name = file.getName();

                if (".".equals(name) || "..".equals(name)) {
                    continue;
                }

                if (file.isDirectory()) {
                    String dirPath = localPath + "\\" + name;
                    String ftpDirPath = fixPath(ftpPath + "/" + name);
                    File file2 = new File(dirPath);

                    if (!file2.exists()) {
                        file2.mkdir();
                    }

                    download(dirPath, ftpDirPath);
                } else {
                    File temp_file = new File(fixPath(localPath + "\\temp_" +
                                name));
                    File dest_file = new File(fixPath(localPath + "\\" + name));
                    FileOutputStream fos = new FileOutputStream(temp_file);
                    boolean flag = ftpClient.retrieveFile(name, fos);
                    fos.flush();
                    fos.close();

                    if (flag) {
                        if (dest_file.exists()) {
                            dest_file.delete();
                        }

                        temp_file.renameTo(dest_file);
                        System.out.println("ftp -- file '" + name +
                            "' download success !!! ");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("ftp -- file '" + name + "' not download !!! ");
            e.printStackTrace();
        }
    }

    /**
     * 用本地文件夹同步FTP文件夹
     * @param sourceFolder
     * @param destFolder
     */
    public void syncFolder(String sourceFolder, String destFolder) {
        File file = new File(sourceFolder);
        File[] sourceFileList;

        if (file.isDirectory()) {
            sourceFileList = file.listFiles();
        } else {
            sourceFileList = new File[1];
            sourceFileList[0] = file;
        }

        String sourceFileName = null;

        try {
            changeWorkDir(destFolder);

            FTPFile[] destFileList = ftpClient.listFiles();

            for (int i = 0; i < sourceFileList.length; i++) {
                boolean exsist = false;
                File sourceFile = sourceFileList[i];
                sourceFileName = sourceFile.getName();

                for (int j = 0; j < destFileList.length; j++) {
                    FTPFile destFile = destFileList[j];

                    Date destFileDate = destFile.getTimestamp().getTime();

                    if (destFile.getName().equals(sourceFileName)) {
                        exsist = true;
                    }

                    if (destFile.getName().equals(sourceFileName) &&
                            (destFileDate.getTime() < sourceFile.lastModified())) {
                        System.out.println(sourceFileName + " update");
                        upload(sourceFile.getAbsolutePath(), destFolder);
                    }
                }

                if (!exsist) {
                    System.out.println(sourceFileName + " add");
                    upload(sourceFile.getAbsolutePath(), destFolder);
                }

                if (sourceFile.isDirectory()) {
                    String destPath = fixPath(destFolder + "/" +
                            sourceFileName);
                    ftpClient.makeDirectory(destPath);
                    syncFolder(sourceFile.getAbsolutePath(), destPath);
                }
            }
        } catch (Exception e) {
            System.out.println("ftp -- file '" + sourceFileName +
                "' not download !!! ");
            e.printStackTrace();
        }
    }

    /**
     * 替换ftp路径中的多余的斜杠
     * @param path
     * @return
     */
    public String fixPath(String path) {
        return path.replaceAll("//", "/"); //.replaceAll("\\\\", "\\");
    }

    public static void main(String[] args) {
        FTPUtil utils = new FTPUtil();

        try {
            utils.connect("218.77.75.165", 365, "zhw", "zhw123456");
            //utils.upload("C:\\新建文件夹","/info/20110718/");
            //utils.download("C:\\新建文件夹", "/info/20110718/");
            utils.syncFolder("C:\\新建文件夹", "/info/20110718/");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            utils.close();
        }
    }
}

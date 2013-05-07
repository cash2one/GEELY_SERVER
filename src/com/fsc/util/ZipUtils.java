package com.fsc.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


/**
 * <p>Title: 系统框架</p>
 * <p>Description:压缩文件工具类</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class ZipUtils {
    //缓存字节数 
    private static final int BUFFER = 8192;

    private static void log(String msg) {
        System.out.println(msg);
    }

    //获得文件名
    private static String getFileName(String filePath) {
        int index = filePath.indexOf(".");

        return filePath.substring(0, index);
    }

    //获得跟路径
    @SuppressWarnings("unused")
    private static String getRootPath(String filePath) {
        int index = filePath.indexOf(".");

        return filePath.substring(0, index);
    }

    /**
     * 压缩文件
     * @param sourceFilePath
     */
    public static void zip(String sourceFilePath) {
        File fileDir = new File(sourceFilePath);

        if (fileDir.exists()) {
            log(fileDir.getPath() + " Starting Zip ...");

            long startTime = System.currentTimeMillis();
            doZip(fileDir);

            long endTime = System.currentTimeMillis();
            long costTime = endTime - startTime;
            log("Zip Success!");
            log("use time -- " + costTime + " millsec!");
        } else {
            log("can't find the File!");
        }
    }

    /**
     * 解压缩
     * @param zipFilePath
     */
    public static void unZip(String zipFilePath) {
        File fileDir = new File(zipFilePath);

        if (fileDir.exists()) {
            log(fileDir.getPath() + " Starting UnZip ...");

            long startTime = System.currentTimeMillis();
            doUnZip(fileDir);

            long endTime = System.currentTimeMillis();
            long costTime = endTime - startTime;
            log("UnZip Success!");
            log("use time -- " + costTime + " millsec!");
        } else {
            log("can't find the File!");
        }
    }

    /**
     * 压缩该文件为zip类型
     * @param file
     */
    public static void doZip(File file) {
        List<File> fileList = new ArrayList<File>();
        List<File> allFiles = (ArrayList<File>) searchFiles(file.getPath(),
                fileList);

        Object[] fileArray = allFiles.toArray();

        BufferedInputStream in = null;
        FileInputStream fis = null;
        ZipOutputStream zos = null;
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file.getParent() + File.separator +
                    file.getName() + ".zip");
            zos = new ZipOutputStream(new BufferedOutputStream(fos, BUFFER));

            zos.setLevel(9);

            byte[] data = new byte[BUFFER];

            for (int i = 0; i < fileArray.length; i++) {
                // 设置压缩文件入口entry,为被读取的文件创建压缩条目
                File tempFile = new File(fileArray[i].toString());
                String rootStr = file.getPath();
                String entryStr = null;

                // entry以相对路径的形式设置。以文件夹C:\temp例如temp\test.doc或者test.xls 如果设置不当，会出现拒绝访问等错误 
                // 分别处理单个文件/目录的entry
                if (rootStr.equals(tempFile.getPath())) {
                    entryStr = tempFile.getName();
                } else {
                    entryStr = tempFile.getPath()
                                       .substring((rootStr + File.separator).length());
                }

                log(entryStr);

                ZipEntry entry = new ZipEntry(entryStr);
                zos.putNextEntry(entry);

                fis = new FileInputStream(tempFile);
                in = new BufferedInputStream(fis, BUFFER);

                int count;

                while ((count = in.read(data, 0, BUFFER)) != -1) {
                    zos.write(data, 0, count);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }

                if (zos != null) {
                    zos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *解压缩该文件
     * @param file
     */
    public static void doUnZip(File file) {
        try {
            final int BUFFER = 2048;
            BufferedOutputStream dest = null;
            FileInputStream fis = new FileInputStream(file);
            CheckedInputStream checksum = new CheckedInputStream(fis,
                    new Adler32());
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(
                        checksum));
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                log("Extracting: " + entry);

                int count;
                byte[] data = new byte[BUFFER];
                log("unzip to " + getFileName(file.getPath()));

                FileOutputStream fos = new FileOutputStream(getFileName(
                            file.getPath()) + File.separator +
                        newDir(file, entry.getName()));

                dest = new BufferedOutputStream(fos, BUFFER);

                while ((count = zis.read(data, 0, BUFFER)) != -1) {
                    dest.write(data, 0, count);
                }

                dest.flush();
                dest.close();
            }

            zis.close();
            System.out.println("Checksum: " +
                checksum.getChecksum().getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查找文件
     * @param sourceFilePath
     * @param fileList
     * @return
     */
    public static List<File> searchFiles(String sourceFilePath,
        List<File> fileList) {
        File fileDir = new File(sourceFilePath);

        if (fileDir.isDirectory()) {
            File[] subfiles = fileDir.listFiles();

            for (int i = 0; i < subfiles.length; i++) {
                searchFiles(subfiles[i].getPath(), fileList);
            }
        } else {
            fileList.add(fileDir);
        }

        return fileList;
    }

    @SuppressWarnings("static-access")
    private static String newDir(File file, String entryName) {
        String rootDir = getFileName(file.getPath());
        log("root：" + rootDir);

        int index = entryName.lastIndexOf("\\");
        String dirStr = new File(rootDir).getParent();
        log(dirStr);

        if (index != -1) {
            String path = entryName.substring(0, index);
            log("new Dir：" + rootDir + file.separator + path);
            new File(rootDir + file.separator + path).mkdirs();

            log("entry:" + entryName.substring(0, index));
        } else {
            new File(rootDir).mkdirs();
            log("entry:" + entryName);
        }

        return entryName;
    }
}

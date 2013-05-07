package com.fsc.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.jsp.PageContext;

import org.apache.struts2.ServletActionContext;


/**
 * <p>Title: 系统框架</p>
 * <p>Description:文件处理工具</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class FileUtil {
    protected ServletContext m_application;
    private boolean m_denyPhysicalPath;

    public FileUtil() {
    }

    //取得虚拟目录对应的磁盘路径
    //Web站点主目录的位置为<%=request.getRealPath("/")%>
    //JSP网页所在的目录位置<%=request.getRealPath("./")%>
    //JSP网页所在目录上一层目录的位置<%=request.getRealPath("../")%>

    /**
     * 读取文件
     * @param filePath
     * @param fileName
     * @return
     * @throws IOException
     */
    public static final String readAllFile(String filePath, String fileName)
        throws IOException {
        FileReader fr = new FileReader(filePath.trim() + fileName.trim());
        String fileContent = "";
        int count = fr.read();

        while (count != -1) {
            fileContent = fileContent + (char) count;
            count = fr.read();

            if (count == 13) {
                fr.skip(1);
            }
        }

        fr.close();

        return fileContent;
    }

    /**
    * 读取指定的文件
    * @param String strPath
    * @return byte[]
    */
    public static byte[] readFile(String strPath) {
        try {
            File file = new File(strPath);
            FileInputStream fileInputStream = new FileInputStream(file);
            int intLength = fileInputStream.available();
            byte[] bytStream = new byte[intLength];

            fileInputStream.read(bytStream);
            fileInputStream.close();

            return bytStream;
        } catch (Exception e) {
            return null;
        } finally {
        }
    }
    
    /**
     * 读取指定的文件
     * @param String strPath
     * @return byte[]
     */
     public static byte[] readFile(File file) {
         try {
             FileInputStream fileInputStream = new FileInputStream(file);
             int intLength = fileInputStream.available();
             byte[] bytStream = new byte[intLength];

             fileInputStream.read(bytStream);
             fileInputStream.close();

             return bytStream;
         } catch (Exception e) {
             return null;
         } finally {
         }
     }

    /**
     * 读取文件内容
     * @param file 文件信息
     * @return 文件内容的集合列表，文件每一行是列表的一个值
     */
    public List readFileToList(File file) {
        BufferedReader breader;
        List list;
        String line;
        list = new ArrayList();

        try {
            breader = new BufferedReader(new FileReader(file));

            while ((line = breader.readLine()) != null)
                list.add(line);

            breader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 创建一个新的文件
     * @param file 文件类型
     * @return 是否创建成功的布尔型变量
     */
    public static final boolean createNewFile(File file) {
        boolean result = false;

        if (file.exists()) {
            file.delete();
        }

        try {
            result = file.createNewFile();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return result;
    }

    /**
     * 通过文件名称创建一个新的文件
     * @param filename要创建的文件名称
     * @return 是否创建成功的布尔型变量
     */
    public static final boolean createNewFile(String filename) {
        File file = new File(filename.trim());
        boolean result = false;

        if (file.exists()) {
            file.delete();
        }

        try {
            result = file.createNewFile();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return result;
    }

    /**
     * 判断某个文件是否存在
     * @param fileName 文件名称
     * @return 是否存在的布尔型变量
     */
    public static final boolean isFileExists(String fileName) {
        File file = new File(fileName.trim());

        return file.exists();
    }

    public static final boolean isFileExists(File file) {
        return file.exists();
    }

    /**
     * 创建一个新目录
     * @file 要创建的文件目录
     * @return 是否创建成功
     */
    public static final boolean createFolder(File file) {
        boolean result = false;

        try {
            if (file.exists()) {
                file.delete();
            }

            result = file.mkdir();
            ;
        } catch (Exception ex) {
            result = false;
            ex.printStackTrace();
        }

        return result;
    }

    /**
     * 创建一个新目录
     * @folderName 要创建的文件目录名
     * @filePath 创建文件的路径
     * @return 是否创建成功
     */
    public static final boolean createFolder(String folderName) {
        boolean result = false;
        File file = new File(folderName.trim());

        try {
            if (file.exists()) {
                file.delete();
            }

            result = file.mkdir();
            ;
        } catch (Exception ex) {
            result = false;
            ex.printStackTrace();
        }

        return result;
    }

    /**
     * 复制文件内容，从一源文件复制到目标文件
     * @param sourcename 源文件
     * @param targetname 目标文件
     * @throws Exception
     */
    public void copyFile(String sourcename, String targetname)
        throws Exception {
        BufferedReader breader;
        BufferedWriter bwriter;

        try {
            breader = new BufferedReader(new FileReader(sourcename.trim()));
            bwriter = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(targetname.trim())));

            while (breader.ready())
                bwriter.write(breader.read());

            breader.close();
            bwriter.close();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 在文件中添加内容
     * @param 要添加的字符串
     * @param filename 目标文件名
     * @throws Exception
     */
    public void appendToFile(String str, String filename)
        throws Exception {
        FileOutputStream stream;
        OutputStreamWriter writer;

        try {
            stream = new FileOutputStream(filename.trim(), true);
            writer = new OutputStreamWriter(stream);
            writer.write(str);
            writer.close();
            stream.close();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 输出目录中的所有文件名
     * @param filePath 目录名
     * return  文件名数组
     */
    public static final String[] readPathFile(String filePath) {
        File file = new File(filePath.trim());
        File[] tempFile = file.listFiles();
        String[] filestr = new String[tempFile.length];

        for (int i = 0; i < tempFile.length; i++) {
            if (tempFile[i].isFile()) {
                filestr[i] = tempFile[i].getName();
            }
        }

        return filestr;
    }

    /**
     * 输出目录中某种文件扩展名的文件
     * @param filePath 目录路径
     * @param extension 文件扩展名
     * @return 文件名数组
     */
    public static final String[] readExtFile(String filePath, String extension) {
        File file = new File(filePath.trim());
        File[] tempFile = file.listFiles();
        String[] filestr = new String[tempFile.length];
        int ie = 0;

        for (int i = 0; i < tempFile.length; i++) {
            if (tempFile[i].isFile()) {
                String filename = tempFile[i].getName();
                ie = filename.lastIndexOf('.');

                if (filename.substring(ie, filename.length()) == extension.trim()) {
                    filestr[i] = filename;
                }
            }
        }

        return filestr;
    }

    /**
     * 输出目录中的所有文件夹名称
     * @param filePath 文件夹路径
     * @return 文件夹名称数组
     */
    public static final String[] readFolderByFile(String filePath) {
        File file = new File(filePath.trim());
        File[] tempFile = file.listFiles();
        String[] foldertr = new String[tempFile.length];
        ;

        for (int i = 0; i < tempFile.length; i++) {
            if (tempFile[i].isDirectory()) {
                foldertr[i] = tempFile[i].getName();
            }
        }

        return foldertr;
    }

    /**
     * 检查文件中是否为一个空
     * @param filePath
     * @param fileName
     * @return 为空返回true
     * @throws IOException
     */
    public static final boolean fileIsNull(File file) throws IOException {
        boolean result = false;
        FileReader fr = new FileReader(file);

        if (fr.read() == -1) {
            result = true;
        } else {
            result = false;
        }

        fr.close();

        return result;
    }

    /**
     * 一行一行的读取文件中的数据
     * @param filePath 文件路径
     * @param fileName 文件名称
     * @return 文件内容的集合。
     * @throws IOException
     */
    public ArrayList readLineFile(String filePath, String fileName)
        throws IOException {
        FileReader fr = new FileReader(filePath.trim() + fileName.trim());
        BufferedReader br = new BufferedReader(fr);
        ArrayList list = new ArrayList();
        String line = br.readLine();

        while (line != null) {
            line = br.readLine();
            list.add(line);
        }

        br.close();
        fr.close();

        return list;
    }

    /**
     * 将字符串数组写入到文件
     * @param filePath(文件路径)
     * @param fileName(文件名)
     * @param args[] 要写入的字符串数组
     * @throws IOException
     */
    public void writeFile(String filePath, String fileName, String[] args)
        throws IOException {
        FileWriter fw = new FileWriter(filePath.trim() + fileName.trim());
        PrintWriter out = new PrintWriter(fw);

        for (int i = 0; i < args.length; i++) {
            out.write(args[i]);
            out.println();
            out.flush();
        }

        fw.close();
        out.close();
    }

    /**
     * 将一字符串写入到文件
     * @param filePath(文件路径)
     * @param fileName(文件名)
     * @param args 要写入的字符串
     * @throws IOException
     */
    public void writeFile(String filePath, String fileName, String args)
        throws IOException {
        FileWriter fw = new FileWriter(filePath.trim() + fileName.trim());
        fw.write(args);
        fw.close();
    }

    /***************************************************************************
     * 取得一个文件名称的扩展名
     * @param FileName 取得扩展名的文件名称
     * return 文件的扩展名
     */
    public static final String getFileExt(String fileName) {
        int i = fileName.lastIndexOf(".");
        String fileExt = "";

        if (i != -1) {
            fileExt = fileName.substring(i + 1, fileName.trim().length());
        }

        return fileExt;
    }

    /***************************************************************************
     * 取得一个文件名称的路径
     * @param FileName 文件的路径
     */
    public static final String getFilePath(String fileName) {
        int i = fileName.lastIndexOf("/");
        String path = "";

        if (i != -1) {
            path = fileName.substring(0, i + 1);
        }

        return path;
    }

    /***************************************************************************
     * 根据文件的相对路径文件名称取得，文件的名称
     * @param FileName 文件名称
     * return相对路径文件名称
     */
    public static final String getDocFileName(String fileName) {
        int i = fileName.lastIndexOf("/");
        String file = "";

        if (i != -1) {
            file = fileName.substring(i + 1, fileName.trim().length());
        }

        return file;
    }

    /***************************************************************************
     * 取得一个新的临时文件名称，该名称不带路径
     * @param FileExt 文件的扩展名
     * 文件的扩展名
     */
    public static final String getFileName(String fileExt) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date currentTime = new Date();
        String fileName = formatter.format(currentTime);
        int i = (int) (Math.random() * 1000000000);
        String str = i + "";
        i = str.length() - 4;

        if (i < 0) {
            i = 0;
        }

        ;

        String sub = str.substring(i, str.length());
        fileName = fileName + sub;

        if (fileExt.length() > 0) {
            fileName = fileName + "." + fileExt;
        }

        return fileName;
    }

    //得到字符串中的子串的个数
    /*
     * public static int getSubtringCount(String source, String sub) { if
     * (source == null || source.length() == 0) { return 0; } int count = 0; int
     * index = source.indexOf(sub); while (index >= 0) { count++; index =
     * source.indexOf(sub, index + 1); } return count; }
     */

    //通过FILE获取文件扩展名
    public static final String getExtension(File f) {
        return (f != null) ? getExtension(f.getName()) : "";
    }

    //通过文件名获取此文件的扩展名
    public static final String getExtension(String filename) {
        return getExtension(filename, "");
    }

    public static final String getExtension(String filename, String defExt) {
        if ((filename != null) && (filename.length() > 0)) {
            int i = filename.lastIndexOf('.');

            if ((i > -1) && (i < (filename.length() - 1))) {
                return filename.substring(i + 1);
            }
        }

        return defExt;
    }

    //去除文件扩展名，返回文件名
    public static final String trimExtension(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int i = filename.lastIndexOf('.');

            if ((i > -1) && (i < (filename.length()))) {
                return filename.substring(0, i);
            }
        }

        return filename;
    }

    //java io读取文件到String
    //3个方法去读取一个大于50M的文件，当不设置jvm参数时都OutofMemery，当设置-Xmx128M时。
    //只有方法3 可以通过，设置到-Xmx256M时也只有方法3可以通过，干脆设置512M，都可以了，
    //运行时间如果正常的话一般都在4～5S 
    public static String loadAFileToStringDE1(File f) throws IOException {
        long beginTime = System.currentTimeMillis();
        InputStream is = null;
        String ret = null;

        try {
            is = new BufferedInputStream(new FileInputStream(f));

            long contentLength = f.length();
            ByteArrayOutputStream outstream = new ByteArrayOutputStream((contentLength > 0)
                    ? (int) contentLength : 1024);
            byte[] buffer = new byte[4096];
            int len;

            while ((len = is.read(buffer)) > 0) {
                outstream.write(buffer, 0, len);
            }

            outstream.close();
            ret = outstream.toString();

            //byte[] ba = outstream.toByteArray();
            //ret = new String(ba);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                }
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("方法1用时" + (endTime - beginTime) + "ms");

        return ret;
    }

    public static String loadAFileToStringDE2(File f) throws IOException {
        long beginTime = System.currentTimeMillis();
        InputStream is = null;
        String ret = null;

        try {
            is = new FileInputStream(f);

            long contentLength = f.length();
            byte[] ba = new byte[(int) contentLength];
            is.read(ba);
            ret = new String(ba);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                }
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("方法2用时" + (endTime - beginTime) + "ms");

        return ret;
    }

    public static String loadAFileToStringDE3(File f) throws IOException {
        long beginTime = System.currentTimeMillis();
        BufferedReader br = null;
        String ret = null;

        try {
            br = new BufferedReader(new FileReader(f));

            String line = null;
            StringBuffer sb = new StringBuffer((int) f.length());

            while ((line = br.readLine()) != null) {
                //sb.append(line).append(LINE_BREAK);
            }

            ret = sb.toString();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                }
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("方法3用时" + (endTime - beginTime) + "ms");

        return ret;
    }

    public final void initialize(PageContext pageContext)
        throws ServletException {
        m_application = pageContext.getServletContext();
    }

    public String getPhysicalPath(String filePathName, int option)
        throws IOException {
        String path = new String();
        String fileName = new String();
        String fileSeparator = new String();
        boolean isPhysical = false;
        fileSeparator = System.getProperty("file.separator");

        if (filePathName == null) {
            throw new IllegalArgumentException(
                "There is no specified destination file (1140).");
        }

        if (filePathName.equals("")) {
            throw new IllegalArgumentException(
                "There is no specified destination file (1140).");
        }

        if (filePathName.lastIndexOf("\\") >= 0) {
            path = filePathName.substring(0, filePathName.lastIndexOf("\\"));
            fileName = filePathName.substring(filePathName.lastIndexOf("\\") +
                    1);
        }

        if (filePathName.lastIndexOf("/") >= 0) {
            path = filePathName.substring(0, filePathName.lastIndexOf("/"));
            fileName = filePathName.substring(filePathName.lastIndexOf("/") +
                    1);
        }

        path = (path.length() != 0) ? path : "/";

        java.io.File physicalPath = new java.io.File(path);

        if (physicalPath.exists()) {
            isPhysical = true;
        }

        if (option == 0) {
            if (isVirtual(path)) {
                path = m_application.getRealPath(path);

                if (path.endsWith(fileSeparator)) {
                    path = path + fileName;
                } else {
                    path = String.valueOf((new StringBuffer(String.valueOf(path))).append(
                                fileSeparator).append(fileName));
                }

                return path;
            }

            if (isPhysical) {
                if (m_denyPhysicalPath) {
                    throw new IllegalArgumentException(
                        "Physical path is denied (1125).");
                } else {
                    return filePathName;
                }
            } else {
                throw new IllegalArgumentException(
                    "This path does not exist (1135).");
            }
        }

        if (option == 1) {
            if (isVirtual(path)) {
                path = m_application.getRealPath(path);

                if (path.endsWith(fileSeparator)) {
                    path = path + fileName;
                } else {
                    path = String.valueOf((new StringBuffer(String.valueOf(path))).append(
                                fileSeparator).append(fileName));
                }

                return path;
            }

            if (isPhysical) {
                throw new IllegalArgumentException(
                    "The path is not a virtual path.");
            } else {
                throw new IllegalArgumentException(
                    "This path does not exist (1135).");
            }
        }

        if (option == 2) {
            if (isPhysical) {
                if (m_denyPhysicalPath) {
                    throw new IllegalArgumentException(
                        "Physical path is denied (1125).");
                } else {
                    return filePathName;
                }
            }

            if (isVirtual(path)) {
                throw new IllegalArgumentException(
                    "The path is not a physical path.");
            } else {
                throw new IllegalArgumentException(
                    "This path does not exist (1135).");
            }
        } else {
            return null;
        }
    }

    private boolean isVirtual(String pathName) //判断是否是虚拟路径
     {
        if (m_application.getRealPath(pathName) != null) {
            java.io.File virtualFile = new java.io.File(m_application.getRealPath(
                        pathName));

            return virtualFile.exists();
        } else {
            return false;
        }
    }

    /**
     * 列出某文件夹及其子文件夹下面的文件，并可根据扩展名过滤
     * @param path 文件夹
     */
    public static void list(File path) {
        if (!path.exists()) {
            System.out.println("文件名称不存在!");
        } else {
            if (path.isFile()) {
                if (path.getName().toLowerCase().endsWith(".pdf") ||
                        path.getName().toLowerCase().endsWith(".doc") ||
                        path.getName().toLowerCase().endsWith(".chm") ||
                        path.getName().toLowerCase().endsWith(".html") ||
                        path.getName().toLowerCase().endsWith(".htm")) { // 文件格式
                    System.out.println(path);
                    System.out.println(path.getName());
                }
            } else {
                File[] files = path.listFiles();

                for (int i = 0; i < files.length; i++) {
                    list(files[i]);
                }
            }
        }
    }

    /**
     * 功能描述：拷贝一个目录或者文件到指定路径下，即把源文件拷贝到目标文件路径下
     * @param source 源文件
     * @param target 目标文件路径
     * @return void
     */
    public static void copy(File source, File target) {
        File tarpath = new File(target, source.getName());

        if (source.isDirectory()) {
            tarpath.mkdir();

            File[] dir = source.listFiles();

            for (int i = 0; i < dir.length; i++) {
                copy(dir[i], tarpath);
            }
        } else {
            try {
                InputStream is = new FileInputStream(source); // 用于读取文件的原始字节流
                OutputStream os = new FileOutputStream(tarpath); // 用于写入文件的原始字节的流
                byte[] buf = new byte[1024]; // 存储读取数据的缓冲区大小
                int len = 0;

                while ((len = is.read(buf)) != -1) {
                    os.write(buf, 0, len);
                }

                is.close();
                os.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        FileUtil fu = new FileUtil();
        PageContext pageContext = null;
        String fp = "";

        try {
            fu.initialize(pageContext);
            fp = fu.getPhysicalPath("/", 0);
        } catch (Exception ex) {
        }

        System.out.println("fp:::" + fp);
    }

    /**
     * 从文本文件中读取所有的行。
     * @param path 文件全路径。
     * @return 返回以集合列表形式存储的所有行。
     * @throws IOException 读取文件有误时抛出。
     */
    public static List<String> readLines(String path) throws IOException {
        List<String> lines = new ArrayList<String>(); // 用于存放读出的字符列表。

        String line; // 读取到的每行数据。

        BufferedReader br = new BufferedReader(new FileReader(path)); // 输入流。

        while ((line = br.readLine()) != null) { // 循环读取文件。
            lines.add(line);
        }

        close(br);

        return lines;
    }

    /**
     * 将字符串数组按行写入文件，如果文件已经存在将被覆盖。
     * @param path 写入的文件路径。
     * @param line 需要写入的数据
     * @throws java.io.IOException 写入文件有误时抛出。
     */
    public static void writeLines2(String path, List<String> lines)
        throws IOException {
        FileWriter fw;

        try {
            fw = new FileWriter(new File(path), true); //此项为true是关键,若不填,评测将会用一空白文件覆盖原件  

            for (String line : lines) {
                fw.write(line + "\n");
            }

            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将字符串数组按行写入文件，如果文件已经存在将被覆盖。
     * @param path 写入的文件路径。
     * @param lines 需要写入的数据集合。
     * @throws java.io.IOException 写入文件有误时抛出。
     */
    public static void writeLines(String path, List<String> lines)
        throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(path));

        for (String line : lines) {
            bw.write(line);
            bw.newLine();
        }

        close(bw);
    }

    /**
     * 打开文件并得到BufferedReader。
     * @param file 要打开的文件的全路径。
     * @return BufferedReader 如果文件不存在，返回null
     * @throws java.io.IOException 读取文件有误时抛出
     */
    public static BufferedReader getReader(File file) throws IOException {
        if (isFile(file)) {
            return new BufferedReader(new FileReader(file));
        }

        return null;
    }

    /**
     * 打开文件，如果文件不存在，自动创建，并得到BufferedWriter。
     * @param file 文件
     * @param allowOverwrite 是否运行覆盖旧文件
     * @return BufferedWriter，如果文件存在但不运行覆盖，返回null
     * @throws java.io.IOException 读取或创建新文件有误时抛出
     */
    public static BufferedWriter getWriter(File file, boolean allowOverwrite)
        throws IOException {
        if (checkFile(file, allowOverwrite)) {
            return new BufferedWriter(new FileWriter(file));
        }

        return null;
    }

    /**
     * 打开文件并得到InputStrea
     * @param file 要打开的文件。
     * @return FileInputStream，如果文件不存在，返回null
     * @throws java.io.IOException 读取文件有误时抛出
     */
    public static FileInputStream getInputStream(File file)
        throws IOException {
        if (isFile(file)) {
            return new FileInputStream(file);
        }

        return null;
    }

    /**
     * 打开文件，如果文件不存在，自动创建，并得到FileOutputStream。
     * @param file 文件
     * @param allowOverwrite 是否运行覆盖旧文件
     * @return FileOutputStream，如果文件存在但不允许覆盖，返回null
     * @throws java.io.IOException 读取或创建新文件有误时抛出
     */
    public static FileOutputStream getOutputStream(File file,
        boolean allowOverwrite) throws IOException {
        if (checkFile(file, allowOverwrite)) {
            return new FileOutputStream(file, allowOverwrite);
        }

        return null;
    }

    /**
     * 关闭字节输入流。
     * @param inputStream 要关闭的字节输入流。
     * @return 返回关闭的结果。如果字节输入流为空或字节输入流关闭成功返回true；否则返回false
     */
    public static boolean close(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();

                return true;
            } catch (IOException e) {
                e.printStackTrace();

                return false;
            }
        }

        return true;
    }

    /**
     * 关闭字节输出流。
     * @param outputStream 要关闭的字节输出流。
     * @return 返回关闭的结果。如果字节输出流为空或字节输出流关闭成功返回true；否则返回false
     */
    public static boolean close(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();

                return true;
            } catch (IOException e) {
                e.printStackTrace();

                return false;
            }
        }

        return true;
    }

    /**
     * 关闭Reader输入。
     * @param reader 要关闭的Reader输入流。
     * @return 返回关闭的结果。如果Reader输入流为空或Reader输入流关闭成功返回true；否则返回false
     */
    public static boolean close(Reader reader) {
        if (reader != null) {
            try {
                reader.close();

                return true;
            } catch (IOException e) {
                e.printStackTrace();

                return false;
            }
        }

        return true;
    }

    /**
     * 关闭Writer输出流。
     * @param writer 要关闭的Writer输出流。
     * @return 返回关闭的结果。如果Writer输出流为空或Writer输出流关闭成功返回true；否则返回false
     */
    public static boolean close(Writer writer) {
        if (writer != null) {
            try {
                writer.close();

                return true;
            } catch (IOException e) {
                e.printStackTrace();

                return false;
            }
        }

        return true;
    }

    /**
     * 移动文件。
     * @param file 要移动的文件。
     * @param destPath 目的文件夹。
     * @throws IOException 移动文件出错抛出该异常。
     */
    public static void moveFile(File file, String destPath)
        throws IOException {
        if (isFile(file) && (destPath != null)) {
            File destDir = new File(destPath);

            if (!destDir.exists()) {
                destDir.mkdir();
            }

            FileInputStream in = new FileInputStream(file);
            FileOutputStream out = new FileOutputStream(destDir.getPath() +
                    File.separator + file.getName());

            // 传输字节
            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            close(in);
            close(out);
            file.delete();
        }
    }

    /**
     * 复制文件。
     * @param file 要复制的文件。
     * @param destFile 目的文件。
     * @throws IOException 移动文件出错抛出该异常。
     */
    public static void copyFile(File src, File destFile)
        throws IOException {
        if (isFile(src) && (destFile != null)) {
            if (destFile.exists()) {
                throw new IOException("文件“" + destFile.getPath() +
                    "”已经存在（File \"" + destFile.getPath() + "\" is exists.）。");
            }

            checkFile(destFile, false);

            FileInputStream in = new FileInputStream(src);
            FileOutputStream out = new FileOutputStream(destFile);

            // 传输字节
            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            close(in);
            close(out);
        }
    }

    /**
     * 批量重命名文件夹下的文件，包括文件夹。
     * @param dir 目录
     * @param prefix 重命名的后文件的前缀
     * @param find 查找
     * @param replace 替换为
     * @param suffix 重命名后文件的后缀
     * @return 被重命名过的文件
     */
    public static File[] batchRename(File dir, String prefix, String find,
        String replace, String suffix) {
        List<File> ls = new ArrayList<File>();

        if (isDirectory(dir)) {
            File[] files = dir.listFiles();

            for (File file : files) {
                String oldName = file.getName();
                String newName = prefix + oldName.replaceAll(find, replace) +
                    suffix;

                if (!newName.equals(oldName)) {
                    File dest = new File(file.getParent() + File.separator +
                            newName);

                    if (file.renameTo(dest)) {
                        ls.add(dest);
                    }
                }
            }
        }

        return ls.toArray(new File[ls.size()]);
    }

    /**
     * 批量删除一个目录下的所有文件（夹），该目录并不删除。
     * @param dir 要删除子对象的目录。
     */
    public static void deleteSubs(File dir) {
        if (isDirectory(dir)) {
            File[] files = dir.listFiles();

            for (File file : files) {
                if (file.isDirectory()) {
                    deleteSubs(file);
                }

                file.delete();
            }
        }
    }

    /**
     * 删除一个目录。该目录下的所有对象同时全部删除。
     * @param file 要删除的目录。
     * @author liliang.
     */
    public static void deleteDirectory(File file) {
        if (isDirectory(file)) {
            deleteSubs(file);
            file.delete();
        }
    }

    /**
     * 检查文件是否存在，如果不存在自动创建。
     * @param file 要打开的文件。
     * @param allowOverwrite 是否允许
     * @return 返回结果。如果文件存在，且不允许覆盖，返回false。
     * @throws IOException 创建文件出错时抛出此异常。
     * @author liliang.
     */
    private static boolean checkFile(File file, boolean allowOverwrite)
        throws IOException {
        if (file != null) {
            // 检查父目录，不存在时自动创建
            File parent = file.getParentFile();
            boolean parentExist = true;

            if ((parent != null) && !parent.exists()) {
                parentExist = parent.mkdirs();
            }

            if (parentExist &&
                    (file.createNewFile() || (file.exists() && allowOverwrite))) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断一个File对象否是文件。
     * @param file 要判断的File对象。
     * @return 返回判断的结果，如果文件不为空并且文件存在并且该对象是文件则返回true；否则返回false。
     * @author liliang.
     */
    private static boolean isFile(File file) {
        return (file != null) && file.exists() && file.isFile();
    }

    /**
     * 判断一个File对象是否是目录。
     * @param dir 要判断的File对象。
     * @return 返回判断的结果。如果File不为空并且文件存在并且该对象是目录，则返回true；否则返回false。
     * @author liliang.
     */
    private static boolean isDirectory(File dir) {
        return (dir != null) && dir.exists() && dir.isDirectory();
    }
    
    /**
     * 获取工程应用中的路径
     * @param spath
     * @return
     */
    public static String getWebRealPath(String spath) {
    	String path=ServletActionContext.getServletContext().getRealPath(spath);
    	return path;
	}   
    
    public static String inputStream2String(InputStream is) throws IOException {
		/*
		 * 为了将InputStream转换成String我们使用函数BufferedReader.readLine().
		 * 我们迭代调用BufferedReader直到其返回null, null意味着没有其他的数据要读取了.
		 * 每一行将会追加到StringBuilder的末尾, StringBuilder将作为String返回。
		 */
		if (is != null) {
			StringBuilder sb = new StringBuilder();
			String line;
			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is));
				while ((line = reader.readLine()) != null) {
					sb.append(line).append("\n");
				}
			} finally {
				is.close();
			}
			return sb.toString();
		} else {
			return "";
		}
	}
    public static String inputStream2String(InputStream is,String encode) throws IOException {
    	/*
		 * 为了将InputStream转换成String我们使用函数BufferedReader.readLine().
		 * 我们迭代调用BufferedReader直到其返回null, null意味着没有其他的数据要读取了.
		 * 每一行将会追加到StringBuilder的末尾, StringBuilder将作为String返回。
		 */
		if (is != null) {
			StringBuilder sb = new StringBuilder();
			String line;
			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is,encode));
				
				while ((line = reader.readLine()) != null) {
					sb.append(line).append("\n");
				}
			} finally {
				is.close();
			}
			return sb.toString();
		} else {
			return "";
		}
    }
}

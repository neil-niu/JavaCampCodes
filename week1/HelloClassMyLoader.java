package jvm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class HelloClassMyLoader extends ClassLoader{
    public static void main(String[] args) throws Exception{
        String className = "Hello";
        String methodName = "hello";

        // create class loader
        ClassLoader classLoader = new HelloClassMyLoader();
        // load Hello class
        Class<?> helloClass = classLoader.loadClass(className);
        // create object for HelloClass
        Object helloObj = helloClass.getDeclaredConstructor().newInstance();

        // call the instance method
        Method method = helloClass.getMethod(methodName);
        method.invoke(helloObj);
    }

    @Override
    protected Class<?> findClass (String name) throws ClassNotFoundException {
        // String HelloBase64 = "yv66vgAAADMAHAoABgAOCQAPABAIABEKABIAEwcAFAcAFQEABjxpbml0PgEAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBAAg8Y2xpbml0PgEAClNvdXJjZUZpbGUBAApIZWxsby5qYXZhDAAHAAgHABYMABcAGAEAGEhlbGxvIENsYXNzIEluaXRpYWxpemVkLgcAGQwAGgAbAQAJanZtL0hlbGxvAQAQamF2YS9sYW5nL09iamVjdAEAEGphdmEvbGFuZy9TeXN0ZW0BAANvdXQBABVMamF2YS9pby9QcmludFN0cmVhbTsBABNqYXZhL2lvL1ByaW50U3RyZWFtAQAHcHJpbnRsbgEAFShMamF2YS9sYW5nL1N0cmluZzspVgAhAAUABgAAAAAAAgABAAcACAABAAkAAAAdAAEAAQAAAAUqtwABsQAAAAEACgAAAAYAAQAAAAMACAALAAgAAQAJAAAAJQACAAAAAAAJsgACEgO2AASxAAAAAQAKAAAACgACAAAABQAIAAYAAQAMAAAAAgAN";
        // read Hello.xlass file contents to byte buffer
        String filePath = name.replace(".", "/");
        String suffix = ".xlass";

        System.out.println("file path = " + filePath + suffix);
        System.out.println(System.getProperty("user.dir"));
        String currentDirectory = System.getProperty("user.dir");
        String absouteDiretory = currentDirectory + File.separator + "jvm" + File.separator + filePath + suffix;
        System.out.println("absolute file path = " + absouteDiretory);
        File myFile = new File(absouteDiretory);
        //InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filePath + suffix);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(myFile);
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        int length;
        try {
            length = inputStream.available();
            byte[] byteArray = new byte[length];
            inputStream.read(byteArray);
        
            byte[] bytes = decode(byteArray);
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new ClassNotFoundException(name, e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public byte[] decode(byte[] byteArray) {
        byte[] targetArray = new byte[byteArray.length];

        for(int i = 0; i < byteArray.length; i++) {
            targetArray[i] = (byte) (255 - byteArray[i]);
        }
        return targetArray;
    }
}

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Reference https://docs.oracle.com/javase/7/docs/api/java/lang/ClassLoader.html
 */
public class HelloClassLoader extends ClassLoader {
  public static void main(String[] args)
      throws InstantiationException, IllegalAccessException, NoSuchMethodException,
      InvocationTargetException {
    HelloClassLoader helloClassLoader = new HelloClassLoader();
    Class HelloClass = helloClassLoader.findClass("Hello.xlass");
    Object helloClass = HelloClass.newInstance();
    HelloClass.getMethod("hello").invoke(helloClass);
  }

  public Class findClass(String name) {
    byte[] b = new byte[0];
    try {
      b = loadClassData(name);
    } catch (IOException e) {
      e.printStackTrace();
    }
    b = transferByte(b);
    return defineClass("Hello", b, 0, b.length);
  }

  private byte[] loadClassData(String name) throws IOException {
    return Files.readAllBytes(Paths.get(name));
  }

  private byte[] transferByte(byte[] b) {
    for (int i = 0; i < b.length; i++) {
      b[i] = (byte) (255 - b[i]);
    }
    return b;
  }
}

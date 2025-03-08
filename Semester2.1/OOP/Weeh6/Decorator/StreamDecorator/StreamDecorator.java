import java.io.*;


public class StreamDecorator extends FilterInputStream {
    final int MAX=1000;
    public StreamDecorator(InputStream f) {
        super(f);
    }

    public String readLine() {
        String s;
        int length = 0;

        byte b[] = new byte[MAX];
        try {
            length = super.read(b);
            s = new String(b, 0, length);
        } catch (IOException e) {
            s = "";
        }
        StringBuffer buf = new StringBuffer(s);
        boolean punctFound = true;
        for (int i=0; i < length; i++) {
            char ch = buf.charAt (i);
            if (punctFound && (ch != ' ') ) {
                ch = Character.toUpperCase (ch);
                buf.setCharAt (i, ch);
                punctFound = false;
            }
            if (ch == '.' || ch == '\n')
                punctFound = true;
        }
        s = buf.toString ();
        return s;
    }
}

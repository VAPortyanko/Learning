package testingGround;

public enum Sample {
	READ, WRITE
}

// Internally, enums will be translated to something like this.
// final public class Sample extends Enum {
//    public static final Sample READ = new Sample("READ", 0);
//    public static final Sample WRITE = new Sample("WRITE", 1);
//
//    private Sample(String s, int i){
//        super(s, i);
//    }
//}
package suanfa;

public class ReverseString {
    public String reverseString(String s) {
        char[] chars = s.toCharArray();
        char[] tmp = new char[chars.length];
        int j = chars.length - 1;
        for (int i = 0; i < chars.length; i++) {
            tmp[j] = chars[i];
            j--;
        }
        return new String(tmp);
    }

    public static void main(String[] args) {
        String s = "hello";
        System.out.println(new ReverseString().reverseString(s));
    }
}

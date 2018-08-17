
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            justFiftyBytes();
            concatination();
            readPage();
            //readPageTwo();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void justFiftyBytes() throws IOException {

        FileInputStream in = new FileInputStream("1/1.txt");
        byte[] arr = new byte[50];
        int x;
        while ((x = in.read(arr)) != -1) {
            System.out.println(new String(arr, 0, x));
        }
        System.out.println();
    }

    public static void concatination() throws IOException {
        ArrayList<InputStream> all = new ArrayList<>();

        all.add(new FileInputStream("2/1.txt"));
        all.add(new FileInputStream("2/2.txt"));
        all.add(new FileInputStream("2/3.txt"));
        all.add(new FileInputStream("2/4.txt"));
        all.add(new FileInputStream("2/5.txt"));

        SequenceInputStream in = new SequenceInputStream(Collections.enumeration(all));

        int x;
        while ((x = in.read()) != -1) {
            System.out.print((char) x);
        }
        System.out.println();
        in.close();
    }

    public static void readPage() throws Exception {
        RandomAccessFile rand = new RandomAccessFile("3/theBook.txt", "r");
        Scanner sc = new Scanner(System.in);
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Введите номер страницы (0 для выхода)");  //всего 34
            int x = sc.nextInt();
            //int x = Integer.parseInt(br.readLine());
            if (x == 0) break;
            else {
                rand.seek((Math.abs(x) - 1) * 1800);
                StringBuilder sb = new StringBuilder("");
                for (int i = 0; i < 1800; i++) {
                    sb.append((char) rand.read());
                }
                System.out.println(sb);
            }
        }
        rand.close();
    }

    private static final int CHARS_IN_PAGE = 1800;

    private static void readPageTwo() {

        try (
                InputStream in = new FileInputStream("3/theBook.txt");
             ByteArrayOutputStream book = new ByteArrayOutputStream())
        {
            byte[] buff = new byte[CHARS_IN_PAGE];
            int len;

            while ((len = in.read(buff)) != -1) {
                book.write(buff, 0, len);
            }
            int page;
            int numberOfPages = book.size() / CHARS_IN_PAGE;
            int pages;
            int enterPage;
            int offset;
            int turnThePages;

            Scanner input = new Scanner(System.in);

            while (true) {
                pages = numberOfPages;

                do {
                    System.out.printf("%n%nplease enter the page: 1 - %d%n", pages + 1);
                    page = input.nextInt();
                } while (page < 1 && page < pages);
                enterPage = page;
                offset = (enterPage - 1) * CHARS_IN_PAGE;
                turnThePages = offset + CHARS_IN_PAGE;

                byte[] bytes = book.toByteArray();
                len = bytes.length;

                while (offset != turnThePages && offset < len) {
                    System.out.print((char) bytes[offset]);
                    offset++;
                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, FileNotFoundException, UnsupportedEncodingException {
        Scanner scanner = new Scanner(System.in);
        Dictionary dictionary = new Dictionary();
        DictionaryManagement dictionaryManagement = new DictionaryManagement();
        DictionaryCommandLine dictionaryCommandLine = new DictionaryCommandLine();
        dictionaryManagement.insertFromFile(dictionary);
        while (true) {
            System.out.println("Bạn muốn ?" + '\n' + "1:Tra từ" + '\n' + "2:Thêm, Sửa, Xóa" + '\n'
                    + "3:Hiện toàn bộ list" + '\n' + "4:Thoát");
            int n = scanner.nextInt();
            scanner.nextLine();
            if (n == 1) {
                System.out.println("Từ bạn muốn tìm kiếm là:");
                String tim = scanner.nextLine().toLowerCase();
                dictionaryCommandLine.dictionarySearch(dictionary, tim);
            }
            if (n == 2) {
                dictionaryManagement.themSuaXoaDuLieu(dictionary);
            }
            if (n == 3) {
                dictionaryCommandLine.showAllWords(dictionary);
            }
            if (n == 4) {
                return;
            }
        }
    }
}

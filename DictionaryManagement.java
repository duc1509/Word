import com.sun.source.tree.WhileLoopTree;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.Scanner;

public class DictionaryManagement {
    public void insertFromCommandline(Dictionary list) throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("Từ bạn sẽ thêm là:");
        Scanner sc = new Scanner(System.in);
        String tuthem = sc.nextLine().toLowerCase();
        if (!dictionaryLookup(list, tuthem)) {
            System.out.print("Vietnamese " + tuthem + " is ");
            String nghiatiengviet = sc.nextLine();
            Word tumoi = new Word(tuthem, nghiatiengviet);
            list.words.add(tumoi);
            System.out.println("Đã thêm từ " + tumoi.getWordTarget());
            dictionaryExportToFile(list);
        } else {
            System.out.println("Từ này đã có trong từ điển ");
            return;
        }
    }

    public void insertFromFile(Dictionary list) throws IOException {
        Scanner sc = new Scanner(Paths.get("scr/dictionaries.txt"));

        while (sc.hasNext()) {
            while (sc.hasNextLine()) {
                Word tu = new Word();
                String target = sc.nextLine();
                String explain = sc.nextLine();
                tu.setWordTarget(target);
                tu.setWordEplain(explain);
                list.words.add(tu);
            }
        }
    }

    public boolean dictionaryLookup(Dictionary list, String target) {
        Word tu = list.words.stream().filter(word -> target.equals(word.getWordTarget())).findFirst().orElse(null);
        if (tu != null) {
            return true;
        } else {
            return false;
        }
    }
    public Word timTu(Dictionary list, String target) {
        Word tu = list.words.stream().filter(word -> target.equals(word.getWordTarget())).findFirst().orElse(null);
        return tu;

    }
    public void themSuaXoaDuLieu(Dictionary list) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Yêu cầu:" + '\n' + "1:Thêm từ" + '\n'
                    + "2:Sửa từ" + '\n' + "3:Xóa từ" + '\n' + "4:Trở về");
            int n = sc.nextInt();
            sc.nextLine();
            String word = new String();
            if (n == 1) {
                insertFromCommandline(list);
            }
            if (n == 2) {
                System.out.println("Từ bạn sẽ sửa là:");
                word = sc.nextLine().toLowerCase();
                if (dictionaryLookup(list,word)) {
                    Word sua = timTu(list, word);
                    sua.print();
                    System.out.println("Nghĩa mới của từ " + sua.getWordTarget() +"là :");
                    String explain = sc.nextLine();
                    sua.setWordEplain(explain);
                } else {
                    System.out.println("Không có từ này trong từ điển");
                }
            }
            if (n == 3) {
                System.out.println("Từ bạn sẽ xóa là:");
                word = sc.nextLine().toLowerCase();
                if (dictionaryLookup(list,word)) {
                    Word xoa = timTu(list, word);
                    list.words.remove(xoa);
                    System.out.println("Đã xóa từ này ra khỏi từ điển");
                } else {
                    System.out.println("Không có từ này trong từ điển");
                }
            }
            if (n == 4) {
                dictionaryExportToFile(list);
                break;
            }
        }
    }

    public void dictionaryExportToFile(Dictionary list) throws UnsupportedEncodingException, FileNotFoundException {
        Collections.sort(list.words, Comparator.comparing(Word::getWordTarget));
        PrintWriter printWriter= new PrintWriter("src/dictionaries.txt","UTF-8");
        for (int i =0; i < list.words.size(); i++) {
            printWriter.println(list.words.get(i).getWordTarget().toLowerCase() +
                    '\n' + list.words.get(i).getWordExplain().toLowerCase());
        }
        printWriter.close();
    }
}

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class DictionaryCommandLine {
    DictionaryManagement dic = new DictionaryManagement();

    public  void showAllWords(Dictionary list) {
        System.out.println("No    |   English   |  Vietnamese");
        for(int i = 0; i < list.words.size(); i++) {
            System.out.printf("%-3s| %-15s| %s%n", i+1, list.words.get(i).getWordTarget(), list.words.get(i).getWordExplain());
        }
    }

    public void dictionaryBasic(Dictionary list) throws FileNotFoundException, UnsupportedEncodingException {
        dic.insertFromCommandline(list);
        showAllWords(list);
    }

    public void dictionaryAdvanced(Dictionary list) throws IOException {
        dic.insertFromFile(list);
        showAllWords(list);
        Scanner sc = new Scanner(System.in);
        String target = sc.nextLine().toLowerCase();
        dic.dictionaryLookup(list,target);
    }

    public void dictionarySearch( Dictionary list, String tim) {
        Dictionary ketqua = new Dictionary();
        for (Word i : list.words) {
            if (i.getWordTarget().startsWith(tim)) {
                ketqua.words.add(i);
            }
        }
        if(ketqua.words.isEmpty()) {
            System.out.println("Không có từ này trong từ điển");
        } else {
            showAllWords(ketqua);
        }
    }
}

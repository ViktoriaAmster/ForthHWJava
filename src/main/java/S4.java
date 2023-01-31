import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class S4 {
    public static void main(String[] args) {
        FileWriter fileWriter = null;
        FileReader reader = null;
        String file_text = "";

        try{
            fileWriter = new FileWriter("hw.sql");
            fileWriter.append("Ferret");
            fileWriter.flush();
            reader = new FileReader("hw.sql");
            while (reader.ready()){
                file_text += (char)reader.read();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(file_text);

        FileReader base_reader = null;
        String data_base = "";
        try{
            base_reader = new FileReader("data_base.sql");
            while (base_reader.ready()) {
                data_base += (char) base_reader.read();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(data_base);

        String[] arr = data_base.split("\n");
        ArrayList<String> surname = new ArrayList<>();
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> patronym = new ArrayList<>();
        ArrayList<Integer> age = new ArrayList<>();
        ArrayList<Boolean> gender = new ArrayList<>();
        LinkedList<Integer> list_index = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {
            String[] tmp = arr[i].split(" ");
            surname.add(tmp[0]);
            name.add(tmp[1]);
            patronym.add(tmp[2]);
            age.add(Integer.parseInt(tmp[3]));
            gender.add(tmp[4].equals("Ж") ? true:false);
            list_index.add(i);
        }
        for (int i = 0; i < name.size(); i++) {
            System.out.printf("%d. %s %s.%s. %d %s\n",list_index.get(i),surname.get(list_index.get(i)),
                    name.get(list_index.get(i)).charAt(0),patronym.get(list_index.get(i)).charAt(0),
                    age.get(list_index.get(i)),gender.get(list_index.get(i)).equals(true) ? "Ж":"М");
        }
        for (int i = 1; i < age.size() ; i++) {
            int current = list_index.get(i);
            int j = i-1;
            while (j >= 0 && age.get(current) < age.get(list_index.get(j))){
                list_index.set(j+1,list_index.get(j));
                j--;
            }
            list_index.set(j+1,current);
        }
        System.out.println();
        for (int i = 0; i < name.size(); i++) {
            System.out.printf("%d. %s %s.%s. %d %s\n",list_index.get(i),surname.get(list_index.get(i)),
                    name.get(list_index.get(i)).charAt(0),patronym.get(list_index.get(i)).charAt(0),
                    age.get(list_index.get(i)),gender.get(list_index.get(i)).equals(true) ? "Ж":"М");
        }
    }
}

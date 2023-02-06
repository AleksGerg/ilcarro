package manager;


import org.testng.annotations.DataProvider;

import model.UserLombok;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {
    @DataProvider()
    public Iterator<Object[]> loginDataCls() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"benb@gmail.com", "Beny$123456"});
        list.add(new Object[]{"ili@gmail.com", "IliB$123456"});
        list.add(new Object[]{"benb@gmail.com", "Beny$123456"});

        return list.iterator();
    }

    @DataProvider()
    public Iterator<Object[]> loginDataUser() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{UserLombok.builder().email("benb@gmail.com").password("Beny$123456").build()});
        list.add(new Object[]{UserLombok.builder().email("ili@gmail.com").password("IliB$123456").build()});
        list.add(new Object[]{UserLombok.builder().email("benb@gmail.com").password("Beny$123456").build()});

        return list.iterator();
    }

    @DataProvider()
    public Iterator<Object[]> loginDataUserFromFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("src/test/resources/data.csv")));
        String line = bufferedReader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{UserLombok.builder().email(split[0]).password(split[1]).build()});
            line = bufferedReader.readLine();
        }
        return list.iterator();
    }

    @DataProvider()
    public Iterator<Object[]> loginDataUserFromFileReg() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("src/test/resources/dataReg")));
        String line = bufferedReader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{UserLombok.builder().email(split[0]).password(split[1]).build()});
            line = bufferedReader.readLine();
        }
        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]>registrationDataFromFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("src/test/resources/dataReg.csv")));
        String line = bufferedReader.readLine();
        while (line != null){
            String[] split = line.split(",");
            list.add(new Object[]{UserLombok.builder().email(split[0]).password(split[1]).build()});
            line = bufferedReader.readLine();

        }
        return list.iterator();
    }
}

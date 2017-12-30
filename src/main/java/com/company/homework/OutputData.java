package com.company.homework;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.lang.reflect.Field;
import java.util.List;

public class OutputData {

    private static final String FILENAME = "user.txt";


    public static void main(String[] args) throws IOException, IllegalAccessException, ClassNotFoundException {
        ObjectMapper objMap = new ObjectMapper();
        List<User> list = objMap.readValue(new FileInputStream(FILENAME), new TypeReference<List<User>>() {});
        System.out.println(checkUser(list));
    }

    private static List<User> deserialize(String filename) throws IOException, ClassNotFoundException {
        List<User> list;
        File f = new File(filename);
        if (f.exists()) {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (List<User>) ois.readObject();
            ois.close();
            fis.close();
        } else {
            list = null;
        }
        return list;
    }

    private static String checkUser(List<User> list) throws IOException, IllegalAccessException {
        if (list != null) {
            User uTest = new User();
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            Class c = User.class;
            Field[] allFields = c.getDeclaredFields();
            for (int i = 2; i < 4; i++) {
                boolean f = true;
                String str = "";
                String err = "";
                while (f) {

                    System.out.println("Input " + Validator.getFieldName(allFields[i])[0] + ". " + Validator.getFieldName(allFields[i])[1] + "\nor type Exit to exit:");

                    allFields[i].setAccessible(true);
                    str = br.readLine();
                    if (str.equalsIgnoreCase("exit")) {
                        isr.close();
                        br.close();
                        return "";
                    }
                    allFields[i].set(uTest, str);
                    f = false;
                    err = Validator.validateProcessing(allFields[i], uTest);

                    if (!err.equals("")) {
                        System.out.println("\n" + err);
                        f = true;
                    }

                }


            }
            for (User user : list) {
                if (user.getUsername().equalsIgnoreCase(uTest.getUsername()) && user.getPassword().equals(uTest.getPassword())) {
                    return user.toString();
                }
            }
            System.out.println("There is no user " + uTest.getUsername());
            return "";
        }
        System.out.println("No file!");
        return "";
    }
}
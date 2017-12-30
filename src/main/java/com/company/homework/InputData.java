package com.company.homework;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class InputData {

    private static final String FILENAME = "user.txt";

    public static void main(String[] args) throws IllegalAccessException, IOException {
        User u = new User();
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        ObjectMapper objMap = new ObjectMapper();
        String json;
        Class c = User.class;
        Field[] allFields = c.getDeclaredFields();
        Field id = null;
        for (Field field : allFields) {
            if (field.getType().isPrimitive()) {
                id = field;
            } else {
                boolean f = true;
                String str = "";
                String err = "";
                while (f) {
                    System.out.println("Input "
                            + Validator.getFieldName(field)[0] + ". "
                            + Validator.getFieldName(field)[1]
                            + "\nor type Exit to exit:");
                    field.setAccessible(true);
                    str = br.readLine();
                    if (str.equalsIgnoreCase("exit")) {
                        isr.close();
                        br.close();
                        return;
                    }

                    field.set(u, str);
                    f = false;
                    err = Validator.validateProcessing(field, u);
                    if (!err.equals("")) {
                        System.err.println("\n" + err);
                        f = true;
                    }

                }
            }


        }

        isr.close();
        br.close();
        boolean flag = true;
        File f = new File(FILENAME);
        int size = 0;
        List<User> list = new ArrayList<>();
        if (f.exists())

        {
            list = objMap.readValue(new FileInputStream(FILENAME), new TypeReference<List<User>>() {});
            size = list.size();
            for (User user : list) {
                if (user.getUsername().equalsIgnoreCase(u.getUsername())) {
                    flag = false;
                    System.out.println("User " + u.getUsername() + " exists");
                }
            }
        }
        if (flag)

        {
            id.setAccessible(true);
            id.setInt(u, (size + 1));
            list.add(u);

            objMap.writeValue(new FileOutputStream(FILENAME), list);
        }
    }


    private static void serialize(Object listUsers) {
        try {
            FileOutputStream fos = new FileOutputStream(FILENAME, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listUsers);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*
    private static Object deserialize(String file) {
        List<User> list = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (List<User>) ois.read();
            ois.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("User class not found...");
            e.printStackTrace();
        }
        System.out.println();
        return list;
    }*/
}



